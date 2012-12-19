package org.mybatis.caches.coherence;

import org.apache.ibatis.cache.decorators.LoggingCache;

/**
 * {@code LoggingCache} adapter for Coherence.
 *
 * @version $Id: LoggingCoherencecache.java  Akhil Kodali $
 */
public final class LoggingCoherencecache extends LoggingCache {

    public LoggingCoherencecache(final String id) {
        super(new CoherenceCache(id));
    }

}
