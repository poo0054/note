#前置条件
https://kubernetes.io/zh-cn/docs/setup/production-environment/container-runtimes/

> 每个节点安装docker,kubeadm ,kubelet,kubectl

> CentOS / RHEL / Fedora

```shell
cat <<EOF > /etc/yum.repos.d/kubernetes.repo
[kubernetes]
name=Kubernetes
baseurl=https://mirrors.aliyun.com/kubernetes/yum/repos/kubernetes-el7-x86_64/
enabled=1
gpgcheck=1
repo_gpgcheck=1
gpgkey=https://mirrors.aliyun.com/kubernetes/yum/doc/yum-key.gpg https://mirrors.aliyun.com/kubernetes/yum/doc/rpm-package-key.gpg
EOF
setenforce 0
yum install -y kubelet kubeadm kubectl
systemctl enable kubelet && systemctl start kubelet
#ps: 由于官网未开放同步方式, 可能会有索引gpg检查失败的情况, 这时请用 yum install -y --nogpgcheck kubelet kubeadm kubectl 安装
```

```shell
 
#初始化master节点

kubeadm init \
--pod-network-cidr=10.244.0.0/16 \
--image-repository registry.aliyuncs.com/google_containers  \
--cri-socket unix:///var/run/cri-dockerd.sock

kubeadm init \
--apiserver-advertise-address=10.0.2.15   \
--image-repository registry.aliyuncs.com/google_containers  \
--service-cidr=10.10.0.0/16 \
--cri-socket unix:///var/run/cri-dockerd.sock \
--pod-network-cidr=10.244.0.0/16
#解释:
# --apiserver-advertise-address  mast地址，API 服务器所公布的其正在监听的 IP 地址。如果未设置，则使用默认网络接口。
# image-repository 镜像地址
# pod-network-cidr k8s最小单元 可能是多个容器， 指明 pod 网络可以使用的 IP 地址段。如果设置了这个参数，控制平面将会为每一个节点自动分配 CIDRs。
# service-cidr 为服务的虚拟 IP 地址另外指定 IP 地址段
#--cri-socket 要连接的 CRI 套接字的路径
#   containerd	unix:///var/run/containerd/containerd.sock
#   CRI-O	unix:///var/run/crio/crio.sock
#   Docker Engine（使用 cri-dockerd）	unix:///var/run/cri-dockerd.sock
```

注意
kubeadm init时，这样下载可能不知道哪些下载失败，使用下面文件，命名master_images.sh,上传服务器进行下载，下载后再执行初始化
上传可能没有权限，使用chmod 700 master_images.sh 赋予执行权限
coredns/coredns需要单独下载,去docker.io找一个版本即可
> master_images.sh

```shell
#!/bin/bash
images=(
kube-apiserver:v1.27.1
kube-controller-manager:v1.27.1
kube-scheduler:v1.27.1
kube-proxy:v1.27.1
etcd:3.5.7-0
pause:3.9
coredns/coredns:V1.10.1
)
 
for imageName in ${images[@]} ; do
   docker pull dyrnq/$imageName
#  docker tag registry.cn-hangzhou.aliyuncs.com/google_containers/$imageName  registry.k8s.io/$imageName
docker tag dyrnq/$imageName registry.k8s.io/$imageName
done
#for imageName in ${images[@]} ; do
#    docker pull registry.cn-shenzhen.aliyuncs.com/google_containers/$imageName
##   docker tag registry.cn-hangzhou.aliyuncs.com/google_containers/$imageName  k8s.gcr.io/$imageName
#done
```

> 其中注释掉的如果没有指定阿里云，可以指定阿里云的镜像

> 初始化之后，系统提示会让一个普通用户执行

```shell
#上面提示，按照提示执行
mkdir -p $HOME/.kube
  sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
  sudo chown $(id -u):$(id -g) $HOME/.kube/config
  
#注意：最下有提示怎么加入从节点 使用自己的，如果秘钥过期，重新生成一个
kubeadm join 10.0.2.15:6443 --token dg115g.wqt0sfhzst15exla \
    --discovery-token-ca-cert-hash sha256:5b1abb958c78e922574156d3ae329ae8591fc3d1963d3e0af88b7a0607c63512
    
#中间一个文档，可以浏览器打开查询有哪些网络配置
https://kubernetes.io/zh/docs/concepts/cluster-administration/addons/
#打开提示给的网址，找到下面命令 可能打不开，就先下载下来保存
kubectl apply -f https://raw.githubusercontent.com/coreos/flannel/master/Documentation/kube-flannel.yml
#使用Flannel 部署一个网络：使用上面文件kube-flannel.yml 上传服务器中
kubectl apply -f  kube-flannel.yml
kubectl delete -f  kube-flannel.yml  #删除 
#如果一直下载失败，找的initContainers，可以在docker hub上面搜索flannel，找一个能下载的
这里是v0.11.0-arm64 找到这个 复制tages中的docker pull...后面的内容复制，把 image: quay.io/coreos/flannel:v0.11.0-arm64 中只要是amd64改成上面复制的内容
 
#查询所有名称空间的所有pod
kubectl get pods --all-namespaces
#获取名称空间
kubectl get ns 
#看见kube-flannel-ds-amd64-jtm98是允许状态 就说明网络准备好了
```

> 获取所有节点
> kubectl get nodes

> 使用上面的加入节点，在需要加入集群的机器上运行上面的话，加入集群，获取所有节点信息.

```shell
[root@k8s-node1 k8s]# kubectl get nodes
NAME        STATUS     ROLES    AGE   VERSION
k8s-node1   Ready      master   31m   v1.17.3
k8s-node2   Ready      <none>   95s   v1.17.3
k8s-node3   NotReady   <none>   13s   v1.17.3
```

