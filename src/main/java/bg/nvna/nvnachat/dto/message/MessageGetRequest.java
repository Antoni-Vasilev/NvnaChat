package bg.nvna.nvnachat.dto.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class MessageGetRequest {
    private boolean getOldMessages;
    private Date currentDate;
}
