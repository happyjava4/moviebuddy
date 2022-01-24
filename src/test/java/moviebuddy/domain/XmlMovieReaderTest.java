package moviebuddy.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class XmlMovieReaderTest {

    @Test
    void NotEmpty_LoadMovies() {
        XmlMovieReader reader = new XmlMovieReader();
        List<Movie> movies = reader.loadMovies();

        assertEquals(1375, movies.size());
    }
}