package bg.nvna.nvnachat.service.impl;

import bg.nvna.nvnachat.model.Session;
import bg.nvna.nvnachat.model.User;
import bg.nvna.nvnachat.repository.SessionRepository;
import bg.nvna.nvnachat.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;

    @Autowired
    public SessionServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Session createSession(User user) {
        return sessionRepository.save(new Session(null, user));
    }

    @Override
    public Session findSessionById(String sessionId) {
        return sessionRepository.findSessionById(sessionId);
    }
}
