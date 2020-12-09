/**
 *  이메일 인증을 위한 스크립트
 */
$(document).ready(function() {
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
	$('#mail_check2').click(function() {
		var email = $('#user_email').val() + "@" + $('#user_email2').val();
		var searchUser_id = $('#searchUser_id').val();
		alert("인증번호를 " + email + "로 재발송합니다.");
		$.ajax({
			url : '${pageContext.request.contextPath}/mailSend?receiver='+ email +'&user_id='+searchUser_id,
			type : 'get'
		}); // ajax종료
	}); // click 종료
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

})