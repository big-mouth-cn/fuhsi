package org.bigmouth.fuhsi.access.request.standard;

import org.apache.asyncweb.common.HttpRequest;
import org.bigmouth.fuhsi.access.request.IllegalAccessProtocolException;


public interface RequestParamSetExtractor {

	RequestParamSet extract(HttpRequest httpRequest) throws IllegalAccessProtocolException;
}
