package Model;


import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import View.View;
import java.sql.*;
import java.util.ArrayList;
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
        // TODO : CHECK IF CATEGORY ALREADY EXISTS, AND ADD IT IF NOT
        return false;
    }


    public List<Category> showCategory() throws SQLException {
        String sql = "SELECT * FROM categories";
        ResultSet m_results;
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            m_results = pstmt.executeQuery(sql);
            List<Category> ans = new ArrayList<>();
            int i = 0;
            while (i < m_results.getFetchSize()){
                String tmp =(m_results.getString(i));
                ans.add(new Category(tmp));
                i++;
            }
            return ans;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    public String addEventUpdate(Object value) {
        String insertSQL1 = "INSERT INTO eventUpdates (eventID,updateID,publishedDate,publishedUser,index)"
                +   " VALUES(?,?,?,?,?)";
        String selectSQL1 = "SELECT * FROM eventUpdates WHERE eventID = ?";
        String eventName = (String)value;
        try (Connection conn = this.connect();
             PreparedStatement insrert1 = conn.prepareStatement(insertSQL1);
            ) {
            insrert1.setString(1,eventName);
            Random rnd = new Random();
            Integer random = rnd.nextInt(100);
            insrert1.setString(2,random.toString());
            insrert1.setString(3,Calendar.getInstance().toString());
            insrert1.setString(4,curr_connected_username);
            insrert1.setInt(5,selectSQL1.length()+1);
            return "ok";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return "notOK";
        }
    }

    public ArrayList<Update> showEventUpdates(String title) {
            ArrayList<Update> ans = new ArrayList<Update>();
            String sql = "SELECT title"
                    + "FROM eventUpdates WHERE EventID = ?";
            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                // set the corresponding param
                pstmt.setString(1, "" + title);
                ResultSet rs = pstmt.executeQuery();
                while ( rs.next() ) {
                    Update currUpdate = new Update(rs.getInt("updateID"), rs.getString("publishedUser"),
                            rs.getString("eventName"),rs.getString("publishedDate"), rs.getString("description"));
                    ans.add( currUpdate );
                }
                return ans;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
    }


//    String sqlusersEvents = "CREATE TABLE IF NOT EXISTS usersEvents (\n"
//            + "	eventID integer PRIMARY KEY\n"
//            + "	userName text PRIMARY KEY\n"
//            + "	authorization text NOT NULL\n"
//            + "	title text NOT NULL\n"
//            + ");";
//public Event(int eventID, String title, String publishedTime, String userName, TreeMap<Integer,Update> updates, EventStatus EventStatus, List<Category> categories){
    //return all the events of the current user.
    public ArrayList<String> getUserEvent(){
        ArrayList<String> ans = new ArrayList<String>();
        String sql = "SELECT title"
                + "FROM usersEvents WHERE userName = ?";
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


    public void updateEvent() {

    }

    public void addEventFeedback() {

    }

    public void sendFeedback(int eventID, String feedbackedUserName, int value) {

            String sql = "INSERT INTO eventFeedbacks(eventID, feedbackeduserName, feedbackerUserName, value) VALUES(?,?,?,?,?,?)";

            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, eventID);
                pstmt.setString(2, feedbackedUserName);
                pstmt.setString(3, curr_connected_username);
                pstmt.setInt(4, value);

                //execute query
                pstmt.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    }

    public void addEventUpdate() {

    }

    public void showCategories() {

    }

}
