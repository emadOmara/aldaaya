package net.pd.aldaaya.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.pd.aldaaya.common.model.ContactUs;
import net.pd.aldaaya.common.model.Mission;

@Repository
public interface MissionDao extends CrudRepository<Mission, Long> {

	@Query("SELECT m FROM  Mission m where m.id>:id")
	Page<Mission>  getNext(@Param("id") Long id, Pageable limit);


}
