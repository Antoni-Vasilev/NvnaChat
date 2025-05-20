package bg.nvna.nvnachat.repository;

import bg.nvna.nvnachat.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, String> {

    @Query("select m from Message as m where m.createdAt > :createdDate order by m.createdAt")
    List<Message> findMessagesByCreatedAtAfter(Date createdDate);


    @Query("select m from Message as m where m.createdAt < :createdDate order by m.createdAt")
    List<Message> findMessagesByCreatedAtBefore(Date createdDate);
}
