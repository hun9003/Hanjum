<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="js/jquery-3.5.1.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#wrap").mouseover(function(){
		$("#update").css("display","inline");
	});
	$("#wrap").mouseout(function(){
		$("#update").css("display","none");
	});
	$("#update").click(function() {
		var phone = $("#phoneNum").html();
		$("#phone").empty();
		$("#phone").append("<input type='text' id='phone_text' value='"+phone+"'><input type='button' id='phone_btn' value='확인' >")
	});
	$("#wrap").on("click","#phone_btn",function(){
		var phone = $("#phone_text").val();
		$("#phone").empty();
		$("#phone").append('<span id="phoneNum">'+phone+'</span> <span id="update">수정</span>');
	});
})
</script>
<style>
#update {
	display: none;
	color: blue;
	margin-left: 20px;
	font-weight: bold;
	cursor: pointer;
}
#update:hover{
	text-decoration: underline;
}
</style>
<title>한줌에디터</title>
</head>
<body>
<div id="wrap">
휴대폰 번호 <span id="phone"><span id="phoneNum">010-4003-3892</span> <span id="update">수정</span></span>
</div>
</body>
</html>