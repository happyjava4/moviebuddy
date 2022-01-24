package moviebuddy.domain;

import lombok.Getter;
import lombok.Setter;
import moviebuddy.ApplicationException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class XmlMovieReader implements MovieReader {
    @Override
    public List<Movie> loadMovies() {

        try {
            final Unmarshaller unmarshaller = JAXBContext
                    .newInstance(MovieMetadata.class)
                    .createUnmarshaller();
            final Source source = new StreamSource(ClassLoader.getSystemResourceAsStream("movie_metadata.xml"));
            final MovieMetadata movieMetadata = (MovieMetadata) unmarshaller.unmarshal(source);

            return movieMetadata.toMovies();
        } catch (JAXBException e) {
            throw new ApplicationException("fail to load movie data", e);
        }
    }

    @XmlRootElement(name = "moviemetadata")
    public static class MovieMetadata {
        @Getter
        @Setter
        private List<Moviedata> movies;

        public List<Movie> toMovies() {
            return movies.stream().map(Moviedata::toMovie).collect(Collectors.toList());
        }
    }

    @Getter
    @Setter
    public static class Moviedata {
        private String title;
        private List<String> genres;
        private String language;
        private String country;
        private int releaseYear;
        private String director;
        private List<String> actors;
        private URL imdbLink;
        private String watchedDate;

        public Movie toMovie() {
            return Movie.of(title, genres, language, country, releaseYear, director, actors, imdbLink, watchedDate);
        }
    }
}
