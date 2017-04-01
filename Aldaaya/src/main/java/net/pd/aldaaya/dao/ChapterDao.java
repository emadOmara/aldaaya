package net.pd.aldaaya.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.pd.aldaaya.common.model.Chapter;

@Repository
public interface ChapterDao extends CrudRepository<Chapter, Long> {

	List<Chapter> findByLessonId();

}