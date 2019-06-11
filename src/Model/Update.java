package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Update {

    private int updateId;
    private SimpleStringProperty publishedBy;
    private SimpleStringProperty eventName;
    private SimpleStringProperty description;
    private SimpleStringProperty publishedDate;
    private SimpleIntegerProperty index;

    public Update (int updateId, String publishedBy, String eventName, String description, String publishedDate, int index){
        this.description = new SimpleStringProperty(description);
        this.eventName = new SimpleStringProperty(eventName);
        this.updateId = updateId;
        this.publishedBy  = new SimpleStringProperty(publishedBy);
        this.publishedDate = new SimpleStringProperty(publishedDate);
        this.index = new SimpleIntegerProperty(index);
    }

    public SimpleStringProperty getPublishedDate() {
        return publishedDate;
    }

    public int getUpdateId() {
        return updateId;
    }

    public SimpleStringProperty getPublishedBy() {
        return publishedBy;
    }

    public SimpleStringProperty getDescription() {
        return description;
    }

    public SimpleIntegerProperty getIndex() {
        return index;
    }

    public SimpleStringProperty getEventName() {
        return eventName;
    }
}
