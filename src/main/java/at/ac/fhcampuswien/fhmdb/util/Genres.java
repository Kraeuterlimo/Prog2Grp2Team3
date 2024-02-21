package at.ac.fhcampuswien.fhmdb.util;

import java.util.Arrays;
import java.util.List;

public class Genres {
    private List<String> genres = Arrays.asList("ACTION", "ADVENTURE", "ANIMATION", "BIOGRAPHY", "COMEDY", "CRIME", "DRAMA",
        "DOCUMENTARY", "FAMILY", "FANTASY", "HISTORY", "HORROR", "MUSICAL", "MYSTERY", "ROMANCE", "SCIENCE_FICTION",
        "SPORT", "THRILLER", "WAR", "WESTERN");

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
}
