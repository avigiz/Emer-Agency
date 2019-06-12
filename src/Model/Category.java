package Model;

import javafx.beans.property.SimpleStringProperty;

public class Category {

    private SimpleStringProperty categoryName;

    public Category(String name){
        this.categoryName = new SimpleStringProperty(name);
    }

    public SimpleStringProperty getcategoryName() {
        return categoryName;
    }
}
