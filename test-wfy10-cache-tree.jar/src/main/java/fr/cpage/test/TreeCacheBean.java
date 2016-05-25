package fr.cpage.test;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.infinispan.Cache;
import org.infinispan.cache.impl.AbstractDelegatingCache;
import org.infinispan.tree.TreeCacheFactory;

@Startup
@Singleton
public class TreeCacheBean {
  @Resource(lookup = "java:jboss/infinispan/cache/testContainer/testCache")
  private Cache<String, String> testBaseCache;

  @PostConstruct
  public void onStartup() {
    debugInfo();
    new TreeCacheFactory().createTreeCache(this.testBaseCache);
  }

  private void debugInfo() {
    try {
      final AbstractDelegatingCache delegating = (AbstractDelegatingCache) this.testBaseCache;
      final Cache delegate = delegating.getDelegate();
      System.out.println("*****************");
      System.out.println("Cache Class:" + delegate.getClass());
      System.out.println("Batching: " + this.testBaseCache.getCacheConfiguration()
          .invocationBatching()
          .enabled());
      System.out.println("Max entries: " + this.testBaseCache.getCacheConfiguration().eviction().maxEntries());
      if (delegate instanceof org.infinispan.cache.impl.CacheImpl) {
        final org.infinispan.cache.impl.CacheImpl cacheImpl = (org.infinispan.cache.impl.CacheImpl) delegate;
        System.out.println("Batch Container:" + cacheImpl.getBatchContainer());
      }
      System.out.println("*****************");
    } catch (final Throwable t) {
    }
  }

}
