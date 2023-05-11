# JAVA WEB

## 1、基本概念

### 1.1 前言

web开发：

- web，网页的意思，www.baidu.com

- 静态web

    - html，css，
    - 提供给所有人看的数据始终不会发生变化

- 动态web

-
    - 淘宝，几乎是所有的网站。

    - 提供给所有人看的数据始终会发生变化，每个人在不同的时间，在不同的地点看到的信息各不相同。
    - 技术栈：Servlet/JSP，ASP，PHP

在Java中，动态web资源开发的技术统称为JavaWeb

### 1.2 web应用程序

web应用程序：可以提供浏览器访问的程序；

- a.html、b.html.....多个web资源，这些web资源可以被外界访问，对外界提供服务；
- 访问到的任何一个页面或者资源，都存在于世界某一个角落的计算机上
- URL
- 这个统一的web资源会被放在同一个文件夹下，web应用程序-->Tomcat：服务器
- 一个web应用由多部分组成（静态web，动态web）
    - html，css，js
    - jsp，servlet
    - Java程序
    - jar包
    - 配置文件（Properties）

- web应用程序编写完毕后，若想提供给外界访问，需要一个服务器来统一管理

### 1.3 静态web

- *.htm, *.html，这些都是网页的后缀，如果服务器上一直存在这些东西，我们就可以直接读取

<img src="JavaWeb\1.png"  />

