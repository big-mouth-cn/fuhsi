package org.bigmouth.fuhsi.servicelogic;

import org.bigmouth.fuhsi.servicelogic.handler.TransactionExecutor;
import org.bigmouth.nvwa.sap.SapRequest;
import org.bigmouth.nvwa.sap.SapResponse;


public interface MutableTransactionInvocation extends TransactionInvocation {

	void setRequestModel(Object model);

	void setResponseModel(Object model);

	void setTransactionHandler(TransactionExecutor transactionHandler);

	TransactionExecutor getTransactionHandler();

	SapResponse getResponse();

	SapRequest getRequest();
}
