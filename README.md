# ����
���ˣ���һ���ֲ�ʽ��Ⱥҵ��ϵͳ������Ҫ����������ɣ�
- access - ������տͻ������󣬽�����ת����servicelogic�������Ӧ��
- servicelogic - ����ҵ�������ؼ�ִ�С�
- plugin - �����ҵ����ģ�顣��jar���ķ�ʽ���뵽servicelogic�С�

## ʾ��
### ׼��
ʹ��ʾ��ǰ������Ҫ׼�������»�����
- zookeeper
- memcached
- �յ����ݿ�

### ����Access
Java Application�ķ�ʽִ��
```
org.bigmouth.fuhsi.access.AccessServer
```

### ����ServiceLogic
Java Application�ķ�ʽִ��

```
org.bigmouth.fuhsi.servicelogic.ServiceLogicServer
```
### ���ز��
����Ҫ�Թ���fuhsi-servicelogic-plugin-demo���б�������fuhsi-servicelogic-plugin-demo-1.0.0.jar����󽫸ð��ŵ�./pluginsĿ¼�£�����ServiceLogic��ӡ������־��˵��������سɹ���

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
### ������Ч
> ��֧���Զ�ע�ᣬ����Ҫ���˹����á�

����ɹ����ص�ServiceLogicϵͳ�󣬻���Ҫʹ������Ч����ʹ�ã�����Ҫ��Ч�ķ���������ӵ��������ã�namecode���У�Ĭ�ϴ洢��ZooKeeper�Ľڵ�·����/FUHSI/servicelogic/cluster/namecodes��

ʹ�ÿͻ��˵�¼��ZooKeeper����������ʹ�÷�ʽ��
- �Դ�zkCli

```
# ./zkCli.sh
# [zk: localhost:2181(CONNECTED) 2] get /FUHSI/servicelogic/cluster/namecodes
# [zk: localhost:2181(CONNECTED) 2] set /FUHSI/servicelogic/cluster/namecodes [{"plugInName":"access","plugInCode":0,"serviceName":"default","serviceCode":0},{"plugInName":"demo","plugInCode":1,"serviceName":"pair","serviceCode":0},{"plugInName":"demo","plugInCode":1,"serviceName":"demo","serviceCode":1}]
```

- nvwa-zkadmin [�����������](http://big-mouth.cn/blog/361.html)

����ҳ����ҵ�namecode�ڵ㣬�����޸Ĳ����档

```
[{"plugInName":"access","plugInCode":0,"serviceName":"default","serviceCode":0},{"plugInName":"demo","plugInCode":1,"serviceName":"pair","serviceCode":0},{"plugInName":"demo","plugInCode":1,"serviceName":"demo","serviceCode":1}]
```
### ʹ�÷���
ͨ����������� ==http://localhost:8888/demo/demo== �������˲���е�DemoService����