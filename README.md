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


