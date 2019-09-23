import simulacrum._

trait MonoidImplicits2 {
  @typeclass trait Monoid[A] {
    @op("|+|") def append(x: A, y: A): A
    def id: A
  }

  implicit val Additive: Monoid[Int] = new Monoid[Int] {
    def id = 0
    def append(x: Int, y: Int): Int = x + y
  }

  implicit val Concatenation: Monoid[String] = new Monoid[String] {
    def id = ""
    def append(x: String, y: String): String = x + y
  }
}

object Hello2 extends App with MonoidImplicits2 {
  import Monoid.ops._

  val x: Int = 1 |+| 2
  println(s"x=$x")

  val y: String = "a" |+| "b"
  println(s"y=$y")
}
