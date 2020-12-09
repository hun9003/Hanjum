  
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/style.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-3.5.1.js"></script>
<script type="text/javascript" src="plugin/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="js/smartediter.js"></script>

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
	
		$("#editor_photo_btn").click(function(){
			$("#editor_photo").val("");
			$("#editor_photo").click();
		});
		
		$("#editor_photo").change(function(){
			var photoIndex = $("#editor_photo").val().lastIndexOf('\\')
			var photo = $("#editor_photo").val().substring(Number(photoIndex)+Number(1));
			$("#photo_content").val(photo);
		})
	
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
<title>회원가입</title>
<style>
	#editor_photo { display:none; }
	.check_list { display: inline-block; padding:10px;}
	.check_list span { margin-left: 10px;}
</style>
</head>
<body>
<div class="login-wrap p-4 p-md-5" style="margin-top:0px;">
              <h3 class="mb-4">편집자 가입</h3>
              <form action="JoinEditorPro.uo" method="post" name="fr_write" id="WriteForm" enctype="multipart/form-data" onsubmit="return check()">
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
                    <input type="text" id="user_email" style="min-width:100px; width:30%; display: inline-block;" class="form-control" name="user_email" required="required" placeholder="Email"/> @ 
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
                <div class="form-group">
                <label class="label" for="editor_photo_btn">프로필 사진</label>
                <input class="form-control" id="photo_content" type="text" readonly="readonly" style="min-width:100px; width:40%; display: inline-block;" > 
                <input type="button" class="btn btn-primary" id="editor_photo_btn" value="프로필 업로드"> 
                <input type="file" id="editor_photo" name="editor_photo"/>
                </div>
                <div class="form-group">
                <label class="label" for="editor_des">한줄 소개</label>
                <textarea id="editor_des" name="editor_des" cols="50" rows="3" ></textarea>
                </div>
                <div class="form-group">
                <label  class="label" for="DetailContent">본인 소개</label>
                <div class="form-control" style="height: auto;">
                	<div style="padding-top:10px;"></div>
                	<textarea id="DetailContent" name="editor_profile" style="padding: 20px; width:100%; min-width:260px; "></textarea>
                </div>
                </div>
                <div class="form-group">
                <label class="label">이용 프로그램</label>
	                <div class="check_list"><input type="checkbox" id="program1" name="editor_program" value="1"><span class="checkbox">프리미어</span></div>
					<div class="check_list"><input type="checkbox" id="program2" name="editor_program" value="2"><span class="checkbox">파이널컷</span></div>
					<div class="check_list"><input type="checkbox" id="program3" name="editor_program" value="3"><span class="checkbox">베가스</span></div>
					<div class="check_list"><input type="checkbox" id="program4" name="editor_program" value="4"><span class="checkbox">애프터 이펙트</span></div>
					<div class="check_list"><input type="checkbox" id="program5" name="editor_program" value="5"><span class="checkbox">기타</span></div>
                </div>
                <div class="form-group">
                <label class="label">최종 납품 해상도</label>
	                <div class="check_list"><input type="checkbox" id="solution1" name="editor_solution" value="1"><span class="checkbox">HD</span></div>
					<div class="check_list"><input type="checkbox" id="solution2" name="editor_solution" value="2"><span class="checkbox">FHD</span></div>
					<div class="check_list"><input type="checkbox" id="solution3" name="editor_solution" value="3"><span class="checkbox">UHD</span></div>
					<div class="check_list"><input type="checkbox" id="solution4" name="editor_solution" value="4"><span class="checkbox">맞춤 가능</span></div>
                </div>
                <div class="form-group">
                <label class="label">작업 가능 항목</label>
	                <div class="check_list"><input type="checkbox" id="inventory1" name="editor_inventory" value="1"><span class="checkbox">컷 편집</span></div>
					<div class="check_list"><input type="checkbox" id="inventory2" name="editor_inventory" value="2"><span class="checkbox">오디오 싱크</span></div>
					<div class="check_list"><input type="checkbox" id="inventory3" name="editor_inventory" value="3"><span class="checkbox">BGM 삽입</span></div>
					<div class="check_list"><input type="checkbox" id="inventory4" name="editor_inventory" value="4"><span class="checkbox">효과음 삽입</span></div>
					<div class="check_list"><input type="checkbox" id="inventory5" name="editor_inventory" value="5"><span class="checkbox">모션 그래픽</span></div>
					<div class="check_list"><input type="checkbox" id="inventory6" name="editor_inventory" value="6"><span class="checkbox">템플릿 작업</span></div>
                </div>
                <div class="form-group">
                <label class="label">납품 파일 공유 방식</label>
	                <div class="check_list"><input type="radio" id="upload1" name="editor_upload" value="1"><span class="checkbox">이메일</span></div>
					<div class="check_list"><input type="radio" id="upload2" name="editor_upload" value="2"><span class="checkbox">NAS</span></div>
					<div class="check_list"><input type="radio" id="upload3" name="editor_upload" value="3"><span class="checkbox">웹하드</span></div>
					<div class="check_list"><input type="radio" id="upload4" name="editor_upload" value="4"><span class="checkbox">모두 가능</span></div>
                </div>
                <div class="form-group">
                <label class="label">녹화에 이용된 캠</label>
                	<div class="check_list"><input type="radio" id="work1" name="editor_work" value="1"><span class="checkbox">1캠</span></div>
					<div class="check_list"><input type="radio" id="work2" name="editor_work" value="2"><span class="checkbox">2캠</span></div>
					<div class="check_list"><input type="radio" id="work3" name="editor_work" value="3"><span class="checkbox">3캠</span></div>
					<div class="check_list"><input type="radio" id="work4" name="editor_work" value="4"><span class="checkbox">4캠 이상</span></div>
                </div>
                <div class="form-group">
                <label class="label">실물 미팅</label>
	                <div class="check_list"><input type="radio" id="meeting1" name="editor_meeting" value="1"><span class="checkbox">쌉가능</span></div>
					<div class="check_list"><input type="radio" id="meeting2" name="editor_meeting" value="2"><span class="checkbox">불가능</span></div>
                </div>
                <div class="form-group">
                <label class="label">선호 작업 유형</label>
	                <div class="check_list"><input type="radio" id="fort1" name="editor_fort" value="1"><span class="checkbox">건당 계약</span></div>
					<div class="check_list"><input type="radio" id="fort2" name="editor_fort" value="2"><span class="checkbox">단기 계약</span></div>
					<div class="check_list"><input type="radio" id="fort3" name="editor_fort" value="3"><span class="checkbox">장기 계약</span></div>
                </div>
                <div class="form-group">
                <label class="label">샘플 작업 가능 유무</label>
	                <div class="check_list"><input type="radio" id="sample1" name="editor_sample" value="1"><span class="checkbox">가능</span></div>
					<div class="check_list"><input type="radio" id="sample2" name="editor_sample" value="2"><span class="checkbox">불가능</span></div>
                </div>
                <div class="form-group">
                <label class="label" for="Content">예상 단가</label>
              	  <div class="input_price"><input class="form-control" style="width:30%; display: inline-block;" type="number" id="MinPrice" step="1000" min="1000" name="editor_ed_min_price" value="0"> 원 ~ <input class="form-control" style="width:30%; display: inline-block;" type="number" id="MaxPrice" step="1000" min="1000" name="editor_ed_max_price" value="5000"> 원</div>
                </div>
                <div class="form-group">
                <label class="label">거주지</label>
	                <select class="form-control"  name="editor_address">
	                <option>서울</option>
	                <option>부산</option>
	                <option>대구</option>
	                <option>대전</option>
	                <option>인천</option>
	                <option>광주</option>
					<option>울산</option>
					<option>세종</option>
					<option>경기</option>
					<option>강원</option>
					<option>충북</option>
					<option>충남</option>
					<option>경북</option>
					<option>경남</option>
					<option>전북</option>
					<option>전남</option>
					<option>제주</option>
					</select>
                </div>
             <div class="form-group d-flex justify-content-end mt-4">
                 <button type="submit"  id="WriteSubmit" class="btn btn-primary submit"><span class="fa fa-paper-plane"></span></button>
             </div>
         </form>
     </div>
     </body>
     </html>