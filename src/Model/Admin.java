package Model;

import Enums.AccountStatus;

import java.util.Observable;

public class Admin extends AUser {
    public Admin(String userName, String password, String email, Organization organization, AccountStatus accountStatus) {
        super(userName, password, email, organization, accountStatus);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
