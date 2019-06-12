package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class Model {

    private String curr_connected_username = ""; // TODO : EVERY ACTION IN THE DB IS DONE WITH THE CURRENT LOGGED IN USER

    // TODO : BEFORE EVERY CONNECTION TO THE DB, CALL THIS FUNCTION
    private Connection connect() {
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

    /**
     * sets a given username as the current logged in username
     * @param username - a given username
     */
    public void setLoggedInUser(String username) {
        curr_connected_username = username;
    }

    /**
     * adds a new category to the database
     * @param category - a new category
     * @return - true if addition is successful. else - false
     */
    public boolean addCategory(String category) {
        String sql = "INSERT INTO categories (categoryName)VALUES(?)";
        try (Connection conn = this.connect();
             PreparedStatement insert1 = conn.prepareStatement(sql);
        ) {
            insert1.setString(1, category);
            insert1.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

        /**
     * gets all the available categories from the database
     * @return - a list containing all the categories
     */
    public List<Category> showCategory() {
        String sql = "SELECT * FROM categories";
        ResultSet m_results;
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            m_results = pstmt.executeQuery();
            List<Category> ans = new ArrayList<>();
            while (m_results.next()){
                String tmp =(m_results.getString(1));
                ans.add(new Category(tmp));
            }
            return ans;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * adds a new update to the databse
     * @param eventName - a given event's name
     * @return - "ok" if addition is successful. else - "notOk"
     */
    public String addEventUpdate(String eventName, String updateContent) {
        String insertSQL1 = "INSERT INTO eventUpdates (eventName,updateID,publishedDate,publishedUser,ordering,description)"
                +   " VALUES(?,?,?,?,?,?)";
        String selectSQL1 = "SELECT * FROM eventUpdates WHERE eventName = ?";
        try (Connection conn = this.connect();
             PreparedStatement insert1 = conn.prepareStatement(insertSQL1);
            ) {
            insert1.setString(1,eventName);
            Random rnd = new Random();
            Integer random = rnd.nextInt(100);
            insert1.setString(2,random.toString());
            insert1.setString(3,Calendar.getInstance().toString());
            insert1.setString(4,curr_connected_username);
            insert1.setInt(5,selectSQL1.length()+1);
            insert1.setString(6,updateContent);
            return "ok";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return "notOK";
        }
    }

    /**
     * gets all the updates of a given event
     * @param title - a given event's title
     * @return - an arraylist with all the updates
     */
    public ArrayList<Update> showEventUpdates(String title) {
            ArrayList<Update> ans = new ArrayList<Update>();
            String sql = "SELECT *"
                    + " FROM eventUpdates WHERE eventName = ?";
            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                // set the corresponding param
                pstmt.setString(1, title);
                ResultSet rs = pstmt.executeQuery();
                while ( rs.next() ) {
                    Update currUpdate = new Update(rs.getInt("updateID"), rs.getString("publishedUser"),
                            rs.getString("eventName"), rs.getString("description"),
                            rs.getString("publishedDate"), rs.getInt("ordering"));
                    ans.add( currUpdate );
                }
                return ans;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
    }

    /**
     * gets all the event titles connected to the connected user
     * @return - all the event titles connected to the connected user
     */
    public ArrayList<String> getUserEvents(){
        ArrayList<String> ans = new ArrayList<String>();
        String sql = "SELECT title"
                + " FROM usersEvents WHERE userName = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, curr_connected_username);
            ResultSet rs = pstmt.executeQuery();
            while ( rs.next() ) {
                ans.add( rs.getString("title") );
            }
            return ans;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * gets all the user names connected to the same given event as the connected user
     * @return - all the user names connected to the same given event as the connected user
     */
    public ArrayList<String> getEventsUsers(String eventName){
        ArrayList<String> ans = new ArrayList<String>();
        String sql = "SELECT userName"
                + " FROM usersEvents WHERE title = ? and userName <> ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, eventName);
            pstmt.setString(2, curr_connected_username);
            ResultSet rs = pstmt.executeQuery();
            while ( rs.next() ) {
                ans.add( rs.getString("userName") );
            }
            return ans;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * adds a new feedback to the database
     * @param eventID - a given event's id
     * @param feedbackedUserName - a given user name to be feedbacked
     * @param value - a given feedback value
     * @return - true if addition is successful. else - false
     */
    public boolean sendFeedback(String eventID, String feedbackedUserName, int value) {

            String sql = "INSERT INTO eventFeedbacks(eventID, feedbackeduserName, feedbackerUserName, value) VALUES(?,?,?,?)";

            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, eventID);
                pstmt.setString(2, feedbackedUserName);
                pstmt.setString(3, curr_connected_username);
                pstmt.setInt(4, value);

                //execute query
                pstmt.executeUpdate();
                return true;

            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
    }
}
