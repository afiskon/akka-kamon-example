import com.typesafe.sbt.SbtAspectj._

name := """kamon-test"""

version := "0.0.1"

scalaVersion := "2.11.4"

val kamonVersion = "0.3.4"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.5",
  "io.kamon" %% "kamon-core" % kamonVersion,
  // "io.kamon" %% "kamon-statsd" % kamonVersion,
  "io.kamon" %% "kamon-datadog" % kamonVersion,
  "io.kamon" %% "kamon-log-reporter" % kamonVersion,
  "io.kamon" %% "kamon-system-metrics" % kamonVersion,
  "org.aspectj" % "aspectjweaver" % "1.8.2"
)

aspectjSettings

javaOptions <++= AspectjKeys.weaverOptions in Aspectj

fork in run := true

//mergeStrategy in assembly := {
//  case PathList("org", "aspectj",  _*) => MergeStrategy.first
//  case x => (mergeStrategy in assembly).value(x)
//}
