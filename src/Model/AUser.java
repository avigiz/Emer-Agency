package Model;

public abstract class AUser {

    protected String userName;
    protected String password;
    protected String email;
    protected Organization userOrganization;
    protected accoutStatus accoutStatus;

    public AUser(String userName,String password,String email,Organization organization,accoutStatus accoutStatus){
        this.userOrganization = organization;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.accoutStatus = accoutStatus;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Organization getUserOrganization() {
        return userOrganization;
    }

    public void setUserOrganization(Organization userOrganization) {
        this.userOrganization = userOrganization;
    }

    public Model.accoutStatus getAccoutStatus() {
        return accoutStatus;
    }

    public void setAccoutStatus(Model.accoutStatus accoutStatus) {
        this.accoutStatus = accoutStatus;
    }
}
