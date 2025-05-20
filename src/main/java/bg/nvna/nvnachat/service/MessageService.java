package bg.nvna.nvnachat.service;

import bg.nvna.nvnachat.model.Message;

import java.util.Date;
import java.util.List;

public interface MessageService {

    void sendMessage(Message message);

    List<Message> getOldMessages(Date currentDate);

    List<Message> getNewMessages(Date currentDate);
}
