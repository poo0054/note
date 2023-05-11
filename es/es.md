> 集群,节点和分片

有许多性能注意事项和权衡 为索引配置的分片大小和主分片数。越多 分片，维护这些索引的开销就越大。这 分片大小越大，在
Elasticsearch 时移动分片所需的时间就越长 需要重新平衡集群。

查询大量小分片使每个分片的处理速度更快，但更多 查询意味着更多的开销，因此查询更小 较大的分片数量可能更快。总之。。。这要看情况。

作为起点：

目标是将平均分片大小保持在几 GB 到几十 GB 之间。为 使用基于时间的数据，通常会看到 20GB 到 40GB 的分片 范围。
避免海量分片问题。节点可以容纳的分片数量为 与可用堆空间成正比。作为一般规则，数量 每 GB 堆空间的分片数应小于 20。

> 发生灾害时

出于性能原因，群集中的节点需要位于同一节点上 网络。在不同数据中心的节点之间平衡集群中的分片 只是需要太长时间。但高可用性体系结构要求您避免
把所有的鸡蛋放在一个篮子里。在一个发生重大停电的情况下 位置，则位于另一个位置的服务器需要能够接管。无缝。 答案是什么？跨集群复制
（CCR）。

CCR 提供了一种从主集群自动同步索引的方法 到可用作热备份的辅助远程集群。如果主 集群出现故障，辅助集群可以接管。您还可以使用
CCR 来 创建辅助群集，以在地理位置接近的情况下向用户提供读取请求。

跨集群复制是主动-被动的。主集群上的索引为 活动领导者索引并处理所有写入请求。复制到的索引 辅助集群是只读关注者。

> 护理和喂养

与任何企业系统一样，您需要工具来保护、管理和 监控您的 Elasticsearch 集群。安全、监视和管理功能 集成到 Elasticsearch 中后，您可以将
Kibana 用作管理集群的控制中心。数据汇总和索引生命周期管理等功能可帮助您随着时间的推移智能地管理数据。

# 安装和启动

# elasticsearch

```shell
docker pull elasticsearch:7.6.2
docker pull kibana:7.6.2
#版本要统一
```

> 配置

```shell
# 将docker里的目录挂载到linux的/mydata目录中
# 修改/mydata就可以改掉docker里的
mkdir -p /mydata/elasticsearch/config
mkdir -p /mydata/elasticsearch/data

# es可以被远程任何机器访问
echo "http.host: 0.0.0.0" >/mydata/elasticsearch/config/elasticsearch.yml

# 递归更改权限，es需要访问
chmod -R 777 /mydata/elasticsearch/
```

> 启动Elastic search

```shell
# 9200是用户交互端口 9300是集群心跳端口
# -e指定是单阶段运行
# -e指定占用的内存大小，生产时可以设置32G
docker run --name elasticsearch -p 9200:9200 -p 9300:9300 \
-e  "discovery.type=single-node" \
-e ES_JAVA_OPTS="-Xms64m -Xmx512m" \
-v /mydata/elasticsearch/config/elasticsearch.yml:/usr/share/elasticsearch/config/elasticsearch.yml \
-v /mydata/elasticsearch/data:/usr/share/elasticsearch/data \
-v  /mydata/elasticsearch/plugins:/usr/share/elasticsearch/plugins \
-d elasticsearch:7.6.2


# 设置开机启动elasticsearch
docker update elasticsearch --restart=always
```

> 启动kibana

```shell
# kibana指定了了ES交互端口9200  # 5600位kibana主页端口
docker run --name kibana -e ELASTICSEARCH_HOSTS=http://192.168.56.10:9200 -p 5601:5601 -d kibana:7.6.2


# 设置开机启动kibana
docker update kibana  --restart=always
```

# 索引

```shell
# 此请求会自动创建索引（如果尚未创建索引） 存在，添加一个 ID 为 的新文档，并存储和 为字段编制索引
PUT /customer/_doc/1
{
"name": "John Doe"
}

# 响应指示找到具有指定 ID 的文档 并显示已编制索引的原始源字段。
GET /customer/_doc/1
{}

#结果
{
  "_index" : "customer",
  "_type" : "_doc",
  "_id" : "1",
  "_version" : 1,
  "_seq_no" : 26,
  "_primary_term" : 4,
  "found" : true,
  "_source" : {
    "name": "John Doe"
  }
}
```

# 搜索

