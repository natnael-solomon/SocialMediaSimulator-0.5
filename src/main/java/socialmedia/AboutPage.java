package socialmedia;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AboutPage extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        showAboutPage(primaryStage);
    }

    public void showAboutPage(Stage primaryStage) {
        primaryStage.setTitle("About Us");

        // Team Members Data
        String[][] teamData = {
                {"Natnael Solomon", "@tempusername69", " - Developer \n - Designer "},
                {"Nebiyu Samuel", "@Nebasaml", " - Developer \n - Technical Writer "},
                {"Leul Teferi", "@Pri_nceleul", " - Developer \n - Project Manager "},
                {"Leul Zegeye", "@scarlxrd_x", "- Developer \n - Tester "},
        };

        // Main container (VBox to stack the header and the team member cards)
        VBox aboutContainer = new VBox(30);
        aboutContainer.setStyle("-fx-padding: 20; -fx-background-color: #f5f8fa;");
        aboutContainer.setStyle("-fx-alignment: center;"); // Center the cards

        // Header
        Label header = new Label("Meet the Team");
        header.setFont(new Font("Segoe UI Bold", 28));
        header.setStyle("-fx-text-fill: #1da1f2;");

        // Add header to the container
        aboutContainer.getChildren().add(header);

        // Add each team member
        HBox memberCards = new HBox(30);
        memberCards.setStyle("-fx-alignment: center;"); // Center the cards horizontally

        for (String[] member : teamData) {
            VBox memberCard = createMemberCard(member[0], member[1], member[2]);
            memberCards.getChildren().add(memberCard);
        }

        aboutContainer.getChildren().add(memberCards);

        // Create the scene with updated window size
        Scene scene = new Scene(aboutContainer, 1200, 700);
        scene.getStylesheets().add("file:styles/slick.css");

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createMemberCard(String fullName, String username, String role) {
        VBox card = new VBox(100);
        card.setStyle(
                "-fx-padding: 10; " +
                        "-fx-background-radius: 10; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.2), 4, 0.5, 0, 2);" +
                        "-fx-background-color: linear-gradient(to left, #3c6e72, #0046b7);" +
                        "-fx-cursor: hand;" +
                        "-fx-width: 360px; " + // Double the width
                        "-fx-height: 180px; " + // Smaller height
                        "-fx-margin: 10px 15px;" +
                        "-fx-transition: all 0.3s ease-in-out;");

        // Hover Effect for the Card (Pop-Out Effect)
        card.setOnMouseEntered(event -> card.setStyle(
                "-fx-padding: 10; " +
                        "-fx-background-radius: 10; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.3), 10, 0.5, 0, 10);" +
                        "-fx-background-color: linear-gradient(to left, #00695c, #0046b7);" +
                        "-fx-cursor: hand;" +
                        "-fx-scale-x: 1.3; " + // Increased scale effect
                        "-fx-scale-y: 1.3;"));

        card.setOnMouseExited(event -> card.setStyle(
                "-fx-padding: 10; " +
                        "-fx-background-radius: 10; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.2), 4, 0.5, 0, 2);" +
                        "-fx-background-color: linear-gradient(to left, #3c6e72, #0046b7);" +
                        "-fx-cursor: hand;" +
                        "-fx-scale-x: 1.0;" + // Reset scale
                        "-fx-scale-y: 1.0;"));

        // Full Name and Username
        Label nameLabel = new Label(fullName);
        nameLabel.setFont(new Font("Segoe UI Bold", 16));
        nameLabel.setStyle("-fx-text-fill: #ffffff;");

        Label usernameLabel = new Label(username);
        usernameLabel.setFont(new Font("Segoe UI", 12));
        usernameLabel.setStyle("-fx-text-fill: #d6e0e5;");

        // Hover Effect for Username
        usernameLabel.setOnMouseEntered(event -> usernameLabel.setStyle("-fx-text-fill: #1da1f2;"));
        usernameLabel.setOnMouseExited(event -> usernameLabel.setStyle("-fx-text-fill: #d6e0e5;"));

        // Team Role Label (at the bottom)
        Label roleLabel = new Label(role);
        roleLabel.setFont(new Font("Segoe UI", 10));
        roleLabel.setStyle("-fx-text-fill: #d6e0e5; -fx-font-weight: bold;");

        // Arrange Name and Username, and Role
        VBox memberInfo = new VBox(5);
        memberInfo.getChildren().addAll(nameLabel, usernameLabel, roleLabel);

        // Card Animation: When card is clicked
        card.setOnMouseClicked(event -> {
            card.setStyle("-fx-background-color: linear-gradient(to left, #009688, #0046b7);");
            // Add more interaction if needed, such as opening a detailed page
        });

        card.getChildren().add(memberInfo);
        return card;
    }
}

















