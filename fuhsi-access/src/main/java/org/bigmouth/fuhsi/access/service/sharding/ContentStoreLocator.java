package org.bigmouth.fuhsi.access.service.sharding;

import org.bigmouth.nvwa.contentstore.ContentStore;


public interface ContentStoreLocator {

	ContentStore lookup(int contentFlag) throws ContentStoreNotFoundException;
}
