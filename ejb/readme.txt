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


[class]
com.ejb3inaction.actionbazaar.buslogic.ShippingRequestProcessorMDB	监控MQ队列，消费消息
com.ejb3inaction.actionbazaar.client.ShippingRequestJMSProducer		MQ队列消息生成
注：只在jboss4.x可以通过
com.ejb3inaction.actionbazaar.client.QueueSender 					消息发送器

org.jboss.as.quickstarts.ejb.remote.stateful.CounterBean
org.jboss.as.quickstarts.ejb.remote.stateless.CalculatorBean


com.ejb3inaction.actionbazaar.buslogic.Manager	注入ejb
com.ejb3inaction.actionbazaar.buslogic.OtherBean 注入ejb

