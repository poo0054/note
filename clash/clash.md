```yaml
parsers: # array
  - url: url
    yaml:
      prepend-rules:
        - DOMAIN-SUFFIX,1-dian.cn,DIRECT
        - DOMAIN-KEYWORD,bing,GLOBAL
        - DOMAIN-KEYWORD,openai.com,GLOBAL
        # ChatGPT
        - DOMAIN-SUFFIX,openai.com, GLOBAL
        - DOMAIN-SUFFIX,openaiapi-site.azureedge.net, GLOBAL
        - DOMAIN-SUFFIX,identrust.com, GLOBAL
        - DOMAIN-SUFFIX,autho.com, GLOBAL
        - DOMAIN-SUFFIX,cloudflare.com, GLOBAL
        - DOMAIN-SUFFIX,freenom.com, GLOBAL
```