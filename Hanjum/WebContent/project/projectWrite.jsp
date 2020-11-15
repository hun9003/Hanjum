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
<title>한줌에디터</title>
</head>
<body>
<div id="wrap">
<jsp:include page="../inc/top.jsp"/>

<div class="write_form">
<div class="write_title"><h1>프로젝트 등록</h1></div>
<form action="ProjectWritePro.bo" method="post" name="fr_write" id="WriteForm">
<table class="write_table">
<tr><td class="td_name"><label for="Subject">프로젝트 제목</label></td><td class="td_content"><div class="input_subject"><input id="Subject" type="text" name="board_subject" required="required"></div></td></tr>
<tr><td class="td_name"><label for="Content">프로젝트 소개</label></td><td class="td_content"><textarea id="Content" name="board_content" cols="100" rows="5" ></textarea></td></tr>
<tr><td class="td_name">장르</td><td class="td_content">
<div class="check_list"><input type="checkbox" id="genre1" name="board_creator_genre[]" value="1"><label for="genre1"></label><span class="checkbox">유튜브</span></div>
<div class="check_list"><input type="checkbox" id="genre2" name="board_creator_genre[]" value="2"><label for="genre2"></label><span class="checkbox">홍보</span></div>
<div class="check_list"><input type="checkbox" id="genre3" name="board_creator_genre[]" value="3"><label for="genre3"></label><span class="checkbox">광고</span></div>
<div class="check_list"><input type="checkbox" id="genre4" name="board_creator_genre[]" value="4"><label for="genre4"></label><span class="checkbox">뮤직비디오</span></div>
<div class="check_list"><input type="checkbox" id="genre5" name="board_creator_genre[]" value="5"><label for="genre5"></label><span class="checkbox">드라마</span></div>
<div class="check_list"><input type="checkbox" id="genre6" name="board_creator_genre[]" value="6"><label for="genre6"></label><span class="checkbox">모션그래픽</span></div>
<div class="check_list"><input type="checkbox" id="genre7" name="board_creator_genre[]" value="7"><label for="genre7"></label><span class="checkbox">기타</span></div>
</td></tr>
<tr><td class="td_name"><label for="DetailContent">세부설명</label></td><td class="td_content">
<textarea id="DetailContent" name="board_creator_content_detail"></textarea>
</td></tr>
<tr><td class="td_name">동시 녹음 유부</td><td class="td_content">
<div class="check_list"><input type="radio" id="Recording1" name="board_creator_recording" value="1"><label for="Recording1"></label><span class="checkbox">싱크 작업 필요</span></div>
<div class="check_list"><input type="radio" id="Recording2"  name="board_creator_recording" value="2"><label for="Recording2"></label><span class="checkbox">부분적 필요</span></div>
<div class="check_list"><input type="radio" id="Recording3"  name="board_creator_recording" value="3"><label for="Recording3"></label><span class="checkbox">원본 녹음 사용</span></div>
</td></tr>
<tr><td class="td_name">녹화에 이용된 캠</td><td class="td_content">
<div class="check_list"><input type="radio" id="CamNum1" name="board_creator_cam_num" value="1"><label for="CamNum1"></label><span class="checkbox">1캠</span></div>
<div class="check_list"><input type="radio" id="CamNum2" name="board_creator_cam_num" value="2"><label for="CamNum2"></label><span class="checkbox">2캠</span></div>
<div class="check_list"><input type="radio" id="CamNum3" name="board_creator_cam_num" value="3"><label for="CamNum3"></label><span class="checkbox">3캠</span></div>
<div class="check_list"><input type="radio" id="CamNum4" name="board_creator_cam_num" value="4"><label for="CamNum4"></label><span class="checkbox">4캠 이상</span></div>
</td></tr>
<tr><td class="td_name">원본 클립</td><td class="td_content">
<div class="check_list"><input type="radio" id="OriClipNum1" name="board_creator_ori_clip_num" value="1"><label for="OriClipNum1"></label><span class="checkbox">5개 이하</span></div>
<div class="check_list"><input type="radio" id="OriClipNum2" name="board_creator_ori_clip_num" value="2"><label for="OriClipNum2"></label><span class="checkbox">5개 ~ 20개</span></div>
<div class="check_list"><input type="radio" id="OriClipNum3" name="board_creator_ori_clip_num" value="3"><label for="OriClipNum3"></label><span class="checkbox">20 ~ 50개</span></div>
<div class="check_list"><input type="radio" id="OriClipNum4" name="board_creator_ori_clip_num" value="4"><label for="OriClipNum4"></label><span class="checkbox">50개 이상</span></div>
</td></tr>
<tr><td class="td_name">편집 전 런타임</td><td class="td_content">
<div class="check_list"><input type="radio" id="OriLength1" name="board_creator_ori_length" value="1"><label for="OriLength1"></label><span class="checkbox">10분 이하</span></div>
<div class="check_list"><input type="radio" id="OriLength2" name="board_creator_ori_length" value="2"><label for="OriLength2"></label><span class="checkbox">30분 이하</span></div>
<div class="check_list"><input type="radio" id="OriLength3" name="board_creator_ori_length" value="3"><label for="OriLength3"></label><span class="checkbox">1시간 이하</span></div>
<div class="check_list"><input type="radio" id="OriLength4" name="board_creator_ori_length" value="4"><label for="OriLength4"></label><span class="checkbox">5시간 이하</span></div>
<div class="check_list"><input type="radio" id="OriLength5" name="board_creator_ori_length" value="5"><label for="OriLength5"></label><span class="checkbox">5시간 이상</span></div>
<div class="check_list"><input type="radio" id="OriLength6" name="board_creator_ori_length" value="6"><label for="OriLength6"></label><span class="checkbox">정확히 알 수 없음</span></div>
</td></tr>
<tr><td class="td_name">편집 후 런타임</td><td class="td_content">
<div class="check_list"><input type="radio" id="EditLength1" name="board_creator_edit_length" value="1"><label for="EditLength1"></label><span class="checkbox">5분 이하</span></div>
<div class="check_list"><input type="radio" id="EditLength2" name="board_creator_edit_length" value="2"><label for="EditLength2"></label><span class="checkbox">10분 이하</span></div>
<div class="check_list"><input type="radio" id="EditLength3" name="board_creator_edit_length" value="3"><label for="EditLength3"></label><span class="checkbox">30분 이하</span></div>
<div class="check_list"><input type="radio" id="EditLength4" name="board_creator_edit_length" value="4"><label for="EditLength4"></label><span class="checkbox">1시간 이하</span></div>
<div class="check_list"><input type="radio" id="EditLength5" name="board_creator_edit_length" value="5"><label for="EditLength5"></label><span class="checkbox">1시간 이상</span></div>
<tr><td class="td_name"><label for="OriTransfer1">파일의 전달방식</label></td><td class="td_content">
<div class="check_list"><input type="radio" id="OriTransfer1" name="board_creator_ori_transfer" value="1"><label for="OriTransfer1"></label><span class="checkbox">이메일</span></div>
<div class="check_list"><input type="radio" id="OriTransfer2" name="board_creator_ori_transfer" value="2"><label for="OriTransfer2"></label><span class="checkbox">웹하드</span></div>
<div class="check_list"><input type="radio" id="OriTransfer3" name="board_creator_ori_transfer" value="3"><label for="OriTransfer3"></label><span class="checkbox">NAS</span></div>
<div class="check_list"><input type="radio" id="OriTransfer4" name="board_creator_ori_transfer" value="4"><label for="OriTransfer4"></label><span class="checkbox">SMS</span></div>
<div class="check_list"><input type="radio" id="OriTransfer5" name="board_creator_ori_transfer" value="5"><label for="OriTransfer5"></label><span class="checkbox">직접전달</span></div>
</td></tr>
<tr><td class="td_name"><label for="MinPrice">예상 단가</label></td><td class="td_content">
<div class="input_price"><input type="number" id="MinPrice" step="1000" min="1000" name="board_creator_cre_min_price" value="0"> 원 ~ <input type="number" id="MaxPrice" step="1000" min="1000" name="board_creator_cre_max_price" value="5000"> 원</div>
</td></tr>
<tr><td class="td_name"><label for="Ref1">레퍼런스 링크</label></td><td class="td_content">
<div class="input_link"><input type="url" name="board_creator_cre_ref[]" id="Ref1" placeholder="링크를 업로드 하세요."></div>
<div class="input_link"><input type="url" name="board_creator_cre_ref[]" id="Ref2" placeholder="링크를 업로드 하세요."></div>
<div class="input_link"><input type="url" name="board_creator_cre_ref[]" id="Ref3" placeholder="링크를 업로드 하세요."></div>
</td></tr>
</table>
<div class="write_form_submit"><input type="submit" id="WriteSubmit" value="등록하기"></div>
</form>
</div>

<jsp:include page="../inc/bottom.jsp"/>
</div>
</body>
</html>