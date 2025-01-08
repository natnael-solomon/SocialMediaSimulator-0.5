package socialmedia;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class NavigationPanelHomePage extends VBox {

    public NavigationPanelHomePage() {
        this.setSpacing(15);
        this.getStyleClass().add("nav-panel");


        Button navItem = new Button("Profile");
        navItem.getStyleClass().add("nav-item");
        navItem.setOnAction(event -> {
            ProfilePage profilePage = new ProfilePage();
            HomePage page = new HomePage();
            page.setScene(profilePage.getScene());
        });


        String[] navItems = {"Home", "Explore", "Notifications", "Messages"};
        for (String item : navItems) {
            this.getChildren().add(createNavItem(item));
        }
        this.getChildren().add(navItem);
    }

    private Label createNavItem(String item) {
        Label navItem = new Label(item);
        navItem.getStyleClass().add("nav-item");
        return navItem;
    }
}
