package org.tommylii.testing.spring.cache.dao.implementation;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.tommylii.testing.spring.cache.dao.DummyDao;
import org.tommylii.testing.spring.cache.enums.CacheType;

/**
 * Created by David Alonso Garcia on 28/05/15.
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DummyDaoImpl implements DummyDao{

    private final CacheType cacheType;

    public DummyDaoImpl(final CacheType cacheType) {
        this.cacheType = cacheType;
    }

    @Cacheable
    @Override
    public long getSomething(final String key){
        return System.currentTimeMillis();
    }

    public CacheType getCacheType() {
        return cacheType;
    }
}
