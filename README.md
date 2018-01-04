# 多环境配置
在cloud-config-server中，
指通过本地读取配置文件：spring.profiles.active=native
指通过git读取配置文件：spring.profiles.active=git
上述可以通过在程序中加上启动参数指定如：--spring.profiles.active=git

在cloud-simple-service中，
通过这样的配置：spring.cloud.config.profile=${config.profile:test}
指定项目默认加载的是test环境的配置，如果在程序中加上启动参数指定具体环境的配置如指定生产环境的配置：
--config.profile=prd或者-Dconfig.profile=dev
注意：如果是通过命令行启动，则是 java -jar xxx.jar --config.profile=dev 或者是 java -jar -Dconfig.profile=dev xxx.jar

# 读取配置文件
仓库中的配置文件会被转换成web接口，访问可以参照以下的规则：
application表示微服务的名称，也就是上面请求URL中的cloud-simple-service；
profile表示对于的环境，即dev；label是可选参数，表示Git分支名称，因为Config Server默认从master分支作获取配置文件，
所以该参数可以省略，如果配置文件放在其他分支，就需要指定该参数值。

/{application}/{profile}[/{label}]
/{application}-{profile}.yml
/{label}/{application}-{profile}.yml
/{application}-{profile}.properties
/{label}/{application}-{profile}.properties

# 动态刷新-手动【只需要客户端配置】
需要客户端主动调用POST请求/refresh方法就可以刷新配置内容

1.让客户端支持/refresh方法：在pom.xml中添加以下依赖。spring-boot-starter-actuator是一套监控的功能，可以监控程序在运行时状态，其中就包括/refresh的功能
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
2.开启refresh机制， 需要给加载变量的类上面加载@RefreshScope注解，其它代码可不做任何改变，那么在客户端执行/refresh的时候就会更新此类下面的变量值
3.用postman发送【POST】如项目中的cloud-simple-service请求：http://localhost:8082/refesh

注意：
1.对于spring.profiles.active=native的配置，需要修改了参数后，重启配置中心服务，再调用POST请求/refresh方法就可以刷新配置内容
2.配置必须放在配置中心或者git,svn上，如果放在本地服务上，则不会生效

# 动态刷新-手动【spring-cloud-starter-bus-amqp】
参考：https://www.cnblogs.com/ityouknow/p/6931958.html

1.config服务增加配置cloud-config-server：
  pom.xml增加依赖：
        <!--消息总线-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-bus-amqp</artifactId>
        </dependency>
        <!--config-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-config-monitor</artifactId>
        </dependency>
  application.properties增加：      
        management.security.enabled=false
        
        spring.rabbitmq.host=localhost
        spring.rabbitmq.port=5672
        spring.rabbitmq.username=guest
        spring.rabbitmq.password=guest
        
2.客户端服务增加配置cloud-simple-service：
  pom.xml增加依赖：
          <!--消息总线-->
          <dependency>
              <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-starter-bus-amqp</artifactId>
          </dependency>
3.开启refresh机制， 需要给加载变量的类上面加载@RefreshScope注解，其它代码可不做任何改变  
4.用postman发送【POST】注意是向conf服务发送请求：http://localhost:8888/bus/refresh
  这样就能刷新所有的客户服务的配置
