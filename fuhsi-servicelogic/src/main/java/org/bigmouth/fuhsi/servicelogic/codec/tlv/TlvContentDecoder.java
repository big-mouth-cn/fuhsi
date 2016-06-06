package org.bigmouth.fuhsi.servicelogic.codec.tlv;

import org.bigmouth.fuhsi.servicelogic.codec.ContentDecoder;
import org.bigmouth.nvwa.codec.tlv.TLVDecoder;
import org.bigmouth.nvwa.codec.tlv.TLVDecoderProvider;

public class TlvContentDecoder implements ContentDecoder {

	private final TLVDecoderProvider tlvDecoderProvider;

	public TlvContentDecoder(TLVDecoderProvider tlvDecoderProvider) {
		this.tlvDecoderProvider = tlvDecoderProvider;
	}

	@Override
	public Object decode(byte[] source, Class<?> template) {
		if (null == source)
			throw new NullPointerException("source");
		if (null == template)
			throw new NullPointerException("template");

		TLVDecoder<Object> tlvObjectDecoder = tlvDecoderProvider.getObjectDecoder();
		return tlvObjectDecoder.codec(source, template);
	}
}
