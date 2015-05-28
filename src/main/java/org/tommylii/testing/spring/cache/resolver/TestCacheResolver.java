package org.tommylii.testing.spring.cache.resolver;

import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.CacheResolver;
import org.tommylii.testing.spring.cache.dao.implementation.DummyDaoImpl;
import org.tommylii.testing.spring.cache.enums.CacheType;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by David Alonso Garcia on 28/05/15.
 */
public class TestCacheResolver implements CacheResolver{

    private final Cache normalCache;
    private final Cache ttlCache;

    public TestCacheResolver(final Cache normalCache, final Cache ttlCache) {
        this.normalCache = normalCache;
        this.ttlCache = ttlCache;
    }

    @Override
    public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> context) {
        final DummyDaoImpl dummyDao = (DummyDaoImpl) context.getTarget();
        if (dummyDao.getCacheType().equals(CacheType.NORMAL)){
            return Arrays.asList(normalCache);
        }
        return Arrays.asList(ttlCache);
    }
}
