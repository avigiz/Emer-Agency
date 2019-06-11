package Model;

public class Update {

    private int updateId;
    private Update beforeUpdate;
    private String publishedBy;
    private int eventID;
    private String description;

    public Update (int updateId,String publishedBy,int eventID,String description){
//        this.beforeUpdate = beforeUpdate;
        this.description = description;
        this.eventID = eventID;
        this.updateId = updateId;
        this.publishedBy  = publishedBy;
    }

    public int getUpdateId() {
        return updateId;
    }

    public void setUpdateId(int updateId) {
        this.updateId = updateId;
    }

    public Update getBeforeUpdate() {
        return beforeUpdate;
    }

    public void setBeforeUpdate(Update beforeUpdate) {
        this.beforeUpdate = beforeUpdate;
    }

    public String getPublishedBy() {
        return publishedBy;
    }

    public void setPublishedBy(String publishedBy) {
        this.publishedBy = publishedBy;
    }

    public int getEventConectedTo() {
        return eventID;
    }

    public void setEventConectedTo(int eventID) {
        this.eventID = eventID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
