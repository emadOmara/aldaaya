package net.pd.aldaaya.integration;

import net.pd.aldaaya.common.AldaayaConstants;
import net.pd.aldaaya.common.AldaayaException;
import net.pd.aldaaya.integration.response.BaseResponse;

public abstract class BaseController {

    protected void handleSuccessResponse(BaseResponse response, Object result) {
	response.setStatus(AldaayaConstants.OK);
	response.setComment(AldaayaConstants.GENERAL_SUCCESS);
	response.setResult(result);
    }

    protected void handleNullID(Long... ids) throws AldaayaException {

	for (Long id : ids) {
	    if (id == null || id < 1) {
		throw new AldaayaException(AldaayaConstants.ERROR_MSG_ID_CAN_T_BE_NULL);
	    }

	}

    }
}
