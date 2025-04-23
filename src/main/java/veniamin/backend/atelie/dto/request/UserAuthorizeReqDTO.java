package veniamin.backend.atelie.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserAuthorizeReqDTO {

    @Email
    private String email;

    @NotBlank
    private String password;
}
