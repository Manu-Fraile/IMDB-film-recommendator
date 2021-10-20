package Main

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.functions.{col, _}
import org.apache.spark.sql.{SQLContext, SparkSession}

object Main extends App {
  println("Hola Mundo desde Scala")

  //Turn off red INFO logs
  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)

  val spark = SparkSession.builder()
    .appName("Spark-Demo") //assign a name to the spark application
    .master("local[*]") //utilize all the available cores on local
    .getOrCreate()
  import spark.implicits._

  val sparkConf = new SparkConf()
  val sc = spark.sparkContext

  val sqlcontext:SQLContext = new SQLContext(sc)

  val jsonMovieObj = sqlcontext.read.option("multiline", true).json("data.dat")
  jsonMovieObj.printSchema()
  val moviesOneCol = jsonMovieObj.select(explode(col("movie_results")).as("movies"))
  val moviesMultiCol = moviesOneCol.select(col("movies.title").as("title"),
                                           col("movies.imdb_rating").as("rating"),
                                           col("movies.genres").as("genres"),
                                           col("movies.stars").as("actors"),
                                           col("movies.directors").as("directors"))
  val bagOfWord = moviesMultiCol
    .withColumn("bag_of_words", array_union(array_union($"genres", $"actors"), $"directors"))
  val bagOfWordMerged = bagOfWord
    .withColumn("bag_of_words", concat_ws(",", $"bag_of_words"))
    .select(col("title"), regexp_replace(regexp_replace(col("bag_of_words"), " ", "").as("bag_of_words"), ",", " ").as("bag_of_words"))
  val bagOfWordLowercase = bagOfWordMerged.withColumn("bag_of_words", lower(col("bag_of_words")))

  bagOfWordLowercase.show(41, false)

  //val moviesRaw = sc.textFile("data.dat")

  //TODO 1: Process the data from data.dat to retrieve the info for each film with the structure of the case class.
  //TODO 2: Map the data into the case class.
  //TODO 3: Transform into DF or any other structure if necessary for the Recommendation Algorithm.
  //TODO 4: Pipe the RDD with spark to Python. See https://stackoverflow.com/questions/32975636/how-to-use-both-scala-and-python-in-a-same-spark-project
}
