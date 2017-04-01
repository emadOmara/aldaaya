package net.pd.aldaaya.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.pd.aldaaya.common.model.Lesson;

@Repository
public interface LessonDao extends CrudRepository<Lesson, Long> {

	List<Lesson> findBySectionId(Long id);

}