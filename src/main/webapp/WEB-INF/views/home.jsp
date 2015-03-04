<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
	<body>
		<h2><fmt:message key="olaMundo"/> Spring MVC!</h2>
		<ul>
			<li><a href="http://localhost:8080/springmvc/bind">GO</a> - Exemplo de bind utilizando um bean.</li>
			<li><a href="http://localhost:8080/springmvc/dao">GO</a> - Exemplo de acesso ao banco de dados utilizando injeção de dependência.</li>
			<li><a href="http://localhost:8080/springmvc/beanValidation">GO</a> - Exemplo de validação utilizando Bean Validation.</li>
			<li><a href="?locale=en_US">Inglês</a> <a href="?locale=pt_BR">Português</a> - Exemplo de intercionalização de mensagens.</li>
		</ul>
	</body>
</html>