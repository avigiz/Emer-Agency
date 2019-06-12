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

    public String getPublishedDate() {
        return publishedDate.getValue();
    }

    public int getUpdateId() {
        return updateId;
    }

    public String getPublishedBy() {
        return publishedBy.getValue();
    }

    public String getDescription() {
        return description.getValue();
    }

    public int getIndex() {
        return index.getValue();
    }

    public String getEventName() {
        return eventName.getValue();
    }
}
