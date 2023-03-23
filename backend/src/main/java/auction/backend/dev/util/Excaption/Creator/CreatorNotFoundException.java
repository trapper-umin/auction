package auction.backend.dev.util.Excaption.Creator;

public class CreatorNotFoundException extends RuntimeException{
    public CreatorNotFoundException(String message){
        super(message);
    }
}