> 监控pod进度

watch kubectl get pod -n kube-system -o wide

```shell
# 每个节点的flannel都是运行状态  集群搭建成功
kube-flannel-ds-amd64-bj4bl         1/1     Running   0          5m45s   10.0.2.4     k8s-node2   <none>           <none>
kube-flannel-ds-amd64-jtm98         1/1     Running   0          12m     10.0.2.15    k8s-node1   <none>           <none>
kube-flannel-ds-amd64-k45vb         1/1     Running   0          4m23s   10.0.2.5     k8s-node3   <none>           <none>
```

# 基本命令

> 创建一个tomcat

```shell
kubectl create deployment tomcat6 --image=tomcat:6.0.53-jre8
# 创建一个deployment名字为tomcat6 image（容器）为tomcat:6.0.53-jre8的服务
```

获取k8s所有资源 -o wide打印更多信息
> kubectl get all -o wide

发现在node-2节点创建了tomcat

> 暴露tomcat访问

```shell
kubectl expose deployment tomcat6 --port=80 --target-port=8080 --type=NodePort
# --target-port指的是pod容器里面的端口为8080，访问port的端口为80。 
# --type=NodePort是随机分配一个端口暴露出去，也可以使用--node-port指定一个端口给外部。从外部访问随机分配的端口，
# 就会访问到pod的8080端口，pod的80端口就会代理到tomcat的8080端口
```

> 动态扩容

```shell
kubectl get scale --replicas=3 deployment tomcat6
# 把tomcat6动态扩容3个副本，无论访问哪个node的指定端口都可以访问到
kubectl get svc -o wide 
# 查看服务信息
```

> 删除

```shell
kubectl delete deployment.apps/tomcat6
# 删除此次部署 
kubectl delete service/nginx-service
# 删除这个service
```

详细信息参考[kubectl](https://kubernetes.io/zh/docs/reference/kubectl/overview/)

## yaml

> 查看ymal格式

```shell
#查看帮助，最下面会提示--dry-run 测试
kubectl create deployment tomcat6 --image=tomcat:6.0.53-jre8 --help
#查看yaml
kubectl create deployment tomcat6 --image=tomcat:6.0.53-jre8 --dry-run  -o yaml
#把这段yaml保存到tomcat6.yaml
kubectl create deployment tomcat6 --image=tomcat:6.0.53-jre8 --dry-run  -o yaml > tomcat6.yaml
#使用yaml运行，和上面使用create命令创建是一样的
kubectl apply -f tomcat6.yaml 
 
#暴露服务和上面一样
kubectl expose deployment tomcat6 --port=80 --target-port=8080 --type=NodePort --dry-run  -o yaml > tomcat6.yaml
```

> 可以把deployment和service同时一起部署，把俩个yaml文件合并成为一个，中间使用---分割，kubectl apply -f ...
> 同时生效。并部署3台节点spec: replicas: 3

## ingress

> 运行kubectl apply -f ingress-controller.yaml添加ingress

添加ingress后就可以定义网络

```shell
#定义规则
apiVersion: extensions/v1beta1
kind: Ingress
metadata:
  name: web
spec:
  rules:
  - host: tomcat6.theangem.com
    http:
      paths:
      - backend:
          serviceName: tomcat6
          servicePort: 80
#解释
apiVersion: extensions/v1beta1 #api版本
kind: Ingress #清单类型
metadata: #元数据
  name: web    #ingress的名称
spec:      #规格
  rules:   #定义后端转发的规则
  - host: tomcat6.theangem.com    #通过域名进行转发
    http:
      paths:       
      - path:       #配置访问路径，如果通过url进行转发，需要修改；空默认为访问的路径为"/"
        backend:    #配置后端服务
          serviceName: tomcat6
          servicePort: 80
```

把这段yaml运行之后，在windows的hosts中配置入192.168.56.101（k8s节点）
tomcat6.theangem.com（通过域名进行转发）之后，就能直接通过tomcat6.theangem.com访问tomcat，就算其中之一宕机，也会自动调整。我们在上面复制了3台

#k8s可视化界面
运行kubernetes-dashboard.yaml文件
#不使用kubernetes自带的可视化界面，使用kubeSphere。完成以后的自动化流水线工作

##安装kubeSphere
> 需要先安装Helm,包管理器

```shell
#直接在本地运行
curl -LO https://git.io/get_helm.sh | bash
```

注意：这个文件在国外，下载不了参考下面[官网](https://github.com/kubesphere/ks-installer/blob/v2.1.1/README_zh.md)
或者使用get_helm.sh sh


> 我们将在一个 yaml 文件中添加服务帐户和集群绑定
> 创建指定文件并将以下内容复制到文件。helm-rbac.yaml

```shell
apiVersion: v1
kind: ServiceAccount
metadata:
  name: tiller
  namespace: kube-system
---
apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRoleBinding
metadata:
  name: tiller
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: ClusterRole
  name: cluster-admin
subjects:
  - kind: ServiceAccount
    name: tiller
    namespace: kube-system
```

> 创建丢失文件，再次执行helm init 即可
> echo "" > /root/.helm/repository/repositories.yaml


> 配置镜像，修改下面为指定的helm的对应版本
> helm init --service-account=tiller --tiller-image=sapcc/tiller:v2.16.3 --history-max 300




注意：如果有问题，删除titler重新安装
kubectl get -n kube-system secrets,sa,clusterrolebinding -o name|grep tiller|xargs kubectl -n kube-system delete
kubectl get all -n kube-system -l app=helm -o name|xargs kubectl delete -n kube-system

kubectl delete deployment tiller-deploy -n kube-system

# All-in-One 模式安装 KubeSphere

如果localhost.localdomain: conntrack is required.

> yum install conntrack-tools
