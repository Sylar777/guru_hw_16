package models.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestRegistrationBodyPojoModel {
    private String email;
    private String password;
}
