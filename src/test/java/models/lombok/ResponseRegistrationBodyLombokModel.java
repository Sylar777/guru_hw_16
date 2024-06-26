package models.lombok;

import lombok.Data;

@Data
public class ResponseRegistrationBodyLombokModel {
    private String id;
    private String token;
    private String error;
}
