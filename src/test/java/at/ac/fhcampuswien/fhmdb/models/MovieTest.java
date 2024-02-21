package at.ac.fhcampuswien.fhmdb.models;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTest {

    @Test
    public void testGetTitle() {
        Movie movie = new Movie("Inception", "People dream a lot.", "Science Fiction");
        assertEquals("Inception", movie.getTitle());
    }

    @Test
    public void testGetDescription() {
        Movie movie = new Movie("Inception", "People dream a lot.", "Science Fiction");
        assertEquals("People dream a lot.", movie.getDescription());
    }

    @Test
    public void testGetGenres() {
        Movie movie = new Movie("Inception", "People dream a lot.", "Science Fiction");
        List<String> expectedGenres = List.of("Science Fiction");
        assertEquals(expectedGenres, movie.getGenres());
    }

    @Test
    public void testGetGenresAsString() {
        Movie movie = new Movie("Inception", "People dream a lot.", "Science Fiction");
        assertEquals("Science Fiction", movie.getGenresAsString());
    }

    @Test
    public void testAddGenres() {
        Movie movie = new Movie("Inception", "People dream a lot.", "Science Fiction");
        movie.addGenres("Action");
        List<String> expectedGenres = List.of("Science Fiction", "Action");
        assertEquals(expectedGenres, movie.getGenres());
    }

    @Test
    public void testInitializeMovies() {
        List<Movie> movies = Movie.initializeMovies();
        assertEquals(4, movies.size());
    }
}

