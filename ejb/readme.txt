requirement:
ejb 3.x
wildfly 8.x

[ejb]
各种容器（jBoss,webLogic）有自己的ejb方式，包括部署与jndi访问


[jndi.properties]
java.naming.factory.initial=org.jboss.naming.remote.client.InitialContextFactory
java.naming.provider.url=http-remoting://localhost:8080
jboss.naming.client.ejb.context=true


[context.lookup]
Selected URI: java:global/ejb/CalculatorBean!org.jboss.as.quickstarts.ejb.remote.stateless.RemoteCalculator
context .lookup("ejb/CalculatorBean!org.jboss.as.quickstarts.ejb.remote.stateless.RemoteCalculator" )

[maven]
META-INF (default resources path): src/main/resources
