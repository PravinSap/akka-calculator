name := "divider"

version := "1.0"

version := "1.0"
scalaVersion := "2.12.4"

val akkaVersion = "2.5.8"

val akkaHttpVersion = "10.0.11"


libraryDependencies ++= Seq(
  // Uncomment to use Akka
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  "junit"             % "junit"           % "4.12"  % "test",
  "com.novocode"      % "junit-interface" % "0.11"  % "test"
)
