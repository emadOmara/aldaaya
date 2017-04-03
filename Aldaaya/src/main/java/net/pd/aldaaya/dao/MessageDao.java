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

	List<Message> findByToAdminTrueOrderByCreationDateDesc();

}
