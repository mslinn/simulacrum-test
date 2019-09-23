import simulacrum._

// Modified from http://eed3si9n.com/herding-cats/making-our-own-typeclass-with-simulacrum.html
trait TruthyImplicits {
  @typeclass trait Truthy[A] {
    /** @return true if `a` is truthy. */
    @op("truthy") def truthy(a: A): Boolean
  }

  implicit val intCanTruthy: Truthy[Int] = {
    case 0 => false
    case _ => true
  }

  implicit val stringCanTruthy: Truthy[String] = {
    case "true" | "yes" | "verdad" | "si" => true
    case _ => false
  }
}

object CanTruthy extends App with TruthyImplicits {
  import Truthy.ops._

  println(s"true.truthy = ${ "true".truthy }")
  println(s"false.truthy = ${ "false".truthy }")
  println(s"si.truthy = ${ "si".truthy }")
  println(s"yes.truthy = ${ "yes".truthy }")
  
  println(s"0.truthy = ${ 0.truthy }")
  println(s"10.truthy = ${ 10.truthy }")
}
