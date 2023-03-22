package auction.backend.dev.util.Excaption.Creator;

public class CreatorNotCreatedException extends RuntimeException{
    public CreatorNotCreatedException(String msg){
        super(msg);
    }
}
