package Controller;

import Model.Model;
import View.View;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

    private Model model;
    private View view;

    public TextField txtfld_category;
    public Button btn_logout;
    public ChoiceBox feedback_events;
    public ChoiceBox feedback_ranks;
    public ChoiceBox feedback_users;
    public ChoiceBox update_events;
    public ChoiceBox txtfld_update_content;

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
        view.onLogOut((Stage)txtfld_category.getScene().getWindow());
    }

    public void securityForcesUserLogOut() {
        model.setLoggedInUser("");
        view.onLogOut((Stage)btn_logout.getScene().getWindow());
    }

    public void addCategory() {
        boolean result = model.addCategory(txtfld_category.getText());
        if (result)
            handleAlert("s_category");
        else
            handleAlert("f_category");
    }

    public void showEventUpdates() {

    }

    public void updateEvent() {

    }

    public void addEventFeedback() {

    }

    public void sendFeedback() {

    }

    public void addEventUpdate() {

    }

    public void showCategories() {

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
