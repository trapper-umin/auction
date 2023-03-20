package auction.backend.dev.util.Excaption.Creator;

public class CreatorsNotFoundException extends RuntimeException{
    public CreatorsNotFoundException(String message){
        super(message);
    }
}
