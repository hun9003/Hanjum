<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/default.css" rel="stylesheet">
<script src="js/jquery-3.5.1.js"></script>
<script src="js/topMenu.js"></script>
<title>회원가입</title>
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
<script src="../js/jquery-3.5.1.js"></script>
<script type="text/javascript">
	var checkIdResult = false, checkPasswdResult = false;
$(document).ready(function(){
	$('#user_id').blur(function(){
		var element = document.getElementById('id_check');
		var user_id = $('#user_id').val();
		$.ajax({
			url : '${pageContext.request.contextPath}/UserCheck.uo?user_id='+ user_id,
			type : 'get',
			success : function(data){
				$("#checkIdResult").html(data);
				
				if (data == 1){
					$("#checkIdResult").html("이미 사용 중인 아이디 입니다.");
				}else if(user_id == "") {
					$("#checkIdResult").html("아이디를 입력해 주세요 :)");
				}else {
					$("#checkIdResult").html("사용가능");
				}
			}			
		});
	});
});
	
	
	
	
	function checkId(idForm) { 
		var id = user_idForm.value; 
		
		var element = document.getElementById('checkIdResult');
		
		var regex = /^[A-Za-z][A-Za-z0-9]{3,11}$/g;
		
		if(regex.exec(id)) { 
			element.innerHTML = "사용 가능";
			checkIdResult = true;
		} else { 
			element.innerHTML = "사용 불가";
			checkIdResult = false;
		}
		
	}
	function checkUserID(id){
		var id = user_id.value;
	}
	
	function checkPasswd(passwdForm) { 
		var passwd = passwdForm.value; 
		var element = document.getElementById('checkPasswdResult');
		var langthRegex = /^[A-Za-z0-9!@#$%]{4,16}$/;
		var upperCaseRegex = /[A-Z]/;
		var lowerCaseRegex = /[a-z]/;
		var digitRegex = /[0-9]/;
		var specRegex = /[!@#$%]/;
		
		if (langthRegex.exec(passwd)){
			var count = 0;
			if (upperCaseRegex.exec(passwd)) {count ++;} 
			if (lowerCaseRegex.exec(passwd)) {count ++;} 
			if (digitRegex.exec(passwd)) {count ++;} 
			if (specRegex.exec(passwd)){count ++;}
			
			if (count == 4){
				element.innerHTML ="사용 가능(안전)";
				checkPasswdResult = true;
			} else if(count == 3) {
				element.innerHTML ="사용 가능(보통)";
				checkPasswdResult = true;
			} else if(count == 2) {
				element.innerHTML ="사용 가능(위험)";
				checkPasswdResult = true;
			} else {
				element.innerHTML ="사용 불가(두 가지 이상 조합)";
				checkPasswdResult = false;
			}
		} else { 
			element.innerHTML = "사용 불가";
			checkPasswdResult = false;
		}
	}
	function check() {
		if(checkIdResult && checkPasswdResult) {
			return true;
		} else {
			alert('아이디 또는 패스워드 규칙 확인 필수!');
			return false;
		}
	}
</script>
</head>
<body>
<div id="wrap">
<jsp:include page="../inc/top.jsp"/>
	<section id = "writeForm">
		<h2>회원가입</h2>
		<form action="UserInsertPro.uo" method="post" name="Userform" onsubmit="return check()">
			<table>
				<tr>
					<td class="td_left"><label for="user_id">아이디</label></td>
					<td class="td_right"><input type="text" name="user_id" id="user_id" placeholder="4-12자리 영문,숫자 조합" required="required" onkeyup="checkId(this)"/>
					<div id="checkIdResult"><!-- 자바스크립트에서 메세지 출력 공간 --></div></td>
					
				</tr>
				<tr>
					<td class="td_left"><label for="user_pass">비밀번호</label></td>
					<td class="td_right"><input name="user_pass" type="password" id="user_pass" required="required" placeholder="4-16자리 영문,숫자,특수문자 조합" onkeyup="checkPasswd(this)"/><div id="checkPasswdResult"><!-- 자바스크립트에서 메세지 출력 공간 --></div></td>
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
				<input type="submit" value="회원가입">&nbsp;&nbsp;
				<input type="reset" value="취소"/>
			</section>
		</form>
	</section>
	<jsp:include page="../inc/bottom.jsp"/>
</div>
</body>
</html>