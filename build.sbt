name := "cinnamon-test"

version := "0.1"

scalaVersion := "2.12.7"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

// Enable the Lightbend Telemetry (Cinnamon) sbt plugin
lazy val app = project in file(".") enablePlugins (Cinnamon)

// Add the Cinnamon Agent for run and test
cinnamon in run := true
cinnamon in test := true

// Set the Cinnamon Agent log level
cinnamonLogLevel := "INFO"

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots"),
  Resolver.jcenterRepo
)


libraryDependencies ++= {
  val akkaV       = "2.5.16"
  val akkaHttpV   = "10.1.5"
  val scalaTestV  = "3.0.0"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaV,
    "com.typesafe.akka" %% "akka-stream" % akkaV,
    "com.typesafe.akka" %% "akka-testkit" % akkaV,
    "com.typesafe.akka" %% "akka-cluster" % akkaV,
    "com.typesafe.akka" %% "akka-cluster-metrics" % akkaV,
    "com.typesafe.akka" %% "akka-cluster-sharding" % akkaV,
    "com.typesafe.akka" %% "akka-contrib" % akkaV,
    "com.typesafe.akka" %% "akka-persistence" % akkaV,
    "com.typesafe.akka" %% "akka-persistence-tck" % akkaV,
    "com.typesafe.akka" %% "akka-remote" % akkaV,
    "com.typesafe.akka" %% "akka-slf4j" % akkaV,
    "com.typesafe.akka" %% "akka-multi-node-testkit" % akkaV,
    "com.typesafe.akka" %% "akka-persistence-query" % akkaV,
    "com.typesafe.akka" %% "akka-http" % akkaHttpV,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpV,
    "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpV,
    "org.scalatest"     %% "scalatest" % scalaTestV % "test",


    // Use Coda Hale Metrics
    Cinnamon.library.cinnamonCHMetrics,
    // Use Akka instrumentation
    Cinnamon.library.cinnamonAkka,
    Cinnamon.library.cinnamonAkkaStream,
    // Use Akka HTTP instrumentation
    Cinnamon.library.cinnamonAkkaHttp
  )
}

PB.targets in Compile := Seq(
  scalapb.gen() -> (sourceManaged in Compile).value
)
