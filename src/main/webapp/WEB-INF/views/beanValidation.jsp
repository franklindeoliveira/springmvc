<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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