# 网络

```shell
#关闭防火墙
systemctl stop firewalld
systemctl disable firewalld
#关闭selinx
sed -i 's/enforcing/disabled/' /etc/selinux/config
setenforce 0
#关闭swap
swapoff -a #临时
sed -ri 's/.*swap.*/#&/' /etc/fstab #永久
```

# 下载

> 要使用二进制下载在x86-64 Linux上安装最新的minikube稳定版本：

```shell
curl -LO https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64
sudo install minikube-linux-amd64 /usr/local/bin/minikube
```

## 启动

```shell
minikube start
```

# dashboard

## 下载yaml

```shell
curl  https://raw.githubusercontent.com/kubernetes/dashboard/v2.7.0/aio/deploy/recommended.yaml > dashboard.yaml
```

## 外部访问

> 创建示例用户

```yaml
apiVersion: v1
kind: ServiceAccount
metadata:
name: admin-user
namespace: kubernetes-dashboard
```

在大多数情况下，在使用 或任何其他常用工具预配群集后，群集中已存在 。我们可以使用它并仅为我们的 . 如果不存在，则需要先创建此角色并手动授予所需的权限。

```yaml
apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRoleBinding
metadata:
  name: admin-user
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: ClusterRole
  name: cluster-admin
subjects:
  - kind: ServiceAccount
    name: admin-user
    namespace: kubernetes-dashboard
```

> 获取持有者令牌

```shell
kubectl -n kubernetes-dashboard create token admin-user
```

## 标签

设置master节点
> kubectl label node minikube node-role.kubernetes.io/master=master

# 动态扩容

```shell
kubectl get scale --replicas=3 deployment tomcat6
# 把tomcat6动态扩容3个副本，无论访问哪个node的指定端口都可以访问到
kubectl get svc -o wide 
# 查看服务信息
```