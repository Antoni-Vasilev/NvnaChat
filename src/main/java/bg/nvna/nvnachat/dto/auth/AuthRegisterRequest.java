package bg.nvna.nvnachat.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class AuthRegisterRequest {
    private String username;
    private String email;
    private String password;
}
