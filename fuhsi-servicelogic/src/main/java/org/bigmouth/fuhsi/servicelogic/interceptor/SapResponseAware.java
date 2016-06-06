package org.bigmouth.fuhsi.servicelogic.interceptor;

import org.bigmouth.nvwa.sap.SapResponse;

public interface SapResponseAware {

	void setSapResponse(SapResponse response);
}
