# dubbo-demo


使用前最好现在 dubbo-demo-api 下执行  `mvn install` 将其安装到本地maven 仓库中

1. 在 dubbo-demo-provider下执行 `mvn tomcat7:run` 启动对应容器
2. 在 dubbo-demo-consumer下执行 `mvn tomcat7:run` 启动对应容器
3. 访问 http://localhost:8081/sayHello?name=12 ，参数name可修改


*配置文件不需要修改*
