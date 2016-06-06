package org.bigmouth.fuhsi.access.response;

import java.util.UUID;

public interface HttpResponseExtFactory {

	HttpResponseExt create(HttpResponseSource httpResponseSource, UUID tid);
}
