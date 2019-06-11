package Model;

import Enums.AccountStatus;

import java.util.Observer;

public abstract class AUser implements Observer {

    protected String userName;
    protected String password;
    protected String email;
    protected Organization userOrganization;
    protected Enums.AccountStatus AccountStatus;

    public AUser(String userName, String password, String email, Organization organization, AccountStatus AccountStatus){
        this.userOrganization = organization;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.AccountStatus = AccountStatus;
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

    public AccountStatus getAccountStatus() {
        return AccountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.AccountStatus = accountStatus;
    }

    public void update(Observer o, Object arg) {
        // TODO : ADD UPDATE IMPLEMENTATION
    }
}
