### 单元测试场景及示例说明
#### service 测试示例
 - 场景描述：service实现类中包含有依赖注入的对象，entityManager；entityManager返回的有Query对象，以及Query对象执行unwrap
方法时，会出现类型不匹配或空指针之类的错误。
 - 示例代码：DepartmentServiceImplTest

#### Controller 测试示例（一）
 - 场景描述：通过MockMvc测试Controller的业务逻辑
 - 示例代码：HelloControllerTest

#### Controller 测试示例（二）
- 场景描述：通过MockMvc测试Controller的业务逻辑，包含文件上传、返回结果验证、mock service对象等场景
- 示例代码：DepartmentController