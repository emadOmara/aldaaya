package net.pd.aldaaya.business;

import java.util.List;

import net.pd.aldaaya.common.AldaayaException;
import net.pd.aldaaya.common.model.Section;

public interface SectionService {

	List<Section> getAll() throws AldaayaException;

	void delete(Long id) throws AldaayaException;

	Section find(Long id) throws AldaayaException;

	Section save(Section section) throws AldaayaException;

	List<Section> getAll(Long accountId) throws AldaayaException;

}
