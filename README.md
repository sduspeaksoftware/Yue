# Yue
“约了么”软件工程实验项目。

## 项目概述
约了吗系统是社交软件的一种，其主要作用是能够帮助大学生相互交流，在进行各种活动时寻找到具有相同目的，志同道合的伙伴。

|系统名称|约了么|
|---|---|
|项目开发方|sdu都说话软件公司（Sdu Speaking Software）|
|项目需求方|sdu益西曲珍软件工程分公司|
|项目投资方|自费|
|用户|在校大学生|

## 项目文档
[可行性分析(研究)报告(FAR)](https://docs.qq.com/doc/BOWEda2hDwy81C5mRL4NBKF24XHjyE3ReMas1)

[软件开发计划(SDP)](https://docs.qq.com/doc/BOWEda2hDwy816ONZY0P1vSV2lgeIN3sV3Aw0)

[工具调查分析（CASE）](https://docs.qq.com/doc/BOWEda2hDwy81xC3ZH39CW372TejIT2Qa0Qn2)

[需求变更申请](https://docs.qq.com/doc/BPbClJ1YBPB8038TlN1rIvvJ1FomKU3JUuqC2)

[软件需求规格说明(SRS)](https://docs.qq.com/doc/BOWEda2hDwy81XvzCg0vU8RU3TGzRR3BbcVR0)

[软件(结构)设计说明(SDD)](https://docs.qq.com/doc/BOWEda2hDwy81TegVV4ljkJQ0tfxVX2GDb9B1)

## 开发进度
[图在此查看](https://www.zybuluo.com/rayiooo/note/1173355)

```gantt
title “约了么”项目开发流程
section 项目确定
	可行性分析报告（FAR）		:a1, 2018-03-21, 7d
	软件开发计划（SDP）		:a2, after a1, 7d
	CASE工具调查分析			:a3, after a2, 7d
	软件需求变更单				:a4, after a3, 7d
	软件需求规格说明(SRS)		:a5, after a4, 28d
section 项目实施
	软件(结构)设计说明(SDD)	:2018-5-16, 14d
	编码                    :2018-5-6, 30d
section 项目验收
    发布                    :3d
    验收                    :3d
```

## SimpleHttpUtils
### 案例主函数入口
```java
package com.xiets.http;

public class Main {

    public static void main(String[] args) throws Exception {
        // GET 请求, 返回响应文本
        String html = SimpleHttpUtils.get("http://blog.csdn.net/");

        System.out.println(html);
    }

}
```

### 接口说明
* 全局设置

```java
// (可选) 设置默认的User-Agent是否为移动浏览器模式, 默认为PC浏览器模式
public static void setMobileBrowserModel(boolean isMobileBrowser);

// (可选) 设置默认的请求头, 每次请求时都将会 添加 并 覆盖 原有的默认请求头
public static void setDefaultRequestHeader(String key, String value);

// (可选) 设置 连接 和 读取 的超时时间, 连接超时时间默认为15000毫秒, 读取超时时间为0(即不检查超时)
public static void setTimeOut(int connectTimeOut, int readTimeOut);
```

* GET 请求

```java
// 返回响应文本
public static String get(String url);
public static String get(String url, Map<String, String> headers);

// 下载文件, 返回文件路径
public static String get(String url, File saveToFile);
public static String get(String url, Map<String, String> headers, File saveToFile);
```
* POST 请求

```java
// 提交数据, 返回响应文本
public static String post(String url, byte[] body);
public static String post(String url, Map<String, String> headers, byte[] body);

// 上传文件, 返回响应文本
public static String post(String url, File bodyFile);
public static String post(String url, Map<String, String> headers, File bodyFile);

// 从输入流中读取数据上传, 返回响应文本
public static String post(String url, InputStream bodyStream);
public static String post(String url, Map<String, String> headers, InputStream bodyStream);
```

* 通用的 HTTP / HTTPS 请求

```java
/*
 * 每一个参数的说明详见最后的 SimpleHttpUtils 类中的代码,
 * 该接口繁琐，建设直接使用上面说明的 get(...) 和 post(...) 方法
 */
public static String sendRequest(String url, 
                                 String method, 
                                 Map<String, String> headers, 
                                 InputStream bodyStream, 
                                 File saveToFile);
```


## 参考资料
[轻量级简易 Java http 网络请求的封装: SimpleHttpUtils](https://blog.csdn.net/xietansheng/article/details/70478221)
