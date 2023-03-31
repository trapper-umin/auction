package auction.backend.dev.base;

import lombok.Getter;

@Getter
public enum EntityTag {
    CREATOR("CREATOR","The developer of this site"),
    PERSON("USER","The user of this site"),
    ITEM("LOT","Auction item"),
    DEAL("DEAL","Abstract operation of moving an item to a new owner and charging him money");

    private final String name;

    private final String description;

    EntityTag(String name,
              String description){
        this.name=name;
        this.description=description;
    }
}