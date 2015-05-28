package org.tommylii.testing.spring.cache.config;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.tommylii.testing.spring.cache.CacheTestMain;
import org.tommylii.testing.spring.cache.resolver.TestCacheResolver;

import java.util.concurrent.TimeUnit;

/**
 * Created by David Alonso Garcia on 28/05/15.
 */
@Configuration
@EnableCaching
@ComponentScan(basePackageClasses = CacheTestMain.class)
public class CacheTestConfig extends CachingConfigurerSupport {

    public static final String NORMAL_CACHE = "normalCache";
    public static final String TTL_CACHE = "ttlCache";
    public static final String TEST_CACHE_RESOLVER = "testCacheResolver";

    @Bean(name = TEST_CACHE_RESOLVER)
    @Override
    public CacheResolver cacheResolver(){
        return new TestCacheResolver(getNormalCache(),getTtlCache());
    }

    @Bean
    public CacheTestMain cacheTestMain(){
        return new CacheTestMain();
    }

    private Cache getNormalCache(){
        return new ConcurrentMapCache(NORMAL_CACHE,false);
    }

    private Cache getTtlCache(){
        return new GuavaCache(TTL_CACHE, CacheBuilder.newBuilder().expireAfterAccess(3, TimeUnit.SECONDS).build());
    }
}
