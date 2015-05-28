package org.tommylii.testing.spring.cache.resolver;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.AbstractCacheResolver;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.tommylii.testing.spring.cache.dao.implementation.DummyDaoImpl;
import org.tommylii.testing.spring.cache.enums.CacheType;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by David Alonso Garcia on 28/05/15.
 */
public class TestCacheResolver extends AbstractCacheResolver{

    public TestCacheResolver(final CacheManager cacheManager) {
        super(cacheManager);
    }

    @Override
    public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> context) {
        final DummyDaoImpl dummyDao = (DummyDaoImpl) context.getTarget();
        if (dummyDao.getCacheType().equals(CacheType.NORMAL)){
            return Arrays.asList(this.getCacheManager().getCache(CacheType.NORMAL.getCacheName()));
        }
        return Arrays.asList(this.getCacheManager().getCache(CacheType.TTL.getCacheName()));
    }

    @Override
    protected Collection<String> getCacheNames(CacheOperationInvocationContext<?> context) {
        return this.getCacheManager().getCacheNames();
    }
}
