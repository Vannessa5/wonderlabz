package za.co.wonderbank.models;

import lombok.*;

@Data
@ToString
@Getter
@Setter
public class AuthenticationRequest {
    private String username;
    private String password;

    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
