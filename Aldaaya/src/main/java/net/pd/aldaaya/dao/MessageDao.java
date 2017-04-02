package net.pd.aldaaya.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.pd.aldaaya.common.model.Message;

@Repository
public interface MessageDao extends CrudRepository<Message, Long> {

	@Query("SELECT m FROM Message m where m.receiver.id=:id  order by m.creationDate desc")
	List<Message> getUserInbox(@Param("id") Long id);

	@Query("SELECT m FROM Message m where m.sender.id=:id order by m.creationDate desc")
	List<Message> getOutbox(@Param("id") Long id);

	@Query("SELECT m FROM Message m left outer join m.recipients r where r.newMessage=true and ( r.recipient.id=:id or m.sender=:id ) order by m.creationDate asc")
	List<Message> getNewUserMessages(@Param("id") Long id);

	List<Message> findByToAdminTrueOrderByCreationDateDesc();

	@Query("SELECT m FROM Message m  where m.toAdmin=true and m.newAdminMessage=true and m.sender.id=:userId order by m.creationDate asc")
	List<Message> getNewAdminMessagesForUser(@Param("userId") Long userId);

	@Query("SELECT m FROM Message m left outer join m.recipients r where (r.recipient.id=:userId and  m.sender.id=:adminId) or (m.sender.id=:userId and m.toAdmin=true) order by m.creationDate asc")
	List<Message> getAllMessagesBetweenAdminAndUser(@Param("adminId") Long adminId, @Param("userId") Long userId);

}