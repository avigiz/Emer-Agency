package Model;

import View.View;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        // TODO : CHECK IF CATEGORY ALREADY EXISTS, AND ADD IT IF NOT
        return false;
    }

    public ArrayList<Update> showEventUpdates(int EventID) {
            ArrayList<Update> ans = new ArrayList<Update>();
            String sql = "SELECT EventID"
                    + "FROM eventUpdates WHERE EventID = ?";
            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                // set the corresponding param
                pstmt.setString(1, "" + EventID);
                ResultSet rs = pstmt.executeQuery();
                while ( rs.next() ) {
                    Update currUpdate = new Update(rs.getInt("updateID"), rs.getString("publishedUser"),rs.getInt("eventID"),rs.getString("description"));
                    ans.add( currUpdate );
                }
                return ans;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
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

}
