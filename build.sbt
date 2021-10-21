name := "IMDB-film-recommendator"

version := "0.1"

scalaVersion := "2.13.6"

libraryDependencies ++= Seq(
  "com.lihaoyi" %% "ujson" % "1.4.2",
  "com.lihaoyi" %% "requests" % "0.6.9",
  "org.apache.httpcomponents" % "httpclient" % "4.5.13",
  "com.lihaoyi" %% "upickle" % "1.4.2",
  "org.apache.spark" %% "spark-core" % "3.2.0",
  "org.apache.spark" %% "spark-sql" % "3.2.0"
)