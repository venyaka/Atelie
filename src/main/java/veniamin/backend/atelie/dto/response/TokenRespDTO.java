package veniamin.backend.atelie.dto.response;

import lombok.Data;

@Data
public class TokenRespDTO {

    private String accessToken;

    private String refreshToken;

}
