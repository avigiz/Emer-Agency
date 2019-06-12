package View;

import Controller.Main;
import Model.Category;
import Model.Update;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class View {

    /**
     * displays the dispatch admin home screen
     */
    public void onDispatchAdminLogin() {
        createNewWindow("Hello Dispatch Admin", "dispatch_admin_home.fxml");
    }

    /**
     * displays the security forces user home screen
     * @param title - a given SFU title
     * @param events - a given list of events
     */
    public void onSecurityForcesUserLogin(String title, ArrayList<String> events) {
        try {
            Stage stage = new Stage();
            stage.setTitle("Hello " + title);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/security_forces_user_home.fxml"));
            loader.setController(Main.controller);
            Parent root = loader.load();
            Scene scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            getHomeScreenEvents(events);
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
//        createNewWindow("Hello " + title, "security_forces_user_home.fxml");
    }

    /**
     * sets the possible events in the choice box in the user's home screen
     * @param events - a given list of events
     */
    private void getHomeScreenEvents( ArrayList<String> events) {
        ObservableList<String> list = FXCollections.observableArrayList(events);
        Main.controller.feedback_events.setItems(list);
    }

    /**
     * displays a new screen with all of an event's updates
     * @param updates - a given arraylist of updates
     */
    public void onShowEventUpdates(ArrayList<Update> updates) {
        try {
            Stage stage = new Stage();
            stage.setTitle("Updates");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/show_event_updates.fxml"));
            loader.setController(Main.controller);
            Parent root = loader.load();
            Scene scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            setEventUpdates(updates);
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
//        createNewWindow("Updates", "show_event_updates.fxml");
    }

    /**
     * sets the event's updates
     * @param updates - a given arraylist of updates
     */
    private void setEventUpdates(ArrayList<Update> updates) {
        Main.controller.eventName.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        Main.controller.index.setCellValueFactory(new PropertyValueFactory<>("index"));
        Main.controller.description.setCellValueFactory(new PropertyValueFactory<>("description"));
        Main.controller.publishedBy.setCellValueFactory(new PropertyValueFactory<>("publishedBy"));
        Main.controller.publishedDate.setCellValueFactory(new PropertyValueFactory<>("publishedDate"));
        ObservableList<Update> updateModel = FXCollections.observableArrayList(updates);
        Main.controller.tblview_event_updates.setItems(updateModel);
    }

    /**
     * displays a new screen with all the available categories
     */
    public void onShowCategories(List<Category> ans) {
        try {
            Stage stage = new Stage();
            stage.setTitle("Available Categories");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/show_categories.fxml"));
            loader.setController(Main.controller);
            Parent root = loader.load();
            Scene scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            setNewCategories(ans);
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
//        createNewWindow("Available Categories", "show_categories.fxml", View.class.getMethod("setNewCategories", List.class));
    }

    /**
     * sets the current categories in the table
     * @param categories - a list of give categories
     */
    private void setNewCategories(List<Category> categories) {
        Main.controller.categoryName.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        ObservableList<Category> categoryModel = FXCollections.observableArrayList(categories);
        Main.controller.tblview_categories.setItems(categoryModel);
    }

    /**
     * displays a new screen in which a user can add a new update to an event
     */
    public void onUpdateEvent() {
        createNewWindow("Update an Event", "add_update_window.fxml");
    }

    /**
     * displays a new screen in which a user can add a new feedback for a user in an event
     * @param users - a given user's list
     */
    public void onAddEventFeedback(ArrayList<String> users) {

        try {
            Stage stage = new Stage();
            stage.setTitle("Add an Event Feedback");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/add_update_window.fxml"));
            loader.setController(Main.controller);
            Parent root = loader.load();
            Scene scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            setFeedbackedUsers(users);
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
//        createNewWindow("Add an Event Feedback", "add_update_window.fxml");
    }

    /**
     * sets the possible feedbacked users
     * @param users - a list of given user names
     */
    private void setFeedbackedUsers(ArrayList<String> users) {
        ObservableList<String> list = FXCollections.observableArrayList(users);
        Main.controller.feedback_users.setItems(list);
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
