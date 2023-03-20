package MyProjects.Eshop.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import MyProjects.Eshop.Model.MessageSend;
import MyProjects.Eshop.Model.User;

@Repository
public interface MessageSendRepository extends JpaRepository<MessageSend,Integer>{
List<MessageSend> findAll();
List<MessageSend> findByTo(User user);
List<MessageSend> findByFrom(User user);
}
