<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="todoList" value="${requestScope.todoList }" />
<c:set var="doingList" value="${requestScope.doingList }" />
<c:set var="doneList" value="${requestScope.doneList }" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="./resources/css/common.css"/>
<link rel="stylesheet" href="./resources/css/main.css"/>
<title>Todo</title>
</head>
<body>
	<main>
	<article>
		<h2 class="main-logo">나의 해야할 일들</h2>
		<a href="TodoFormServlet" class="main-newTodo">새로운 TODO 등록</a>
		<section class="todos-container">
			<ul id="TODO" class="todos-col">
				<h3 class="todos-title todo-card">TODO</h3>
				<c:forEach items="${todoList }" var="todo">
					<li class="todo-card" id="${todo.id}">
						<h3 class="todo-card-title">${todo.title }</h3>
						<p class="todo-card-text">
							등록날짜 : ${todo.regdate}, ${todo.name}, 우선순위 ${todo.sequence }
						</p>
						<button class="js__update-btn" onclick="updateType(event);">➡️</button>
					</li>
				</c:forEach>
			</ul>
			<ul id="DOING" class="todos-col">
				<h3 class="todos-title todo-card">DOING</h3>
				<c:forEach items="${doingList }" var="doing">
					<li class="todo-card" id="${doing.id}">
						<h3 class="todo-card-title">${doing.title }</h3>
						<p class="todo-card-text">
							등록날짜 : ${doing.regdate}, ${doing.name}, 우선순위 ${doing.sequence }
						</p>
						<button class="js__update-btn" onclick="updateType(event);">➡️</button>
					</li>
				</c:forEach>
			</ul>
			<ul id="DONE" class="todos-col">
				<h3 class="todos-title todo-card">DONE</h3>
				<c:forEach items="${doneList }" var="done">
					<li class="todo-card" id="${done.id}">
						<h3 class="todo-card-title">${done.title }</h3>
						<p class="todo-card-text">
						등록날짜 : ${done.regdate}, ${done.name}, 우선순위 ${done.sequence }
						</p>
					</li>
				</c:forEach>
			</ul>
		</section>
	</article>
	</main>
	<script>

		function modifyType(id, type) {
			var uri = "TodoTypeServlet/"+id;
			var data = {};
			data.type = type;
			var json = JSON.stringify(data);
			
			var xhr = new XMLHttpRequest();
			xhr.open("PUT", uri, true);
			xhr.setRequestHeader('Content-type','application/json; charset=utf-8');
			xhr.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					if (this.responseText === 'success') {
						moveCard(id, type);
					} else {
						alert("Todo없데이트에 실패하였습니다.");
					}
				}
			}
			xhr.send(json);
		}
		function updateType(event) {
			var btn = event.target;
			var id = btn.parentElement.id;
			var type = btn.parentElement.parentElement.id;
			modifyType(id, type);
		}
		function moveCard(id, type) {
			var ul = document.getElementById(type);
			var li = document.getElementById(id);

			if (type == 'TODO') {
				ul.removeChild(li);
				document.getElementById('DOING').append(li);
			} else {
				var btn = li.querySelector('.js__btn');
				ul.removeChild(li);
				li.removeChild(btn);
				document.getElementById('DONE').append(li);
			}
		}
	</script>
</body>
</html>
