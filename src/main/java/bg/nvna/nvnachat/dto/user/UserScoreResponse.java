package bg.nvna.nvnachat.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserScoreResponse {

    private String username;
    private int score;
    private List<UserScoreListResponse> scores;
}
