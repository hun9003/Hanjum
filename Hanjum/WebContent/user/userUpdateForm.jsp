  
<%@page import="com.hanjum.user.vo.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	
	UserBean userBean = (UserBean)request.getAttribute("userBean");
	String email = userBean.getUser_email();
	String[] cutEmail = email.split("@");
	String user_type = null;
	switch (userBean.getUser_type()){
	case 1:
		user_type = "일반 회원";
		break;
	case 2:
		user_type = "편집자 회원";
		break;
	default:
		user_type = "관리자";
	}
	
	
%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="js/jquery-3.5.1.js"></script>
<script>
// 제이쿼리 시작
$(document).ready(function(){
	// 비밀번호 변경 ajax
	$("#updatePass").click(function(){
		var user_id = $('#user_id').val();
		var user_pass = $('#user_pass').val();
		var user_changePass = $('#user_updatePass').val();
		$.ajax({
			url : '${pageContext.request.contextPath}/changePass.uo?user_id='+ user_id + '&user_pass=' + user_pass + '&user_changePass=' + user_changePass,
			type : "get",
			success : function(data){
				if(data == 1) {
					alert('비밀번호 변경 성공!');
					$("#passSet").hide();
					$("#updatePass").hide();
					$("#changePass").show();
					$("#checkPasswdResult").html('');
					$("#checkPasswdResult2").html('');
					$('#user_pass').val('');
					$('#user_updatePass').val('');
					$('#user_updatePass2').val('');
				} else {
					alert('비밀번호 변경 실패!');
					$('#user_pass').val('');
					$('#user_pass').focus();
					$('#user_updatePass').val('');
					$('#user_updatePass2').val('');
				}
			}
		}) // ajax종료
	}) // 비밀번호 변경 종료
	
}) // 제이쿼리 종료

// 이메일 셀렉트박스 컨트롤
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


// 비밀번호 변경 누르면 변경창나오게
function changePass(){
	document.getElementById("changePass").style.display = "none";
	document.getElementById("passSet").style.display = "block";
}

// 비밀번호 확인 
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

// 비밀번호 유효성 검사
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
</script>
<meta charset="UTF-8">
<link href="css/default.css" rel="stylesheet">
<link href="css/board.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-3.5.1.js"></script>
<script type="text/javascript" src="js/topMenu.js"></script>
<script type="text/javascript" src="plugin/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="js/smartediter.js"></script>
<title>한줌에디터</title>
</head>
<body>
<div id="wrap">
<jsp:include page="../inc/top.jsp"/>

<div class="write_form">
<div class="write_title"><h1>내 정보</h1></div>
<form action="UserUpdatePro.uo" method="post" name="fr_write" id="WriteForm" >
<table class="write_table">
<tr><td class="td_name"><label for="Subject">아이디</label></td><td class="td_content"><input id="user_id" type="text" name="user_id" value="<%=userBean.getUser_id()%>" readonly="readonly"></td></tr>
<tr><td class="td_name"><label for="Content">비밀번호</label></td>
<td class="td_content">

<!-- ↓ 누르면 비밀번호 변경창 오픈 -->
<a onclick="changePass()" id ="changePass" >변경</a>
<div id="passSet" style="display: none;">
현재 비밀번호 : <input id="user_pass" name="user_pass" type="password"/><br><br>
변경 비밀번호 : <input id="user_updatePass" name="user_updatePass" type="password" onkeyup="checkPasswd(this)" />
<div id="checkPasswdResult"><!-- 비밀번호 유효성검사 메세지 출력 공간 --></div><br>
비밀번호 확인 : <input id="user_updatePass2" name="user_updatePass2" type="password" onkeyup="checkPasswd2(this)"/>
<div id="checkPasswdResult2"><!-- 비밀번호 확인 메세지 출력 공간 --></div><br>
<!-- ↓ 누르면  비밀번호 변경됨-->
<a id ="updatePass" style="display: none;">변경</a> 
</div>

</td>
</tr>
<tr><td class="td_name"><label for="Content">이름</label></td><td class="td_content"><input id="user_name" type="text" name="user_name" value="<%=userBean.getUser_name()%>" readonly="readonly"/></td></tr>
<tr><td class="td_name"><label for="Content">이메일</label></td><td class="td_content"><input id="user_email" type="text" name="user_email" value="<%=cutEmail[0]%>"/>
 @ <input type="text" name="user_email2" id="user_email2" value="<%=cutEmail[1]%>" onfocus="inInput(this)" onblur="outInput(this)"/>
<select onfocus="inInput(this)" onblur="outInput(this)" onChange="selEmail(this.value)">
	<option onselect="focus">직접입력</option>
	<option value="naver.com" 
	<%if(cutEmail[1].equals("naver.com")){%> selected ="selected" <%} %>>naver.com</option>
	<option value="gmail.com"
	<%if(cutEmail[1].equals("gmail.com")){%> selected ="selected" <%} %>>gmail.com</option>
	<option value="daum.net"
	<%if(cutEmail[1].equals("daum.net")){%> selected ="selected" <%} %>>daum.net</option>
</select></td></tr>
<tr><td class="td_name"><label for="Content">휴대 전화</label></td><td class="td_content"><input id="user_phone" type="text" name="user_phone" value="<%=userBean.getUser_phone()%>"/></td></tr>
<tr><td class="td_name"><label for="Content">레벨</label></td><td class="td_content">Lv <%=userBean.getUser_level() %></td></tr>
<tr><td class="td_name"><label for="Content">경험치</label></td><td class="td_content"><%=userBean.getUser_lv_exp() %> exp</td></tr>
<tr><td class="td_name"><label for="Content">총 거래 건수</label></td><td class="td_content"><%=userBean.getUser_project_count() %> 건</td></tr>
<tr><td class="td_name"><label for="Content">내 평점</label></td><td class="td_content"><%=userBean.getUser_score() %> 점</td></tr>
<tr><td class="td_name"><label for="Content">회원 타입</label></td><td class="td_content"><%=user_type %></td></tr>
<tr><td class="td_name"><label for="Content">로그인 횟수</label></td><td class="td_content"><%=userBean.getUser_login_count() %> 회</td></tr>
</table>
	<div class="write_form_submit"><input type="submit" id="WriteSubmit" value="수정 완료">
	<input type="button" onclick="location.href='UserDeleteForm.uo?user_id=<%=userBean.getUser_id()%>'" value="회원 탈퇴"></div>
</form>
</div>

<jsp:include page="../inc/bottom.jsp"/>
</div>
</body>
</html>