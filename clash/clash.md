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
        - DOMAIN-SUFFIX,azureedge.net, GLOBAL
        - DOMAIN-SUFFIX,intercom.io, GLOBAL
        - DOMAIN-SUFFIX,stripe.com, GLOBAL
        - DOMAIN-SUFFIX,intercomcdn.com, GLOBAL
        - DOMAIN-SUFFIX,stripe.network, GLOBAL
        - DOMAIN-SUFFIX,stripe.com, GLOBAL
```
