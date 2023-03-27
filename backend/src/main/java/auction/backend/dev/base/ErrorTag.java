package auction.backend.dev.base;

import lombok.Getter;

@Getter
public enum ErrorTag {
    OK("200"),
    NOT_FOUND("404"),
    FORBIDDEN("403"),
    BAD_REQUEST("400");

    private final String code;
    ErrorTag(String code){
        this.code=code;
    }
}
