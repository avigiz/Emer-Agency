package Model;

public class Update {

    private int updateId;
    private Update beforeUpdate;
    private String publishedBy;
    private int eventID;
    private String description;
    private String publishedDate;

    public Update (int updateId, String publishedBy, int eventID, String description, String publishedDate){
//        this.beforeUpdate = beforeUpdate;
        this.description = description;
        this.eventID = eventID;
        this.updateId = updateId;
        this.publishedBy  = publishedBy;
        this.publishedDate = publishedDate;

    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public int getUpdateId() {
        return updateId;
    }

    public Update getBeforeUpdate() {
        return beforeUpdate;
    }

    public String getPublishedBy() {
        return publishedBy;
    }

    public int getEventConectedTo() {
        return eventID;
    }

    public String getDescription() {
        return description;
    }
}
