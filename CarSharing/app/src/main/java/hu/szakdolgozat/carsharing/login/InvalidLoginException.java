package hu.szakdolgozat.carsharing.login;

/**
 * Created by Szabo Peter on 2016. 11. 14..
 */

public class InvalidLoginException extends Exception {

    private String reason;

    public InvalidLoginException(String reason) {
        this.reason = reason;
    }

    @Override
    public String getMessage() {
        return reason;
    }
}
