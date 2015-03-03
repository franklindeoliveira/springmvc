<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
	<body>
		<h2>Ol� mundo Spring MVC!</h2>
		<fmt:message key="mensagem.boasvindas"/>
		<ul>
			<li><a href="http://localhost:8080/springmvc/bind">GO</a> - Exemplo de bind utilizando um bean.</li>
			<li><a href="http://localhost:8080/springmvc/dao">GO</a> - Exemplo de acesso ao banco de dados utilizando inje��o de depend�ncia.</li>
			<li><a href="http://localhost:8080/springmvc/beanValidation">GO</a> - Exemplo de valida��o utilizando Bean Validation.</li>
		</ul>
	</body>
</html>