# seata-client-v210-error-test

- For https://github.com/apache/incubator-seata/issues/6886 and https://github.com/apache/shardingsphere/pull/33030 .
- Verified under `Ubuntu 22.04.4 LTS` with `Docker Engine` and `SDKMAN!`.

```bash
sdk install java 22.0.2-graalce
git clone git@github.com:linghengqian/seata-client-v210-error-test.git
cd ./seata-client-v210-error-test/
sdk use java 22.0.2-graalce
./mvnw clean test
```

- The Log as follows.

```shell
$ ./mvnw clean test
[INFO] Scanning for projects...
[INFO] 
[INFO] --------< io.github.linghengqian:seata-client-v210-error-test >---------
[INFO] Building seata-client-v210-error-test 1.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- clean:3.2.0:clean (default-clean) @ seata-client-v210-error-test ---
[INFO] Deleting /home/linghengqian/TwinklingLiftWorks/git/public/seata-client-v210-error-test/target
[INFO] 
[INFO] --- resources:3.3.1:resources (default-resources) @ seata-client-v210-error-test ---
[INFO] skip non existing resourceDirectory /home/linghengqian/TwinklingLiftWorks/git/public/seata-client-v210-error-test/src/main/resources
[INFO] 
[INFO] --- compiler:3.13.0:compile (default-compile) @ seata-client-v210-error-test ---
[INFO] No sources to compile
[INFO] 
[INFO] --- resources:3.3.1:testResources (default-testResources) @ seata-client-v210-error-test ---
[INFO] Copying 4 resources from src/test/resources to target/test-classes
[INFO] 
[INFO] --- compiler:3.13.0:testCompile (default-testCompile) @ seata-client-v210-error-test ---
[INFO] Recompiling the module because of changed source code.
[INFO] Compiling 4 source files with javac [debug target 22] to target/test-classes
[INFO] 
[INFO] --- surefire:3.2.5:test (default-test) @ seata-client-v210-error-test ---
[INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
[INFO] 
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running io.github.linghengqian.SeataTest
[ERROR] 2024-09-28 12:57:19.535 [main] o.a.s.config.ConfigurationFactory - failed to load non-spring configuration :not found service provider for : org.apache.seata.config.ConfigurationProvider
org.apache.seata.common.loader.EnhancedServiceNotFoundException: not found service provider for : org.apache.seata.config.ConfigurationProvider
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 8.742 s -- in io.github.linghengqian.SeataTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  11.383 s
[INFO] Finished at: 2024-09-28T12:57:23+08:00
[INFO] ------------------------------------------------------------------------
```
