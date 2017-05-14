#### Quick Example

1.创建索引

```bash
curl -XPUT http://localhost:9200/songs
```

2.创建映射方式

Note: ik分词器插件https://github.com/medcl/elasticsearch-analysis-ik

```bash
curl -XPOST http://localhost:9200/songs/song/_mapping -d'
{
  "mappings": {
    "song": {
      "properties": {
        "song_name": {
          "type":  "string",
          "index": "ik_smart"		  
        },
        "singer_name": {
          "type":  "string",
          "index": "ik_smart" 
        }
      }
    }
  }
}'
```

3.批量导入数据

```bash
curl -XPOST http://localhost:9200/_bulk --data-binary @${project_dir}data.json
```

4.REST-API Run

```bash
${project_dir} mvn spring-boot:run 或者直接在中间件上部署也行
```

5.REST-API 检索

Note：“w”检索字段如果是中文需要URLEncode("[queryValue]","UTF-8")

```bash
curl -XGET http://localhost:9200/songs?w=Nick
```
