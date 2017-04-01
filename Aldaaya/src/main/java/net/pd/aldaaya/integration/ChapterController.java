package net.pd.aldaaya.integration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.pd.aldaaya.business.ChapterService;
import net.pd.aldaaya.common.AldaayaConstants;
import net.pd.aldaaya.common.AldaayaException;
import net.pd.aldaaya.common.model.Chapter;
import net.pd.aldaaya.integration.response.BaseResponse;

@RestController()
@RequestMapping(path = "api/chapter")
public class ChapterController extends BaseController {

	@Autowired
	private ChapterService chapterService;

	@RequestMapping(path = "/get/{id}", method = RequestMethod.GET)
	public BaseResponse get(@PathVariable("id") Long id) throws AldaayaException {

		BaseResponse response = new BaseResponse();
		handleNullID(id);

		Chapter chapter = chapterService.find(id);
		handleSuccessResponse(response, chapter);

		return response;

	}

	@RequestMapping(path = "/add", method = RequestMethod.POST)
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
	public BaseResponse listChapters(@PathVariable("id") Long id) throws AldaayaException {

		BaseResponse response = new BaseResponse();

		List<Chapter> chapters = chapterService.getLessonChapters(id);
		handleSuccessResponse(response, chapters);

		return response;

	}

}
