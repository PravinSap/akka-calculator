import sbt.Resolver

name := "calculator"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.4"
val akkaVersion = "2.5.8"
val akkaHttpVersion = "10.0.11"

resolvers ++= Seq(
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
  Resolver.url("Typesafe Ivy releases", url("https://repo.typesafe.com/typesafe/ivy-releases"))(Resolver.ivyStylePatterns),
  Resolver.url("sbt-plugins-releases", url("https://dl.bintray.com/playframework/sbt-plugin-releases"))(Resolver.ivyStylePatterns)
)


libraryDependencies ++= Seq(
  cache,
  javaWs,
  "com.typesafe.play" %% "play-json" % "2.6.8",
  "com.typesafe.play" %% "play-iteratees" % "2.6.1",
  "com.typesafe.play" %% "play-iteratees-reactive-streams" % "2.6.1",
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion
)
