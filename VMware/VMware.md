#设置网络

```shell
cd /etc/sysconfig/network-scripts
```

> 添加

```
BOOTPROTO="static"

IPADDR="192.168.56.10"
NETMASK="255.255.255.0"
GATEWAY="192.168.56.0"
DNS1="223.5.5.5"
DNS2="8.8.8.8"
```

> 重启

```shell
service network restart
```

#允许密码登录
