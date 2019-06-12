package Controller;

import Model.Model;
import View.View;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;

public class Main extends Application {

    static public Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Model model = new Model();
        View view = new View();
        controller = new Controller(model, view);
        createAllTables();
        addTODB();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 400));
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
                + "	categoryName text PRIMARY KEY\n"
                + ");";
        String sqlEvents = "CREATE TABLE IF NOT EXISTS events (\n"
                + "	eventID integer,\n"
                + "	title text,\n"
                + "	publishTime text NOT NULL,\n"
                + "	postedDispatchUser text NOT NULL,\n"
                + "	accountStatus text NOT NULL,\n"
                + "	status text NOT NULL,\n"
                + "PRIMARY KEY (eventID,title)"
                + ");";
        String sqlUsers = "CREATE TABLE IF NOT EXISTS users (\n"
                + "	userName text PRIMARY KEY,\n"
                + "	accountStatus text NOT NULL,\n"
                + "	email text NOT NULL,\n"
                + "	password text NOT NULL,\n"
                + "	role text NOT NULL,\n"//admin or regular user
                + "	organization text NOT NULL,\n"
                + "	rank integer NOT NULL,\n"
                + "	warnings integer NOT NULL\n"
                + ");";
//        String sqleventOrganizations = "CREATE TABLE IF NOT EXISTS eventOrganizations (\n"
//                + "	eventID integer PRIMARY KEY\n"
//                + "	title text PRIMARY KEY\n"
//                + "	publishDate text NOT NULL\n"
//                + "	postedDispatchUser text NOT NULL\n"
//                + "	acount-status text NOT NULL\n"
//                + "	status text NOT NULL\n"
//                + ");";
        String sqleventUpdates = "CREATE TABLE IF NOT EXISTS eventUpdates (\n"
                + "	eventName text,\n"
                + "	updateID text,\n"
                + "	publishedDate text NOT NULL,\n"
                + "	publishedUser text NOT NULL,\n"
                + "	index1 integer NOT NULL,\n"
                + "	description text NOT NULL,\n"
                + "PRIMARY KEY (eventName,updateID)\n"
                + ");";
        String sqlusersEvents = "CREATE TABLE IF NOT EXISTS usersEvents (\n"
                + "	eventName text,\n"
                + "	userName text,\n"
                + "	authorization text NOT NULL,\n"
                + "	title text NOT NULL,\n"
                + "PRIMARY KEY (eventName,userName)"
                + ");";
        String sqleventFeedbacks = "CREATE TABLE IF NOT EXISTS eventFeedbacks (\n"
                + "	eventID integer,\n"
                + "	feedbackeduserName text,\n"
                + "	feedbackerUserName text,\n"
                + "	value integer NOT NULL,\n"
                + "PRIMARY KEY (eventID,feedbackeduserName,feedbackerUserName)"
                + ");";


        try (Connection conn = DriverManager.getConnection(url);
             Statement pstmt = conn.createStatement();
             Statement pstmt2 = conn.createStatement();
             Statement pstmt3 = conn.createStatement();
             Statement pstmt4 = conn.createStatement();
             Statement pstmt5 = conn.createStatement();
             Statement pstmt6 = conn.createStatement()) {
            // create a new table
            pstmt.execute(sqlUsers);
            pstmt2.execute(sqlEvents);
            pstmt3.execute(sqlCategories);
            //stmt.execute(sqleventOrganizations);
            pstmt4.execute(sqleventUpdates);
            pstmt5.execute(sqlusersEvents);
            pstmt6.execute(sqleventFeedbacks);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    public void addTODB(){
        //events
        String sql = "INSERT INTO events(eventID, title, publishTime, postedDispatchUser,accountStatus,status)" +
                " VALUES(?,?,?,?,?,?)";
        String sq2 = "INSERT INTO events(eventID, title, publishTime, postedDispatchUser,accountStatus,status)" +
                " VALUES(?,?,?,?,?,?)";

        //users
        String sq3 = "INSERT INTO users(userName, accountStatus, email, password,role,organization,rank,warnings)" +
                " VALUES(?,?,?,?,?,?,?,?)";
        String sq4 = "INSERT INTO users(userName, accountStatus, email, password,role,organization,rank,warnings)" +
                " VALUES(?,?,?,?,?,?,?,?)";
        String sq5 = "INSERT INTO users(userName, accountStatus, email, password,role,organization,rank,warnings)" +
                " VALUES(?,?,?,?,?,?,?,?)";
        String sq6 = "INSERT INTO users(userName, accountStatus, email, password,role,organization,rank,warnings)" +
                " VALUES(?,?,?,?,?,?,?,?)";

        //usersEvents
        String sq7 = "INSERT INTO usersEvents(eventName, userName, authorization, title)" +
                " VALUES(?,?,?,?)";
        String sq8 = "INSERT INTO usersEvents(eventName, userName, authorization, title)" +
                " VALUES(?,?,?,?)";
        String sq9 = "INSERT INTO usersEvents(eventName, userName, authorization, title)" +
                " VALUES(?,?,?,?)";
        String sq10 = "INSERT INTO usersEvents(eventName, userName, authorization, title)" +
                " VALUES(?,?,?,?)";

        try (Connection conn = this.connect2();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             PreparedStatement pstmt2 = conn.prepareStatement(sq2);
             PreparedStatement pstmt3 = conn.prepareStatement(sq3);
             PreparedStatement pstmt4 = conn.prepareStatement(sq4);
             PreparedStatement pstmt5 = conn.prepareStatement(sq5);
             PreparedStatement pstmt6 = conn.prepareStatement(sq6);
             PreparedStatement pstmt7 = conn.prepareStatement(sq7);
             PreparedStatement pstmt8 = conn.prepareStatement(sq8);
             PreparedStatement pstmt9 = conn.prepareStatement(sq9);
             PreparedStatement pstmt10 = conn.prepareStatement(sq10)
        ) {
            //event1
            pstmt.setInt(1, 1);
            pstmt.setString(2, "gunShoot");
            pstmt.setString(3, "12-06-2019,14:50");
            pstmt.setString(4, "shalev");
            pstmt.setString(5, "ACTIVE");
            pstmt.setString(6, "ON_GOING");

            //event2
            pstmt2.setInt(1, 2);
            pstmt2.setString(2, "stabbing");
            pstmt2.setString(3, "11-06-2019,14:50");
            pstmt2.setString(4, "shalev");
            pstmt2.setString(5, "ACTIVE");
            pstmt2.setString(6, "DONE");

            //user1
            pstmt3.setString(1, "shalev");
            pstmt3.setString(2, "ACTIVE");
            pstmt3.setString(3, "shalev@gmail.com");
            pstmt3.setString(4, "1234");
            pstmt3.setString(5, "admin");
            pstmt3.setString(6, "dispatch");
            pstmt3.setInt(7, 0);
            pstmt3.setInt(8, 0);

            //user2
            pstmt4.setString(1, "avi");
            pstmt4.setString(2, "ACTIVE");
            pstmt4.setString(3, "avi@gmail.com");
            pstmt4.setString(4, "123456");
            pstmt4.setString(5, "fireFirhgter");
            pstmt4.setString(6, "fireDepartment");
            pstmt4.setInt(7, 6);
            pstmt4.setInt(8, 1);

            //user3
            pstmt5.setString(1, "idan");
            pstmt5.setString(2, "ACTIVE");
            pstmt5.setString(3, "idan@gmail.com");
            pstmt5.setString(4, "1234567");
            pstmt5.setString(5, "policeman");
            pstmt5.setString(6, "police");
            pstmt5.setInt(7, 8);
            pstmt5.setInt(8, 2);

            //user4
            pstmt6.setString(1, "niv");
            pstmt6.setString(2, "ACTIVE");
            pstmt6.setString(3, "niv@gmail.com");
            pstmt6.setString(4, "12345678");
            pstmt6.setString(5, "medic");
            pstmt6.setString(6, "mda");
            pstmt6.setInt(7, 9);
            pstmt6.setInt(8, 0);

            //user1event1
            pstmt7.setString(1, "1");
            pstmt7.setString(2, "avi");
            pstmt7.setString(3, "write");
            pstmt7.setString(4, "gunShoot");

            //user2event1
            pstmt8.setString(1, "1");
            pstmt8.setString(2, "idan");
            pstmt8.setString(3, "write");
            pstmt8.setString(4, "gunShoot");

            //user1event1
            pstmt9.setString(1, "2");
            pstmt9.setString(2, "idan");
            pstmt9.setString(3, "write");
            pstmt9.setString(4, "stabbing");

            //user1event1
            pstmt10.setString(1, "2");
            pstmt10.setString(2, "niv");
            pstmt10.setString(3, "write");
            pstmt10.setString(4, "stabbing");

            //execute query
            pstmt.executeUpdate();
            pstmt2.executeUpdate();
            pstmt3.executeUpdate();
            pstmt4.executeUpdate();
            pstmt5.executeUpdate();
            pstmt6.executeUpdate();
            pstmt7.executeUpdate();
            pstmt8.executeUpdate();
            pstmt9.executeUpdate();
            pstmt10.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private Connection connect2() {
        // SQLite connection string
        String url = "jdbc:sqlite:resources/sqlite_db/emeragency.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
