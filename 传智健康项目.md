# 传智健康项目

## 1.项目简介

传智健康管理系统是一款应用于健康管理机构的业务系统，实现健康管理机构工作内容可视化、患者管理专业化、健康评估数字化、健康干预流程化、知识库集成化，从而提高健康管理师的工作效率，加强与患者间的互动，增强管理者对健康管理机构运营情况的了解。

系统分为传智健康后台管理系统和移动端应用两部分。其中后台系统提供给健康管理机构内部人员（比如医院工作人员）使用，微信端应用提供给健康管理机构的用户（体检用户）使用。

**项目地址：**

## 2.技术架构

![1](H:\blog\1.png)



## 3.功能架构



![2](H:\blog\2.png)

通过上面的功能架构图可以看到，传智健康后台管理系统有会员管理、预约管理、健康评估、健康干预等功能。移动端有会员管理、体检预约、体检报告等功能。后台系统和移动端应用都会通过Dubbo调用服务层发布的服务来完成具体的操作。本项目属于典型的SOA架构形式。

​	**SOA**是一种粗粒度、松耦合服务架构，服务之间通过简单、精确定义接口进行通讯，不涉及底层编程接口和通讯模型。 SOA可以看作是B/S模型、 XML （ 标准通用标记语言 的子集）/Web Service技术之后的自然延伸。 SOA将能够帮助软件工程师们站在一个新的高度理解企业级架构中的各种组件的开发、部署形式，它将帮助企业系统架构者更迅速、更可靠、更具重用性地架构整个业务系统。

## 4.项目结构

本项目采用maven分模块开发方式，即对整个项目拆分为几个maven工程，每个maven工程存放特定的一类代码，具体如下：

![10](H:\blog\10.png)



各模块职责定位：

**health_parent：**父工程，打包方式为pom，统一锁定依赖的版本，同时聚合其他子模块便于统一执行maven命令。

**health_common**：通用模块，打包方式为jar，存放项目中使用到的一些工具类、实体类、返回结果和常量类。

**health_interface**：打包方式为jar，存放服务接口。

**health_service_provider：**Dubbo服务模块，打包方式为war，存放服务实现类、Dao接口、Mapper映射文件等，作为服务提供方，需要部署到tomcat运行。

**health_backend**：传智健康管理后台，打包方式为war，作为Dubbo服务消费方，存放Controller、HTML页面、js、css、spring配置文件等，需要部署到tomcat运行。

**health_mobile**：移动端前台，打包方式为war，作为Dubbo服务消费方，存放Controller、HTML页面、js、css、spring配置文件等，需要部署到tomcat运行。

![img](H:\blog\L[_2V8AW9TJU1LZ$WY}Y~HM.png)

## 5.页面静态化

### 5.1页面静态化介绍

页面静态化其实就是将原来的动态网页(例如通过ajax请求动态获取数据库中的数据并展示的网页)改为通过静态化技术生成的静态网页，这样用户在访问网页时，服务器直接给用户响应静态html页面，没有了动态查询数据库的过程。

我们可以通过专门的页面静态化技术帮我们生成所需的静态HTML页面，例如：Freemarker、thymeleaf等。

### 5.2Freemarker介绍

FreeMarker 是一个用 Java 语言编写的模板引擎，它基于模板来生成文本输出。FreeMarker与 Web 容器无关，即在 Web 运行时，它并不知道 Servlet 或 HTTP。它不仅可以用作表现层的实现技术，而且还可以用于生成 XML，JSP 或 Java 等。

### 5.3实现步骤

```java
public static void main(String[] args) throws Exception{
    //1.创建配置类
    Configuration configuration=new Configuration(Configuration.getVersion());
    //2.设置模板所在的目录 
    configuration.setDirectoryForTemplateLoading(new File("D:\\ftl"));
    //3.设置字符集
    configuration.setDefaultEncoding("utf-8");
    //4.加载模板
    Template template = configuration.getTemplate("test.ftl");
    //5.创建数据模型
    Map map=new HashMap();
    map.put("name", "张三");
    map.put("message", "欢迎");
    //6.创建Writer对象
    Writer out =new FileWriter(new File("d:\\test.html"));
    //7.输出
    template.process(map, out);
    //8.关闭Writer对象
    out.close();
}
```







