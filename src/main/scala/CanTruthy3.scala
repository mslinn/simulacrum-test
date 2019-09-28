import simulacrum._

// FIXME Does not compile: value truthy is not a member of object North, South, East or West

// True north is the only truthy direction
sealed trait Direction
object North extends Direction
object South extends Direction
object East  extends Direction
object West  extends Direction

trait TruthyImplicits3 {
  @typeclass trait Truthy[A <: Direction] { self =>
    /** @return true if `a` is truthy. */
    /*@op("truthy") */def truthy(a: A): Boolean
  }

  implicit val directionCanTruthy: Truthy[Direction] = {
    case North => true
    case _ => false
  }
}

object CanTruthy3 extends App with TruthyImplicits3 {
  import Truthy.ops._

  def north: Direction = North
  def south: Direction = South
  def east: Direction = East
  def west: Direction = West

  println(s"North.truthy = ${ north.truthy }")
  println(s"South.truthy = ${ south.truthy }")
  println(s"East.truthy  = ${ east.truthy }")
  println(s"West.truthy  = ${ west.truthy }")
}
