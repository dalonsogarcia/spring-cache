package org.tommylii.testing.spring.cache.dao;

import org.tommylii.testing.spring.cache.enums.CacheType;

/**
 * Created by David Alonso Garcia on 28/05/15.
 */
public interface DaoManager {
    DummyDao getDummyDao(CacheType cacheType);
}
