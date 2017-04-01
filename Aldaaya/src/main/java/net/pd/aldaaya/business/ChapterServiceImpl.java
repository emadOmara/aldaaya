package net.pd.aldaaya.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.pd.aldaaya.common.AldaayaException;
import net.pd.aldaaya.common.model.Chapter;
import net.pd.aldaaya.dao.ChapterDao;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {

	@Autowired
	private ChapterDao chapterDao;

	@Override
	public Chapter save(Chapter chapter) throws AldaayaException {
		try {
			return chapterDao.save(chapter);

		} catch (Exception e) {
			throw new AldaayaException(e);
		}

	}

	@Override
	public void delete(Long id) throws AldaayaException {
		try {

			chapterDao.delete(id);
		} catch (Exception e) {
			throw new AldaayaException(e);
		}

	}

	@Override
	public List<Chapter> getLessonChapters(Long id) throws AldaayaException {
		try {
			return (List<Chapter>) chapterDao.findByLessonId(id);
		} catch (Exception e) {
			throw new AldaayaException(e);
		}
	}

	@Override
	public Chapter find(Long id) throws AldaayaException {

		try {

			return chapterDao.findOne(id);
		} catch (Exception e) {
			throw new AldaayaException(e);
		}

	}

}