- 静态web存在的缺点
    - Web页面无法动态更新，所有用户看到都是同一页面
        - 轮播图，点击特效：微动态
        - JavaScript[实际开发中它用的最多
        - VBScript
    - 它无法和数据库交互（数据无法持久化，用户无法交互）

### 1.4 动态web

页面会动态展示："Web的页面展示效果因人而异";

![](JavaWeb\2.png)

缺点：

- 假如服务器的动态web资源出现错误，我们需要重新编写我们的**后台程序**，重新发布；
    - 停机维护

优点：

- Web页面无法动态更新，所有用户看到都不是同一页面
- 它可以与数据库交互（数据持久化：注册，商品信息，用户信息）

## 2、web服务器

### 2.1 技术讲解

ASP：

- 微软：国内最早流行
- 在HTML中嵌入了VB的脚本，ASP+COM；
- 在ASP开发中，基本一个页面都有几千行的业务代码，页面及其混乱
- 维护成本高

```html
<html>
	<head>
	</head>
    <body>
        <h1>
        </h1>
        <h1>
            <%
               System.out.println("hello");
              %>
        </h1>
    </body>
</html>
```

PHP：

- PHP开发速度很快，功能强大，跨平台，代码简单
- 无法承载大访问量的情况（局限性）

JSP/Servlet：

- B/S：浏览和服务器
- C/S：客户端和服务器
    - sun公司主推的B/S架构
    - 基于java语言的（所有大公司，或者一些开源的组件，都是java写的）
    - 可以承载高并发、高可用、高性能问题带来的影响
    - 语法像ASP

### 2.2 web服务器

服务器是一种被动的操作，用来处理用户的请求和给用户一些响应的信息；

**IIS:**

微软的;WIndows自带

**Tomcat:**

omcat 服务器是一个免费的开放源代码的Web 应用服务器，属于轻量级应用[服务器](https://baike.baidu.com/item/服务器)
，在中小型系统和并发访问用户不是很多的场合下被普遍使用，是开发和调试JSP 程序的首选。对于一个初学者来说，可以这样认为，当在一台机器上配置好Apache
服务器，可利用它响应[HTML](https://baike.baidu.com/item/HTML)（[标准通用标记语言](https://baike.baidu.com/item/标准通用标记语言/6805073)
下的一个应用）页面的访问请求。实际上Tomcat是Apache 服务器的扩展，但运行时它是独立运行的，所以当你运行tomcat
时，它实际上作为一个与Apache 独立的进程单独运行的。

## 3、Tomcat

- Tomcat的端口号：8888(本机设置后)
- mysql默认端口号：3306
- http：80
- https:443

### 3.1 配置

更改端口号（D:\apache-tomcat-9.0.31\conf\server.xml\86行）：

```xml
<Connector port="8888" protocol="HTTP/1.1"
	connectionTimeout="20000"
           redirectPort="8443" />
```

配置主机名称（D:\apache-tomcat-9.0.31\conf\server.xml\152行） (还需修改本地hosts文件)

- 默认的主机名为：localhost-》127.0.0.1
- 默认网站应用存放的位置为：webapps

```xml
<Host name="www.test.com"  appBase="webapps"
            unpackWARs="true" autoDeploy="true">
```

#### **高难度面试题**

请你谈谈网站是如何进行访问的？

1. 输入一个域名；回车

2. 检查本机的C:\Windows\System32\drivers\etc\hosts配置文件下有没有这个域名的映射

   1.有：直接返回对应的IP地址，这个地址中，有我们需要的web程序，可以直接访问

   ```java
   127.0.0.1		www.test.com
   ```

   2.没有：去DNS服务器找，找到返回，找不到报错

### 3.2 发布一个web网站

- 将自己写的网站，放到服务器(Tomcat)中指定的web应用的文件夹（webapps）下，就可以访问了网站应该有的结构

**目录:**

```
--webapps : Tomcat服务器的web目录
    -ROOT
    -kritostudy : 网站的目录名
    	-WEB-INF
    		-classes : java程序
            	-lib : web应用所依赖的jar包
    		-web.xml : 网站配置文件
    	-index.html默认的首页
        -static
        	-css
                -style.css
           	-js
            -img
```

## 4、HTTP

### 4.1 什么是HTTP

HTTP（超文本传输协议）是一个简单地请求-响应协议，它通常运行在TCP上。

- 文本：html，字符串... ...
- 超文本：图片，音乐，视频，定位，地图
- 端口号：80

Https：安全的

- 端口号：443

### 4.2 两个时代

- HTTP1.0
    - HTTP/1.0：客户端可以与web服务器连接后，只能获得一个web资源，断开连接。
- HTTP2.0
    - HTTP/1.1：客户端可以与web服务器连接后，可以获得多个web资源。

### 4.3 HTTP请求

- 客户端--->发请求（Request）--->服务器

百度：

```java
Request URL:https://www.baidu.com/     请求地址
Request Method:GET   get方法/post方法
Status Code:200 OK   状态码：200
Remote（远程） Address:14.215.199.39:443
```

```
Accept:text/html
Accept-Encoding:gzip,deflate,br
Accept-Language:zh-CN,zh;q=0.9	语言
Cache-Control:max-age=0
Connection:keep-alive
```

#### 4.3.1 请求行

- 请求行中的请求方式：GET
- 请求方式：Get，Post... ...
    - get：请求能够携带的参数比较少，大小有限制，会在浏览器的URL地址栏中显示数据内容，不安全，但高效
    - post：请求能够携带的参数没有限制，大小没有限制，不会在浏览器的URL地址栏中显示数据内容，安全，但不高效

#### 4.3.2 消息头

```java
Accept	告诉浏览器它所支持的数据类型
Accept-Encoding 支持哪种编码格式 GBK UTF-8 GB2312
Accept-Language	告诉浏览器它的语言环境
Cache-Control	缓存控制
Connection	高速浏览器，请求完是断开还是保持连接
Host	表示主机
```

### 4.4 HTTP响应

- 服务器--->响应--->客户端

百度：

```java
Cache-Control:private	缓存控制
Connection:Keep-Alive	连接
Content-Encoding:gzip	编码
Content-Type:text/html	类型
```

#### 4.4.1 响应体

```
Accept	告诉浏览器它所支持的数据类型
Accept-Encoding 支持哪种编码格式 GBK UTF-8 GB2312
Accept-Language	告诉浏览器它的语言环境
Cache-Control	缓存控制
Connection	高速浏览器，请求完是断开还是保持连接
Host	表示主机
Refresh	告诉客户端多有刷新一次
Location	让网页重新定位
```

#### 4.4.2 响应状态码（重点）

- 200：请求响应成功
- 3XX：请求重定向
    - 重定向：你重新到我给你的新位置
- 4XX：找不到资源 404
- 5XX：服务器代码错误 500
    - 502：网关错误

#### 常见面试题：

当你的浏览器中地址栏输入地址并回车的一瞬间到页面能过展示回来，经历了什么？

## 5、Maven

**为什么学习这个技术？**

1. 在Javaweb开发中，需要使用大量的jar包，需要我们自己导入。

2. 如何能够让一个东西自动帮我们导入和配置这个jar包。

   由此，Maven诞生了！

### 5.1 Maven项目架构管理工具

我们目前用来方便导入jar包

Maven核心思想：**约定大于配置**

- 有约束，不要去违反

Maven会规定好你该如何去编写我们的Java代码，必须按照这个规范来；

### 5.2 配置环境变量

在系统环境变量中

配置如下：

- M2_HOME : D:\apache-maven-3.6.3\bin
- MAVEN_HOME : D:\apache-maven-3.6.3
- Path里面配置 %MAVEN_HOME%\bin

### 5.3 阿里云镜像

- 镜像：mirrors
    - 作用：加速我们的下载
- 国内建议使用阿里云镜像

```xml
<!--在D:\apache-maven-3.6.3\conf\settings\160行-->
<mirrors>
　　　　<id>nexus-aliyun</id>
　　　　<mirrorOf>*,!jeecg,!jeecg-snapshots</mirrorOf>
　　　　<name>Nexus aliyun</name>
       <url>http://maven.aliyun.com/nexus/content/groups/public/</url>     
</mirrors>
```

### 5.4 本地仓库

在本地的仓库，远程仓库

建立一个本地仓库：localRepository

```xml
<!--在D:\apache-maven-3.6.3\conf\settings\55行-->
<localRepository>D:\apache-maven-3.6.3\maven-repo</localRepository> 
```

### 5.5 在IDEA中使用MAVEN

1. 启动IDEA
2. 创建一个MavenWeb模板项目

![](JavaWeb\3.png)

3.等待初始化完毕

4.观察maven仓库中多了什么

5.模板maven配置结束

### 5.6 在IDEA中创建一个普通的Maven项目

1.![](JavaWeb\4.png)

2.![](JavaWeb\5.png)

### 5.7 标记文件夹功能

![]( JavaWeb\6.png)

### 5.8 在IDEA中配置Tomcat

会出现：

![](JavaWeb\7.png)

必须要配置：**解决警告问题**

![](JavaWeb\8.png)

为什么会有这个问题：我们访问一个网站，需要制定一个文件夹的名字；

### 5.9 pom文件

pom.xml是Maven的核心配置文件

![](JavaWeb\9.png)

maven由于他的约定大于配置，我们之后可能遇到我们写的配置文件无法被导出或者生效的问题，解决方案：

```xml
<!--在build中配置resources，来防止我们资源导出失败的问题-->
<build>
      <resources>
        <resource>
            <directory>src/main/resources</directory>
            <excludes>
                <exclude>**/*.properties</exclude>
                <exclude>**/*.xml</exclude>
             </excludes>
            <filtering>false</filtering>
        </resource>
        <resource>
            <directory>src/main/java</directory>
            <includes>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
            <filtering>false</filtering>
        </resource>
    </resources>

</build>
```

网址：https://www.cnblogs.com/pixy/p/4798089.html

### 5.10替换webapps版本

更改web.xml文件替换为webapps版本和Tomcat一致

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                      http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0"
         metadata-complete="true">

</web-app>
```

### 5.11Maven仓库使用

地址：https://mvnrepository.com/

## 6、Servlet

### 6.1 Servlet简介

- Servlet就是sun公司开发动态web的一门将技术
- Sun在这些API中提供一个接口：Servlet，如果你想开发一个Servlet程序，只需要完成两个步骤：
    - 编写一个类，实现Servlet接口
    - 把开发好的Java类部署到web服务器中

**把实现了Servlet接口的Java程序叫做------Servlet**

### 6.2 HelloServlet

Servlet接口在Sun公司有两个默认的**实现类**：**HttpServlet**，**GenericServlet**

1. 构建一个普通的Maven项目，删掉src目录，以后我们的学习就在这个项目里建立Model；这个空的工程就是Maven的主工程

2. 关于Maven父子工程

   父项目中会有

```xml
<modules>
        <module>Servlet01</module>
    </modules>
```

​ 子项目中会有

```xml
<parent>
        <artifactId>JavaWeb-02-Servlet</artifactId>
        <groupId>org.example</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
```

父项目中的java子项目可以直接使用

3.Maven环境优化

1. 修改web.xml为最新
2. 将Maven的结构搭建完整 java包和resorces包

4.编写一个Servlet程序

1. 编写一个普通类
2. 实现Servlet接口，直接继承HttpServlet

```java
public class HelloServlet extends HttpServlet {
    //由于get或者post只是请求实现的不同的方式，可以相互调用，因为业务逻辑一样
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //ServletOutputStream outputStream = resp.getOutputStream();
        PrintWriter writer = resp.getWriter();//响应流

        writer.print("Hello Servlet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
```

5.编写Servlet的映射

为什么要映射：我们写的是Java程序，但是要通过浏览器访问，而浏览器需要连接web服务器，所以我们需要在web服务中注册我们写的Servlet，还需给他一个浏览器能够访问的路径；

```xml
<!--注册Servlet-->
    <servlet>
        <servlet-name>hello</servlet-name>
        <servlet-class>com.krito.servlet.HelloServlet</servlet-class>
    </servlet>
    <!--Servlet请求路径-->
    <servlet-mapping>
        <servlet-name>hello</servlet-name>
        <url-pattern>hello</url-pattern>
    </servlet-mapping>
```

6.配置Tomcat

7.启动测试

### 6.3 Servlet原理

Servlet是由Web服务器调用，web服务器在收到浏览器请求之后，会：

![](JavaWeb\10.png)

### 6.4 Mapping问题

1.一个Servlet可以指定一个映射路径

```xml
 <servlet-mapping>
        <servlet-name>hello</servlet-name>
        <url-pattern>/hello</url-pattern>
    </servlet-mapping>
```

2.一个Servlet可以指定多个映射路径

```xml
 <servlet-mapping>
        <servlet-name>hello</servlet-name>
        <url-pattern>/hello1</url-pattern>
    </servlet-mapping>
 <servlet-mapping>
        <servlet-name>hello</servlet-name>
        <url-pattern>/hello2</url-pattern>
    </servlet-mapping>
```

3.一个Servlet可以指定通用映射路径

```
<servlet-mapping>
    <servlet-name>hello</servlet-name>
    <url-pattern>/hello/*</url-pattern>
</servlet-mapping>
```

4.默认请求路径

```xml
 <servlet-mapping>
        <servlet-name>hello</servlet-name>
        <url-pattern>/*</url-pattern>
    </servlet-mapping>
```

5.制定一些后缀或者前缀等..

```xml
<!--没有"/"--> 
<servlet-mapping>
        <servlet-name>hello</servlet-name>
        <url-pattern>*.krito</url-pattern>
    </servlet-mapping>
```

6.优先级问题

​ 制定了固有的映射路径优先级最高，如果找不到就会走默认的处理请求；

```xml
<!--404-->
    <servlet>
        <servlet-name>error</servlet-name>
        <servlet-class>com.krito.servlet.ErrorServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>error</servlet-name>
        <url-pattern>/*</url-pattern>
    </servlet-mapping>
```

### 6.5 SevletContext

web容器在启动的时候，它会为每个web程序都创建一个对应的ServletContext对象，它代表了当前的web应用；

#### 6.5.1 共享数据

我在这个Servlet中保存的数据，可以在另一个Servlet中拿到

```java
@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        String username = (String) context.getAttribute("username");

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().print("名字："+username);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
```

测试访问结果

#### 6.5.2 获取初始化参数

```xml
<!--配置一些web应用初始化参数-->
    <context-param>
        <param-name>url</param-name>
        <param-value>jdbc:mysql://localhost:3306/mybatis</param-value>
    </context-param>
```

```java
@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        String url = context.getInitParameter("url");
        resp.getWriter().print(url);
    }
```

#### 6.5.3 请求转发

```java
@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        System.out.println("进入了该方法");
//        RequestDispatcher requestDispatcher = context.getRequestDispatcher("/gp");//转发的请求路径
//        requestDispatcher.forward(req,resp);//forward() 转发的作用 ,实现请求转发
        context.getRequestDispatcher("/gp").forward(req,resp);
    }
```

#### 6.5.4 读取资源文件

Properties

- 在java目录下新建properties
- 在resources目录下新建properties

发现：都被打包到了同一个路径下:classes,这个路径俗称classpath;

思路：需要一个文件流；

```java
@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InputStream is = this.getServletContext().getResourceAsStream("/WEB-INF/classes/db.properties");
        Properties properties = new Properties();
        properties.load(is);
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        resp.getWriter().print(username+" "+password);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
```

### 6.6 HttpSevletResponse

- web服务器接收到客户端的HTTP请求，针对这个请求，分别创建一个代表请求的HttpSevletRequest对象，代表响应的一个HttpSevletRequest；
    - 如果要获取客户端请求过来的参数：找HttpSevletResponse
    - 如果要给客户端响应一些信息：找HttpSevletRequest

#### 6.6.1 简单分类

**负责向浏览器发送数据的方法**

```java
ServletOutputStream getOutputStream() throws IOException;
PrintWriter getWriter() throwsIOException
```

负责向浏览器发送响应头的方法

```java
void setCharacterEncoding(String var1);

void setContentLength(int var1);

void setContentLengthLong(long var1);

void setContentType(String var1);

void setDateHeader(String var1, long var2);

void addDateHeader(String var1, long var2);

void setHeader(String var1, String var2);

void addHeader(String var1, String var2);

void setIntHeader(String var1, int var2);

void addIntHeader(String var1, int var2);
```

响应状态码

```java
    int SC_CONTINUE = 100;
    int SC_SWITCHING_PROTOCOLS = 101;
    int SC_OK = 200;
    int SC_CREATED = 201;
    int SC_ACCEPTED = 202;
    int SC_NON_AUTHORITATIVE_INFORMATION = 203;
    int SC_NO_CONTENT = 204;
    int SC_RESET_CONTENT = 205;
    int SC_PARTIAL_CONTENT = 206;
    int SC_MULTIPLE_CHOICES = 300;
    int SC_MOVED_PERMANENTLY = 301;
    int SC_MOVED_TEMPORARILY = 302;
    int SC_FOUND = 302;
    int SC_SEE_OTHER = 303;
    int SC_NOT_MODIFIED = 304;
    int SC_USE_PROXY = 305;
    int SC_TEMPORARY_REDIRECT = 307;
    int SC_BAD_REQUEST = 400;
    int SC_UNAUTHORIZED = 401;
    int SC_PAYMENT_REQUIRED = 402;
    int SC_FORBIDDEN = 403;
    int SC_NOT_FOUND = 404;
    int SC_METHOD_NOT_ALLOWED = 405;
    int SC_NOT_ACCEPTABLE = 406;
    int SC_PROXY_AUTHENTICATION_REQUIRED = 407;
    int SC_REQUEST_TIMEOUT = 408;
    int SC_CONFLICT = 409;
    int SC_GONE = 410;
    int SC_LENGTH_REQUIRED = 411;
    int SC_PRECONDITION_FAILED = 412;
    int SC_REQUEST_ENTITY_TOO_LARGE = 413;
    int SC_REQUEST_URI_TOO_LONG = 414;
    int SC_UNSUPPORTED_MEDIA_TYPE = 415;
    int SC_REQUESTED_RANGE_NOT_SATISFIABLE = 416;
    int SC_EXPECTATION_FAILED = 417;
    int SC_INTERNAL_SERVER_ERROR = 500;
    int SC_NOT_IMPLEMENTED = 501;
    int SC_BAD_GATEWAY = 502;
    int SC_SERVICE_UNAVAILABLE = 503;
    int SC_GATEWAY_TIMEOUT = 504;
    int SC_HTTP_VERSION_NOT_SUPPORTED = 505;
```

#### 6.6.2常见应用

1. 向浏览器输出消息

2. 下载文件

   1.要获取下载文件的路径

   2.下载的文件名

   3.设置想办法让浏览器能够支持我们下载我们需要的东西

   4.获取下载文件的输入流

   5.创建缓冲区

   6.获取OutputStream对象

   7.将FileOutputStream流写入到buffer缓冲区

   8.使用OutputStream将缓冲区中的数据输出到客户端

```java
@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.要获取下载文件的路径
        String realPath = "C:\\Users\\50593\\IdeaProjects\\JavaWeb-02-Servlet\\Response1\\src\\main\\resources\\1.jpg";
        System.out.println("下载文件的路径"+realPath);
        //2.下载的文件名
        String filename = realPath.substring(realPath.lastIndexOf("\\") + 1);
        //3.设置想办法让浏览器能够支持(Content-Disposition)我们下载我们需要的东西,
        //中文文件名URLEncoder.encode编码,例如URLEncoder.encode(filename,"UTF-8")
        resp.setHeader("Content-Disposition","attachment;filename="+filename);
        //4.获取下载文件的输入流
        FileInputStream in = new FileInputStream(realPath);
        //5.创建缓冲区
        int len = 0;
        byte[] buffer = new byte[1024];
        //6.获取OutputStream对象
        ServletOutputStream out = resp.getOutputStream();
        //7.将FileOutputStream流写入到buffer缓冲区
        //8.使用OutputStream将缓冲区中的数据输出到客户端
        while((len=in.read(buffer))>0)
        {
            out.write(buffer,0,len);
        }

        in.close();
        out.close();
    }
```

#### 6.6.3验证码功能

验证码怎么来的？

- 前端实现
- 后端实现，需要用到Java的图片类，生成一个图片

### 6.7HttpSevletRequset

HttpSevletRequest代表客户端的请求，用户通过Http协议访问服务器，HTTP请求中的所有信息会被封装到HttpServletRequest,通过这个HttpServletRequest的方法，获得客户端的所有信息；

#### 6.7.1获取参数 请求转发

```java
req.getParameter(String s) 					String	
req.getParameterValues(String s)			String[]
```

```java
@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String[] hobbies = req.getParameterValues("hobby");
        
        System.out.println("---------------------------");
        System.out.println(username);
        System.out.println(password);
        System.out.println(Arrays.toString(hobbies));
        System.out.println("---------------------------");

        //通过请求转发
        //这里的 "/" 代表当前的web应用
        req.getRequestDispatcher("/success.jsp").forward(req,resp);

    }
```

**面试题：请你聊聊重定向和转发的区别？**

相同点：

- 页面都会实现跳转

不同点：

- 请求转发的时候，url不会变化 编号307
- 重定向时候，URL地址会发生变化 编号302

## 7、Cookie、Session

### 7.1 会话

**会话**：用户打开一个浏览器，点击了很多超链接，访问多个web资源，关闭浏览器，这个过程可以称之为会话

**有状态会话**: 一个同学来过教师，下次再来教室，我们会知道这个铜须曾经来过，称之为有状态会话。

**你能怎么证明你是升达的学生？**

​ 你 升达

1.学生证 升达给你发学生证

2.学校登记 升达标记你来过了

**一个网站，怎么证明你来过了？**

​ 客户端 服务端

1.服务端给客户端一个信件，客户端下次访问服务器带上信件就可以了 。Cookie

2.服务器登记你来过了，下次你来的时候我来匹配你。Session

### 7.2保存会话的两种技术

**Cookie**

- 客户端技术（响应，请求）

**Session**

- 服务器技术，利用这个技术可以保存用户的会话信息。我们可以把信息或者数据放在Session中

常见例子：网站登陆之后，下次就不需要再次登录。

### 7.3 Cookie

1. 从请求中拿到Cookie信息
2. 服务器响应给客户端Cookie

```java
Cookie[] cookies = req.getCookies();//获得Cookie
cookie.getName();//获得cookie中的key
cookie.getValue();//获得cookie中的值
new Cookie("lastLoginTime", System.currentTimeMillis()+"");//新建一个cookie
cookie.setMaxAge(24*60*60);//设置cookie的有效期为1天
resp.addCookie(cookie);//响应给客户端一个cookie
```

**cookie:一般会保存在本地的用户目录下 appdata**

**删除cookie：**

- 不设置有效期，关闭浏览器，自动失效
- 设置有效期时间为0

**编码解码：**

```java
URLEncoder.encode("张鹏博","UTF-8");
URLEncoder.decode(cookie.getValue(),"utf-8");
```

### 7.4 Session（重点）

什么是Session：

- 服务器会给每一个用户（浏览器）创建一个Session对象
- 一个Session独占一个浏览器，只要浏览器没有关闭，这个Session就存在
- 用户登录之后，整个网站都可以访问 ---->保存用户信息；保存购物车信息 等等..
- Session能存字符串，也能存入用户信息

![](JavaWeb\12.png)

Session和Cookie的区别：

- Cookie是把用户的数据写给用户的浏览器，浏览器保存（可以保存多个）
- Session把用户的数据写到用户独占Session中，服务器保存（保存重要信息，减少服务器资源浪费）
- Session对象由服务器创建

使用场景：

- 保存一个登陆用户的信息
- 购物车信息
- 在整个网站中经常会使用的数据，我们将它保存在Session中

使用Session:

```java
@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //解决乱码问题
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        //得到Session
        HttpSession session = req.getSession();

        //给session中存入东西
        session.setAttribute("name",new Person("桐人",16));

        //获取Session的ID
        String id = session.getId();

        //判断Session是不是新创建的
        if(session.isNew())
        {
            resp.getWriter().write("Session创建成功，ID:"+id);
        }
        else
        {
            resp.getWriter().write("Session已经在服务器中存在，ID:"+id);
        }

//        Session创建的时候做了什么事情：
//        Cookie cookie = new Cookie("JESSIONID",id);
//        resp.addCookie(cookie);
    }
```

```java
 //得到Session
        HttpSession session = req.getSession();

        Person name = (Person) session.getAttribute("name");

        System.out.println(name.toString());
```

```java
		HttpSession session = req.getSession();
        session.removeAttribute("name");
        //手动注销Session
        session.invalidate();
```

会话自动过期:web.xml配置

```xml
<!--设置Session默认的失效时间-->
    <session-config>
        <!--15分钟后Session自动失效(以分钟为单位)-->
        <session-timeout>1</session-timeout>
    </session-config>
```

![](JavaWeb\13.png)

## 8、JSP

### 8.1什么是JSP

java Server Pages : Java服务器端页面，也和Servlet一样，用于动态Web技术。

最大的特点：

- 写JSP就像在写HTML
- 区别：
    - HTML只写给用户提供静态的数据
    - JSP页面中可以嵌入java代码，为用户提供动态数据

### 8.2 JSP原理

思路：JSP怎么执行？

- 代码层面无问题

- 服务器内部工作

    - tomcat中有一个work目录

    - IDEA 使用Tomcat的会在IDEA的Tomcat中生产一个work目录
    - 页面转换为了Java程序

  ![](JavaWeb\14.png)

**浏览器向服务器发送请求，不管访问什么资源，其实都是在访问Servlet**

JSP最终会被转换成为一个Java类

**JSP本质就是一个Servlet**

```java
//初始化
public void _jspInit(){
}
//销毁
public void _jspDestroy(){
}
//JSPServices
public void _jspService(HttpServletRequest request,HttpServletResponse response){
}
```

1. 判断请求

2. 内置一些对象

   ```java
   final javax.servlet.jsp.PageContext pagecontext;	//页面上下文
   javax.servlet.http.HttpSession session = null; 		//session
   final javax.servlet.ServletContext application;		//applicationContext
   final javax.servlet.ServletConfig config;			//config
   javax.servlet.jsp.JspWriter out = null;				//out
   final java.lang.Object page = this;					//page:当前
   HttpServletRequest request							//请求
   HttpServletResponse response						//响应
   ```

3. 输出页面增加的代码

   ```java
   response.setContextType("text/html");		//设置响应的页面类型
   page.Context = _jspxfactory.getPageContext(this,request,response,null,true,8192,true);
   _jspx_page_context = pageContext;
   application = pageContext.getServletContext();
   config = pafeContext.getServletConfig();
   session = pageContext.getSession();
   out = pageContext.getOut();
   _jspx_out = out;
   ```

4. 以上的这些对象可以再JSP页面中直接使用

![](JavaWeb\15.png)

在JSP页面中 ，只要是Java代码就会原封不动地输出

如果是HTML代码，就会被转换为

```
out.write("<html>\r\n")
```

这样的格式输出到前端。

### 8.3 JSP基础语法

#### 8.3.1 JSP表达式

```jsp
<%--JSP表达式
  作用：用来将程序的输出发送到客户端
  <%= 变量或者表达式%>
  --%>
  <%= new java.util.Date()%>
```

#### 8.3.2 JSP脚本片段

```jsp
<%--JSP脚本片段--%>
  <%
    int sum=0;
    for (int i = 0; i < 10; i++) {
      sum+=i;
    }
    out.println("<h1>Sum="+"</h1>");
  %>
```

#### 8.3.3 脚本片段的再实现

```jsp
<%
    int x = 10;
    out.println(x);
  %>
  <p>这是一个jsp文档</p>
  <%
    int y = 3;
    out.println(y);
  %>
  <hr>

  <%--在代码HTML元素--%>
  <%
    for (int i = 0; i < 5; i++) {
  %>
    <h1>Hello,Krito  ${i}</h1>
  <%
    }
  %>
```

#### 8.3.4 JSP声明

```jsp
<%!
    static{
      System.out.println("Loading Servlet");
    }

    private int glovalVar = 0;

    public void Krito()
    {
      System.out.println("进入了方法Krito");
    }
  %>
```

- JSP声明：会被编译到JSP生成JAVA的类中。其他的就会被生成到_jspService方法中

- 在JSP中嵌入Java代码即可

- JSP的注释不会在客户端显示，HTML会

### 8.4 JSP指令

```jsp
<%@ page ....%>

<%--提取公共页面--%>
<%@include file=""%>

<%--将两个页面合一起--%>
<%@include file="common/header.jsp"%>
<h1>
    网页主体
</h1>
<%@include file="common/footer.jsp"%>

<%--jsp标签
    jsp:include: 拼接页面，本质上还是三个
    --%>
<jsp:include page="common/header.jsp"/>
<h1>
    网页主体
</h1>
<jsp:include page="common/footer.jsp"/>
```

### 8.5 9大内置对象

- PageContext 存东西
- Request 存东西
- Response
- Session 存东西
- Application  [ServletContext]  存东西
- config  [SevletConfig]
- out
- page (不用)
- exception

```java
pageContext.setAttribute("name1","桐人1号");//保存的数据只在一个页面中有效 
request.setAttribute("name2","桐人2号");//保存的数据只在一次请求中有效，请求转发会携带这个数据
session.setAttribute("name3","桐人3号");//保存的数据只在一次会话中有效，从打开浏览器到关闭浏览器
application.setAttribute("name4","桐人4号");//保存的数据只在一次服务器中有效，从打开服务器到关闭服务器
```

request:客户端向服务器发送请求，产生的数据用户用完就无用了。比如：新闻，用户看完后此消息变得无用

session：客户端向服务器发送请求，产生的数据用户用完后一会儿还有用。比如：购物车

application：客户端向服务器发送请求，产生的数据一个用户用完了，其他用户还可能使用。比如：聊天数据

## 9、 JavaBean

实体类

JavaBean有特定的写法

- 必须要有一个无惨构造
- 属性必须私有化
- 必须有对应的get/set方法

一般用来和数据库的字段做映射 ORM

ORM：对象关系映射

- 表--->类
- 字段--->属性
- 行记录--->对象

| id | name | age | address |
|----|------|-----|---------|
| 1  | 桐人   | 22  | 日本      |
| 2  | 亚丝娜  | 23  | 日本      |
| 3  | 爱丽丝  | 24  | 日本      |

```java
class People{
    private int id;
    private String name;
    private int age;
    private String name;
}

class A{
    new People(1,"桐人",22,"日本");
}
```

- 过滤器
- 文件上传
- 邮件发送
- JDBC复习：如何使用JDBC，JDBC crud,jdbc 事务

## 10、MVC三层架构

什么是MVC：MOdel view Controller 模型、视图、控制器

### 10.1 远古架构

![](JavaWeb\16.png)

用户直接访问控制层，控制层可以直接操作数据库

```java
servlet--CRUD-->数据库
弊端：程序臃肿不利于维护		
servlet的代码中：处理请求、响应、视图跳转、处理JDBC、处理业务代码、处理逻辑代码
    
架构：没有什么是加一层解决不了的
```

### 10.2 MVC三层架构

![](JavaWeb\17.png)

Model：

- 业务处理：业务逻辑（Service）
- 数据持久层：CRUD（Dao）

View：

- 展示数据
- 提供链接发起Servlet请求（a，form，img....）

Controller（Servlet）：

- 接受用户的请求：（（req：请求参数）、Session的信息）
- 交给业务层处理响应的代码
- 控制视图的跳转

```java
登录--->接受用户的登录请求--->处理用户的请求（获取用户登录的参数，username，password）--->交给业务层处理登录业务（判断用户名密码是否正确：事务）--->Dao层查询用户名和密码是否正确--->数据库
```

## 11、Filter

Filter:过滤器，用来过滤网站的数据

- 处理中文乱码
- 登录验证...

![](JavaWeb\18.png)

Filter开发步骤：

1. 导包

2. 编写过滤器

    1. 导包不要错![](JavaWeb\19.png)

    2. 实现Filter接口，重写相应的方法即可

       ```java
       public class CharaterEncodingFilter implements Filter {
               //初始化
           public void init(FilterConfig filterConfig) throws ServletException {
               System.out.println("CharaterEncodingFilter初始化");
           }
       
           //filterChain:链
           /*
               1.过滤中的所有代码，在过滤特定请求的时候都会执行
               2.必须要让过滤器继续同行
                   doFilter(servletRequest,servletResponse)
            */
           public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
               servletRequest.setCharacterEncoding("UTF-8");
               servletResponse.setCharacterEncoding("UTF-8");
               servletResponse.setContentType("text/html;charset=UTF-8");
       
               System.out.println("CharaterEncodingFilter执行前");
               filterChain.doFilter(servletRequest,servletResponse);//让我们的请求继续走，如果不写，程序到这里就被拦截停止
               System.out.println("CharaterEncodingFilter执行后");
           }
           //销毁:web服务器关闭的时候，过滤器会销毁
           public void destroy() {
               System.out.println("CharaterEncodingFilter销毁");
           }
       }
       ```

    3. 在web.xml中配置Filter

       ```xml
       <filter>
               <filter-name>CharacterEncodingFilter</filter-name>
               <filter-class>com.krito.filter.CharaterEncodingFilter</filter-class>
           </filter>
           <filter-mapping>
               <filter-name>CharacterEncodingFilter</filter-name>
               <!--只要是/servlet的任何请求都会经过这个过滤器-->
               <url-pattern>/*</url-pattern>
           </filter-mapping>
       ```

## 12、监听器

实现一个监听器的接口（有N种）

1. 编写一个监听器

2. 实现监听器的接口

   ```java
   //统计网络在线人数：统计Session
   public class OnlineCountListener implements HttpSessionListener {
       //创建Session的监听：看你的一举一动
       //一旦创建一个Session就会触发一次事件
       public void sessionCreated(HttpSessionEvent se) {
           ServletContext ctx = se.getSession().getServletContext();
           Integer onlineCount = (Integer)ctx.getAttribute("OnlineCount");
   
           if(onlineCount==null)
           {
               onlineCount = new Integer(1);
           }
           else{
               int count = onlineCount.intValue();
               onlineCount = new Integer(count+1);
           }
   
           ctx.setAttribute("OnlineCount",onlineCount);
   
       }
   
       //销毁Session的监听
       //一旦销毁Session就会触发一次事件
       public void sessionDestroyed(HttpSessionEvent se) {
           ServletContext ctx = se.getSession().getServletContext();
           Integer onlineCount = (Integer)ctx.getAttribute("OnlineCount");
   
           if(onlineCount==null)
           {
               onlineCount = new Integer(0);
           }
           else{
               int count = onlineCount.intValue();
               onlineCount = new Integer(count-1);
           }
   
           ctx.setAttribute("OnlineCount",onlineCount);
       }
   
       /*
       Session销毁:
       1.手动销毁 getSession().invalidate();
       2.自动销毁 
       	<session-config>
           <session-timeout>1</session-timeout>
       	</session-config>
        */
   }
   ```

3. web.xml注册监听器

   ```xml
   <!--注册监听器-->
       <listener>
           <listener-class>com.krito.listener.OnlineCountListener</listener-class>
       </listener>
   ```

4. 看情况是否使用

## 13、过滤器、监听器常见应用

**监听器：GUI（图形界面编程）编程中经常使用**

用户登录之后才能进入主页，用户 注销之后就不能进入主页。

1. 用户登录之后向Session中放入用户的数据

2. 进入主页的时候要判断用户是否已经登陆；要求：在过滤器中实现

   ```java
   HttpServletRequest request = (HttpServletRequest) req;
           HttpServletResponse response = (HttpServletResponse) resp;
   
           if(request.getSession().getAttribute("USER_SESSION")==null)
           {
               response.sendRedirect("/error.jsp");
           }
   
           filterChain.doFilter(req,resp);
   ```

## 14、JDBC

什么是JDBC：Java连接数据库

<img src="JavaWeb\20.png" style="zoom:80%;" />

需要jar包的支持：

- java.sql
- javax.sql
- mysql-connecter-java..连接驱动（必须导入）

1.数据写入数据库

  ```sql
CREATE TABLE user(
	id INT PRIMARY KEY,
    `name` VARCHAR(40),
    `PASSWORD` varchar(40),
    email VARCHAR(60)
    birthday DATE
);

INSERT INTO users(id,`name`,`password`,email,birthday)
VALUES(1,'1','1','1@qq.com','2000-01-01');
INSERT INTO users(id,`name`,`password`,email,birthday)
VALUES(2,'2','2','2@qq.com','2000-02-02');
INSERT INTO users(id,`name`,`password`,email,birthday)
VALUES(3,'3','3','3@qq.com','2000-03-03');

SELECT * FROM users;
  ```

2.导入数据库依赖

```xml
<!--mysql的驱动-->
<dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.47</version>
        </dependency>
```

3.IDEA中连接数据库

**4.JDBC固定步骤**

1. 加载驱动
2. 连接数据库，代表数据库
3. 向数据库发送SQL的对象Statement:CRUD
4. 编写SQL（根据业务，不同的SQL）
5. 执行SQL
6. 关闭连接

```java
public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //配置信息
        //useUnicode=true&characterEncoding=UTF-8 解决中文乱码
        String url = "jdbc:mysql://localhost:3306/test?user=root&password=&useUnicode=true&characterEncoding=UTF-8";
        String username = "root";
        String password = "password";
        
        //1.加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2.连接数据库，代表数据库
        Connection connection = DriverManager.getConnection(url,username,password)
        
        //3.向数据库发送SQL的对象Statement： CRUD
        Statement statement = connection.createStatement();
        
        //4.编写SQL
        String sql = "select * from users";
        
        //5.执行查询SQL，返回一个ResultSet：结果集
        ResultSet rs = statement.executeQuery(sql);
        
        while(rs.next())
        {
            System.out.println("id="+rs.getObject("id"));
            System.out.println("name="+rs.getObject("name"));
        }
        
        //6.关闭连接：释放资源（一定要做）先开后关
        rs.close();
        statement.close();
        connection.close();
    }

//增删改都用executeUpdate即可
```

预编译SQL

```java
public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //配置信息
        //useUnicode=true&characterEncoding=UTF-8 解决中文乱码
        String url = "jdbc:mysql://localhost:3306/test?user=root&password=&useUnicode=true&characterEncoding=UTF-8";
        String username = "root";
        String password = "password";

        //1.加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2.连接数据库，代表数据库
        Connection connection = DriverManager.getConnection(url,username,password);

        //3.编写SQL
        String sql = "insert into users(id,name,password,email,birthday) values (?,?,?,?,?)";

        //预编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1,4);//给第一个占位符？赋值为1
        preparedStatement.setString(2,"Krito");//给第二个占位符？赋值为Krito
        preparedStatement.setString(3,"123456");
        preparedStatement.setString(4,"krito@qq.com");
        preparedStatement.setDate(5,new Date(new java.util.Date().getTime()));

        //5.执行SQL
        int i = preparedStatement.executeUpdate();
        if(i<0)
        {
            System.out.println("输入成功");
        }

        //6.关闭连接：释放资源（一定要做）先开后关
        preparedStatement.close();
        connection.close();
    }
```

事务

要么都成功，要么都失败

ACID原则：保证数据的安全。

- 开启事务
- 事务提交 commit()
- 事务回滚 rollback()
- 关闭事务