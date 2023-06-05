package com.mindtree.course.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import net.sf.ehcache.config.CacheConfiguration;

@EnableJpaRepositories(basePackages = "com.mindtree.course.repository")
@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {
	@Bean
	public CacheManager cacheManager() {
		return new EhCacheCacheManager(ehCacheManager());
		// return new EhCacheCacheManager(cacheManagerFactory().getObject());
	}

//	@Bean
//	public EhCacheManagerFactoryBean cacheManagerFactory() {
//		EhCacheManagerFactoryBean bean = new EhCacheManagerFactoryBean();
//		bean.setConfigLocation(new ClassPathResource("ehcache.xml"));
//		bean.setShared(true);
//		return bean;
//	}

	@Bean
	public net.sf.ehcache.CacheManager ehCacheManager() {
		CacheConfiguration cache = new CacheConfiguration();
		cache.setName("courseCache");
		cache.setMemoryStoreEvictionPolicy("LRU");
		cache.setMaxEntriesLocalHeap(1000);
		cache.setTimeToLiveSeconds(60);

		net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
		config.addCache(cache);
		return net.sf.ehcache.CacheManager.newInstance(config);
	}

}
