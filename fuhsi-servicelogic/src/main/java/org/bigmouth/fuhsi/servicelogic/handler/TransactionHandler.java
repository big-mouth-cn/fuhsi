package org.bigmouth.fuhsi.servicelogic.handler;

public interface TransactionHandler<REQ, RESP> extends TransactionExecutor {

	void handle(REQ requestModel, RESP responseModel) throws ResourceNotFoundException,
			TransactionException;
}
