import org.json4s.jackson.JsonMethods.{compact, render}
import org.json4s.JsonDSL._


object Json {
  def itemToJSON(name: Item): String = {
    val serialized = ("ID" -> item.id.toString) ~ ("Name" -> item.name)
    compact(render(serialized))
  }

}
