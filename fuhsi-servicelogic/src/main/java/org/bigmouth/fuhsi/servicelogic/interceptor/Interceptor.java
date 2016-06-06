package org.bigmouth.fuhsi.servicelogic.interceptor;

import org.bigmouth.fuhsi.servicelogic.TransactionInvocation;
import org.bigmouth.nvwa.codec.tlv.encoders.TLVBooleanArrayEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Interceptor {

	void intercept(TransactionInvocation invocation);

	public static final Interceptor END = new Interceptor() {

		private final Logger LOGGER = LoggerFactory.getLogger(TLVBooleanArrayEncoder.class);

		@Override
		public void intercept(TransactionInvocation invocation) {
			if (LOGGER.isDebugEnabled())
				LOGGER.debug("Interceptor execution chain is ended.");
		}
	};
}
