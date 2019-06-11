package Model;

import Enums.EventStatus;

import java.util.List;
import java.util.Observable;
import java.util.TreeMap;

// TODO : AFTER EACH CHANGE - setChanged(); notifyAll();

public class Event extends Observable {

    private int eventID;
    private String title;
    private String publishedTime;
    private AUser postedDispatchUser;
    private TreeMap<Integer,Update> updates;
    private Enums.EventStatus EventStatus;
    private List<Category> categories;


    public Event(int eventID, String title, String publishedTime, AUser postedDispatchUser, TreeMap<Integer,Update> updates, EventStatus EventStatus, List<Category> categories){
        this.categories = categories;
        this.eventID = eventID;
        this.EventStatus = EventStatus;
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

    public EventStatus getEventStatus() {
        return EventStatus;
    }

    public void setEventStatus(EventStatus eventStatus) {
        this.EventStatus = eventStatus;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
