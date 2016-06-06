package org.bigmouth.fuhsi.servicelogic.codec.json;

import org.bigmouth.fuhsi.servicelogic.codec.ContentEncoder;
import org.bigmouth.nvwa.utils.JsonHelper;

public class JsonContentEncoder implements ContentEncoder {

	@Override
	public byte[] encode(Object source) {
		if (null == source)
			throw new NullPointerException("source");
		return JsonHelper.convert2bytes(source);
	}
}
