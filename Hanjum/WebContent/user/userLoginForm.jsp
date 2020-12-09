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
// j쿼리 시작
$(document).ready(function() {
	// 비밀번호찾기 - 이메일 인증번호 전송
	$('#mail_check').click(function() {
	var email = $('#user_email').val() + "@" + $('#user_email2').val();
	var searchUser_id = $('#searchUser_id').val();
	alert('가입하신 이메일로 인증코드가 전송됩니다.');
	$.ajax({
		url : '${pageContext.request.contextPath}/mailSend?receiver='+ email +'&user_id='+searchUser_id,
		type : 'get',
		success : function(data) {
			if(data == 1) {
			$('#searchform').hide();
			$('#codeCheck').show();
			$('#codeMessage').html("메일의 인증코드를 입력 해주세요.");
			}
			else {
			alert('해당 회원 정보가 없습니다. 다시 확인 해주세요.');
			location.href='UserLoginForm.uo';
			}
		}// 석세스 종료
	}); // ajax종료
}); // click 종료

// 이메일 인증번호 재전송
$('#mail_check2').click(function() {
var email = $('#user_email').val() + "@" + $('#user_email2').val();
var searchUser_id = $('#searchUser_id').val();
alert("인증번호를 " + email + "로 재발송합니다.");
$.ajax({
	url : '${pageContext.request.contextPath}/mailSend?receiver='+ email +'&user_id='+searchUser_id,
	type : 'get'
}); // ajax종료
}); // click 종료



	// 이메일 인증번호 코드체크
	$('#email_code').keyup(function() {
	var email = $('#user_email').val() + "@" + $('#user_email2').val();
	var code = $('#email_code').val();
	$.ajax({
		url : '${pageContext.request.contextPath}/CodeCheck.uo?email='+ email + '&code=' + code,
		type : 'get',
		success : function(data) {
			if(data == 1) {
			$('#codeMessage').html("메일 인증 완료! <br>");
			$('#codeCheck').hide();
			$('#passSet').show();
			} else if(data == 0) {
			$('#codeMessage').html("아쉽게도 코드번호 그거 아니에요.. <br>");
			}
		}// 석세스 종료
	}); // ajax종료
}); // click 종료


$("#updatePass").click(function(){
	var user_id = $('#searchUser_id').val();
	var user_changePass = $('#user_updatePass').val();
	$.ajax({
		url : '${pageContext.request.contextPath}/changePass.uo?user_id=' + user_id + '&user_changePass=' + user_changePass,
		type : "get",
		success : function(data){
			if(data == 1) {
				alert('비밀번호 변경 성공! 다시 로그인 하여 주세요.');
				location.href='UserLoginForm.uo';
			}
			else {
				alert('비밀번호 변경 실패! 다시 시도해 주세요.');
				location.href='UserLoginForm.uo';
			}
		}
	}) // ajax종료
})



}); // j쿼리 종료

// 비밀번호 찾기 누르면 뜨는거
function searchPass(){
	document.getElementById("userform").style.display = "none";
	document.getElementById("formTop").html ="비밀번호 찾기";
	document.getElementById("searchform").style.display = "block";
}

//이메일 셀렉트박스 선택
function selEmail(email){
	if(email != "직접입력"){
		document.getElementById("user_email2").value = email;
		document.getElementById("user_email2").readOnly = true;
	}else{
		document.getElementById("user_email2").readOnly = false;
		document.getElementById("user_email2").value = "";
                document.getElementById("user_email2").focus();
	}
}

