package socialmedia;

import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.application.Platform;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

import static socialmedia.Main.userManager;

public class HeaderHomePage extends HBox {

    private final ComboBox<User> searchComboBox;
    private final TextField searchField;

    public HeaderHomePage(Stage primaryStage) {
        UiComponent ui = new UiComponent(primaryStage);
        this.setSpacing(20);
        this.getStyleClass().add("header");
        this.setAlignment(Pos.CENTER_LEFT);

        // Create the search combo box
        searchComboBox = new ComboBox<>();
        searchComboBox.getStyleClass().add("search-bar");
        searchComboBox.setVisibleRowCount(6);

        // Set custom cell factory to display user information
        searchComboBox.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(User user, boolean empty) {
                super.updateItem(user, empty);
                if (empty || user == null) {
                    setText(null);
                } else {
                    setText("👤 - " + user.getFullName() + " - @" + user.getUsername());
                }
            }
        });

        // Set action on selection
        searchComboBox.setOnAction(event -> {
            User selectedUser = searchComboBox.getSelectionModel().getSelectedItem();
            if (selectedUser != null) {
                ProfilePage profilePage = new ProfilePage(selectedUser, primaryStage);
                primaryStage.setScene(profilePage.getScene());
            }
        });

        // Handle search input
        searchField = new TextField();
        searchField.setPromptText("🔍 Search users");
        searchField.setMinHeight(50);
        searchField.getStyleClass().add("search-bar");
        searchField.setOnKeyReleased(this::handleSearch);

        StackPane test = new StackPane();
        test.getChildren().addAll(searchComboBox, searchField);

        // Label for the page title
        Label titleLabel = createLabel();

        // Layout for the header and results
        HBox headerContainer = new HBox(20, titleLabel, test);
        headerContainer.setAlignment(Pos.CENTER_LEFT);

        this.getChildren().add(headerContainer);
    }

    private Label createLabel() {
        Label label = new Label("Simple Social Media Simulator");
        label.getStyleClass().add("logo");
        return label;
    }

    private void handleSearch(KeyEvent event) {
        String query = searchField.getText();
        Platform.runLater(() -> processSearchQuery(query));
    }

    private void processSearchQuery(String query) {
        List<User> filteredUsers = userManager.getUsers().stream()
                .filter(user -> user.getUsername().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());

        updateDropdownResults(filteredUsers);
    }

    private void updateDropdownResults(List<User> users) {
        searchComboBox.getItems().clear();
        if (users.isEmpty()) {
            searchComboBox.hide();
        } else {
            searchComboBox.getItems().addAll(users);
            searchComboBox.show();
        }
    }
}