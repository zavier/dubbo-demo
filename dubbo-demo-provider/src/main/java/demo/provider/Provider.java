package demo.provider;

import java.io.IOException;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import demo.service.DemoService;

public class Provider {

    public static void main(String[] args) throws IOException {
        // 服务实现
        DemoService demoService = new DemoServiceImpl();

        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("demo-provider");

        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig("zookeeper://127.0.0.1:2181");

        // 服务提供者协议配置(可不指定，使用默认配置)
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setPort(20881);
        protocol.setThreads(200);

        // 注意：ServiceConfig为重对象，内部封装了与注册中心的连接，以及开启服务端口

        // 服务提供者暴露服务配置
        ServiceConfig<DemoService> serviceConfig = new ServiceConfig<DemoService>(); // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
        serviceConfig.setApplication(application);
        // 多个注册中心可以用setRegistries()
        serviceConfig.setRegistry(registry);
        // 多个协议可以用setProtocols()
        serviceConfig.setProtocol(protocol);
        serviceConfig.setInterface(DemoService.class);
        serviceConfig.setRef(demoService);
        serviceConfig.setVersion("1.0.0");

        // 暴露及注册服务
        serviceConfig.export();

        System.out.println("provider is running");
        System.in.read();
    }
}
