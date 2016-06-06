package org.bigmouth.fuhsi.servicelogic.codec;

public interface ContentDecoder {

	Object decode(byte[] source, Class<?> template);// throws DecodingException?
}
