package at.ac.fhcampuswien.fhmdb.models;

import java.util.*;

public class Movie {
    private final String title;
    private final String description;
    // TODO add more properties here
    private final List<String> genres = new ArrayList<>();


    public Movie(String title, String description, String genre) {
        this.title = title;
        this.description = description;
        addGenres(genre);
    }
    public Movie(String title, String description, List<String> genres) {
        this.title = title;
        this.description = description;
        addGenres(genres);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getGenres() {
        return genres;
    }

    public String getGenresAsString(){
        StringJoiner joiner = new StringJoiner(", ");
        for (String genre : genres) {
            joiner.add(genre);
        }
        return joiner.toString();
    }

    public void addGenres(String genre) {
        this.genres.add(genre);
    }
    public void addGenres(List<String> genres) {
        this.genres.addAll(genres);
    }

    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();
        List<String> movieGenres = List.of("Action", "Romance");
        // TODO add some dummy data here
        movies.add(new Movie("Inception","People dream alot.", "Science Fiction"));
        movies.add(new Movie("The Dark Knight","No family and likes the night.", "Action"));
        movies.add(new Movie("Forrest Gump","If running was fun." , "Drama"));
        movies.add(new Movie("Spider-Man Homecoming", "Basic life in High school.", movieGenres));

        return movies;
    }
}
