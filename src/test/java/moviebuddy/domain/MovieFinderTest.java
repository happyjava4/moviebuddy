package moviebuddy.domain;

import moviebuddy.MovieBuddyFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author springrunner.kr@gmail.com
 */
public class MovieFinderTest {
    final MovieFinder movieFinder = new MovieBuddyFactory().movieFinder();

    @Test
    void NotEmpty_directedBy() {
        List<Movie> actual = movieFinder.directedBy("Michael Bay");
        assertEquals(3, actual.size());
    }

    @Test
    void NotEmpty_releasedByYear() {
        List<Movie>actual = movieFinder.releasedYearBy(2015);
        assertEquals(225, actual.size());
    }

}
