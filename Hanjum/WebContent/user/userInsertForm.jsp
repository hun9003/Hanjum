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
	width: 500px;
	height: 610px;
	border: 1px solid red;
	margin: auto;
}

h2 {
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
<script src="../js/jquery-3.5.1.js"></script>
<script type="text/javascript">
	var checkIdResult = false, checkPasswdResult = false, checkEmailResult = false; 
	$(document).ready(function() {
				$('#user_id').blur(function() {
				var element = document.getElementById('id_check');
				var user_id = $('#user_id').val();
				var regex = /^[A-Za-z][A-Za-z0-9]{3,11}$/g;
				$.ajax({
						url : '${pageContext.request.contextPath}/UserCheck.uo?user_id='+ user_id,
						type : 'get',
						success : function(data) {
						$("#checkIdResult").html(data);
						if (data == 1) {
							$("#checkIdResult").html("이미 사용 중인 아이디 입니다.");
						} else {
							if (user_id == "") {
							$("#checkIdResult").html("아이디를 입력해 주세요 :)");
							} else if (regex.exec(user_id)){
								$("#checkIdResult").html("사용가능");
								checkIdResult = true;
							} else {
								$("#checkIdResult").html("사용불가");
							}
						}
					} // 석세스 종료
				}); //에이짹스 종료
			}); // blur 종료

			
			// 이메일 인증번호 전송
			$('#mail_check').click(function() {
			var email = $('#user_email').val() + "@" + $('#user_email2').val();
			alert('입력하신 이메일로 인증코드가 전송됩니다.');
			$.ajax({ // 회원가입 폼에서는 email만 가지고갑니다. 회원가입 페이지에서 제어를 하기때문(id값이 필요없음)
				url : '${pageContext.request.contextPath}/mailSend?receiver='+ email, 
				type : 'get',
				success : function(data) {
					$('#mailSet').hide();
					$('#codeCheck').show();
				}// success 종료
			}); // ajax종료
		}); // click 종료
	
		// 이메일 인증번호 재전송
		$('#mail_check2').click(function() {
		var email = $('#user_email').val() + "@" + $('#user_email2').val();
		alert("인증번호를 " + email + "로 재발송합니다.");
		$.ajax({
			url : '${pageContext.request.contextPath}/mailSend?receiver='+ email,
			type : 'get'
		}); // ajax종료
	}); // click 종료
		
		
			// 이메일 인증번호 체크
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
					$('#user_email3').val(email);
					$('#mailSet2').show();
					checkEmailResult = true;
					} else if(data == 0) {
					$('#codeMessage').html("아쉽게도 코드번호 그거 아니에요.. <br>");
					}
				}// 석세스 종료
			}); // ajax종료
		}); // click 종료
	});
	
	
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
	function check() {
		if(checkIdResult && checkPasswdResult && checkEmailResult) {
			return true;
		} else if(checkIdResult && checkPasswdResult) {
			alert('이메일 인증을 해주세요.');
			return false;
		} else {
			alert('아이디 또는 패스워드 규칙을 확인해주세요.');
			return false;
		}
	}
	function selEmail(email) {
		if (email != "직접입력") {
			document.getElementById("user_email2").value = email;
			document.getElementById("user_email2").readOnly = true;
		} else {
			document.getElementById("user_email2").readOnly = false;
			document.getElementById("user_email2").value = "";
			document.getElementById("user_email2").focus();
		}
	}
</script>
</head>
<body>
	<div id="wrap">
		<jsp:include page="../inc/top.jsp" />
		<section id="writeForm">
			<h2>회원가입</h2>
			<form action="UserInsertPro.uo" method="post" name="Userform"
				onsubmit="return check()">
				<table>
					<tr>
						<td class="td_left"><label for="user_id">아이디</label></td>
						<td class="td_right"><input type="text" name="user_id"
							id="user_id" placeholder="4-12자리 영문,숫자 조합" required="required"/>
							<div id="checkIdResult">
								<!-- 자바스크립트에서 메세지 출력 공간 -->
							</div></td>

					</tr>
					<tr>
						<td class="td_left"><label for="user_pass">비밀번호</label></td>
						<td class="td_right"><input name="user_pass" type="password"
							id="user_pass" required="required"
							placeholder="4-16자리 영문,숫자,특수문자 조합" onkeyup="checkPasswd(this)" />
						<div id="checkPasswdResult">
								<!-- 자바스크립트에서 메세지 출력 공간 -->
							</div></td>
					</tr>
					<tr>
						<td class="td_left"><label for="user_name">이름</label></td>
						<td class="td_right"><input name="user_name" type="text"
							id="user_name" required="required" /></td>
					</tr>
					<tr>
						<td class="td_left"><label for="user_email">이메일</label></td>
						<td class="td_right">
						<div id="mailSet">
						<input type="text" id="user_email"
							name="user_email" required="required" /> @ <input type="text"
							name="user_email2" id="user_email2" onfocus="inInput(this)"
							onblur="outInput(this)" /> <select onfocus="inInput(this)"
							onblur="outInput(this)" onChange="selEmail(this.value)">
								<option onselect="focus">직접입력</option>
								<option value="naver.com">naver.com</option>
								<option value="gmail.com">gmail.com</option>
								<option value="daum.net">daum.net</option>
						</select><br><input type="button" value="인증 번호 전송" name="mail_check" id="mail_check">
						</div>
						<div id="codeMessage"><!-- 코드번호 틀린거 맞는거 들어갈칸 -->
						</div>
						<div id="codeCheck" style="display: none;">
						<input type="text" name="email_code" id="email_code">
						<input type="button" value="인증 번호 재전송" name="code_check" id="code_check">
						</div>
						<div id="mailSet2" style="display: none;">
						<input type="text" name="user_email3" id="user_email3" readonly="readonly">
						</div>
						</td>
					</tr>
					<tr>
						<td class="td_left"><label for="user_phone">핸드폰 번호</label></td>
						<td class="td_right"><input type="text" name="user_phone"
							id="user_phone" required="required" /></td>
					</tr>

				</table>
				<section id="commandCell">
					<input type="submit" value="회원가입">&nbsp;&nbsp; <input
						type="reset" value="취소" />
				</section>
			</form>
		</section>
		<jsp:include page="../inc/bottom.jsp" />
	</div>
</body>
</html>