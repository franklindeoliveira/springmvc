# Howtos
1. Bind request parameter

    Descrição...

2. Injeção de dependência

    Utilização de injeção de dependência para acesso a banco de dados.
    
    spring-context.xml
``` xml
    <!-- Configuração do datasource para conexão com o banco de dados -->
	<bean id="mySqlDataSource" class="org.apache.commons.dbcp2.BasicDataSource">
		<property name="driverClassName" value="org.hsqldb.jdbcDriver"></property>
		<property name="url" value="jdbc:hsqldb:mem:."></property>
		<property name="username" value="SA"></property>
		<property name="password" value=""></property>
	</bean>
```
DAOController.java
``` Java
@Autowired
public DAOController(DAO dao) {
	this.dao = dao;
}
```
DAO.java
``` Java
@Repository
public class DAO {

	private Connection connection;
	
	@Autowired
	public DAO(DataSource ds) {
		try {
			this.connection = ds.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
}
```
3. Bean validation

    pom.xml

``` xml
<!-- Bean Validation -->
		<dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-validator</artifactId>
			<version>5.1.3.Final</version>
		</dependency>
		<dependency>
			<groupId>javax.validation</groupId>
			<artifactId>validation-api</artifactId>
			<version>1.1.0.Final</version>
		</dependency>
```

    BeanValidation.java

``` java
public class BeanValidation {
	@Size(min=5, message="Este campo deve conter no mínimo 5 caracteres.")
	private String parametro1;
	@NotEmpty
	@NotNull(message="Este campo deve ser preenchido.")
	private String parametro2;
}
```

beanvalidation.jsp

``` jsp
<form:errors path="beanValidation.parametro1"/>
			Parametro 1 <input name="parametro1" type="text" value="parametro1"/><br/>
			<form:errors path="beanValidation.parametro2"/>
			Parametro 2 <input name="parametro2" type="text" value="parametro2"/><br/>
			Parametro 3 <input name="parametro3" type="text" value="parametro3"/><br/>
```
