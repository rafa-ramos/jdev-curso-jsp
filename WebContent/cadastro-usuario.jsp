<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Usuários</title>
<link rel="stylesheet" href="resources/css/cadastro.css">
</head>
<body>
	<center>
		<h1>Cadastro de Usuários</h1>
		<h3 style="color: orange;">${ msg }</h3>
	</center>
	
	
	<form action="salvar-usuario" method="POST" id="formUser">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>ID:</td>
						<td><input type="text" name="id" readonly="readonly" id="id"
							value="${user.id}" class="field-long" /></td>
					</tr>
					<tr>
						<td>Login:</td>
						<td><input type="text" name="login" id="login"
							value="${user.login}" /></td>
					</tr>
					<tr>
						<td>Senha:</td>
						<td><input type="password" name="password" id="password"
							value="${user.senha}" /></td>
					</tr>
					<tr>
						<td><input type="submit" value="Salvar"> <input  type="submit" value="Cancelar" onclick="document.getElementById('formUser').action = 'salvar-usuario?acao=reset'"></td>
					</tr>
				</table>
			</li>
		</ul>
	</form>

	<div class="container">
		<table class="responsive-table">
			<caption>Listagem de Usuários</caption>
			<tr>
				<th>Id</th>
				<th>Login</th>
				<th>Senha</th>
				<th>Remover</th>
				<th>Editar</th>
				<th></th>
			</tr>
			<c:forEach items="${usuarios}" var="user">
				<tr>
					<td style="width: 150px"><c:out value="${user.id}"></c:out></td>
					<td style="width: 150px"><c:out value="${user.login}"></c:out></td>
					<td><c:out value="${user.senha}"></c:out></td>
					<td><a href="salvar-usuario?acao=delete&id=${user.id}"><img title="Remover" width="40px" height="40px" src="resources/img/depositphotos_96476554-stock-illustration-trash-bin-icon-icon-design.jpg"></a></td>
					<td><a href="salvar-usuario?acao=edit&id=${user.id}"><img title="Editar" width="20px" height="20px" src="resources/img/269074.png"></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>