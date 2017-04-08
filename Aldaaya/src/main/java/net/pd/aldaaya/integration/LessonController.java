package net.pd.aldaaya.integration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.pd.aldaaya.business.LessonService;
import net.pd.aldaaya.business.SectionService;
import net.pd.aldaaya.common.AldaayaConstants;
import net.pd.aldaaya.common.AldaayaException;
import net.pd.aldaaya.common.model.Lesson;
import net.pd.aldaaya.common.model.Section;
import net.pd.aldaaya.integration.response.BaseResponse;
import net.pd.aldaaya.integration.response.LessonResponse;

@RestController()
@RequestMapping(path = "api/lesson")
public class LessonController extends BaseController {

	@Autowired
	private LessonService lessonService;
	@Autowired
	private SectionService sectionService;

	@RequestMapping(path = "/get/{id}", method = RequestMethod.GET)
	public BaseResponse get(@PathVariable("id") Long id) throws AldaayaException {

		LessonResponse response = new LessonResponse();
		handleNullID(id);

		Lesson lesson = lessonService.find(id);
		handleSuccessResponse(response, lesson);
  
		Section fetchedSection = sectionService.find(id);
		response.setSection(fetchedSection);
		 
		return response;

	}

	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public BaseResponse addSection(@RequestBody Lesson lesson) throws AldaayaException {

		BaseResponse response = new BaseResponse();

		lesson = lessonService.save(lesson);
		handleSuccessResponse(response, lesson);

		return response;

	}

	@RequestMapping(path = "/edit", method = RequestMethod.POST)
	public BaseResponse editSection(@RequestBody Lesson lesson) throws AldaayaException {

		BaseResponse response = new BaseResponse();

		if (lesson.isNew()) {
			throw new AldaayaException(AldaayaConstants.ERROR_MSG_ID_CAN_T_BE_NULL);
		}
		lessonService.save(lesson);
		handleSuccessResponse(response, null);

		return response;

	}

	@RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
	public BaseResponse deleteLesson(@PathVariable("id") Long id) throws AldaayaException {

		handleNullID(id);

		BaseResponse response = new BaseResponse();

		lessonService.delete(id);

		handleSuccessResponse(response, null);

		return response;

	}

	@RequestMapping(path = "/list/{id}", method = RequestMethod.GET)
	public BaseResponse listSectionLessons(@PathVariable("id") Long id) throws AldaayaException {

		handleNullID(id);
		LessonResponse response = new LessonResponse();

		List<Lesson> lessons = lessonService.getSectionLessons(id);
		
		Section fetchedSection = sectionService.find(id);
		 response.setSection(fetchedSection);
		
		 handleSuccessResponse(response, lessons);
		return response;

	}

}
