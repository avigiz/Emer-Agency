package Controller;

import Model.Model;
import View.View;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

    private Model model;
    private View view;

    public TextField txtfld_category;
    public Button btn_logout;

    public Controller (Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void dispatchAdminLogin(ActionEvent e) {
        model.setLoggedInUser("dispatch_admin");
        view.onDispatchAdminLogin();
    }

    public void officerLogin(ActionEvent e) {
        model.setLoggedInUser("officer");
        view.onSecurityForcesUserLogin("Officer");
    }

    public void fireFighterLogin(ActionEvent e) {
        model.setLoggedInUser("firefighter");
        view.onSecurityForcesUserLogin("Firefighter");
    }

    public void medicLogin(ActionEvent e) {
        model.setLoggedInUser("medic");
        view.onSecurityForcesUserLogin("Medic");
    }

    public void dispatchAdminLogOut(ActionEvent e) {
        model.setLoggedInUser("");
        view.onLogOut((Stage)txtfld_category.getScene().getWindow());
    }

    public void securityForcesUserLogOut(ActionEvent e) {
        model.setLoggedInUser("");
        view.onLogOut((Stage)btn_logout.getScene().getWindow());
    }

    public void addCategory(ActionEvent e) {
        boolean result = model.addCategory(txtfld_category.getText());
        if (result)
            handleAlert("s_category");
        else
            handleAlert("f_category");
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
