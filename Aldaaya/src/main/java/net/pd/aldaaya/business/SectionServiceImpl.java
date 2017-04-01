package net.pd.aldaaya.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.pd.aldaaya.common.AldaayaException;
import net.pd.aldaaya.common.NullAwareBeanUtilsBean;
import net.pd.aldaaya.common.model.Section;
import net.pd.aldaaya.dao.SectionDao;

@Service
@Transactional
public class SectionServiceImpl implements SectionService {

	@Autowired
	private SectionDao sectionDao;

	@Autowired
	private NullAwareBeanUtilsBean beanUtilService;

	@Override
	public Section save(Section section) throws AldaayaException {

		try {
			if (section.isNew()) {
				return sectionDao.save(section);
			} else {// update
				Section fetchedSection = sectionDao.findOne(section.getId());
				if (fetchedSection == null) {
					throw new AldaayaException("can't find section with specified id " + section.getId());
				}
				beanUtilService.copyProperties(fetchedSection, section);
				return sectionDao.save(fetchedSection);
			}
		} catch (Exception e) {
			throw new AldaayaException(e);
		}

	}

	@Override
	public void delete(Long id) throws AldaayaException {
		try {
			sectionDao.delete(id);
		} catch (Exception e) {
			throw new AldaayaException(e);
		}

	}

	@Override
	public List<Section> getAll() throws AldaayaException {
		try {
			return (List<Section>) sectionDao.findAll();
		} catch (Exception e) {
			throw new AldaayaException(e);
		}
	}

	@Override
	public List<Section> getAll(Long accountId) throws AldaayaException {
		try {
			return (List<Section>) sectionDao.findByAccountId(accountId);
		} catch (Exception e) {
			throw new AldaayaException(e);
		}
	}

	@Override
	public Section find(Long id) throws AldaayaException {

		try {
			return sectionDao.findOne(id);
		} catch (Exception e) {
			throw new AldaayaException(e);
		}

	}

}
