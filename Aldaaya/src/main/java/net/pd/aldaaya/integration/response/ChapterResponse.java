package net.pd.aldaaya.integration.response;

import net.pd.aldaaya.common.model.Lesson;
import net.pd.aldaaya.common.model.Section;

public class ChapterResponse extends BaseResponse {

	/**
	 *
	 */
	private static final long serialVersionUID = 1395918555510719966L;
	 

	private Section section;
	private Lesson lesson;
	public ChapterResponse() {
		super();
	}

	public ChapterResponse(int status, String comment) {
		super(status, comment);
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

	 
 
}
 