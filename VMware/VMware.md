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

# 更新yum

[阿里云](https://developer.aliyun.com/mirror/centos?spm=a2c6h.13651102.0.0.50641b113cOAjU)

# 扩容

扩容，就是将磁盘加在linux系统上，然后实现能够存储文件，磁盘加上容量后，并不能够马上使用，需要对磁盘进行重新分配，主要有两种方法，第一种非
LVM方式，使用lsblk命令查看根分区，就是根目录直接挂载在磁盘分区上，第二种 LVM方式，磁盘分区后，还需要创建为LVM(
逻辑卷管理) ，等于多创建了一层虚拟层，这样更加方便管理磁盘

```shell
#查看当前磁盘的容量
lsblk
#将原有分区删除后，再将容量进行重新分配
fdisk /dev/sda

[root@station Desktop]# fdisk /dev/sda
Command (m for help): p      # 查看分区信息
Disk /dev/sda: 64.4 GB, 64424509440 bytes, 125829120 sectors
... 省略一部分
   Device Boot      Start         End      Blocks   Id  System
/dev/sda1            2048    62914559    31456256   83  Linux
Command (m for help): d      # 删除分区，记住千万不要保存，不要使用 w 命令
Selected partition 1         # 因为只有一个分区，所以无需指定删除哪个分区
Partition 1 is deleted
Command (m for help): n   # new 一个分区
Partition type:
   p   primary (0 primary, 0 extended, 4 free)
   e   extended
Select (default p): p     # 选择主分区，e 代表扩展分区
Partition number (1-4, default 1):                 # 默认敲击回车
First sector (2048-125829119, default 2048):       # 默认敲击回车   
Last sector, +sectors or +size{K,M,G} (2048-125829119, default 125829119):  +50G
Partition 1 of type Linux and of size 50 GiB is set
Command (m for help): w    # 保存
	
	
	
[root@station Desktop]# partprobe    # 如果无效请重启
[root@station Desktop]# xfs_growfs /dev/sda1 # xfs 同步文件系统 先要先查看是是否是xfs类型
[root@station Desktop]# df -h
Filesystem      Size  Used Avail Use% Mounted on
/dev/sda1        50G  8.5G   42G  16% /
devtmpfs        899M     0  899M   0% /dev
tmpfs           914M  8.0K  914M   1% /dev/shm
tmpfs           914M   17M  897M   2% /run
tmpfs           914M     0  914M   0% /sys/fs/cgroup
```