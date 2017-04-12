package net.pd.aldaaya.integration;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import net.pd.aldaaya.business.ChapterService;
import net.pd.aldaaya.business.LessonService;
import net.pd.aldaaya.common.AldaayaConstants;
import net.pd.aldaaya.common.AldaayaException;
import net.pd.aldaaya.common.model.Chapter;
import net.pd.aldaaya.common.model.Lesson;
import net.pd.aldaaya.integration.jackson.Views;
import net.pd.aldaaya.integration.response.BaseResponse;
import net.pd.aldaaya.integration.response.ChapterResponse;
import net.pd.aldaaya.integration.response.LessonResponse;

@RestController()
@RequestMapping(path = "api/chapter")
public class ChapterController extends BaseController {

	@Autowired
	private ChapterService chapterService;

	@Autowired
	private LessonService lessonService;
	
	@RequestMapping(path = "/get/{id}", method = RequestMethod.GET)
	@JsonView(Views.Details.class)
	public BaseResponse get(@PathVariable("id") Long id) throws AldaayaException {

		ChapterResponse response = new ChapterResponse();
		handleNullID(id);

		Chapter chapter = chapterService.find(id);
		
		Lesson lesson = lessonService.find(id);
		response.setLesson(lesson);
		if(lesson!=null){
			response.setSection(lesson.getSection());
		}
		handleSuccessResponse(response, chapter);
		
		return response;

	}

	@RequestMapping(path = "/add", method = RequestMethod.POST)
	@JsonView(Views.Public.class)
	public BaseResponse addSection(@RequestBody Chapter chapter) throws AldaayaException {

		BaseResponse response = new BaseResponse();

		chapter = chapterService.save(chapter);
		handleSuccessResponse(response, chapter);

		return response;

	}

	@RequestMapping(path = "/edit", method = RequestMethod.POST)
	public BaseResponse editSection(@RequestBody Chapter chapter) throws AldaayaException {

		BaseResponse response = new BaseResponse();

		if (chapter.isNew()) {
			throw new AldaayaException(AldaayaConstants.ERROR_MSG_ID_CAN_T_BE_NULL);
		}
		chapterService.save(chapter);
		handleSuccessResponse(response, null);

		return response;

	}

	@RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
	public BaseResponse delete(@PathVariable("id") Long id) throws AldaayaException {

		BaseResponse response = new BaseResponse();
		chapterService.delete(id);
		handleSuccessResponse(response, null);

		return response;

	}

	@RequestMapping(path = "/list/{id}", method = RequestMethod.GET)
	@JsonView(Views.Public.class)
	public BaseResponse listChapters(@PathVariable("id") Long id) throws AldaayaException {

		ChapterResponse response = new ChapterResponse();

		List<Chapter> chapters = chapterService.getLessonChapters(id);
		
		Lesson lesson = lessonService.find(id);
		response.setLesson(lesson);
		if(lesson!=null){
			response.setSection(lesson.getSection());
		}
		
		handleSuccessResponse(response, chapters);
 
		return response;

	}
	
	@JsonView(Views.Public.class)
	@RequestMapping(path = "/search/{query}", method = RequestMethod.GET)
	public BaseResponse search(@PathVariable("query") String queryString) throws AldaayaException {

		BaseResponse response = new ChapterResponse();

		if(StringUtils.isEmpty(queryString)){
			throw new AldaayaException("No search criteria provided" );
		}
		
		List<Chapter> chapters = chapterService.search(queryString);
		
		 
		
		handleSuccessResponse(response, chapters);
 
		return response;

	}
	

}
