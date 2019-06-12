package View;

import Controller.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class View {

    /**
     * displays the dispatch admin home screen
     */
    public void onDispatchAdminLogin() {
        createNewWindow("Hello Dispatch Admin", "dispatch_admin_home.fxml");
    }

    /**
     * displays the security forces user home screen
     */
    public void onSecurityForcesUserLogin(String title) {
        createNewWindow("Hello " + title, "security_forces_user_home.fxml");
    }

    /**
     * displays a new screen with all of an event's updates
     */
    public void onShowEventUpdates() {
        createNewWindow("Updates", "show_event_updates.fxml");
    }

    /**
     * displays a new screen with all the available categories
     */
    public void onShowCategories() {
        createNewWindow("Available Categories", "show_categories.fxml");
    }

    /**
     * displays a new screen in which a user can add a new update to an event
     */
    public void onUpdateEvent() {
        createNewWindow("Update an Event", "add_update_window.fxml");
    }

    /**
     * displays a new screen in which a user can add a new feedback for a user in an event
     */
    public void onAddEventFeedback() {
        createNewWindow("Add an Event Feedback", "add_update_window.fxml");
    }

    /**
     * closes a given stage
     * @param s - a given stage
     */
    public void onCloseStage(Stage s) {
        s.close();
    }

    /**
     * pops a given alert
     * @param a - a given alert
     */
    public void handleAlert(Alert a) {
        a.showAndWait();
    }

    /**
     * creates a new window
     * @param title - a given title
     * @param fxmlPath - a given fxml path
     */
    private void createNewWindow(String title, String fxmlPath) {
        try {
            Stage stage = new Stage();
            stage.setTitle(title);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + fxmlPath));
            loader.setController(Main.controller);
            Parent root = loader.load();
            Scene scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
