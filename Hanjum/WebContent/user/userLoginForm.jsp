<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<div class="login-wrap p-4 p-md-5">
              <h3 class="mb-4">로그인</h3>
              <form action="LoginPro.uo" class="signup-form" method="post" name="Userform">
                 <div class="form-group">
                    <label class="label" for="userid_field">아이디</label>
                    <input type="text" id="userid_field" class="form-control" name="user_id" required="required" placeholder="ID"/>
                </div>
                <div class="form-group">
                 <label class="label" for="password-field">비밀번호</label>
                 <input id="password-field" type="password" class="form-control" name="user_pass" required="required" placeholder="Password">
             </div>
             <div class="form-group d-flex justify-content-end mt-4">
                 <button type="submit" class="btn btn-primary submit"><span class="fa fa-paper-plane"></span></button>
             </div>
         </form>
         <p class="text-center">오늘 처음 오셨나요? <a id="join_btn" data-href="Join.uo">Sign Up</a></p>
     </div>