# Howtos

1. Bind request parameter

    Encapsulando parâmetros de requisições em objetos javabean.

2. Injeção de dependência

    Utilização de injeção de dependência para acesso a banco de dados.

3. Bean validation

    Validação de campos utilizando annotations e carregamento de mensagens a partir de arquivos properties.

4. Internacionalização de mensagens

    Definindo mais de um idioma para o site.
    
---
1 - Bind request parameter

bind.jsp
``` jsp
<html>
	<body>
		<h2>Exemplo de bind utilizando um bean</h2>
		<form action="bind">
			Parametro 1 <input name="parametro1" type="text" /><br/>
			Parametro 2 <input name="parametro2" type="text" /><br/>
			Parametro 3 <input name="parametro3" type="text" /><br/>
			Parametro 4 
			<select name="parametro4">
				<option value="INDEFINIDO">Selecione</option>
				<option value="UM">1</option>
				<option value="DOIS">2</option>
				<option value="TRES">3</option>
			</select><br/>
			<input type="submit" />
		</form>
	</body>
</html>
```
Enum.java
``` java
package br.com.springmvc.modelo;

public enum Enum {
	INDEFINIDO,
	UM,
	DOIS,
	TRES
}
```
Bean.java
``` java
package br.com.springmvc.modelo;

public class Bean {
	
	private String parametro1;
	private String parametro2;
	private String parametro3;
	private Enum parametro4;
	
	public String getParametro1() {
		return parametro1;
	}
	public void setParametro1(String parametro1) {
		this.parametro1 = parametro1;
	}
	public String getParametro2() {
		return parametro2;
	}
	public void setParametro2(String parametro2) {
		this.parametro2 = parametro2;
	}
	public String getParametro3() {
		return parametro3;
	}
	public void setParametro3(String parametro3) {
		this.parametro3 = parametro3;
	}
	public Enum getParametro4() {
		return parametro4;
	}
	public void setParametro4(Enum parametro4) {
		this.parametro4 = parametro4;
	}
	@Override
	public String toString() {
		return "Bean [parametro1=" + parametro1 + ", parametro2=" + parametro2
				+ ", parametro3=" + parametro3 + ", parametro4=" + parametro4
				+ "]";
	}
}
```

---
2 - Injeção de dependência

pom.xml
``` xml
<!-- Driver do banco de dados HSQLDB -->
<dependency>
	<groupId>org.hsqldb</groupId>
	<artifactId>hsqldb</artifactId>
	<version>2.3.1</version>
</dependency>

<!-- Driver do banco de dados MySQL -->
<dependency>
	<groupId>mysql</groupId>
	<artifactId>mysql-connector-java</artifactId>
	<version>5.1.34</version>
</dependency>

<!-- Driver do banco de dados Postgres -->
<dependency>
	<groupId>org.postgresql</groupId>
	<artifactId>postgresql</artifactId>
	<version>9.4-1201-jdbc41</version>
</dependency>
```
spring-context.xml
``` xml
<!-- Configuração do datasource para conexão com o banco de dados hsql -->
<bean name="hsqlDataSource" class="org.apache.commons.dbcp2.BasicDataSource">
	<property name="driverClassName" value="org.hsqldb.jdbcDriver"></property>
	<property name="url" value="jdbc:hsqldb:mem:."></property>
	<property name="username" value="SA"></property>
	<property name="password" value=""></property>
</bean>

<!-- Configuração do datasource para conexão com o banco de dados mysql -->
<bean name="mysqlDataSource" class="org.apache.commons.dbcp2.BasicDataSource">
	<property name="driverClassName" value="com.mysql.jdbc.Driver"></property>
	<property name="url" value="jdbc:mysql://127.0.0.1:3306/dbmysql"></property>
	<property name="username" value="root"></property>
	<property name="password" value=""></property>
</bean>

<!-- Configuração do datasource para conexão com o banco de dados postgres -->
<bean id="postgresDataSource" class="org.apache.commons.dbcp2.BasicDataSource">
	<property name="driverClassName" value="org.postgresql.Driver"/>
	<property name="url" value="jdbc:postgresql://localhost:5432/postgres"/>
	<property name="username" value="postgres"/>
	<property name="password" value="postgres"/>
</bean>
```
DAOController.java
``` Java
package br.com.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.springmvc.dao.DAO;

@Controller
public class DAOController {
	
	private DAO dao;

	@Autowired
	public DAOController(DAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping("/dao")
	public String conexaoEstabelecida() {
		dao.create();
		dao.read();
		dao.update();
		dao.delete();
		return "home";
	}

}
```
DAO.java
``` Java
package br.com.springmvc.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class DAO {

	private Connection connection;

	/**
	 * Construtor que cria uma conexão com o banco de dados de acordo com o
	 * <code>id</code> do datasource configurado no spring-contex.xml, que é
	 * passado como parâmetro do <code>@Qualifier</code>. Neste exemplo
	 * poderemos ter os parâmetros 'hsqlDatSource', 'mysqlDataSource' ou
	 * 'postgresDataSources'.
	 * 
	 * @param ds
	 */
	@Autowired
	public DAO(@Qualifier("mysqlDataSource") DataSource ds) {
		try {
			this.connection = ds.getConnection();
			System.out
					.println("Conexão com o banco de dados estabelecida com sucesso.");
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public void create() {
		try {
			connection.prepareStatement("INSERT INTO tabela VALUES ('teste')")
					.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void read() {
		try {
			connection.prepareStatement("SELECT * FROM tabela").executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		try {
			connection
					.prepareStatement(
							"UPDATE tabela SET coluna1 = 'coluna-update' WHERE coluna1 = 'coluna'")
					.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete() {
		try {
			connection.prepareStatement("DELETE FROM tabela").execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
```

