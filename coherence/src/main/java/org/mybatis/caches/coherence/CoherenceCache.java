package org.mybatis.caches.coherence;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.CacheException;

/**
 * Cache adapter for Coherencecache.
 *
 * @version $Id: CoherencecacheCache.java 5454 2012-10-27 15:06:35Z eduardo.macarron $
 */
public final class CoherenceCache implements Cache {

    /**
     * The {@code ReadWriteLock}.
     */
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    /**
     * The cache id.
     */
    private final String id;

    /**
     *
     *
     * @param id
     */
    public CoherenceCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
        if (CacheFactory.getCache(id) == null) {
            throw new IllegalStateException("Unable to get cache: " + id);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void clear() {
        this.getCache().clear();
    }

    /**
     * {@inheritDoc}
     */
    public String getId() {
        return this.id;
    }

    /**
     * {@inheritDoc}
     */
    public Object getObject(Object key) {
        try {
            return this.getCache().get(key);
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    /**
     * {@inheritDoc}
     */
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }

    /**
     * {@inheritDoc}
     */
    public int getSize() {
        try {
            return this.getCache().size();
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void putObject(Object key, Object value) {
        try {
            this.getCache().put(key, value);
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    /**
     * {@inheritDoc}
     */
    public Object removeObject(Object key) {
        try {
            Object obj = this.getObject(key);
            this.getCache().remove(key);
            return obj;
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    /**
     *
     * @return the named cached
     */
    private NamedCache getCache() {
        return CacheFactory.getCache(this.id);
    }

    /**
     * Returns the configuration for this cache.
     *
     * @return the configuration for this cache.
    private CacheConfiguration getCacheConfiguration() {
        return this.getCache().getCacheConfiguration();
    }
     */

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Cache)) {
            return false;
        }

        Cache otherCache = (Cache) obj;
        return this.id.equals(otherCache.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Coherence {"
                + this.id
                + "}";
    }

    // DYNAMIC PROPERTIES

    /**
     * Sets the time to idle for an element before it expires. Is only used if the element is not eternal.
     *
     * @param timeToIdleSeconds the default amount of time to live for an element from its last accessed or modified date
     */
/*    public void setTimeToIdleSeconds(long timeToIdleSeconds) {
        this.getCacheConfiguration().setTimeToIdleSeconds(timeToIdleSeconds);
    }

    *//**
     * Sets the time to idle for an element before it expires. Is only used if the element is not eternal.
     *
     * @param timeToLiveSeconds the default amount of time to live for an element from its creation date
     *//*
    public void setTimeToLiveSeconds(long timeToLiveSeconds) {
        this.getCacheConfiguration().setTimeToLiveSeconds(timeToLiveSeconds);
    }

    *//**
     * Sets the maximum objects to be held in memory (0 = no limit).
     *
     * @param maxElementsInMemory The maximum number of elements in memory, before they are evicted (0 == no limit)
     *//*
    public void setMaxEntriesLocalHeap(long maxEntriesLocalHeap) {
        this.getCacheConfiguration().setMaxEntriesLocalHeap(maxEntriesLocalHeap);
    }

    *//**
     * Sets the maximum number elements on Disk. 0 means unlimited.
     *
     * @param maxElementsOnDisk the maximum number of Elements to allow on the disk. 0 means unlimited.
     *//*
    public void setMaxEntriesLocalDisk(long maxEntriesLocalDisk) {
        this.getCacheConfiguration().setMaxEntriesLocalDisk(maxEntriesLocalDisk);
    }

    *//**
     * Sets the eviction policy. An invalid argument will set it to null.
     *
     * @param memoryStoreEvictionPolicy a String representation of the policy. One of "LRU", "LFU" or "FIFO".
     *//*
    public void setMemoryStoreEvictionPolicy(String memoryStoreEvictionPolicy) {
        this.getCacheConfiguration().setMemoryStoreEvictionPolicy(memoryStoreEvictionPolicy);
    }*/

}
