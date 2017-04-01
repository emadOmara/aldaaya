package net.pd.aldaaya.business;

import java.util.List;

import net.pd.aldaaya.common.AldaayaException;
import net.pd.aldaaya.common.model.Lesson;

public interface LessonService {

	Lesson save(Lesson lesson) throws AldaayaException;

	void delete(Long id) throws AldaayaException;

	Lesson find(Long id) throws AldaayaException;

	List<Lesson> getSectionLessons(Long id) throws AldaayaException;

}
