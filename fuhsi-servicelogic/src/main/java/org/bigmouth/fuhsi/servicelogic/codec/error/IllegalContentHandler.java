package org.bigmouth.fuhsi.servicelogic.codec.error;

public interface IllegalContentHandler {

	void handle(String flag, byte[] content);
}
