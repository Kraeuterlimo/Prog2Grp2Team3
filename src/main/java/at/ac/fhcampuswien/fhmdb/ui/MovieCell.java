package at.ac.fhcampuswien.fhmdb.ui;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MovieCell extends ListCell<Movie> {
    private final Label title = new Label();
    private final Label detail = new Label();
    private final Label genres = new Label();
    private final VBox layout = new VBox(title, detail, genres);

    public static final Movie SPACER = new Movie("SPACER", "", ""); // Assuming a constructor that accepts title, description, genres


    @Override
    protected void updateItem(Movie movie, boolean empty) {
        super.updateItem(movie, empty);

        if (empty || movie == null) {
            clearCellDisplay();
        } else {
            setCellDisplay(movie);
        }
    }

    private void clearCellDisplay() {
        setText(null);
        setGraphic(null);
    }

    private void setCellDisplay(Movie movie) {
        // Apply basic styling
        layout.setBackground(new Background(new BackgroundFill(Color.web("#454545"), null, null)));
        title.getStyleClass().clear();
        detail.getStyleClass().clear();
        genres.getStyleClass().clear();

        title.getStyleClass().add("text-yellow");
        detail.getStyleClass().add("text-white");
        genres.getStyleClass().add("text-white");

        // Setting text for labels
        title.setText(movie.getTitle());
        detail.setText(movie.getDescription() != null ? movie.getDescription() : "No description available");
        genres.setText(movie.getGenresAsString() != null ? movie.getGenresAsString() : "No genres available");

        // Adjusting layout properties
        title.setFont(title.getFont().font(20)); // Set font size directly
        detail.setMaxWidth(getScene().getWidth() - 30);
        detail.setWrapText(true);
        genres.setMaxWidth(getScene().getWidth() - 30);
        genres.setWrapText(true);
        layout.setPadding(new Insets(10));
        layout.setSpacing(10);
        layout.setAlignment(javafx.geometry.Pos.CENTER_LEFT);

        // Conditional styling example based on genre
        if (movie.getGenres().contains("Action")) {
            layout.setBackground(new Background(new BackgroundFill(Color.web("#202020"), null, null))); // Darker background for action movies
        }

        setGraphic(layout);
    }
}


