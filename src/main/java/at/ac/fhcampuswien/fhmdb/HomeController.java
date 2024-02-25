package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import at.ac.fhcampuswien.fhmdb.util.Genres;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.*;

public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView movieListView;

    @FXML
    public JFXComboBox genreComboBox;

    @FXML
    public JFXButton sortBtn;

    public static Genres genres = new Genres();
    private boolean isInitialized = false;

    public List<Movie> allMovies = Movie.initializeMovies();

    private ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (!isInitialized) {
            observableMovies.addAll(allMovies);         // add dummy data to observable list

            // initialize UI stuff
            movieListView.setItems(observableMovies);   // set data of observable list to list view
            movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data
            isInitialized = true;
        }
        // TODO add genre filter items with genreComboBox.getItems().addAll(...)
        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().add("No Filter");
        genreComboBox.getItems().addAll(genres.getGenres());



        // TODO add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here

        // Sort button example:
        sortBtn.setOnAction(actionEvent -> {
            if(sortBtn.getText().equals("Sort (asc)")) {
                // TODO sort observableMovies ascending
                sortBtn.setText("Sort (desc)");
            } else {
                // TODO sort observableMovies descending
                sortBtn.setText("Sort (asc)");
            }
        });


    }
    @FXML
    public void setGenreFilter() {
        String selectedValue = (String) genreComboBox.getValue();
        if (Objects.equals(selectedValue, "No Filter")){
            resetGenreFilter();
        }
        else{
            observableMovies.clear();
            for (Movie movie : allMovies) {
                if (movie.getGenres().contains(selectedValue)) {
                    observableMovies.add(movie);
                }
            }
        }
        movieListView.refresh();
        System.out.println(selectedValue);
    }
    private void resetGenreFilter() {
        observableMovies.clear();
        observableMovies.setAll(allMovies);
        movieListView.refresh();
    }
}