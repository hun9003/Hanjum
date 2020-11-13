<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보</title>
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
	<section id = "writeForm">
		<h2>회원정보</h2>
		<form action="UserUpdatePro.uo" method="post" enctype="multipart/form-data" name="Userform">
			<table>
				<tr>
					<td class="td_left"><label for="user_id">아이디</label></td>
					<td class="td_right"><input type="text" name="user_id" id="user_id" required="required"/></td>
				</tr>
				<tr>
					<td class="td_left"><label for="user_pass">비밀번호</label></td>
					<td class="td_right"><input name="user_pass" type="password" id="user_pass" required="required"/></td>
				</tr>
				<tr>
					<td class="td_left"><label for="user_name">이름</label></td>
					<td class="td_right"><input name="user_name" type="text" id="user_name" required="required"/></td>
				</tr>
				<tr>
					<td class="td_left"><label for="user_email">이메일</label></td>
					<td class="td_right"><input type="text" id="User_email" name="user_email" required="required"/></td>
				</tr>
				<tr>
					<td class="td_left"><label for="user_phone">핸드폰 번호</label></td>
					<td class="td_right"><input type="text" name="user_phone" id="user_phone" required="required"/></td>
				</tr>
				
			</table>
			<section id="commandCell">
				<input type="submit" value="정보 수정">&nbsp;&nbsp;
				<input type="reset" value="취소"/>
			</section>
		</form>
	</section>
</body>
</html>