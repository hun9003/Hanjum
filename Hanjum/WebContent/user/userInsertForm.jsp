<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <script src="js/jquery.min.js"></script>
<title>회원가입</title>
<script type="text/javascript">
	var checkIdResult = false, checkPasswdResult = false;
	$(document).ready(function() {
		$('#user_id').blur(function() {
			var element = document.getElementById('id_check');
			var user_id = $('#user_id').val();
			var msg = $("#checkIdResult");
			var regex = /^[A-Za-z][A-Za-z0-9]{3,11}$/g;
			$.ajax({
				url : '${pageContext.request.contextPath}/UserCheck.uo?user_id='+ user_id,
				type : 'get',
				success : function(data) {
					$("#checkIdResult").html(data);
					if (data == 1) {
						msg.css("color","#ff471a");
						check_false("#id_label","#user_id");
						msg.html("이미 사용 중인 아이디 입니다.");
						checkIdResult = false;
					} else if (user_id == "") {
						msg.css("color","#ff471a");
						check_false("#id_label","#user_id");
						msg.html("아이디를 입력해 주세요 :)");
						checkIdResult = false;
					} else if (!regex.exec(user_id)){
						msg.css("color","#ff471a");
						check_false("#id_label","#user_id");
						msg.html("아이디는 영문 숫자 조합으로 3~11글자만 가능합니다.");
						checkIdResult = false;
					}	else {
						msg.css("color","#00e673");
						check_true("#id_label","#user_id");
						msg.html("사용 가능한 아이디 입니다.");
						checkIdResult = true;
					}
				}
			});
		});
		function check_true(label, box) {
			$(label).css("color","#00e673");
			$(box).css("border-color","#00e673");
			$(box).css("box-shadow","1px 1px 3px #00e673");
		}
		
		function check_false(label, box) {
			$(label).css("color","#ff471a");
			$(box).css("border-color","#ff471a");
			$(box).css("box-shadow","1px 1px 3px #ff471a");
		}
		
	});
	
	function checkUserID(id) {
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
				checkPasswdResult = true;
			} else if (count == 3) {
				element.innerHTML = "사용 가능(보통)";
				checkPasswdResult = true;
			} else if (count == 2) {
				element.innerHTML = "사용 가능(위험)";
				checkPasswdResult = true;
			} else {
				element.innerHTML = "사용 불가(두 가지 이상 조합)";
				checkPasswdResult = false;
			}
		} else {
			element.innerHTML = "사용 불가";
			checkPasswdResult = false;
		}
	}
	function check() {
		if (checkIdResult && checkPasswdResult) {
			return true;
		} else {
			alert('아이디 또는 패스워드 규칙 확인 필수!');
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
	<div class="login-wrap p-4 p-md-5" style="margin-top:0px;">
              <h3 class="mb-4">회원가입</h3>
              <form action="JoinPro.uo" method="post" name="Userform" onsubmit="return check()">
                 <div class="form-group">
                    <label id="id_label" class="label" for="user_id">아이디</label>
                    <input type="text" id="user_id" class="form-control" name="user_id" required="required" placeholder="ID"/>
                	<div id="checkIdResult" class="offset-1"><!-- 자바스크립트에서 메세지 출력 공간 --></div>
                </div>
                <div class="form-group">
                 <label id="pass_label" class="label" for="password">비밀번호</label>
                 <input id="password-field" type="password" class="form-control" name="user_pass" required="required" placeholder="Password" onkeyup="checkPasswd(this)">
            	 <div id="checkPasswdResult" class="offset-1"><!-- 자바스크립트에서 메세지 출력 공간 --></div>	
            	</div>
            	<div class="form-group">
                    <label class="label" for="user_name">닉네임</label>
                    <input type="text" id="user_name" class="form-control" name="user_name" required="required" placeholder="UserName"/>
                </div>
                <div class="form-group">
                    <label class="label" for="user_email">이메일</label>
                    <input type="text" id="user_email" style="min-width:100px; width:40%; display: inline-block;" class="form-control" name="user_email" required="required" placeholder="Email"/> @ 
                    <input type="text" class="form-control" style="width:30%; display: inline-block;" name="user_email2" id="user_email2" onfocus="inInput(this)"
							onblur="outInput(this)" /> 
							<select  class="form-control" style="width:25%; display: inline-block;" onfocus="inInput(this)" onblur="outInput(this)" onChange="selEmail(this.value)">
								<option onselect="focus">직접입력</option>
								<option value="naver.com">naver.com</option>
								<option value="gmail.com">gmail.com</option>
								<option value="daum.net">daum.net</option>
						</select>
                </div>
                <div class="form-group">
                    <label class="label" for="user_phone">휴대폰번호</label>
                    <input type="text" id="user_phone" class="form-control" name="user_phone" required="required" placeholder="Phone"/>
                </div>
             <div class="form-group d-flex justify-content-end mt-4">
                 <button type="submit" class="btn btn-primary submit"><span class="fa fa-paper-plane"></span></button>
             </div>
         </form>
     </div>
</body>
</html>

