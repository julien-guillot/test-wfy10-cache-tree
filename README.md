# test-wfy10-cache-tree
Test Wildfly 10 Infinispan Tree Cache API

* start wildfly 10 standalone mode
* create cache:
    
    WILDFLY_HOME/bin/jboss-cli.sh --connect --file=install.cli
    
* deploy with maven:

    mvn clean install wildfly:deploy
