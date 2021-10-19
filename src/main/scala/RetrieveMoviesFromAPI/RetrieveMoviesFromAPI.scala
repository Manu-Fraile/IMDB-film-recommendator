package RetrieveMoviesFromAPI

object RetrieveMoviesFromAPI extends App {
  var i = 0
  for(i <- 1 to 101) {
    RandomMovies.getRandomMovies2k(i.toString)
  }
}
