<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style type="text/css">
#registFrm {
	width : 500px;
	height : 610px;
	border : 1px solid red;
	margin : auto;
}

h2 {	
	text-align: center;
}

table {
	margin : auto;
	width : 450px;
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
	<section id = "loginForm">
		<h2>로그인</h2>
		<form action="UserLoginPro.uo" method="post" enctype="multipart/form-data" name="Userform">
			<table>
				<tr>
					<td class="td_left"><label for="User_id">아이디</label></td>
					<td class="td_right"><input type="text" name="User_id" id="User_id" required="required"/></td>
				</tr>
				<tr>
					<td class="td_left"><label for="User_pass">비밀번호</label></td>
					<td class="td_right"><input name="User_pass" type="password" id="User_pass" required="required"/></td>
				</tr>
			</table>
			<section id="commandCell">
				<input type="submit" value="로그인">&nbsp;&nbsp;
				<input type="reset" value="취소"/>
			</section>
		</form>
	</section>
</body>
</html>