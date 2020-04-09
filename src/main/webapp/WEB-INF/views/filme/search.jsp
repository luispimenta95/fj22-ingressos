<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="ingresso"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<ingresso:template>
	<jsp:body>

			 <form method="get" action="/filme/search">
        <input type="text" name="keyword" /> &nbsp;
        <input type="submit" value="Search" />
    </form>
		<h2 class="text-center">Filmes</h2>
		
		<div class=" col-md-6 col-md-offset-3">
		<table class="table table-hover ">
			<thead>
				<tr>
					<th>Nome</th>
					<th>Gênero</th>
					<th>Duração</th>
					<th>Preço do ingresso </th>
						<th colspan="2" class="text-center">Ações</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="filme" items="${result}">
					<tr>
						<td>${filme.nome}</td>
						<td>${filme.genero.nome}</td>
					<td>${filme.duracao.toMinutes()} minutos</td>
																	
					
					<td>R$ ${filme.preco}</td>
						
						<td>
							<a onclick="excluir(${filme.id})" class="btn btn-danger"> <span
									class="glyphicon glyphicon-trash"></span> Excluir</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="col-md-6 col-md-offset-3">
			<a href="/admin/filme" class="btn btn-block btn-info">Novo</a>
		</div>
		</div>
		<script>
			function excluir(id) {
				var url = window.location.href;
				$.ajax({
					url:"/admin/filme/" + id,
					type: 'DELETE',
					success: function (result) {
						console.log(result);

						window.location.href = url;
					}
				});
			}
		</script>
	</jsp:body>
</ingresso:template>
