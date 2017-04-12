package net.pd.aldaaya.common.model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonView;

import net.pd.aldaaya.integration.jackson.Views;

@Entity
public class Chapter extends BaseEntity {

	/**
	 *
	 */

	private static final long serialVersionUID = 5105914722614237201L;

	@JsonView(Views.Public.class)
	@NotEmpty
	private String name;
	
	@JsonView(Views.Details.class)
	private String description;
	
	@JsonView(Views.Public.class)
	private String shortDesc;

	@JsonView(Views.Public.class)
	@Lob
	private String image;
	
	@ManyToOne
	private Lesson lesson;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getShortDesc() {
		if(!StringUtils.isEmpty(description)&&description.length()>50){
			return description.substring(0, 51);
		}
		return description;
	}

	 
	
	

}
