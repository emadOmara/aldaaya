package net.pd.aldaaya.business;

import java.util.List;

import net.pd.aldaaya.common.AldaayaException;
import net.pd.aldaaya.common.model.Message;
import net.pd.aldaaya.common.model.Mission;

public interface MissionService {


	List<Mission> list()throws AldaayaException;



	void delete(Long id)throws AldaayaException;



	Mission save(Mission request)throws AldaayaException;

	Mission read(Long id) throws AldaayaException;



	Mission deleteAndGetNext(Long id) throws AldaayaException;

}
