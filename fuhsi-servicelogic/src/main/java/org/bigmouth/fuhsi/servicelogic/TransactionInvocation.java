package org.bigmouth.fuhsi.servicelogic;

import org.bigmouth.fuhsi.servicelogic.handler.TransactionExecutor;
import org.bigmouth.nvwa.sap.SapRequest;
import org.bigmouth.nvwa.sap.SapResponse;


public interface TransactionInvocation {

	Object getRequestModel();

	Object getResponseModel();

	InvocationContext getContext();

	TransactionExecutor getTransactionHandler();

	SapRequest getRequest();

	SapResponse getResponse();

	// TODO:
	// boolean isExtendable();

	// TODO:
	// boolean isRecordable();
}
