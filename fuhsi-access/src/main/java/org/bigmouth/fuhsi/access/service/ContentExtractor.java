package org.bigmouth.fuhsi.access.service;

import org.bigmouth.nvwa.sap.ExtendedItem;

public interface ContentExtractor {

	byte[] extract(ExtendedItem extendedItem) throws ContentNotFoundException;
}
