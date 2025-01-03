package socialmedia;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class NavigationPanelHomePage extends VBox {

    public NavigationPanelHomePage() {
        this.setSpacing(15);
        this.getStyleClass().add("nav-panel");

        String[] navItems = {"Home", "Explore", "Notifications", "Messages", "Profile"};
        for (String item : navItems) {
            this.getChildren().add(createNavItem(item));
        }
    }

    private Label createNavItem(String item) {
        Label navItem = new Label(item);
        navItem.getStyleClass().add("nav-item");
        return navItem;
    }
}
