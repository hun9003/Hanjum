<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC_Board</title>
<style type="text/css">
	#registForm {
		width: 500px;
		height: 610px;
		border: 1p solid red;
		margin: auto;
	}
	
	h1 {
		text-align: center;
	}
	
	table {
		margin: auto;
		width: 450px;
	}
	
	.td_left {
		width: 150px;
		background: orange;
	}
	
	.td_right {
		width: 300px;
		background: skyblue;
	}
	
	#commandCell {
		text-align: center;
	}
</style>
</head>
<body>
	<!-- 게시판 등록 -->
	<section id="writeForm">
		<h1>게시판 글 등록</h1>
		<form action="HelpWritePro.hp" method="post" enctype="multipart/form-data" name="boardForm">
			<table>
				<tr>
					<td class="td_left"><label for="board_name">질문</label></td>
					<td class="td_right"><input type="text" name="help_question" id="help_question" required="required"/></td>
				</tr>
				<tr>
					<td class="td_left"><label for="board_content">답변</label></td>
					<td class="td_right">
						<textarea id="help_answer" name="help_answer" cols="40" rows="15" required="required" style="resize: none;"></textarea>
					</td>
				</tr>
			</table>
			<section id="commandCell">
				<input type="submit" value="등록">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기">
			</section>
		</form>
	</section>
	
</body>
</html>