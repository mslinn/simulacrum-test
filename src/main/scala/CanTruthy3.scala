import simulacrum._

// True north is the only truthy direction
sealed trait Direction
class North extends Direction
class South extends Direction
class East  extends Direction
class West  extends Direction

/** Simulacrum's Ops pattern provides an implicit conversion from Direction to a generated class.
 * (TODO need a sentence or two about this class... name? implicit? characteristics? members? docs?).
 * This provides new methods for instances of Direction subtypes. */
trait TruthyImplicits3 {
  @typeclass trait Truthy[D <: Direction] {
    /** @return true if `d` is truthy. */
    /*@op("truthy") */def truthy(d: D): Boolean  // TODO why is @op not required? When is @op required?
  }

  // Implicit conversion from an instance of a Direction subclass to a Truthy[Direction] instance
  implicit val directionCanTruthy: Truthy[Direction] = {
    case _: North => true
    case _ => false
  }
}

object CanTruthy3 extends App with TruthyImplicits3 {
  import Truthy.ops._

  // TODO Verify that `D <: Direction : Truthy` means the following:
  // Two bounds are applied to D:
  //   1) Type D is constrained to be a subtype of Direction, or is of type Direction
  //   2) A context bound parameter (implicit _: Truthy[+Direction]) is defined
  def doTruthy1[D <: Direction : Truthy](d: D): Boolean = d.truthy

  // TODO this is simpler than doTruthy1, so it should be preferred. When is doTruthy1 a better choice?
  def doTruthy2(d: Direction): Boolean = d.truthy

  // If Cats is a dependency then we can write:
  // import cats.Show
  // def printTruthy[D : Truthy : Show](d: D): Unit = println(s"${ d.show }.truthy = ${ d.truthy }")
  // TODO Again we see 2 constraints applied to A. What does `A : Truthy : Show` mean?

  // Instances of Direction subtypes are implicitly enriched by the Truthy typeclass
  val north: Direction = new North
  val south: Direction = new South
  val east: Direction = new East
  val west: Direction = new West

  println(s"doTruthy1(north) = ${ doTruthy1(north) }")
  println(s"doTruthy2(north) = ${ doTruthy2(north) }")
  println(s"south.truthy = ${ south.truthy }")
  println(s"east.truthy  = ${ east.truthy }")
  println(s"west.truthy  = ${ west.truthy }")
}
