<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/default.css" rel="stylesheet">
<script src="js/jquery-3.5.1.js"></script>
<script src="js/topMenu.js"></script>
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
<script>
function searchPass(){
	document.getElementById("userform").style.display = "none";
	document.getElementById("formTop").html ="비밀번호 찾기";
	document.getElementById("searchform").style.display = "block";
}
</script>
</head>
<body>
<div id="wrap">
<jsp:include page="../inc/top.jsp"/>
	<section id = "loginForm">
		<h2 id="formTop">로그인</h2>
		<form action="UserLoginPro.uo" method="post" name="Userform" style="display: block;" id ="userform">
			<table>
				<tr>
					<td class="td_left"><label for="user_id">아이디</label></td>
					<td class="td_right"><input type="text" name="user_id" id="user_id" required="required"/></td>
				</tr>
				<tr>
					<td class="td_left"><label for="user_pass">비밀번호</label></td>
					<td class="td_right"><input name="user_pass" type="password" id="user_pass" required="required"/><a onclick="searchPass()">비밀번호 찾기</a></td>
				</tr>
			</table>
			<section id="commandCell">
				<input type="submit" value="로그인">&nbsp;&nbsp;
				<input type="reset" value="취소"/>
			</section>
		</form>
		<form action="UserSearchPassPro.uo" method="post" name="Userform" style="display: none;" id ="searchform">
			<table>
				<tr>
					<td class="td_left"><label for="user_id">아이디</label></td>
					<td class="td_right"><input type="text" name="user_id" id="user_id" required="required"/></td>
				</tr>
				<tr>
					<td class="td_left"><label for="user_email">이메일</label></td>
					<td class="td_right"><input name="user_email" type="text" id="user_email" required="required"/><a onclick="searchPass()">비밀번호 찾기</a></td>
				</tr>
			</table>
			<section id="commandCell">
				<input type="submit" value="인증코드 전송">&nbsp;&nbsp;
			</section>
		</form>
	</section>
	<jsp:include page="../inc/bottom.jsp"/>
</div>
</body>
</html>