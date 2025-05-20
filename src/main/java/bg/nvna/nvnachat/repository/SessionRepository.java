package bg.nvna.nvnachat.repository;

import bg.nvna.nvnachat.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, String> {

    Session findSessionById(String id);
}
