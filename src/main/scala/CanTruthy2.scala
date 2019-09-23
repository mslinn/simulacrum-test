// No type class needed
trait TruthyImplicits2 {
  implicit class WouldILieToYou(any: Any) {
    implicit def truthy: Boolean = any match {
      case 0 => false
      case _: Int => true
      case "true" | "yes" | "verdad" | "si" => true
      case _ => false
    }
  }
}

object CanTruthy2 extends App with TruthyImplicits2 {
  println(s"true.truthy = ${ "true".truthy }")
  println(s"false.truthy = ${ "false".truthy }")
  println(s"si.truthy = ${ "si".truthy }")
  println(s"yes.truthy = ${ "yes".truthy }")

  println(s"0.truthy = ${ 0.truthy }")
  println(s"10.truthy = ${ 10.truthy }")
}
