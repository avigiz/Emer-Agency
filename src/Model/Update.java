package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Update {

    private int updateId;
    private SimpleStringProperty publishedBy;
    private SimpleStringProperty eventName;
    private SimpleStringProperty description;
    private SimpleStringProperty publishedDate;
    private SimpleIntegerProperty ordering;

    public Update (int updateId, String publishedBy, String eventName, String description, String publishedDate, int ordering){
        this.description = new SimpleStringProperty(description);
        this.eventName = new SimpleStringProperty(eventName);
        this.updateId = updateId;
        this.publishedBy  = new SimpleStringProperty(publishedBy);
        this.publishedDate = new SimpleStringProperty(publishedDate);
        this.ordering = new SimpleIntegerProperty(ordering);
    }

    public String getPublishedDate() {
        return publishedDate.get();
    }

    public int getUpdateId() {
        return updateId;
    }

    public String getPublishedBy() {
        return publishedBy.get();
    }

    public String getDescription() {
        return description.get();
    }

    public int getOrdering() {
        return ordering.get();
    }

    public String getEventName() {
        return eventName.get();
    }
}
