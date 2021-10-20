package Main

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object Main extends App {
  println("Hola Mundo desde Scala")

  //Turn off red INFO logs
  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)

  val spark = SparkSession.builder()
    .appName("Spark-Demo") //assign a name to the spark application
    .master("local[*]") //utilize all the available cores on local
    .getOrCreate()

  val sparkConf = new SparkConf()
  val sc = spark.sparkContext
  val moviesRaw = sc.textFile("data.dat")

  //TODO 1: Process the data from data.dat to retrieve the info for each film with the structure of the case class.
  //TODO 2: Map the data into the case class.
  //TODO 3: Transform into DF or any other structure if necessary for the Recommendation Algorithm.
  //TODO 4: Pipe the RDD with spark to Python. See https://stackoverflow.com/questions/32975636/how-to-use-both-scala-and-python-in-a-same-spark-project


}
