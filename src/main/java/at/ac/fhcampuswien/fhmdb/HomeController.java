package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import at.ac.fhcampuswien.fhmdb.util.Genres;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView movieListView;

    @FXML
    public JFXComboBox <String> genreComboBox;

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


        searchField.textProperty().addListener(
                new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                        ObservableList<Movie> movieListCopy = FXCollections.observableArrayList(observableMovies);

                        movieListCopy =
                        observableMovies.stream()
                                .filter(movie -> movie.getTitle().contains(newValue) || movie.getDescription().contains(newValue))
                                .collect(Collectors.toCollection(FXCollections::observableArrayList));

                        movieListView.setItems(movieListCopy);
                    }
                    // TODO nicht case sensitive
                    // TODO nur die Filme die gefiltert werden anzeigen --> Liste die angezeigt wird austauschen / ohne Filter soll wieder die alte dargestellt werden
                }
        );

        // TODO add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here



        // Sort button example:
        sortBtn.setOnAction(actionEvent -> {
            boolean sortAsc = sortBtn.getText().equals("Sort (asc)");
            List<Movie> currentDisplayedMovies = new ArrayList<>(movieListView.getItems());

            if (sortAsc) {
                currentDisplayedMovies.sort(Comparator.comparing(Movie::getTitle));
            } else {
                currentDisplayedMovies.sort(Comparator.comparing(Movie::getTitle).reversed());
            }

            movieListView.setItems(FXCollections.observableArrayList(currentDisplayedMovies));
            sortBtn.setText(sortAsc ? "Sort (desc)" : "Sort (asc)");

        });
    }


    @FXML
    public void setGenreFilter() {
        String selectedValue = genreComboBox.getValue();

        if ("No Filter".equals(selectedValue)) {
            // Reset to show all movies if "No Filter" is selected
            movieListView.setItems(observableMovies);
        } else {
            // Apply filter and show only matched movies
            ObservableList<Movie> filteredMovies = allMovies.stream()
                    .filter(movie -> movie.getGenres().contains(selectedValue))
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));
            movieListView.setItems(filteredMovies);
        }
    }

    private void resetGenreFilter() {
        movieListView.setItems(observableMovies);
    }
}