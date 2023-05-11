# 查询时间sql

```sql
今天
select * from 表名 where to_days(时间字段名) = to_days(now());

昨天
SELECT * FROM 表名 WHERE TO_DAYS( NOW( ) ) - TO_DAYS( 时间字段名) <= 1

7天
SELECT * FROM 表名 where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(时间字段名)

近30天
SELECT * FROM 表名 where DATE_SUB(CURDATE(), INTERVAL 30 DAY) <= date(时间字段名)

本月
SELECT * FROM 表名 WHERE DATE_FORMAT( 时间字段名, '%Y%m' ) = DATE_FORMAT( CURDATE( ) , '%Y%m' )

上一月
SELECT * FROM 表名 WHERE PERIOD_DIFF( date_format( now( ) , '%Y%m' ) , date_format( 时间字段名, '%Y%m' ) ) =1

#查询本季度数据
select * from `ht_invoice_information` where QUARTER(create_date)=QUARTER(now());
#查询上季度数据
select * from `ht_invoice_information` where QUARTER(create_date)=QUARTER(DATE_SUB(now(),interval 1 QUARTER));
#查询本年数据
select * from `ht_invoice_information` where YEAR(create_date)=YEAR(NOW());
#查询上年数据
select * from `ht_invoice_information` where year(create_date)=year(date_sub(now(),interval 1 year));

查询当前这周的数据 
SELECT name,submittime FROM enterprise WHERE YEARWEEK(date_format(submittime,'%Y-%m-%d')) = YEARWEEK(now());

查询上周的数据
SELECT name,submittime FROM enterprise WHERE YEARWEEK(date_format(submittime,'%Y-%m-%d')) = YEARWEEK(now())-1;

查询当前月份的数据
select name,submittime from enterprise   where date_format(submittime,'%Y-%m')=date_format(now(),'%Y-%m')

查询距离当前现在6个月的数据
select name,submittime from enterprise where submittime between date_sub(now(),interval 6 month) and now();

查询上个月的数据
select name,submittime from enterprise   where date_format(submittime,'%Y-%m')=date_format(DATE_SUB(curdate(), INTERVAL 1 MONTH),'%Y-%m')
```

# 用户及权限管理

## 创建用户

```sql
CREATE USER 'username'@'host' IDENTIFIED BY 'password';
```

username – 你将创建的用户名说明:

host – 指定该用户在哪个主机上可以登陆,如果是本地用户可用localhost, 如 果想让该用户可以从任意远程主机登陆,可以使用通配符%

password – 该用户的登陆密码,密码可以为空,如果为空则该用户可以不需要密码登 陆服务器

例子：

```sql
CREATE USER 'javacui'@'localhost' IDENTIFIED BY '123456'; 
CREATE USER 'javacui'@'172.20.0.0/255.255.0.0' IDENDIFIED BY '123456'; 
CREATE USER 'javacui'@'%' IDENTIFIED BY '123456'; 
CREATE USER 'javacui'@'%' IDENTIFIED BY ''; 
CREATE USER 'javacui'@'%';
```

## **授权**

```sql
GRANT privileges ON databasename.tablename TO 'username'@'host';
```

privileges – 用户的操作权限,如SELECT , INSERT , UPDATE 等(详细列表见该文最后面).如果要授予所 的权限则使用ALL说明:

databasename - 数据库名

tablename - 表名,如果要授予该用户对所有数据库和表的相应操作权限则可用* 表示, 如*.*

例子:

```sql
GRANT SELECT, INSERT ON test.user TO 'javacui'@'%'; 
GRANT ALL ON *.* TO 'javacui'@'%';
```