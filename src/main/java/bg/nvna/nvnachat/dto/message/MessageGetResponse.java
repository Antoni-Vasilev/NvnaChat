package bg.nvna.nvnachat.dto.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class MessageGetResponse {
    private String senderUsername;
    private String message;
    private String sentDate;
    private boolean itsMe;
}