//비밀번호 유효성 검사
function checkPasswd(passwdForm) {
	var passwd = passwdForm.value;
	var element = document.getElementById('checkPasswdResult');
	var langthRegex = /^[A-Za-z0-9!@#$%]{4,16}$/;
	var upperCaseRegex = /[A-Z]/;
	var lowerCaseRegex = /[a-z]/;
	var digitRegex = /[0-9]/;
	var specRegex = /[!@#$%]/;

	if (langthRegex.exec(passwd)) {
		var count = 0;
		if (upperCaseRegex.exec(passwd)) {
			count++;
		}
		if (lowerCaseRegex.exec(passwd)) {
			count++;
		}
		if (digitRegex.exec(passwd)) {
			count++;
		}
		if (specRegex.exec(passwd)) {
			count++;
		}

		if (count == 4) {
			element.innerHTML = "사용 가능(안전)";
			element.style.color = "blue";
			checkPasswdResult = true;
		} else if (count == 3) {
			element.innerHTML = "사용 가능(보통)";
			element.style.color = "green";
			checkPasswdResult = true;
		} else if (count == 2) {
			element.innerHTML = "사용 가능(위험)";
			element.style.color = "orange";
			checkPasswdResult = true;
		} else {
			element.innerHTML = "사용 불가(두 가지 이상 조합)";
			element.style.color = "red";
			checkPasswdResult = false;
		}
	} else {
		element.innerHTML = "사용 불가";
		element.style.color = "red";
		checkPasswdResult = false;
	}
}

//비밀번호 확인 
function checkPasswd2(passwdForm) {
	var passwd = document.getElementById('user_updatePass').value;
	var chackPasswd = passwdForm.value;
	var element = document.getElementById('checkPasswdResult2');
	
	if(passwd==chackPasswd){
		element.innerHTML = "비밀번호 일치";
		element.style.color = "blue";
		document.getElementById("updatePass").style.display = "block";
	} else {
		element.innerHTML = "비밀번호를 확인해주세요.";
		element.style.color = "red";
		document.getElementById("updatePass").style.display = "none";
	}
	
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
		<form name="Userform" style="display: none;" id ="searchform">
			<table>
				<tr>
					<td class="td_left"><label for="user_id">아이디</label></td>
					<td class="td_right"><input type="text" name="searchUser_id" id="searchUser_id" required="required"/></td>
				</tr>
				<tr>
					<td class="td_left"><label for="user_email">이메일</label></td>
					<td class="td_right"><input type="text" id="user_email"
							name="user_email" required="required" /> @ <input type="text"
							name="user_email2" id="user_email2" onfocus="inInput(this)"
							onblur="outInput(this)" /> <select onfocus="inInput(this)"
							onblur="outInput(this)" onChange="selEmail(this.value)">
								<option onselect="focus">직접입력</option>
								<option value="naver.com">naver.com</option>
								<option value="gmail.com">gmail.com</option>
								<option value="daum.net">daum.net</option>
						</select></td>
				</tr>
			</table>
			<section id="commandCell">
				<input type="button" value="인증코드 전송" id="mail_check">&nbsp;&nbsp;
			</section>
		</form>
		<div id="codeMessage" align="center"><!-- 코드번호 틀린거 맞는거 들어갈칸 -->
	</div>
	<div id="codeCheck" style="display: none;" align="center">
	<input type="text" name="email_code" id="email_code"> <input type="button" value="인증코드 재전송" name="mail_check2" id="mail_check2">
	</div>
	
	<div id="passSet" style="display: none;" align="center">
	변경 비밀번호 : <input id="user_updatePass" name="user_updatePass" type="password" onkeyup="checkPasswd(this)" />
	<div id="checkPasswdResult"><!-- 비밀번호 유효성검사 메세지 출력 공간 --></div><br>
	비밀번호 확인 : <input id="user_updatePass2" name="user_updatePass2" type="password" onkeyup="checkPasswd2(this)"/>
	<div id="checkPasswdResult2"><!-- 비밀번호 확인 메세지 출력 공간 --></div><br>
	<!-- ↓ 누르면  비밀번호 변경됨-->
	<a id ="updatePass" style="display: none;">변경</a> 
	</div>
	</section>
	<jsp:include page="../inc/bottom.jsp"/>
</div>
</body>
</html>