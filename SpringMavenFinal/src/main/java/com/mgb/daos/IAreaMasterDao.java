package com.mgb.daos;

import java.util.List;

import com.mgb.bo.AreaMaster;
import com.mgb.forms.AreaMasterDto;
import com.mgb.bo.AreaMaster;

public interface IAreaMasterDao {

	boolean addOrUpdateArea(AreaMaster bo) throws Exception;

	AreaMaster getAreaBo(String boId) throws Exception;

	boolean deleteArea(String boId)throws Exception;

	List<AreaMaster> listAllAreas() throws Exception;

	boolean checkForAreaDuplicate(AreaMasterDto areaMasterDto) throws Exception;
}
