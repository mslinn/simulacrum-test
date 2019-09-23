import simulacrum._

trait MonoidImplicits1 {
  @typeclass trait Semigroup[A] {
    @op("|+|") def append(x: A, y: A): A
  }

  @typeclass trait Monoid[A] extends Semigroup[A] {
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

object Hello extends App with MonoidImplicits1 {
  import Monoid.ops._

  val x: Int = 1 |+| 2
  println(s"x=$x")

  val y: String = "a" |+| "b"
  println(s"y=$y")
}