下载https://github.com/elastic/elasticsearch/blob/7.6/docs/src/test/resources/accounts.json
POST bank/_bulk 新增bank索引

> 请求

```shell
# 按照 account_number 升序
GET /bank/_search
{
  "query": {
    "match_all": {}
  },
  "sort": [
    {
      "account_number": "asc"
    }
  ]
}
```

> 响应

```json
{
  "took": 63,
  "timed_out": false,
  "_shards": {
    "total": 5,
    "successful": 5,
    "skipped": 0,
    "failed": 0
  },
  "hits": {
    "total": {
      "value": 1000,
      "relation": "eq"
    },
    "max_score": null,
    "hits": [
      {
        "_index": "bank",
        "_type": "_doc",
        "_id": "0",
        "sort": [
          0
        ],
        "_score": null,
        "_source": {
          "account_number": 0,
          "balance": 16623,
          "firstname": "Bradshaw",
          "lastname": "Mckenzie",
          "age": 29,
          "gender": "F",
          "address": "244 Columbus Place",
          "employer": "Euron",
          "email": "bradshawmckenzie@euron.com",
          "city": "Hobucken",
          "state": "CO"
        }
      },
      {
        "_index": "bank",
        "_type": "_doc",
        "_id": "1",
        "sort": [
          1
        ],
        "_score": null,
        "_source": {
          "account_number": 1,
          "balance": 39225,
          "firstname": "Amber",
          "lastname": "Duke",
          "age": 32,
          "gender": "M",
          "address": "880 Holmes Lane",
          "employer": "Pyrami",
          "email": "amberduke@pyrami.com",
          "city": "Brogan",
          "state": "IL"
        }
      }
    ]
  }
}
```

响应还提供有关搜索请求的以下信息：

* took– Elasticsearch 运行查询需要多长时间，以毫秒为单位
* timed_out– 搜索请求是否超时
* _shards– 搜索了多少个分片以及有多少分片的细分 成功、失败或被跳过。
* max_score– 找到的最相关文档的分数
* hits.total.value- 找到多少匹配的文档
* hits.sort- 文档的排序位置（不按相关性分数排序时）
* hits._score- 文档的相关性分数（使用时不适用）match_all)

```shell
# 以下请求获得命中 10 到 19：
GET /bank/_search
{
  "query": {
    "match_all": {}
  },
  "sort": [
    {
      "account_number": "asc"
    }
  ],
  "from": 10,
  "size": 10
}

#以下请求搜索要查找的字段 地址包含或 ：mill lane 不准确
GET /bank/_search
{
  "query": {
    "match": {
      "address": "mill lane"
    }
  }
}

#要执行短语搜索而不是匹配单个术语
GET /bank/_search?pretty
{
  "query": {
    "match_phrase": {
      "address": "mill lane"
    }
  }
}

#： 属于 40 岁的客户，但不包括居住在以下地区的任何人 爱达荷州 （ID）
GET /bank/_search?pretty
{
  "query": {
    "bool": {
      "must": [
        {
          "match": {
            "age": "40"
          }
        }
      ],
      "must_not": [
        {
          "match": {
            "state": "ID"
          }
        }
      ]
    }
  }
}


#以下请求使用范围筛选器将结果限制为 余额在 20，000 美元至 30，000 美元（含）之间的账户。
GET /bank/_search?pretty
{
  "query": {
    "bool": {
      "must": {
        "match_all": {}
      },
      "filter": {
        "range": {
          "balance": {
            "gte": 20000,
            "lte": 30000
          }
        }
      }
    }
  }
}
```

# 使用聚合分析结果

```shell
#查询索引信息
GET logstash-test

#查询数据
GET logstash-test/_search
{
  "query": {
    "match_all": {}
  }
}

# 查询所有索引
GET _cat/indices

# 查询所有别名
GET _alias/

#查询匹配的所有数据
GET /logstash-*/_search
{
  "query": {
    "match_all": {}
  }
}
 
# 查询索引 ilm 信息
GET /logstash-*/_ilm/explain

# 查询模板
GET _template/logstash

# 查询 ilm
GET _ilm/policy/logstash-policy

#设置生命周期生效时间
PUT _cluster/settings
{
  "transient": {
    "indices.lifecycle.poll_interval": "3s" 
  }
}

# 添加别名
PUT logstash-test
{
  "aliases": {
    "logstash": {
      "is_write_index": true
    }
  }
}

# 删除
DELETE  /logstash-test

```
