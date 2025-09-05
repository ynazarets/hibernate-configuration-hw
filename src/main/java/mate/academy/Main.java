package mate.academy;

import mate.academy.lib.Injector;
import mate.academy.model.Movie;
import mate.academy.service.MovieService;

public class Main {
    private static final Injector injector = Injector.getInstance("mate.academy");

    public static void main(String[] args) {
        final MovieService movieService = (MovieService) injector.getInstance(MovieService.class);

        System.out.println("--- We will to add new movie to Database ---");
        Movie movie = new Movie();
        movie.setTitle("Lord of Rings");
        movie.setDescription("Very boring movie.");
        Movie addedMovie = movieService.add(movie);
        System.out.println("Added movie with title: " + addedMovie.getTitle());

        System.out.println("--- We will getting movie from Database ---");
        Movie gettingMovie = movieService.get(1L);
        System.out.println("We waiting for movie with title: " + movie.getTitle()
                + " and we get movie with title: " + gettingMovie.getTitle());
    }
}
