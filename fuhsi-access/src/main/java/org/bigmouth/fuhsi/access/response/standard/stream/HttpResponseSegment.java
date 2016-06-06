package org.bigmouth.fuhsi.access.response.standard.stream;

import org.apache.mina.core.buffer.IoBuffer;
import org.bigmouth.fuhsi.access.service.ContentNotFoundException;


public interface HttpResponseSegment {

	IoBuffer getContent() throws ContentNotFoundException;
}
