  
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/default.css" rel="stylesheet">
<link href="css/board.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-3.5.1.js"></script>
<script type="text/javascript" src="js/topMenu.js"></script>
<script type="text/javascript" src="plugin/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="js/smartediter.js"></script>
<script type="text/javascript">
	var checkIdResult = false, checkPasswdResult = false;
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
			} //석세스
		}); //에이젝스
	}); // blur 종료
	
		   
		// 이메일 인증번호 전송
		$('#mail_check').click(function() {
		var email = $('#user_email').val() + "@" + $('#user_email2').val();
		$.ajax({
			url : '${pageContext.request.contextPath}/mailSend?receiver='+ email,
			type : 'get',
			success : function(data) {
				$('#mailSet').hide();
				$('#codeCheck').show();
			}// 석세스 종료
		}); // ajax종료
	}); // click 종료

	// 이메일 인증번호 재전송
		$('#mail_check2').click(function() {
		var email = $('#user_email').val() + "@" + $('#user_email2').val();
		$.ajax({
			url : '${pageContext.request.contextPath}/mailSend?receiver='+ email,
			type : 'get',
			success : function(data) {
				alert("인증번호가 " + email + "로 재 전송 되었습니다.");
			}// 석세스 종료
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
				} else if(data == 0) {
				$('#codeMessage').html("아쉽게도 코드번호 그거 아니에요.. <br>");
				}
			}// 석세스 종료
		}); // ajax종료
		}); // keyup 종료
		
		
});

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
		if(checkIdResult && checkPasswdResult) {
			return true;
		} else {
			alert('아이디 또는 패스워드 규칙 확인 필수!');
			return false;
		}
	}
	
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
	
</script>
<title>한줌에디터</title>
</head>
<body>
<div id="wrap">
<jsp:include page="../inc/top.jsp"/>

<div class="write_form">
<div class="write_title"><h1>에디터 회원 가입</h1></div>
<form action="UserInsertEditorPro.uo" method="post" name="fr_write" id="WriteForm" enctype="multipart/form-data">
<table class="write_table">
<tr><td class="td_name"><label for="Subject">아이디</label></td><td class="td_content">
<input id="user_id" type="text" name="user_id" placeholder="4-12자리 영문,숫자 조합">
<div id="checkIdResult"><!-- 자바스크립트에서 메세지 출력 공간 --></div></td></tr>

<tr><td class="td_name"><label for="Content">비밀번호</label></td><td class="td_content">
<input id="user_pass" name="user_pass" placeholder="4-16자리 영문,숫자,특수문자 조합" onkeyup="checkPasswd(this)"/>
<div id="checkPasswdResult"><!-- 자바스크립트에서 메세지 출력 공간 --></div></td></tr>

<tr><td class="td_name"><label for="Content">이름</label></td><td class="td_content"><input id="user_name" name="user_name"/></td></tr>
<tr><td class="td_name"><label for="Content">이메일</label></td><td class="td_content">
<div id="mailSet">
<input id="user_email" name="user_email"/> @ <input type="text" name="user_email2" id="user_email2" onfocus="inInput(this)" onblur="outInput(this)"/>
<select onfocus="inInput(this)" onblur="outInput(this)" onChange="selEmail(this.value)">
	<option onselect="focus">직접입력</option>
	<option value="naver.com">naver.com</option>
	<option value="gmail.com">gmail.com</option>
	<option value="daum.net">daum.net</option>
