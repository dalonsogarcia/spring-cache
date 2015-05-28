package org.tommylii.testing.spring.cache.enums;

import org.tommylii.testing.spring.cache.config.CacheTestConfig;

/**
 * Created by David Alonso Garcia on 28/05/15.
 */
public enum CacheType {

    NORMAL(CacheTestConfig.NORMAL_CACHE),TTL(CacheTestConfig.TTL_CACHE);

    private final String cacheName;

    CacheType(final String cacheName) {
        this.cacheName = cacheName;
    }

    public String getCacheName() {
        return cacheName;
    }
}
