package RetrieveMoviesFromAPI

object RetrieveMoviesFromAPI extends App {
  var i = 0
  for(i <- 1 to 101) {
    RandomFilms.getRandomFilms2k(i.toString)
  }
}
