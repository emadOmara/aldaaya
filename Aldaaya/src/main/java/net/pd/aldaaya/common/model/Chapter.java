package net.pd.aldaaya.common.model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Section entity
 *
 * @author Emad
 *
 */
@Entity
public class Chapter extends BaseEntity {

	/**
	 *
	 */

	private static final long serialVersionUID = 5105914722614237201L;

	@NotEmpty
	private String name;

	private String description;

	@Lob
	private String image;
	@ManyToOne
	// @JsonIgnore
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

}
