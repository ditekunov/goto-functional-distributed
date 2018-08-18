import akka.actor._
import akka.pattern.{ask, pipe}
import akka.util.Timeout

import scala.collection._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

case class SetRequest(key: String, value: String)
case class GetRequest(key: String)
case class GetResponse(key: Option[String])

class MapActor extends Actor with ActorLogging {
  val state: mutable.Map[String, String] = mutable.Map.empty

  def receive = {
    case r: SetRequest =>
      state += r.key -> r.value
    case r: GetRequest =>
      sender ! GetResponse(state.get(r.key))
    case r =>
      log.warning(s"Unexpected: $r")
  }
}

case object Start

class MainActor extends Actor with ActorLogging {
  implicit val timeout = Timeout(5 seconds)
  val mapActor = context.actorOf(Props(new MapActor), "mapActor")

  override def preStart() {
    self ! Start
  }

  def receive = {
    case Start =>
      mapActor ! "dummy request"
      mapActor ! SetRequest("key", "value")
      val respF: Future[Any] = mapActor ? GetRequest("key")
      respF pipeTo self

    case r: GetResponse =>
      log.warning(s"Response: $r")
      context.system.terminate()
  }
}

object Main extends App {
  val system = ActorSystem("system")
  val mainActor = system.actorOf(Props(new MainActor), "mainActor")
  //system.getWhenTerminated
}
