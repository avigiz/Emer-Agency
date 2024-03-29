package Model;
import Enums.AccountStatus;

import java.util.Observable;

public class User extends AUser {

    private int rank;

    public User(String userName, String password, String email, Organization organization, AccountStatus accoutStatus,int rank) {
        super(userName, password, email, organization, accoutStatus);
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
