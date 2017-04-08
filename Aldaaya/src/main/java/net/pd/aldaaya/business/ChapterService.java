package net.pd.aldaaya.business;

import java.util.List;

import net.pd.aldaaya.common.AldaayaException;
import net.pd.aldaaya.common.model.Chapter;

public interface ChapterService {

	Chapter save(Chapter chapter) throws AldaayaException;

	void delete(Long id) throws AldaayaException;

	Chapter find(Long id) throws AldaayaException;

	List<Chapter> getLessonChapters(Long id) throws AldaayaException;

	List<Chapter> search(String queryString)throws AldaayaException;

}
