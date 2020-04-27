lazy val `new-project` = (project in file("."))
  .settings(
    organization := "com.ikempf",
    name         := "new-project",
    scalaVersion := "2.13.1",
    libraryDependencies ++= List(
      "org.typelevel" %% "cats-core"   % "2.1.1",
      "org.typelevel" %% "cats-effect" % "2.1.3"
    ),
    scalacOptions ++= List(
      "-target:11",
      "-feature",
      "-encoding",
      "UTF-8",
      "-unchecked",
      "-deprecation",
      "-language:higherKinds",
      "-Wdead-code",
      "-Wvalue-discard",
      "-Wunused:imports",
      "-Wunused:patvars",
      "-Wunused:implicits",
      "-Wunused:locals",
      "-Wunused:explicits",
      "-Wunused:params",
      "-Wunused:privates",
      "-Woctal-literal",
      "-Xlint:adapted-args",
      "-Xlint:infer-any",
      "-Xlint:nullary-unit",
      "-Xlint:nullary-override",
      "-Xlint:inaccessible",
      "-Xlint:constant"
    )
  )
