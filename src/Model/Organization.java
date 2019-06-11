package Model;

import java.util.List;

public class Organization {

    private List<AUser> users;
    private String organizationName;

    public Organization(List<AUser> users,String organizationName){
        this.organizationName = organizationName;
        this.users = users;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public List<AUser> getUsers() {
        return users;
    }

    public void setUsers(List<AUser> users) {
        this.users = users;
    }
}
