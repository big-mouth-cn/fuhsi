package org.bigmouth.fuhsi.access.response;

import org.apache.asyncweb.common.HttpResponse;

public interface HttpResponseFactory {

	HttpResponse create(HttpResponseSource httpResponseSource);
}
