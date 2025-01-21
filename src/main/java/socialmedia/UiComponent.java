package socialmedia;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class UiComponent {
    private final Stage stage;
    private final Scene scene;
    private Pane layout;
    private final List<TextField> textFields;
    private final List<PasswordField> passwordFields;

    public UiComponent(Stage stage) {
        this.stage = stage;
        this.textFields = new ArrayList<>();
        this.passwordFields = new ArrayList<>();
        this.layout = new Pane();
        this.scene = new Scene(layout, 1200, 700);
    }

    // Stage Configuration
    public void displayStage() {
        stage.show();
    }

    public void configureStage(String title) {
        stage.setTitle(title);
        stage.setScene(scene);
    }

    // Layout Management
    public void setLayout(Pane newLayout) {
        this.layout = newLayout;
        scene.setRoot(layout);
    }

    public void clearLayout() {
        layout.getChildren().clear();
        textFields.clear();
        passwordFields.clear();
    }

    public void addNode(Node node) {
        layout.getChildren().add(node);
    }

    // Theme and Styling
    public void loadTheme(String cssPath) {
        scene.getStylesheets().clear();
        scene.getStylesheets().add(cssPath);
    }

    public void addStyleClass(Node node, String cssClass) {
        node.getStyleClass().add(cssClass);
    }

    // Component Creation
    public Label createLabel(String text, String cssClass, double fadeDuration) {
        Label label = new Label(text);
        addStyleClass(label, cssClass);
        fadeIn(label, fadeDuration);
        addNode(label);
        return label;
    }

    public TextField createTextField(String placeholder, String cssClass, double maxWidth) {
        TextField textField = new TextField();
        textField.setPromptText(placeholder);
        addStyleClass(textField, cssClass);
        textField.setMaxWidth(maxWidth);
        textField.setFocusTraversable(false);
        textFields.add(textField);
        addNode(textField);
        return textField;
    }

    public PasswordField createPasswordField(String placeholder, String cssClass, double maxWidth) {
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText(placeholder);
        addStyleClass(passwordField, cssClass);
        passwordField.setMaxWidth(maxWidth);
        passwordField.setFocusTraversable(false);
        passwordFields.add(passwordField);
        addNode(passwordField);
        return passwordField;
    }

    public TextField getTextField(int index) {
        if (index >= 0 && index < textFields.size()) {
            return textFields.get(index);
        }
        return null;
    }

    public Button createButton(String text, String cssClass, Runnable action) {
        Button button = new Button(text);
        addStyleClass(button, cssClass);
        button.setOnAction(event -> action.run());
        addNode(button);
        return button;
    }

    public Button createButtonWithIcon(String text, String cssClass, String iconPath, Runnable action) {
        ImageView icon = new ImageView(new Image(iconPath));
        addStyleClass(icon, "icon-small");
        Button button = new Button(text, icon);
        addStyleClass(button, cssClass);
        button.setOnAction(event -> action.run());
        addNode(button);
        return button;
    }

    public ToggleButton createToggleButton(String cssClass) {
        ToggleButton toggleButton = new ToggleButton();

        addStyleClass(toggleButton, cssClass);

        return toggleButton;
    }

    public ImageView createImage(String imagePath, String cssClass) {
        ImageView imageView = new ImageView(new Image(imagePath));
        addStyleClass(imageView, cssClass);
        fadeIn(imageView, 0);
        addNode(imageView);
        return imageView;
    }

    public ProgressBar createProgressBar(String cssClass) {
        ProgressBar progressBar = new ProgressBar(1.0);
        addStyleClass(progressBar, cssClass);
        addNode(progressBar);
        return progressBar;
    }

    // Utility Methods
    public void fadeIn(Node node, double duration) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(duration), node);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    public void addClickEventHandler(Node node, Runnable action) {
        node.setOnMouseClicked(event -> action.run());
    }

    public void showCustomDialog(String title, String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);

        alert.getDialogPane().getStylesheets().add("file:styles/default.css");
        alert.getDialogPane().getStyleClass().add("custom-dialog");

        if (title != null && !title.isEmpty()) {
            Label headerLabel = new Label(title);
            headerLabel.getStyleClass().add("header-label");
            alert.getDialogPane().setHeader(headerLabel);
        }

        if (message != null && !message.isEmpty()) {
            Label messageLabel = new Label(message);
            messageLabel.getStyleClass().add("message-label");
            alert.getDialogPane().setContent(messageLabel);
        }

        alert.showAndWait();
    }


    public void invalidInput(String type) {

        switch (type){
            case "username": {

                addStyleClass(getTextField(1), "invalid-field");
                break;
            }
            case "password": {

                for (PasswordField field : passwordFields) {

                    addStyleClass(field, "invalid-field");
                }
                break;
            }
             case "general": {

                 for (TextField field : textFields) {
                     if (field.getText().isEmpty()) {
                         addStyleClass(field, "invalid-field");
                     }
                 }
                 for (PasswordField field : passwordFields) {
                     if (field.getText().isEmpty()) {
                         addStyleClass(field, "invalid-field");
                     }
                 }
             }
        }


        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> resetFields());
        pause.play();
    }

    public void resetFields() {

        for(TextField field : textFields) {

                field.getStyleClass().remove("invalid-field");
        }
        for(PasswordField field : passwordFields) {

                field.getStyleClass().remove("invalid-field");
        }

    }

    // Layout Factories
    public VBox createVBox(String cssClass, double spacing) {
        VBox vBox = new VBox(spacing);
        addStyleClass(vBox, cssClass);
        return vBox;
    }

    public HBox createHBox(String cssClass, double spacing) {
        HBox hBox = new HBox(spacing);
        addStyleClass(hBox, cssClass);
        return hBox;
    }

    public StackPane createStackPane(String cssClass) {
        StackPane stackPane = new StackPane();
        addStyleClass(stackPane, cssClass);
        return stackPane;

    }

    public ScrollPane createScrollPane(String cssClass) {
        ScrollPane scrollPane = new ScrollPane();
        addStyleClass(scrollPane, cssClass);
        return scrollPane;
    }

    public Stage getStage() {
        return stage;
    }
}
