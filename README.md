# 伏羲
伏羲，是一个分布式集群业务系统。它主要由三部分组成：
- access - 负责接收客户端请求，将请求转发到servicelogic处理后响应。
- servicelogic - 负责业务插件加载及执行。
- plugin - 插件，业务处理模块。以jar包的方式载入到servicelogic中。

## 示例
### 准备
使用示例前，你需要准备好以下环境：
- zookeeper
- memcached
- 空的数据库

### 启动Access
Java Application的方式执行
```
org.bigmouth.fuhsi.access.AccessServer
```

### 启动ServiceLogic
Java Application的方式执行

```
org.bigmouth.fuhsi.servicelogic.ServiceLogicServer
```
### 加载插件
你需要对工程fuhsi-servicelogic-plugin-demo进行编译打包成fuhsi-servicelogic-plugin-demo-1.0.0.jar，最后将该包放到./plugins目录下，看到ServiceLogic打印如下日志即说明插件加载成功。

```
2016-06-06 16:31:00,506 [INFO] (LoggingServiceListener.java:60) - SERVICE[demo] has created.
2016-06-06 16:31:00,535 [INFO] (LoggingServiceListener.java:43) - SERVICE[demo] has actived.
2016-06-06 16:31:00,536 [INFO] (LoggingServiceListener.java:84) - SERVICE[demo] 's status has changed,current status [active]
2016-06-06 16:31:00,537 [INFO] (LoggingServiceListener.java:60) - SERVICE[pair] has created.
2016-06-06 16:31:00,538 [INFO] (LoggingServiceListener.java:43) - SERVICE[pair] has actived.
2016-06-06 16:31:00,538 [INFO] (LoggingServiceListener.java:84) - SERVICE[pair] 's status has changed,current status [active]
2016-06-06 16:31:00,539 [INFO] (LoggingServiceListener.java:60) - SERVICE[seq] has created.
2016-06-06 16:31:00,539 [INFO] (LoggingServiceListener.java:43) - SERVICE[seq] has actived.
2016-06-06 16:31:00,540 [INFO] (LoggingServiceListener.java:84) - SERVICE[seq] 's status has changed,current status [active]
2016-06-06 16:31:00,543 [INFO] (LoggingPlugInListener.java:57) - PLUGIN[demo-demo,pair,seq] has created.
```
### 服务生效
> 已支持自动注册，不需要再人工配置。

插件成功加载到ServiceLogic系统后，还需要使服务生效才能使用，将需要生效的服务配置添加到服务配置（namecode）中，默认存储在ZooKeeper的节点路径：/FUHSI/servicelogic/cluster/namecodes。

使用客户端登录到ZooKeeper，以下两种使用方式：
- 自带zkCli

```
# ./zkCli.sh
# [zk: localhost:2181(CONNECTED) 2] get /FUHSI/servicelogic/cluster/namecodes
# [zk: localhost:2181(CONNECTED) 2] set /FUHSI/servicelogic/cluster/namecodes [{"plugInName":"access","plugInCode":0,"serviceName":"default","serviceCode":0},{"plugInName":"demo","plugInCode":1,"serviceName":"pair","serviceCode":0},{"plugInName":"demo","plugInCode":1,"serviceName":"demo","serviceCode":1}]
```

- nvwa-zkadmin [点击这里下载](http://big-mouth.cn/blog/361.html)

进入页面后找到namecode节点，进行修改并保存。

```
[{"plugInName":"access","plugInCode":0,"serviceName":"default","serviceCode":0},{"plugInName":"demo","plugInCode":1,"serviceName":"pair","serviceCode":0},{"plugInName":"demo","plugInCode":1,"serviceName":"demo","serviceCode":1}]
```
### 使用服务
通过浏览器访问 ==http://localhost:8888/demo/demo== 即调用了插件中的DemoService服务。