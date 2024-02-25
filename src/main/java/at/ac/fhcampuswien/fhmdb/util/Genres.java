package at.ac.fhcampuswien.fhmdb.util;

import java.util.*;

public class Genres {
    private List<String> genres = new ArrayList<>();

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
    public void addGenre(String genre){
        if(!genres.contains(genre)){
            this.genres.add(genre);
        }
    }
    public void addGenres(List<String> genres){
        if(!genres.containsAll(genres)){
            this.genres.addAll(genres);
        }
    }
}
