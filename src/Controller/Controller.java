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
import Model.Update;
import java.util.ArrayList;
import java.sql.SQLException;
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
    public TextArea txtfld_update_content;
    public TableView<Update> tblview_event_updates;
    public TableColumn<Update, String> eventName;
    public TableColumn<Update, Integer> index;
    public TableColumn<Update, String> description;
    public TableColumn<Update, String> publishedBy;
    public TableColumn<Update, String> publishedDate;
    public TableView tblview_categories;

    public Controller (Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void dispatchAdminLogin() {
        model.setLoggedInUser("dispatch_admin");
        view.onDispatchAdminLogin();
    }

    public void officerLogin() {
        model.setLoggedInUser("officer");
        view.onSecurityForcesUserLogin("Officer");
    }

    public void fireFighterLogin() {
        model.setLoggedInUser("firefighter");
        view.onSecurityForcesUserLogin("Firefighter");
    }

    public void medicLogin() {
        model.setLoggedInUser("medic");
        view.onSecurityForcesUserLogin("Medic");
    }

    public void dispatchAdminLogOut() {
        model.setLoggedInUser("");
        view.onCloseStage((Stage)txtfld_category.getScene().getWindow());
    }

    public void securityForcesUserLogOut() {
        model.setLoggedInUser("");
        view.onCloseStage((Stage)btn_logout.getScene().getWindow());
    }

    public void addCategory() {
        boolean result = model.addCategory(txtfld_category.getText());
        if (result)
            handleAlert("s_category");
        else
            handleAlert("f_category");
    }

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

    public void updateEvent() {

    }

    public void addEventFeedback() {

    }
    //public void sendFeedback(int eventID, String feedbackedUserName, int value) {
    public void sendFeedback() {
        model.sendFeedback((int)update_events.getValue(),(String)feedback_users.getValue(),(int)feedback_ranks.getValue());
        view.onSendFeedback();
    }

    public void addEventUpdate() {
        String ans = model.addEventUpdate(update_events.getValue());
    }

    public void showCategories() throws SQLException {
        List<Category> ans = model.showCategory();
    }

    /**
     * handles a new alert
     * @param s - a string represents the alert
     */
    private void handleAlert(String s){
        if(s.equals("s_category")) {
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("We have a problem..");
            alert.setContentText("Category added successfully");
            view.handleAlert(alert);
        }
        if(s.equals("f_category")) {
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("We have a problem..");
            alert.setContentText("Failed to add category");
            view.handleAlert(alert);
        }
    }
}
