package mate.academy.dao;

import java.util.Optional;
import mate.academy.lib.Dao;
import mate.academy.lib.DataProcessingException;
import mate.academy.model.Movie;
import mate.academy.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class MovieDaoImpl implements MovieDao {
    @Override
    public Movie add(Movie movie) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(movie);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can not add new movie with title: "
                    + movie.getTitle(), e);
        } finally {
            if (transaction != null) {
                session.close();
            }
        }
        return movie;
    }

    @Override
    public Optional<Movie> get(Long id) {
        Session session = null;
        Optional<Movie> loadedMovie;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            loadedMovie = Optional.ofNullable(session.find(Movie.class, id));
        } catch (HibernateException e) {
            throw new DataProcessingException("Can not get movie with id: "
                    + id, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return loadedMovie;
    }
}
