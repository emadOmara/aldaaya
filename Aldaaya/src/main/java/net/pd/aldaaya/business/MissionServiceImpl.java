package net.pd.aldaaya.business;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.pd.aldaaya.common.AldaayaException;
import net.pd.aldaaya.common.CommonUtil;
import net.pd.aldaaya.common.model.Mission;
import net.pd.aldaaya.dao.MissionDao;

@Service
@Transactional
public class MissionServiceImpl implements MissionService {

	Logger logger = LoggerFactory.getLogger(MissionServiceImpl.class);

	@Autowired
	private MissionDao missionDao;
	private final Pageable limit = new PageRequest(0, 1);;

   
  
	@Override
	public void delete(Long id) throws AldaayaException {

		try {
			missionDao.delete(id);
		} catch (Exception e) {
			throw new AldaayaException(e);
		}
	}

	@Override
	public Mission deleteAndGetNext(Long id) throws AldaayaException {
		Mission msg=null;
		try {
			missionDao.delete(id); 
			
			Page<Mission> page = missionDao.getNext(id,limit);
			if (page != null) {
				List<Mission> list = page.getContent();
				if(!CommonUtil.isEmpty(list)){
					msg= list.get(0);
					msg.setNewMessage(false);
					missionDao.save(msg);
				}
			}  
			return msg;
		} catch (Exception e) {
			throw new AldaayaException(e);
		}
	}

	 
 

	@Override
	public Mission save(Mission request) throws AldaayaException {
		try {

			request = missionDao.save(request);
			return request;
		} catch (Exception e) {
			throw new AldaayaException(e);
		}

	}

	@Override
	public List<Mission> list() throws AldaayaException {
		try {

			return (List<Mission>) missionDao.findAll();
		} catch (Exception e) {
			throw new AldaayaException(e);
		}
	} 

	@Override
	public Mission read(Long id) throws AldaayaException {
		try {

			Mission msg = missionDao.findOne(id);
			msg.setNewMessage(false);
			missionDao.save(msg);
			return msg;
		} catch (Exception e) {
			throw new AldaayaException(e);
		}
	}

	 
  

 
}
