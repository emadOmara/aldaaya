package net.pd.aldaaya.integration.response;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.pd.aldaaya.common.model.Account;
import net.pd.aldaaya.common.model.Section;

public class LessonResponse extends BaseResponse {

	/**
	 *
	 */
	private static final long serialVersionUID = 1395918555510719966L;
	 
	private Section section ;

	public LessonResponse() {
		super();
	}

	public LessonResponse(int status, String comment) {
		super(status, comment);
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	 

	 

	 
}

 