# dubbo-demo

依赖关系

zavier --> greeting --> user

功能：打招呼
1. 如果参数为用户名称，则greeting.service直接返回client
2. 如果参数为用户ID，则greeting.service调用user.service查询用户名称后返回给clien




启动顺序
1. user.service   `mvn tomcat7:run`
2. greeting.service  `mvn tomcat7:run`
3. zavier.client  `mvn tomcat7:run`

访问链接(参数可以修改)：

http://localhost:8082/greeting/name?name=he

http://localhost:8082/greeting/uid?id=13



* 配置文件不需要修改