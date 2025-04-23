package veniamin.backend.atelie.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterReqDTO {

    @NotBlank
    @Pattern(regexp = "(?i)[a-zа-я]*", message = "Имя должно состоять из букв")
    private String name;

    @NotBlank
    @Pattern(regexp = "(?i)[a-zа-я]*", message = "Фамилия должна состоять из букв")
    private String surname;

    @NotBlank
    @Size(min = 8, max = 24, message = "Пароль должен быть больше 8 символов, но меньше 24")
    private String password;

    @Email
    private String email;
}
