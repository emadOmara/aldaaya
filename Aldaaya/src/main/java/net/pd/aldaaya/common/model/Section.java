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
public class Section extends BaseEntity {

	/**
	 *
	 */

	private static final long serialVersionUID = 5105914722614237201L;

	@NotEmpty
	@JsonView(Views.Public.class)
	private String name;

	private String description;
	
	private boolean regonly;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "section")
	@JsonIgnore
	private List<Lesson> lessons;

	@ManyToOne
	private Account account;

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

	public List<Lesson> getLessons() {
		return lessons;
	}

	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public boolean isRegonly() {
		return regonly;
	}

	public void setRegonly(boolean regonly) {
		this.regonly = regonly;
	}

	
}
