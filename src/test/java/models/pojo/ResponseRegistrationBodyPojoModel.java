package models.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseRegistrationBodyPojoModel {
    private String id;
    private String token;
    private String error;
}
