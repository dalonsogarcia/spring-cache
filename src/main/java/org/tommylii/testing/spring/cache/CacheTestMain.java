package org.tommylii.testing.spring.cache;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;
import org.tommylii.testing.spring.cache.config.CacheTestConfig;
import org.tommylii.testing.spring.cache.dao.DaoManager;
import org.tommylii.testing.spring.cache.dao.DummyDao;
import org.tommylii.testing.spring.cache.enums.CacheType;

import javax.inject.Inject;

/**
 * Created by David Alonso Garcia on 28/05/15.
 */
public class CacheTestMain {


    public static final String DUMMY_KEY = "randomKey";
    @Inject
    private DaoManager daoManager;

    public static void main (final String[] args) throws InterruptedException {
        final ApplicationContext context = new AnnotationConfigApplicationContext(CacheTestConfig.class);
        final CacheTestMain cacheTestMain = context.getBean(CacheTestMain.class);
        cacheTestMain.run();
    }

    private void run() throws InterruptedException {
        final DummyDao dummyDaoNormalCache = daoManager.getDummyDao(CacheType.NORMAL);
        long call1 = dummyDaoNormalCache.getSomething(DUMMY_KEY);
        long call2 = dummyDaoNormalCache.getSomething(DUMMY_KEY);
        Assert.isTrue(call1 == call2, "Normal cache calls were not cached");
        final DummyDao dumnmyDaoTtlCache = daoManager.getDummyDao(CacheType.TTL);
        call1 = dumnmyDaoTtlCache.getSomething(DUMMY_KEY);
        call2 = dumnmyDaoTtlCache.getSomething(DUMMY_KEY);
        Assert.isTrue(call1 == call2, "Ttl cache calls were not cached or ttl expired");
        Thread.sleep(3500);
        call2 = dumnmyDaoTtlCache.getSomething(DUMMY_KEY);
        Assert.isTrue(call1 != call2, "Ttl cache call is the same, ttl didn't expire");
    }
}
