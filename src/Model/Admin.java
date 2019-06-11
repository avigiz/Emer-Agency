package Model;

public class Admin extends AUser {
    public Admin(String userName, String password, String email, Organization organization, Model.accoutStatus accoutStatus) {
        super(userName, password, email, organization, accoutStatus);
    }
}
