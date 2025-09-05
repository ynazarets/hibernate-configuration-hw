package mate.academy.service;

import mate.academy.dao.MovieDao;
import mate.academy.lib.Dao;
import mate.academy.lib.DataProcessingException;
import mate.academy.lib.Injector;
import mate.academy.model.Movie;

@Dao
public class MovieServiceImpl implements MovieService {
    private static final Injector injector = Injector.getInstance("mate.academy");
    private final MovieDao movieDao = (MovieDao) injector.getInstance(MovieDao.class);

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public Movie get(Long id) {
        return movieDao.get(id).orElseThrow(() ->
                new DataProcessingException("Movie with id " + id + " not found."));
    }
}
