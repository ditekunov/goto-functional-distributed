### Базовый стек задач на функциональное программирование в Scala, день 4:

### Здесь вам очень помогут следующие конструкции и импорты:

    import scala.util._
    import scala.concurrent._
    import scala.concurrent.duration._
    
Вариант использования Try:
    
    val a = Try(...) match {
      case Success(succ) => succ
      case Failure(ex) => throw new Exception
    }
    
Вариант использования Future:

  val a = Future {10 * 10}
  
Годная статья по использованию Futures: http://groz.github.io/scala/intro/futures/
  
### ДИСКЛЕЙМЕР: важно! выполняйте все задания из этого блока, поместив их внутри следующей конструкции:

        object UsefulFuture {
        
            ...
        }

#### 1) Реализуйте функцию, которая безопасно находит результ деления одного числа на другое при любом наборе данных


        
#### 2) Реализуйте функцию 
    def bypassOnComplete[A](future: Future[A], function: T => A): Future[T] = {
    ...
    
Которая применяет функцию function к результату исполнения футуры, при этом возвращаю саму футуру


#### 3) Реализуйте функцию 
    def ifFailure(future: Future[A], functionFail: => Unit, exception: Option[Throwable] = None): Future[T] = {
    ...
    
Которая выбрасывает переданный в качестве аргумента Exception, если футура failed, применяя переданную функцию перед этим.

#### 4) Реализуйте функцию
    def completeAndThen(future: Future[A], anotherFuture: Future[T]): Future[T] = {
    ...
    
Которая выполняет первую футуру и потом возвращает вторую футуру в случае, если первая выполнилась.

#### 5) Реализуйте функцию
    def completeAndThenComplete(future: Future[A], anotherFuture: Future[T]) = {
    ...
    
Которая выполняет первую футуру и затем выполняет втторую футуру, если первая выполнилась.
