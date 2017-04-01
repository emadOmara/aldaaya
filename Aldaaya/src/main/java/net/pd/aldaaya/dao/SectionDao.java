package net.pd.aldaaya.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.pd.aldaaya.common.model.Section;

@Repository
public interface SectionDao extends CrudRepository<Section, Long> {

	List<Section> findByAccountId(Long accountId);

}