package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class View {

    public void onDispatchAdminLogin() {
        try {
            Stage stage = new Stage();
            stage.setTitle("Hello Dispatch Admin");
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("../../resources/fxml/dispatch_admin.fxml"));
            Scene scene = new Scene(root, 300, 275);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onSecurityForcesUserLogin(String title) {
        try {
            Stage stage = new Stage();
            stage.setTitle("Hello " + title);
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("../../resources/fxml/security_forces_user_home.fxml"));
            Scene scene = new Scene(root, 300, 275);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onLogOut(Stage s) {
        s.close();
    }

    public void handleAlert(Alert a) {
        a.show();
    }
}
