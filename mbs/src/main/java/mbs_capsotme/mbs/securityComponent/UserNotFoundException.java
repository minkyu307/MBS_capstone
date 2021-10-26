package mbs_capsotme.mbs.securityComponent;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String userName){
        super(userName+"NotFoundException");
    }
}
