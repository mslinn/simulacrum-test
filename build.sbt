import Settings._

cancelable := true

developers := List(
  Developer("mslinn",
            "Mike Slinn",
            "mslinn@micronauticsresearch.com",
            url("https://github.com/mslinn")
  )
)

// define the statements initially evaluated when entering 'console', 'console-quick', but not 'console-project'
initialCommands in console := """
                                |""".stripMargin

javacOptions ++= Seq(
  "-Xlint:deprecation",
  "-Xlint:unchecked",
  "-source", "1.8",
  "-target", "1.8",
  "-g:vars"
)

libraryDependencies ++= Seq(
  "com.github.mpilquist" %% "simulacrum" % "0.19.0" % Provided,
  //
  "org.scalatest"        %% "scalatest"  % "3.0.8"  % Test withSources(),
  "junit"                %  "junit"      % "4.12"   % Test
)

licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html"))

logBuffered in Test := false

logLevel := Level.Warn

// Only show warnings and errors on the screen for compilations.
// This applies to both test:compile and compile and is Info by default
logLevel in compile := Level.Warn

// Level.INFO is needed to see detailed output when running tests
logLevel in test := Level.Info

name := "simulacrum-test"

organization := "com.micronautics"

resolvers ++= Seq(
)

scalacOptions ++= Seq(
  "-deprecation",                      // Emit warning and location for usages of deprecated APIs.
  "-encoding", "utf-8",                // Specify character encoding used by source files.
  "-explaintypes",                     // Explain type errors in more detail.
  "-feature",                          // Emit warning and location for usages of features that should be imported explicitly.
  "-language:existentials",            // Existential types (besides wildcard types) can be written and inferred
  "-language:experimental.macros",     // Allow macro definition (besides implementation and application)
  "-language:higherKinds",             // Allow higher-kinded types
  "-language:implicitConversions",     // Allow definition of implicit functions called views
  "-unchecked",                        // Enable additional warnings where generated code depends on assumptions.
  "-Xcheckinit",                       // Wrap field accessors to throw an exception on uninitialized access.
  "-Xlint:adapted-args",               // Warn if an argument list is modified to match the receiver.
  "-Xlint:constant",                   // Evaluation of a constant arithmetic expression results in an error.
  "-Xlint:delayedinit-select",         // Selecting member of DelayedInit.
  "-Xlint:doc-detached",               // A Scaladoc comment appears to be detached from its element.
  "-Xlint:inaccessible",               // Warn about inaccessible types in method signatures.
  "-Xlint:infer-any",                  // Warn when a type argument is inferred to be `Any`.
  "-Xlint:missing-interpolator",       // A string literal appears to be missing an interpolator id.
  "-Xlint:nullary-override",           // Warn when non-nullary `def f()' overrides nullary `def f'.
  "-Xlint:nullary-unit",               // Warn when nullary methods return Unit.
  "-Xlint:option-implicit",            // Option.apply used implicit view.
  "-Xlint:package-object-classes",     // Class or object defined in package object.
  "-Xlint:poly-implicit-overload",     // Parameterized overloaded implicit methods are not visible as view bounds.
  "-Xlint:private-shadow",             // A private field (or class parameter) shadows a superclass field.
  "-Xlint:stars-align",                // Pattern sequence wildcard must align with sequence component.
  "-Xlint:type-parameter-shadow",      // A local type parameter shadows a type already in scope.
)

scalacOptions ++=
  scalaVersion {
    case sv if sv.startsWith("2.13") => List(
      "-Ymacro-annotations"
    )

    case sv if sv.startsWith("2.12") => List(
      "-Yno-adapted-args",                 // Do not adapt an argument list (either by inserting () or creating a tuple) to match the receiver.
      "-Ypartial-unification",             // Enable partial unification in type constructor inference
      "-Ywarn-extra-implicit",             // Warn when more than one implicit parameter section is defined.
      "-Ywarn-inaccessible",               // Warn about inaccessible types in method signatures.
      "-Ywarn-infer-any",                  // Warn when a type argument is inferred to be `Any`.
      "-Ywarn-nullary-override",           // Warn when non-nullary `def f()' overrides nullary `def f'.
      "-Ywarn-nullary-unit",               // Warn when nullary methods return Unit.
      "-Ywarn-numeric-widen"               // Warn when numerics are widened.
    )

    case _ => Nil
  }.value

// The REPL can’t cope with -Ywarn-unused:imports or -Xfatal-warnings so turn them off for the console
scalacOptions in (Compile, console) --= Seq("-Ywarn-unused:imports", "-Xfatal-warnings")

scalacOptions in (Compile, doc) ++= baseDirectory.map {
  bd: File => Seq[String](
     "-sourcepath", bd.getAbsolutePath,
     "-doc-source-url", s"https://github.com/$gitHubId/simulacrum-test/tree/master€{FILE_PATH}.scala"
  )
}.value

//scalaVersion := "2.12.10"   // comment this line to use Scala 2.13
scalaVersion := "2.13.1" // comment this line to use Scala 2.12

scmInfo := Some(
  ScmInfo(
    url(s"https://github.com/$gitHubId/$name"),
    s"git@github.com:$gitHubId/$name.git"
  )
)

version := "0.1.0"
