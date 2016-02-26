name := """AkkaExplore"""

version := "1.0"

scalaVersion := "2.11.6"

resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4.2",
  "com.typesafe.akka" % "akka-remote_2.11" % "2.4.2",
  "com.typesafe.akka" % "akka-persistence-experimental_2.11" % "2.4-M2",
  "com.typesafe.akka" % "akka-contrib_2.11" % "2.4.2",
  "org.scalatest" %% "scalatest" % "2.2.6" % "test",
  "org.scalactic" %% "scalactic" % "2.2.6",
  "com.typesafe.akka" %% "akka-testkit" % "2.4.2" % "test",
  "org.jsoup" % "jsoup" % "1.8.1",
  "com.ning" % "async-http-client" % "1.7.19",
  "com.typesafe.akka" % "akka-http-experimental_2.11" % "2.4.2-RC2",
  "com.typesafe.akka" % "akka-persistence-experimental_2.11" % "2.4-M2",
  "org.iq80.leveldb"            % "leveldb"          % "0.7",
  "org.fusesource.leveldbjni"   % "leveldbjni-all"   % "1.8",
  "com.typesafe.akka" % "akka-http-experimental_2.11" % "2.4.2",
  "com.typesafe.akka" % "akka-http-core-experimental_2.11" % "2.0.3",
  "org.scala-lang.modules" % "scala-java8-compat_2.11" % "0.7.0",
  "io.spray" % "spray-json_2.11" % "1.3.2",
  "com.typesafe.akka" % "akka-stream-experimental_2.11" % "2.0.3",
  "com.typesafe" % "ssl-config-akka_2.11" % "0.1.3",
  "com.typesafe.akka" % "akka-http-spray-json-experimental_2.11" % "2.4.2",
  "org.twitter4j" % "twitter4j-stream" % "4.0.4",
  "org.twitter4j" % "twitter4j-core" % "4.0.4"
)
