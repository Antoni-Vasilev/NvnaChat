package bg.nvna.nvnachat.service;

import bg.nvna.nvnachat.model.Session;
import bg.nvna.nvnachat.model.User;

public interface SessionService {
    Session createSession(User user);

    Session findSessionById(String sessionId);
}