</select> 
<input type="button" value="인증 번호 전송" name="mail_check" id="mail_check">
</div>
<div id="codeMessage"><!-- 코드번호 틀린거 맞는거 들어갈칸 -->
</div>
<div id="codeCheck" style="display: none;">
<input type="text" name="email_code" id="email_code"> <input type="button" value="인증코드 재전송" name="mail_check2" id="mail_check2">
</div>
<div id="mailSet2" style="display: none;">
<input type="text" name="user_email3" id="user_email3" readonly="readonly">
</div>
</td></tr>
<tr><td class="td_name"><label for="Content">휴대전화</label></td><td class="td_content"><input id="user_phone" name="user_phone"/></td></tr>
<tr><td class="td_name"><label for="Content">프로필 사진</label></td><td class="td_content"><input type="file" id="editor_photo" name="editor_photo"/></td></tr>
<tr><td class="td_name"><label for="Content">한줄 소개</label></td><td class="td_content"><textarea id="editor_des" name="editor_des" cols="100" rows="5" ></textarea></td></tr>
<tr><td class="td_name"><label for="DetailContent">본인 소개</label></td><td class="td_content">
<textarea id="DetailContent" name="editor_profile"></textarea>
</td></tr>
<tr><td class="td_name">이용 프로그램</td><td class="td_content">
<div class="check_list"><input type="checkbox" id="program1" name="editor_program" value="1"><label for="program1"></label><span class="checkbox">프리미어</span></div>
<div class="check_list"><input type="checkbox" id="program2" name="editor_program" value="2"><label for="program2"></label><span class="checkbox">파이널컷</span></div>
<div class="check_list"><input type="checkbox" id="program3" name="editor_program" value="3"><label for="program3"></label><span class="checkbox">베가스</span></div>
<div class="check_list"><input type="checkbox" id="program4" name="editor_program" value="4"><label for="program4"></label><span class="checkbox">애프터 이펙트</span></div>
<div class="check_list"><input type="checkbox" id="program5" name="editor_program" value="5"><label for="program5"></label><span class="checkbox">기타</span></div>
</td></tr>
<tr><td class="td_name">최종 납품 해상도</td><td class="td_content">
<div class="check_list"><input type="checkbox" id="solution1" name="editor_solution" value="1"><label for="solution1"></label><span class="checkbox">HD</span></div>
<div class="check_list"><input type="checkbox" id="solution2" name="editor_solution" value="2"><label for="solution2"></label><span class="checkbox">FHD</span></div>
<div class="check_list"><input type="checkbox" id="solution3" name="editor_solution" value="3"><label for="solution3"></label><span class="checkbox">UHD</span></div>
<div class="check_list"><input type="checkbox" id="solution4" name="editor_solution" value="4"><label for="solution4"></label><span class="checkbox">맞춤 가능</span></div>
</td></tr>
<tr><td class="td_name">작업 가능 항목</td><td class="td_content">
<div class="check_list"><input type="checkbox" id="inventory1" name="editor_inventory" value="1"><label for="inventory1"></label><span class="checkbox">컷 편집</span></div>
<div class="check_list"><input type="checkbox" id="inventory2" name="editor_inventory" value="2"><label for="inventory2"></label><span class="checkbox">오디오 싱크</span></div>
<div class="check_list"><input type="checkbox" id="inventory3" name="editor_inventory" value="3"><label for="inventory3"></label><span class="checkbox">BGM 삽입</span></div>
<div class="check_list"><input type="checkbox" id="inventory4" name="editor_inventory" value="4"><label for="inventory4"></label><span class="checkbox">효과음 삽입</span></div>
<div class="check_list"><input type="checkbox" id="inventory5" name="editor_inventory" value="5"><label for="inventory5"></label><span class="checkbox">모션 그래픽</span></div>
<div class="check_list"><input type="checkbox" id="inventory5" name="editor_inventory" value="6"><label for="inventory6"></label><span class="checkbox">템플릿 작업</span></div>
</td></tr>
<tr><td class="td_name">납품 파일 공유 방식</td><td class="td_content">
<div class="check_list"><input type="radio" id="upload1" name="editor_upload" value="1"><label for="upload1"></label><span class="checkbox">이메일</span></div>
<div class="check_list"><input type="radio" id="upload2" name="editor_upload" value="2"><label for="upload2"></label><span class="checkbox">NAS</span></div>
<div class="check_list"><input type="radio" id="upload3" name="editor_upload" value="3"><label for="upload3"></label><span class="checkbox">웹하드</span></div>
<div class="check_list"><input type="radio" id="upload4" name="editor_upload" value="4"><label for="upload4"></label><span class="checkbox">모두 가능</span></div>
</td></tr>
<tr><td class="td_name">녹화에 이용된 캠</td><td class="td_content">
<div class="check_list"><input type="radio" id="work1" name="editor_work" value="1"><label for="work1"></label><span class="checkbox">1캠</span></div>
<div class="check_list"><input type="radio" id="work2" name="editor_work" value="2"><label for="work2"></label><span class="checkbox">2캠</span></div>
<div class="check_list"><input type="radio" id="work3" name="editor_work" value="3"><label for="work3"></label><span class="checkbox">3캠</span></div>
<div class="check_list"><input type="radio" id="work4" name="editor_work" value="4"><label for="work4"></label><span class="checkbox">4캠 이상</span></div>
</td></tr>
<tr><td class="td_name">실물 미팅</td><td class="td_content">
<div class="check_list"><input type="radio" id="meeting1" name="editor_meeting" value="1"><label for="meeting1"></label><span class="checkbox">쌉가능</span></div>
<div class="check_list"><input type="radio" id="meeting2" name="editor_meeting" value="2"><label for="meeting2"></label><span class="checkbox">불가능</span></div>
</td></tr>
<tr><td class="td_name">선호 작업 유형</td><td class="td_content">
<div class="check_list"><input type="radio" id="fort1" name="editor_fort" value="1"><label for="fort1"></label><span class="checkbox">건당 계약</span></div>
<div class="check_list"><input type="radio" id="fort2" name="editor_fort" value="2"><label for="fort2"></label><span class="checkbox">단기 계약</span></div>
<div class="check_list"><input type="radio" id="fort3" name="editor_fort" value="3"><label for="fort3"></label><span class="checkbox">장기 계약</span></div>
</td></tr>
<tr><td class="td_name">샘플 작업 가능 유무</td><td class="td_content">
<div class="check_list"><input type="radio" id="sample1" name="editor_sample" value="1"><label for="sample1"></label><span class="checkbox">가능</span></div>
<div class="check_list"><input type="radio" id="sample2" name="editor_sample" value="2"><label for="sample2"></label><span class="checkbox">불가능</span></div>
</td></tr>
<tr><td class="td_name"><label for="MinPrice">예상 단가</label></td><td class="td_content">
<div class="input_price"><input type="number" id="MinPrice" step="1000" min="1000" name="editor_ed_min_price" value="0"> 원 ~ <input type="number" id="MaxPrice" step="1000" min="1000" name="editor_ed_max_price" value="5000"> 원</div>
</td></tr>
<tr><td class="td_name">거주지</td><td class="td_content">
<select name="editor_address"><option>서울</option><option>부산</option><option>대구</option><option>대전</option><option>인천</option><option>광주</option>
<option>울산</option><option>세종</option><option>경기</option><option>강원</option><option>충북</option><option>충남</option>
<option>경북</option><option>경남</option><option>전북</option><option>전남</option><option>제주</option></select>
</td></tr>
<!-- <tr><td class="td_name"><label for="Ref1">레퍼런스 링크</label></td><td class="td_content"> -->
<!-- <div class="link_alert"><img src="img/linkAlert.png"> 표시된 곳만 작성해주세요</div> -->
<!-- <div class="input_link"><input type="text" name="board_creator_cre_ref[]" id="Ref1" placeholder="링크를 업로드 하세요."></div> -->
<!-- <div class="input_link"><input type="text" name="board_creator_cre_ref[]" id="Ref2" placeholder="링크를 업로드 하세요."></div> -->
<!-- <div class="input_link"><input type="text" name="board_creator_cre_ref[]" id="Ref3" placeholder="링크를 업로드 하세요."></div> -->
<!-- </td></tr> -->
</table>
<div class="write_form_submit"><input type="submit" id="WriteSubmit" value="회원가입"></div>
</form>
</div>

<jsp:include page="../inc/bottom.jsp"/>
</div>
</body>
</html>