# Test case for Java Melody issue #665

The applications in this repository reproduce the problem described 
in [#665](https://github.com/javamelody/javamelody/issues/665):

 * The application _java-melody-application_ is a no-op war that simply 
   includes java-melody. 
 * The _jdbc-application_ is a war with a single 
   endpoint that simply opens and closes a jdbc connection. This 
   application does **not** include Java-Melody.

In order to produce the stack trace in the issue the following steps are need to be made:
 1. Set the system-property `javamelody.datasources` to `java:jboss/datasources/ExampleDS` 
    (this is only required as the default datasource in Wildfly does not follow Java 
    Melody's jdbc/ convention for datasources)
 1. Deploy java-melody-application and jdbc-application on Wildfly
 1. Open http://localhost:8080/jdbc-application-1.0.0-SNAPSHOT: This should suceed
 1. Undeploy java-melody-application and deploy it again
 1. Open http://localhost:8080/jdbc-application-1.0.0-SNAPSHOT: This should fail now 
    with a NPE in `ServletContextImpl` as shown in the stack trace
