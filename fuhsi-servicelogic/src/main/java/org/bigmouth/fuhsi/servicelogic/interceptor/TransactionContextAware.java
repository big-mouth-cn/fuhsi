package org.bigmouth.fuhsi.servicelogic.interceptor;

import org.bigmouth.fuhsi.servicelogic.InvocationContext;

public interface TransactionContextAware {

	void setTransactionContext(InvocationContext context);
}
