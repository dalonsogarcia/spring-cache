package org.tommylii.testing.spring.cache.dao.implementation;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.tommylii.testing.spring.cache.dao.DaoManager;
import org.tommylii.testing.spring.cache.dao.DummyDao;
import org.tommylii.testing.spring.cache.enums.CacheType;

import javax.inject.Inject;

/**
 * Created by David Alonso Garcia on 28/05/15.
 */
@Component(value = DaoManagerImpl.DAO_MANAGER)
public class DaoManagerImpl implements DaoManager{
    public static final String DAO_MANAGER = "daoManager";

    @Inject
    private ApplicationContext context;

    @Override
    public DummyDao getDummyDao(CacheType cacheType){
        return context.getBean(DummyDao.class,cacheType);
    }

}
