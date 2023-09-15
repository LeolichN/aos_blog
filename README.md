# AosBlog

`aos` 取 Cao 和 Qiao 的末尾共有字母的复数形式
`AosBlog` 是属于 Cao 和 Qiao 的博客项目

## 目录结构

根目录下按照功能模块分层，功能模块下按照 DDD 分层

### 功能模块结构

### DDD 分层

以账本模块为例

```
- aos-book
  -- book-application
  -- book-interface
  -- book-domain
  -- book-infrastructure
  -- book-repo
  -- book-starter
  -- build.gradle
  -- settings.gradle
```

`interface`：暴露对外接口层，负责前置web校验等，可以依赖 application 层 </br>
`application`：参数信息组装/转换层，负责将前置输入转换为上下文参数并进行相关校验、指标监控等，可以依赖 domain，infrastructure
层 </br>
`domain`：业务语义定义和封装,可以依赖 infrastructure, repo 层
`infrastructure`: 基础设施层，提供基础能力
`repo`: 数据库操作定义层，Entity、 DTO、 Mapper 等在本层定义
`starter`: 存放启动类
