package net.pd.aldaaya.common.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import net.pd.aldaaya.integration.jackson.Views;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3167680378625461610L;
    @Id
    @GeneratedValue
    @JsonView(Views.Base.class)
    protected Long id;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    @JsonIgnore
    @Transient
    public boolean isNew() {
	return id == null ? true : id == 0;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (id == null ? 0 : id.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	BaseEntity other = (BaseEntity) obj;
	if (id == null) {
	    if (other.id != null) {
		return false;
	    }
	} else if (!id.equals(other.id)) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }
}