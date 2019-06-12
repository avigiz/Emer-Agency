package Controller;

import Model.Model;
import View.View;
import Model.Update;
import Model.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private Model model;
    private View view;

    public TextField txtfld_category;
    public Button btn_logout;
    public ChoiceBox feedback_events;
    public ChoiceBox feedback_ranks;
    public ChoiceBox feedback_users;
    public ChoiceBox update_events;
    public TextArea txtarea_update_content;
    public TableView<Update> tblview_event_updates;
    public TableColumn<Update, String> eventName;
    public TableColumn<Update, Integer> index;
    public TableColumn<Update, String> description;
    public TableColumn<Update, String> publishedBy;
    public TableColumn<Update, String> publishedDate;
    public TableView<Category> tblview_categories;
    public TableColumn<Category, String> categoryName;

    public Controller (Model model, View view) {
        this.model = model;
        this.view = view;
    }

    /**
     * opens the dispatch admin's homme screen
     */
    public void dispatchAdminLogin() {
        model.setLoggedInUser("dispatch_admin");
        view.onDispatchAdminLogin();
    }

    /**
     * opens the officer's home screen
     */
    public void officerLogin() {
        model.setLoggedInUser("officer");
        getHomeScreenEvents();
        view.onSecurityForcesUserLogin("Officer");
    }

    /**
     * opens the firefighter's home screen
     */
    public void fireFighterLogin() {
        model.setLoggedInUser("firefighter");
        getHomeScreenEvents();
        view.onSecurityForcesUserLogin("Firefighter");
    }

    /**
     * opens the medic's home screen
     */
    public void medicLogin() {
        model.setLoggedInUser("medic");
        getHomeScreenEvents();
        view.onSecurityForcesUserLogin("Medic");
    }

    /**
     * exits the dispatch admin's home screen
     */
    public void dispatchAdminLogOut() {
        model.setLoggedInUser("");
        view.onCloseStage((Stage)txtfld_category.getScene().getWindow());
    }

    /**
     * closes the security forces user's home screen
     */
    public void securityForcesUserLogOut() {
        model.setLoggedInUser("");
        view.onCloseStage((Stage)btn_logout.getScene().getWindow());
    }

    /**
     * adds a new category
     */
    public void addCategory() {
        boolean result = model.addCategory(txtfld_category.getText());
        if (result)
            handleAlert("s_category");
        else
            handleAlert("f_category");
    }

    /**
     * opens a new window with all the updates of all the events the user is a part of
     */
    public void showEventUpdates() {
        ArrayList<Update> ans = model.showEventUpdates((String)update_events.getValue());
        eventName.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        index.setCellValueFactory(new PropertyValueFactory<>("index"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        publishedBy.setCellValueFactory(new PropertyValueFactory<>("publishedBy"));
        publishedDate.setCellValueFactory(new PropertyValueFactory<>("publishedDate"));
        ObservableList<Update> updateModel = FXCollections.observableArrayList(ans);
        tblview_event_updates.setItems(updateModel);
        view.onShowEventUpdates();
    }

    /**
     * opens a new window in which you can add a new update to the chosen event
     */
    public void updateEvent() {
        view.onUpdateEvent();
    }

    /**
     * opens a new window in which you can add an event feedback
     */
    public void addEventFeedback() {
        if (feedback_events.valueProperty().equals(null))
            handleAlert("no_feedback_event");
        else {
            ArrayList<String> users = model.getEventsUsers(feedback_events.valueProperty().getName());
            ObservableList<String> list = FXCollections.observableArrayList(users);
            feedback_users.setItems(list);
            view.onAddEventFeedback();
        }
    }

    /**
     * adds a new feedback to an event
     */
    public void sendFeedback() {
        boolean result = model.sendFeedback((int)feedback_events.getValue(),(String)feedback_users.getValue(),(int)feedback_ranks.getValue());
        if (result) {
            handleAlert("s_feedback");
            view.onCloseStage((Stage)feedback_users.getScene().getWindow());
        }
        else
            handleAlert("f_feedback");
        feedback_ranks.valueProperty().set(null);
        feedback_users.valueProperty().set(null);
    }

    /**
     * adds an update to an event
     */
    public void addEventUpdate() {
        String ans = model.addEventUpdate(update_events.valueProperty().getName());
        if (ans.equals("ok")) {
            handleAlert("s_update");
            view.onCloseStage((Stage)update_events.getScene().getWindow());
        }
        else
            handleAlert("f_update");
        txtarea_update_content.setText("");
        update_events.valueProperty().set(null);
    }

    /**
     * opens a new window with all available categories
     */
    public void showCategories() {
        List<Category> ans = model.showCategory();
        categoryName.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        ObservableList<Category> categoryModel = FXCollections.observableArrayList(ans);
        tblview_categories.setItems(categoryModel);
        view.onShowCategories();
    }

    /**
     * handles a new alert
     * @param s - a string represents the alert
     */
    private void handleAlert(String s){
        if(s.equals("s_category")) {
            createAlert("Success!", "Category added successfully", Alert.AlertType.ERROR);
        }
        if(s.equals("f_category")) {
            createAlert("We have a problem..", "Failed to add category", Alert.AlertType.ERROR);
        }
        if(s.equals("s_update")) {
            createAlert("Success!", "Update added successfully", Alert.AlertType.ERROR);
        }
        if(s.equals("f_update")) {
            createAlert("We have a problem..", "Failed to add a new update", Alert.AlertType.ERROR);
        }
        if(s.equals("no_feedback_event")) {
            createAlert("We have a problem..", "You need to choose an event before adding a new feedback!", Alert.AlertType.ERROR);
        }
        if(s.equals("s_feedback")) {
            createAlert("Success!", "Feedback added successfully", Alert.AlertType.ERROR);
        }
        if(s.equals("f_feedback")) {
            createAlert("We have a problem..", "Failed to add a new feedback", Alert.AlertType.ERROR);
        }
    }

    /**
     * sets the possible events in the choice box in the user's home screen
     */
    private void getHomeScreenEvents() {
        ArrayList<String> events = model.getUserEvents();
        ObservableList<String> list = FXCollections.observableArrayList(events);
        feedback_events.setItems(list);
    }

    /**
     * creates a new alert and passes it to View for display
     * @param header - the alert's header
     * @param content - the alert's content
     * @param type - the alert's type
     */
    private void createAlert(String header, String content, Alert.AlertType type) {
        Alert alert;
        alert = new Alert(type);
        alert.setHeaderText(header);
        alert.setContentText(content);
        view.handleAlert(alert);
    }
}
