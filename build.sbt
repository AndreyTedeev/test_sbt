name := """test-sbt"""
organization := "test-sbt"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.13.7"

libraryDependencies += guice
libraryDependencies += jdbc
libraryDependencies += javaWs
libraryDependencies += "javax.persistence" % "javax.persistence-api" % "2.2"
libraryDependencies += "io.ebean" % "ebean" % "12.8.1"
libraryDependencies += "io.ebean" % "ebean-test" % "12.8.1"
libraryDependencies += "org.postgresql" % "postgresql" % "42.3.1"

// playEbeanModels in Compile := Seq("entities.*")
// playEbeanDebugLevel := 4

// lazy val root = (project in file("."))
//   .enablePlugins(PlayJava, PlayEbean)
//   .settings(
//     name := "play-java-ebean-example",
//     version := "1.0.0-SNAPSHOT",
//     scalaVersion := "2.13.7",
//     libraryDependencies ++= Seq(
//       guice,
//       jdbc,
//       "com.h2database" % "h2" % "1.4.199",
//       "org.awaitility" % "awaitility" % "3.1.6" % Test,
//       "org.assertj" % "assertj-core" % "3.12.2" % Test,
//       "org.mockito" % "mockito-core" % "3.0.0" % Test,
//       // To provide an implementation of JAXB-API, which is required by Ebean.
//       "javax.xml.bind" % "jaxb-api" % "2.3.1",
//       "javax.activation" % "activation" % "1.1.1",
//       "org.glassfish.jaxb" % "jaxb-runtime" % "2.3.2",
//     ),
//     testOptions in Test += Tests.Argument(TestFrameworks.JUnit, "-a", "-v"),
//     javacOptions ++= Seq("-Xlint:unchecked", "-Xlint:deprecation", "-Werror")
//   )