<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/join.js"></script>
<title>회원가입</title>
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
                 <input id="password" type="password" class="form-control" name="user_pass" required="required" placeholder="Password" onkeyup="checkPasswd(this)">
            	 <div id="checkPasswdResult" class="offset-1"><!-- 자바스크립트에서 메세지 출력 공간 --></div>	
            	</div>
            	<div class="form-group">
                    <label class="label" for="user_name">닉네임</label>
                    <input type="text" id="user_name" class="form-control" name="user_name" required="required" placeholder="UserName"/>
                </div>
                <div class="form-group">
                    <label class="label" id="email_label" for="user_email">이메일</label>
                    <input type="text" id="user_email" style="min-width:100px; width:30%; display: inline-block;" class="form-control" name="user_email" required="required" placeholder="Email"/> @ 
                    <input type="text" class="form-control" style="width:35%; min-width:100px; display: inline-block;" name="user_email2" id="user_email2"/> 
							<select  class="form-control" style="width:30%; min-width:150px; display: inline-block;" onChange="selEmail(this.value)">
								<option onselect="focus">직접입력</option>
								<option value="naver.com">naver.com</option>
								<option value="gmail.com">gmail.com</option>
								<option value="daum.net">daum.net</option>
						</select>
					<div id="checkEmailResult" class="offset-1"><!-- 자바스크립트에서 메세지 출력 공간 --></div>
                	
                	<div id="codeMessage"><!-- 코드번호 틀린거 맞는거 들어갈칸 -->
						</div>
						<div id="codeCheck" style="display: none;">
						<input type="text" name="email_code" id="email_code">
						<input type="button" value="인증 번호 확인" name="code_check" id="code_check">
						</div>
						<div id="mailSet2" style="display: none;">
						<input type="text" name="user_email3" id="user_email3" readonly="readonly">
						</div>
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

