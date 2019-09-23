import simulacrum._

trait NonMonoidImplicits {
  @typeclass trait NonMonoid[A] {
    @op("|+|") def append(x: A, y: A): A
    @op("|-|") def remove(x: A, y: A): A
    def id: A
  }

  implicit val Additive: NonMonoid[Int] = new NonMonoid[Int] {
    def id = 0
    def append(x: Int, y: Int): Int = x + y
    def remove(x: Int, y: Int): Int = x - y
  }

  implicit val Concatenation: NonMonoid[String] = new NonMonoid[String] {
    def id = ""
    def append(x: String, y: String): String = x + y
    def remove(x: String, y: String): String = x.replace(y, "")
  }
}

object Hello3 extends App with NonMonoidImplicits {
  import NonMonoid.ops._

  println(s"1 |+| 2 = ${ 1 |+| 2 }")
  println(s""""abcd" |+| "a" = ${ "abcd" |+| "a" }""")

  println(s"1 |-| 2 = ${ 1 |-| 2 }")
  println(s""""abcd" |-| "a" = ${ "abcd" |-| "a" }""")
}
