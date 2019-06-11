package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        createAllTables();
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/fxml/login.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Create a new table in the test database
     *
     */
    private void createAllTables() {
        // SQLite connection string
        String url = "jdbc:sqlite:resources/sqlite_db/emeragency.db";
        String sqlCategories = "CREATE TABLE IF NOT EXISTS categories (\n"
                + "	categoryName text PRIMARY KEY,\n"
                + ");";
        String sqlEvents = "CREATE TABLE IF NOT EXISTS events (\n"
                + "	eventID integer PRIMARY KEY\n"
                + "	title text PRIMARY KEY\n"
                + "	publishTime text NOT NULL\n"
                + "	postedDispatchUser text NOT NULL\n"
                + "	acount-status text NOT NULL\n"
                + "	status text NOT NULL\n"
                + ");";
        String sqlUsers = "CREATE TABLE IF NOT EXISTS users (\n"
                + "	userName text PRIMARY KEY\n"
                + "	acount-status text NOT NULL\n"
                + "	email text NOT NULL\n"
                + "	password text NOT NULL\n"
                + "	role text NOT NULL\n"//admin or regular user
                + "	organization text NOT NULL\n"
                + "	rank integer NOT NULL\n"
                + "	warnings integer NOT NULL\n"
                + ");";
        String sqleventOrganizations = "CREATE TABLE IF NOT EXISTS eventOrganizations (\n"
                + "	eventID integer PRIMARY KEY\n"
                + "	title text PRIMARY KEY\n"
                + "	publishDate text NOT NULL\n"
                + "	postedDispatchUser text NOT NULL\n"
                + "	acount-status text NOT NULL\n"
                + "	status text NOT NULL\n"
                + ");";
        String sqleventUpdates = "CREATE TABLE IF NOT EXISTS eventUpdates (\n"
                + "	eventName text PRIMARY KEY\n"
                + "	updateID text PRIMARY KEY\n"
                + "	publishedDate text NOT NULL\n"
                + "	publishedUser text NOT NULL\n"
                + "	index integer NOT NULL\n"
                + "	description text NOT NULL\n"

                + ");";
        String sqlusersEvents = "CREATE TABLE IF NOT EXISTS usersEvents (\n"
                + "	eventID integer PRIMARY KEY\n"
                + "	userName text PRIMARY KEY\n"
                + "	authorization text NOT NULL\n"
                + "	title text NOT NULL\n"
                + ");";
        String sqleventFeedbacks = "CREATE TABLE IF NOT EXISTS eventFeedbacks (\n"
                + "	eventID integer PRIMARY KEY\n"
                + "	feedbackeduserName text PRIMARY KEY\n"
                + "	feedbackerUserName text PRIMARY KEY\n"
                + "	value integer NOT NULL\n"
                + ");";


        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sqlUsers);
            stmt.execute(sqlEvents);
            stmt.execute(sqlCategories);
            stmt.execute(sqleventOrganizations);
            stmt.execute(sqleventUpdates);
            stmt.execute(sqlusersEvents);
            stmt.execute(sqleventFeedbacks);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
