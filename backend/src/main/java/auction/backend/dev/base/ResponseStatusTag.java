package auction.backend.dev.base;

import lombok.Getter;

@Getter
public enum ResponseStatusTag {
    OK(200,"OK"),
    NOT_FOUND(404, "NOT FOUND"),
    FORBIDDEN(403, "FORBIDDEN"),
    BAD_REQUEST(400,"BAD REQUEST"),
    SUCCESS(1200,"DEAL SUCCESS");

    private final int code;

    private final String message;

    ResponseStatusTag(int code,
                      String message){
        this.code=code;
        this.message=message;
    }
}
