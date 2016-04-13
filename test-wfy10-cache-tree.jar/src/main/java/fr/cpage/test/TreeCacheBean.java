package fr.cpage.test;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.infinispan.Cache;
import org.infinispan.tree.TreeCacheFactory;

@Startup
@Singleton
public class TreeCacheBean {
  @Resource(lookup = "java:jboss/infinispan/cache/testContainer/testCache")
  private Cache<String, String> testBaseCache;

  @PostConstruct
  public void onStartup() {
    new TreeCacheFactory().createTreeCache(this.testBaseCache);
  }

}