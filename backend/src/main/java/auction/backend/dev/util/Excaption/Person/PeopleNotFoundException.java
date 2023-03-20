package auction.backend.dev.util.Excaption.Person;

public class PeopleNotFoundException extends RuntimeException{

    public PeopleNotFoundException(String message){
        super(message);
    }
    public PeopleNotFoundException(){}
}
