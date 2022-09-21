# Springboot 从自定义的 yaml 文件读取配置信息

### 前提
虽然 Springboot 可以直接在 `application.properties` 中配置属性，但是有时候还是想针对某些属性
另外创建一个配置文件比较好，可以集中配置方便阅读。

### 开始
第一步，在 resources 目录创建一个文件 `custom.yaml` 用来存放自定义的配置。

Springboot 对于额外的配置有很好的支持，但是 `@PropertySource` 注解默认是不支持加载 YAML 文件的。

所以使用 `@PropertySource` 注解的话，要么使用标准的 `*.properties` 文件，要么自己实现加载 YAML 文件。

#### 自定义 PropertySourceFactory
从 Spring 4.3 开始， `@PropertySource` 注解有了一个 factory 属性，我们可以利用它来提供我们对 `PropertySourceFactory` 的自定义实现，
该实现将处理 YAML 文件处理。

第二步，创建 `YamlPropertySourceFactory` 类来实现对 YAML 文件的处理。

第三步，创建 `CustomConfig` 配置类，来装载配置文件中的信息，以便在 Spring 中使用。

```java
@SpringBootTest
class SpringbootCustomConfigurationFromYamlApplicationTests {
    @Autowired
    private CustomConfig config;

    @Test
    void contextLoads() {
    }

    @Test
    public void configTest() {
        System.out.println(config.toString());
    }

}
```
最后用上面的代码测试，能够获取到 `config` 中的数据。

### 还是有点问题
虽然经过测试，配置信息确实读取了，但是 `CustomConfig` 有点问题。

使用 SpotBugs 来检测下代码，就会提示：
> setMachine(Map) may expose internal representation by storing an externally mutable object into CustomConfig.machine
> 
> setSwitches(List) may expose internal representation by storing an externally mutable object into CustomConfig.switches
> 
> getSwitches() may expose internal representation by returning CustomConfig.switches
> 
> getMachine() may expose internal representation by returning CustomConfig.machine
> 

由于 `machine` 和 `switches` 都是属于对象属性，这个类的实例的内部状态可以在类之外更改。

相关的链接可以看参考资料的第二个链接。

按照 stackoverflow 上的说法，修改一下 `CustomConfig`，创建 `CustomConfig2`，来测试下。

再次运行 SpotBugs 来检测，发现 `CustomConfig2` 没有报错，说明问题解决了。

---

参考资料：

[@PropertySource with YAML Files in Spring Boot](https://www.baeldung.com/spring-yaml-propertysource)

[Spotbugs + Java: may expose internal representation by storing an externally mutable object into QuestionPojo.map](https://stackoverflow.com/questions/71921159/spotbugs-java-may-expose-internal-representation-by-storing-an-externally-mut)