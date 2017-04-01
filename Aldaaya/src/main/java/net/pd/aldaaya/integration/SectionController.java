package net.pd.aldaaya.integration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.pd.aldaaya.business.SectionService;
import net.pd.aldaaya.common.AldaayaConstants;
import net.pd.aldaaya.common.AldaayaException;
import net.pd.aldaaya.common.model.Section;
import net.pd.aldaaya.integration.response.BaseResponse;

@RestController()
@RequestMapping(path = "api/section")
public class SectionController extends BaseController {

	@Autowired
	private SectionService sectionService;

	@RequestMapping(path = "/get/{id}", method = RequestMethod.GET)
	public BaseResponse get(@PathVariable("id") Long id) throws AldaayaException {

		BaseResponse response = new BaseResponse();
		handleNullID(id);

		Section account = sectionService.find(id);
		handleSuccessResponse(response, account);

		return response;

	}

	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public BaseResponse addSection(@RequestBody Section section) throws AldaayaException {

		BaseResponse response = new BaseResponse();

		section = sectionService.save(section);
		handleSuccessResponse(response, section);

		return response;

	}

	@RequestMapping(path = "/edit", method = RequestMethod.POST)
	public BaseResponse editSection(@RequestBody Section section) throws AldaayaException {

		BaseResponse response = new BaseResponse();

		if (section.isNew()) {
			throw new AldaayaException(AldaayaConstants.ERROR_MSG_ID_CAN_T_BE_NULL);
		}
		sectionService.save(section);
		handleSuccessResponse(response, null);

		return response;

	}

	@RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
	public BaseResponse deleteAccount(@PathVariable("id") Long id) throws AldaayaException {

		handleNullID(id);
		BaseResponse response = new BaseResponse();
		sectionService.delete(id);
		handleSuccessResponse(response, null);

		return response;

	}

	@RequestMapping(path = "/list", method = RequestMethod.GET)
	public BaseResponse listAll() throws AldaayaException {

		BaseResponse response = new BaseResponse();

		List<Section> sections = sectionService.getAll();
		handleSuccessResponse(response, sections);

		return response;

	}

	@RequestMapping(path = "/list/{id}", method = RequestMethod.GET)
	public BaseResponse listAssignedSections(@PathVariable("id") Long id) throws AldaayaException {

		handleNullID(id);
		BaseResponse response = new BaseResponse();

		List<Section> sections = sectionService.getAll(id);
		handleSuccessResponse(response, sections);

		return response;

	}

}