---
3 - Bean validation

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
beanValidation.jsp
``` jsp
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
	<body>
		<h2>Exemplo de bean validation</h2>
		<form action="beanValidation">
			<form:errors path="beanValidation.parametro1"/>
			<p>Parametro 1 <input name="parametro1" type="text" value="parametro1"/></p>
			<form:errors path="beanValidation.parametro2"/>
			<p>Parametro 2 <input name="parametro2" type="number" value="2"/></p>
			<p>Parametro 3 <input name="parametro3" type="text" value="01/01/2001"/></p>
			<p>Parametro 4 
			<select name="parametro4">
				<option value="INDEFINIDO">Selecione</option>
				<option value="UM">1</option>
				<option value="DOIS">2</option>
				<option value="TRES">3</option>
			</select></p>
			<input type="submit" />
		</form>
	</body>
</html>
```
BeanValidation.java
``` java
package br.com.springmvc.modelo;

import java.util.Calendar;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class BeanValidation {

	@Size(min = 5, message = "{mensagem.erro.tamanhoMinimo}")
	private String parametro1;

	@NotNull(message = "{mensagem.erro.campoObrigatorio}")
	private Integer parametro2;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Calendar parametro3;

	private Enum parametro4;

	public String getParametro1() {
		return parametro1;
	}

	public void setParametro1(String parametro1) {
		this.parametro1 = parametro1;
	}

	public Integer getParametro2() {
		return parametro2;
	}

	public void setParametro2(Integer parametro2) {
		this.parametro2 = parametro2;
	}

	public Calendar getParametro3() {
		return parametro3;
	}

	public void setParametro3(Calendar parametro3) {
		this.parametro3 = parametro3;
	}

	public Enum getParametro4() {
		return parametro4;
	}

	public void setParametro4(Enum parametro4) {
		this.parametro4 = parametro4;
	}

	@Override
	public String toString() {
		return "BeanValidation [parametro1=" + parametro1 + ", parametro2="
				+ parametro2 + ", parametro3=" + parametro3 + ", parametro4="
				+ parametro4 + "]";
	}

}
```
BeanValidationController.java
``` java
package br.com.springmvc.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.springmvc.modelo.BeanValidation;

@Controller
public class BeanValidationController {
	
	@RequestMapping("/beanValidation")
	public String execute(@Valid BeanValidation beanValidation, BindingResult result) {
		if (result.hasErrors()) {
			return "beanValidation";
		}
		System.out.println(beanValidation.toString());
		return "beanValidation";
	}
}
```
Para exibição das mensagens de erro do Bean Validation do Spring MVC basta criar um arquivo chamado ValidationMessages.properties dentro do diretório src/main/resources.

---
4 - Internacionalização de mensagens

messages_pt_BR.properties
``` properties
olaMundo=Olá mundo
```
messages_en_US.properties
``` properties
olaMundo=Hello world
```
spring.context.xml
``` xml
<!-- Configuração para mensagens internacionalizadas.
Para alternar entre as mensagens basta enviar uma requisição informando como parâmetro
o 'locale', por exemplo, \?locale=pt_BR para português e \?locale=en_US para inglês -->
<bean id="messageSource" class="org.springframework.context.support.ReloadableResourceBundleMessageSource">
	<property name="basename" value="/WEB-INF/mensagens/messages" />
</bean>
<mvc:interceptors>
<!-- Changes the locale when a 'locale' request parameter is sent; e.g. /?locale=de -->
<bean class="org.springframework.web.servlet.i18n.LocaleChangeInterceptor" />
</mvc:interceptors>
<bean id="localeResolver" class="org.springframework.web.servlet.i18n.CookieLocaleResolver" />
```
home.jsp
``` jsp
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
	<body>
		<h2><fmt:message key="olaMundo"/> Spring MVC!</h2>
		<ul>
			<li><a href="http://localhost:8080/springmvc/bind">GO</a> - Exemplo de bind utilizando um bean.</li>
			<li><a href="http://localhost:8080/springmvc/dao">GO</a> - Exemplo de acesso ao banco de dados utilizando injeção de dependência.</li>
			<li><a href="http://localhost:8080/springmvc/beanValidation">GO</a> - Exemplo de validação utilizando Bean Validation.</li>
			<li><a href="?locale=en_US">Inglês</a> <a href="?locale=pt_BR">Português</a> - Exemplo de internacionalização de mensagens.</li>
		</ul>
	</body>
</html>
```