/*
package socialmedia;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AboutPage extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        showAboutPage(primaryStage);
    }

    public void showAboutPage(Stage primaryStage) {
        primaryStage.setTitle("About Us");

        // Team Members Data
        String[][] teamData = {
                {"Natnael Solomon", "@tempusername69", "Developer"},
                {"Nebiyu Samuel", "@Nebasaml", "Designer"},
                {"Leul Teferi", "@Pri_nceleul", "Product Manager"},
                {"Leul Zegeye", "@scarlxrd_x", "Marketing"}
        };

        // Main container (VBox to stack the header and the team member cards)
        VBox aboutContainer = new VBox(30);
        aboutContainer.setStyle("-fx-padding: 20; -fx-background-color: #f5f8fa;");
        aboutContainer.setStyle("-fx-alignment: center;"); // Center the cards

        // Header
        Label header = new Label("Meet the Team");
        header.setFont(new Font("Segoe UI Bold", 28));
        header.setStyle("-fx-text-fill: #1da1f2;");

        // Add header to the container
        aboutContainer.getChildren().add(header);

        // Add each team member
        HBox memberCards = new HBox(30);
        memberCards.setStyle("-fx-alignment: center;"); // Center the cards horizontally

        for (String[] member : teamData) {
            VBox memberCard = createMemberCard(member[0], member[1], member[2]);
            memberCards.getChildren().add(memberCard);
        }

        aboutContainer.getChildren().add(memberCards);

        // Create the scene with updated window size
        Scene scene = new Scene(aboutContainer, 1200, 700);
        scene.getStylesheets().add("file:styles/slick.css");

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createMemberCard(String fullName, String username, String role) {
        VBox card = new VBox(10);
        card.setStyle(
                "-fx-padding: 10; " +
                        "-fx-background-radius: 10; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.2), 4, 0.5, 0, 2);" +
                        "-fx-background-color: linear-gradient(to left, #3c6e72, #0046b7);" +
                        "-fx-cursor: hand;" +
                        "-fx-width: 360px; " + // Double the width
                        "-fx-height: 180px; " + // Smaller height
                        "-fx-margin: 10px 15px;" +
                        "-fx-transition: all 0.3s ease-in-out;");

        // Hover Effect for the Card (Pop-Out Effect)
        card.setOnMouseEntered(event -> card.setStyle(
                "-fx-padding: 10; " +
                        "-fx-background-radius: 10; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.3), 10, 0.5, 0, 10);" +
                        "-fx-background-color: linear-gradient(to left, #00695c, #0046b7);" +
                        "-fx-cursor: hand;" +
                        "-fx-scale-x: 1.1; " + // Scale effect (Pop-out)
                        "-fx-scale-y: 1.1;"));
        card.setOnMouseExited(event -> card.setStyle(
                "-fx-padding: 10; " +
                        "-fx-background-radius: 10; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.2), 4, 0.5, 0, 2);" +
                        "-fx-background-color: linear-gradient(to left, #3c6e72, #0046b7);" +
                        "-fx-cursor: hand;" +
                        "-fx-scale-x: 1.0;" + // Reset scale
                        "-fx-scale-y: 1.0;"));

        // Profile Icon with more animation
        Circle profileIcon = new Circle(35); // Smaller profile icon
        profileIcon.setFill(new LinearGradient(0, 0, 1, 1, true, null, new Stop[]{
                new Stop(0, Color.LIGHTBLUE),
                new Stop(1, Color.DARKBLUE)
        }));
        profileIcon.setEffect(new DropShadow(8, Color.GRAY));

        // Hover Animation for Profile Icon
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), profileIcon);
        profileIcon.setOnMouseEntered(event -> {
            scaleTransition.setToX(1.2);
            scaleTransition.setToY(1.2);
            scaleTransition.play();
        });
        profileIcon.setOnMouseExited(event -> {
            scaleTransition.setToX(1.0);
            scaleTransition.setToY(1.0);
            scaleTransition.play();
        });

        // Full Name and Username
        Label nameLabel = new Label(fullName);
        nameLabel.setFont(new Font("Segoe UI Bold", 16));
        nameLabel.setStyle("-fx-text-fill: #ffffff;");

        Label usernameLabel = new Label(username);
        usernameLabel.setFont(new Font("Segoe UI", 12));
        usernameLabel.setStyle("-fx-text-fill: #d6e0e5;");

        // Hover Effect for Username
        usernameLabel.setOnMouseEntered(event -> usernameLabel.setStyle("-fx-text-fill: #1da1f2;"));
        usernameLabel.setOnMouseExited(event -> usernameLabel.setStyle("-fx-text-fill: #d6e0e5;"));

        // Team Role Label (at the bottom)
        Label roleLabel = new Label(role);
        roleLabel.setFont(new Font("Segoe UI", 10));
        roleLabel.setStyle("-fx-text-fill: #d6e0e5; -fx-font-weight: bold;");

        // Arrange Name, Icon, and Role
        VBox memberInfo = new VBox(5);
        memberInfo.getChildren().addAll(profileIcon, nameLabel, usernameLabel, roleLabel);

        // Card Animation: When card is clicked
        card.setOnMouseClicked(event -> {
            card.setStyle("-fx-background-color: linear-gradient(to left, #009688, #0046b7);");
            // Add more interaction if needed, such as opening a detailed page
        });

        card.getChildren().add(memberInfo);
        return card;
    }
}
*/
