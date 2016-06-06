package org.bigmouth.fuhsi.servicelogic.interceptor;

import org.bigmouth.nvwa.sap.SapRequest;

public interface SapRequestAware {

	void setSapRequest(SapRequest request);
}
