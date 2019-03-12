<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="./resources/css/common.css" type="text/css" />
<link rel="stylesheet" href="./resources/css/todoForm.css"
	type="text/css" />
<title>Todo</title>
</head>
<body>
	<main>
		<article>
			<section class="todoInput-container">
				<form action="TodoAddServlet" method="POST" class="todoInput-form">
					<h2 class="todoInput-title row">할일 등록</h2>
					<div class="todoInput-text row">
						<label for="title">어떤일인가요?</label> 
						<input type="text" name="title" id="title" placeholder="Edwith강의 듣기(24자까지)" required maxlength="24" />
					</div>
					<div class="todoInput-text row">
						<label for="name">누가 할일인가요?</label>
						<input type="text" name="name" id="name" placeholder="홍길동" required />
					</div>
					<div class="todoInput-radio row">
						<label class="todoInput-radio__title">우선순위를 선택하세요</label> 
						<label class="todoInput-radio__text" for="sequence1"> 
							<input type="radio" name="sequence" id="sequence1" value="1" required />1순위
						</label> 
						<label class="todoInput-radio__text" for="sequence2"> 
							<input type="radio" name="sequence" id="sequence2" value="2" required />2순위
						</label> 
						<label class="todoInput-radio__text" for="sequence3"> 
							<input type="radio" name="sequence" id="sequence3" value="3" required />3순위
						</label>
					</div>
					<div class="todoInput-btn row">
						<div class="todoInput-btn__left">
							<a class="btn btn-back" href="#" onclick="history.back()">이전</a>
						</div>
						<div class="todoInput-btn__right">
							<input class="btn btn-form" type="submit" value="제출" /> 
							<input class="btn btn-form" type="reset" value="내용지우기" />
						</div>
					</div>
				</form>
			</section>
		</article>
	</main>
</body>
</html>
