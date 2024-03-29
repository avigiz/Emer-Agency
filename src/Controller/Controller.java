package Controller;

import Model.Model;
import View.View;
import Model.Update;
import Model.Category;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
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
    public TableColumn<Update, Integer> ordering;
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
        model.setLoggedInUser("shalev");
        view.onDispatchAdminLogin();
    }

    /**
     * opens the officer's home screen
     */
    public void officerLogin() {
        model.setLoggedInUser("idan");
        ArrayList<String> events = model.getUserEvents();
        view.onSecurityForcesUserLogin("Officer", events);
    }

    /**
     * opens the firefighter's home screen
     */
    public void fireFighterLogin() {
        model.setLoggedInUser("avi");
        ArrayList<String> events = model.getUserEvents();
        view.onSecurityForcesUserLogin("Firefighter", events);
    }

    /**
     * opens the medic's home screen
     */
    public void medicLogin() {
        model.setLoggedInUser("niv");
        ArrayList<String> events = model.getUserEvents();
        view.onSecurityForcesUserLogin("Medic", events);
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
        String result = model.addCategory(txtfld_category.getText());
        if (result.equals("ok"))
            handleAlert("s_category");
        else {
            if (result.equals("exists"))
                handleAlert("category_exists");
            else
                handleAlert("f_category");
        }
    }

    /**
     * opens a new window with all the updates of all the events the user is a part of
     */
    public void showEventUpdates() {
        if (update_events.getValue() == null)
            handleAlert("no_update_event_for_show");
        else {
            ArrayList<Update> updates = model.showEventUpdates((String) update_events.getValue());
            view.onShowEventUpdates(updates);
        }
    }

    /**
     * opens a new window in which you can add a new update to the chosen event
     */
    public void updateEvent() {
        ArrayList<String> events = model.getUserEvents();
        view.onUpdateEvent(events);
    }

    /**
     * opens a new window in which you can add an event feedback
     */
    public void addEventFeedback() {
        if (feedback_events.getValue() == null)
            handleAlert("no_feedback_event");
        else {
            ArrayList<String> users = model.getEventsUsers(feedback_events.valueProperty().getValue().toString());
            view.onAddEventFeedback(users);
        }
    }

    /**
     * adds a new feedback to an event
     */
    public void sendFeedback() {
        if (feedback_users.getValue() == null || feedback_ranks.getValue() == null){
            handleAlert("missing_values");
        }
        else {
            String eventName = feedback_events.getValue().toString();
            String userFeed = feedback_users.getValue().toString();
            Object rank = feedback_ranks.getValue();
            int Rank = Integer.parseInt((String) rank);
            boolean result = model.sendFeedback(eventName, userFeed, Rank);
            if (result) {
                handleAlert("s_feedback");
                feedback_ranks.setValue(null);
                feedback_users.setValue(null);
                view.onCloseStage((Stage) feedback_users.getScene().getWindow());
            } else {
                handleAlert("f_feedback");
                feedback_ranks.setValue(null);
                feedback_users.setValue(null);
            }
        }
    }

    /**
     * adds an update to an event
     */
    public void addEventUpdate() {
        if (update_events.getValue() == null)
            handleAlert("no_update_event");
        else {
            if (txtarea_update_content.getText().equals(""))
                handleAlert("no_update_content");
            else {
                String eventName = update_events.getValue().toString();
                String updateContent = txtarea_update_content.getText();
                String ans = model.addEventUpdate(eventName, updateContent);
                if (ans.equals("ok")) {
                    handleAlert("s_update");
                    txtarea_update_content.setText("");
                    update_events.setValue(null);
                    view.onCloseStage((Stage) update_events.getScene().getWindow());
                } else {
                    handleAlert("f_update");
                    txtarea_update_content.setText("");
                    update_events.setValue(null);
                }
            }
        }
    }

    /**
     * opens a new window with all available categories
     */
    public void showCategories() {
        List<Category> ans = model.showCategory();
        view.onShowCategories(ans);
    }

    /**
     * handles a new alert
     * @param s - a string represents the alert
     */
    private void handleAlert(String s){
        if(s.equals("s_category")) {
            createAlert("Success!", "Category added successfully", Alert.AlertType.CONFIRMATION);
        }
        if(s.equals("f_category")) {
            createAlert("We have a problem..", "Failed to add category", Alert.AlertType.ERROR);
        }
        if(s.equals("s_update")) {
            createAlert("Success!", "Update added successfully", Alert.AlertType.CONFIRMATION);
        }
        if(s.equals("f_update")) {
            createAlert("We have a problem..", "Failed to add a new update", Alert.AlertType.ERROR);
        }
        if(s.equals("no_feedback_event")) {
            createAlert("We have a problem..", "You need to choose an event before adding a new feedback!", Alert.AlertType.ERROR);
        }
        if(s.equals("s_feedback")) {
            createAlert("Success!", "Feedback added successfully", Alert.AlertType.CONFIRMATION);
        }
        if(s.equals("f_feedback")) {
            createAlert("We have a problem..", "You already gave a feedback for that user in that event!", Alert.AlertType.ERROR);
        }
        if(s.equals("no_update_event")) {
            createAlert("We have a problem..", "You need to choose an event before adding an new update!", Alert.AlertType.ERROR);
        }

        if(s.equals("missing_values")) {
            createAlert("We have a problem..", "You didn't choose a rank or a user to feedback!", Alert.AlertType.ERROR);
        }
        if(s.equals("no_update_content")) {
            createAlert("We have a problem..", "You need fill the content for the update!", Alert.AlertType.ERROR);
        }
        if(s.equals("no_update_event_for_show")) {
            createAlert("We have a problem..", "You need to choose an event before showing all of its updates!", Alert.AlertType.ERROR);
        }
        if(s.equals("category_exists")) {
            createAlert("We have a problem..", "The category already exists!", Alert.AlertType.ERROR);
        }
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
