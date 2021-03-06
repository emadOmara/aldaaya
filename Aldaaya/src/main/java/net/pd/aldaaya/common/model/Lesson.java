package net.pd.aldaaya.common.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import net.pd.aldaaya.integration.jackson.Views;

@Entity
public class Lesson extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 210029733341510077L;

	@NotEmpty
	@JsonView(Views.Public.class)
	private String name;

	private String description;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "lesson")
	@JsonIgnore
	private List<Chapter> chapters;

	@ManyToOne
	private Section section;

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

	public List<Chapter> getChapters() {
		return chapters;
	}

	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

}
