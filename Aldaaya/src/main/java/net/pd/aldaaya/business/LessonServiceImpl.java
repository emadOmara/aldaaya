package net.pd.aldaaya.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.pd.aldaaya.common.AldaayaException;
import net.pd.aldaaya.common.NullAwareBeanUtilsBean;
import net.pd.aldaaya.common.model.Lesson;
import net.pd.aldaaya.dao.LessonDao;

@Service
@Transactional
public class LessonServiceImpl implements LessonService {

	@Autowired
	private LessonDao lessonDao;
	@Autowired
	private NullAwareBeanUtilsBean beanUtilService;

	@Override
	public Lesson save(Lesson lesson) throws AldaayaException {
		try {
			if (lesson.isNew()) {
				return lessonDao.save(lesson);
			} else {// update
				Lesson fetchedLesson = lessonDao.findOne(lesson.getId());
				if (fetchedLesson == null) {
					throw new AldaayaException("can't find Lesson with specified id " + lesson.getId());
				}
				beanUtilService.copyProperties(fetchedLesson, lesson);
				return lessonDao.save(fetchedLesson);
			}
		} catch (Exception e) {
			throw new AldaayaException(e);
		}

	}

	@Override
	public void delete(Long id) throws AldaayaException {
		try {

			lessonDao.delete(id);
		} catch (Exception e) {
			throw new AldaayaException(e);
		}

	}

	@Override
	public List<Lesson> getSectionLessons(Long id) throws AldaayaException {
		try {
			return (List<Lesson>) lessonDao.findBySectionId(id);
		} catch (Exception e) {
			throw new AldaayaException(e);
		}
	}

	@Override
	public Lesson find(Long id) throws AldaayaException {

		try {

			return lessonDao.findOne(id);
		} catch (Exception e) {
			throw new AldaayaException(e);
		}

	}

}
