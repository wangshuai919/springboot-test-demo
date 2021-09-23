## Swagger 配置和使用说明
### 选择Swagger原因
- 接口文档在线自动生成
- 接口在线调试功能
- 文档与代码可以保持同步（因为文档的方法，参数和模型紧密集成到服务端的代码）
### 版本：采用io.springfox，它是Swagger规范的实现，整合了SpringBoot
### 配置步骤：
    1. build.gradle 中增加以下依赖：
        implementation 'io.springfox:springfox-swagger2:2.9.2'
        implementation 'io.springfox:springfox-swagger-ui:2.9.2'
    2. 设置 SwaggerConfig（可将示例中的SwaggerConfig文件拷贝到项目中，并进行修改）
        2.1 配置并开启swagger
            @Configuration
            @EnableSwagger2
        2.2 修改SwaggerConfig扫描路径
    3. 使用Swagger注解在接口类中增加接口说明

### Swagger注解及属性说明：
- @Api：用在请求的类上，表示对类的说明
  - tags="说明该类的作用，可以在UI界面上看到的注解"
  - value="该参数没什么意义，在UI界面上也看到，所以不需要配置"
- @ApiOperation：用在请求的方法上，说明方法的用途、作用
  - value="说明方法的用途、作用"
  - notes="方法的备注说明"
- @ApiImplicitParams：用在请求的方法上，表示一组参数说明
- @ApiImplicitParam：用在@ApiImplicitParams注解中，指定一个请求参数的各个方面
  - name：参数名 
  - value：参数的汉字说明、解释 
  - required：参数是否必须传 
  - paramType：参数放在哪个地方 
    - header --> 请求参数的获取：@RequestHeader 
    - query --> 请求参数的获取：@RequestParam 
    - path（用于restful接口）--> 请求参数的获取：@PathVariable 
    - body 以form表单的形式提交 仅支持POST
    - form 流的形式提交 仅支持POST
  - dataType：参数类型，默认String，其它值dataType="Integer" 
  - defaultValue：参数的默认值
  
- @ApiResponses：用在请求的方法上，表示一组响应
- @ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息 
  - code：数字，例如400 
  - message：信息，例如"请求参数没填好"
  - response：抛出异常的类
  
- @ApiModel：用于响应类上，表示一个返回响应数据的信息 （这种一般用在post创建的时候，使用@RequestBody这样的场景，
请求参数无法使用@ApiImplicitParam注解进行描述的时候）
- @ApiModelProperty：用在属性上，描述响应类的属性
- @ApiIgnore：该注解忽略这个API 


### 查看文档
    1. 启动服务，在浏览器打开 http://localhost:8080/swagger-ui.html
    2. 接口json格式描述：http://localhost:8080/v2/api-docs