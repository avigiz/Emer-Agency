package Model;

public class Update {

    private int updateId;
    private Update beforeUpdate;
    private String publishedBy;
    private Event eventConectedTo;
    private String description;

    public Update (int updateId,Update beforeUpdate, String publishedBy,Event eventConectedTo,String description){
        this.beforeUpdate = beforeUpdate;
        this.description = description;
        this.eventConectedTo = eventConectedTo;
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

    public Event getEventConectedTo() {
        return eventConectedTo;
    }

    public void setEventConectedTo(Event eventConectedTo) {
        this.eventConectedTo = eventConectedTo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
