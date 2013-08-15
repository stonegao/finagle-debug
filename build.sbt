import AssemblyKeys._

name := "debug-proxy"

version := "0.1.1"

organization := "proxy" 

scalaVersion := "2.10.1"

testFrameworks += TestFrameworks.ScalaTest

testOptions in Test += Tests.Argument("-oF")

seq(assemblySettings: _*)

resolvers ++= Seq(
                  "central mvn repo" at "http://repo1.maven.org/",
                  "Oracle Maven 2 Repository" at "http://download.oracle.com/maven", 
                  "JBoss Maven 2 Repository" at "http://repository.jboss.com/maven2"
                  )

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

libraryDependencies ++= {
  val finagleVersion = "6.5.2"
  Seq(
    "com.twitter" %% "finagle-core" % finagleVersion,
    "com.twitter" %% "finagle-http" % finagleVersion,
    "com.twitter" %% "finagle-ostrich4" % finagleVersion,
    "net.liftweb" % "lift-json_2.10" % "2.5",
    "log4j" % "log4j" % "1.2.14",
    "com.typesafe.slick" %% "slick" % "1.0.1",
    "org.slf4j" % "slf4j-nop" % "1.6.4",
    "com.github.sgroschupf" % "zkclient" % "0.1",
    "org.mongodb" %% "casbah" % "2.6.1",
    "org.scalatest" %% "scalatest" % "1.9.1" % "test",
    "com.github.tomakehurst" % "wiremock" % "1.33" % "test"
  )
}

parallelExecution in Test := false
