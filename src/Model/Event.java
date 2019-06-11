package Model;

import java.util.List;
import java.util.Locale;
import java.util.TreeMap;

public class Event {

    private int eventID;
    private String title;
    private String publishedTime;
    private AUser postedDispatchUser;
    private TreeMap<Integer,Update> updates;
    private eventStatus eventStatus;
    private List<Category> categories;


    public Event(int eventID,String title,String publishedTime, AUser postedDispatchUser,TreeMap<Integer,Update> updates,eventStatus eventStatus,List<Category> categories){
        this.categories = categories;
        this.eventID = eventID;
        this.eventStatus = eventStatus;
        this.postedDispatchUser = postedDispatchUser;
        this.publishedTime = publishedTime;
        this.title = title;
        this.updates = updates;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishedTime() {
        return publishedTime;
    }

    public void setPublishedTime(String publishedTime) {
        this.publishedTime = publishedTime;
    }

    public AUser getPostedDispatchUser() {
        return postedDispatchUser;
    }

    public void setPostedDispatchUser(AUser postedDispatchUser) {
        this.postedDispatchUser = postedDispatchUser;
    }

    public TreeMap<Integer, Update> getUpdates() {
        return updates;
    }

    public void setUpdates(TreeMap<Integer, Update> updates) {
        this.updates = updates;
    }

    public Model.eventStatus getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(Model.eventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
