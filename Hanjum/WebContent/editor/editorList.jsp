<%@page import="com.hanjum.vo.Constant"%>
<%@page import="com.hanjum.vo.PageInfo"%>
<%@page import="com.hanjum.board.vo.EditorBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>한줌에디터</title>
<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">

	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

	<link rel="stylesheet" href="css/animate.css">
	
	<link rel="stylesheet" href="css/owl.carousel.min.css">
	<link rel="stylesheet" href="css/owl.theme.default.min.css">
	<link rel="stylesheet" href="css/magnific-popup.css">

	<link rel="stylesheet" href="css/bootstrap-datepicker.css">
	<link rel="stylesheet" href="css/jquery.timepicker.css">

	
	<link rel="stylesheet" href="css/flaticon.css">
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="../inc/top.jsp"/>
	<!-- END nav -->
	
	<section class="hero-wrap hero-wrap-2" style="background-image: url('images/bg_2.jpg');">
		<div class="overlay"></div>
		<div class="container">
			<div class="row no-gutters slider-text align-items-end justify-content-center">
				<div class="col-md-9 ftco-animate pb-5 text-center">
					<p class="breadcrumbs"><span class="mr-2"><a href="index.html">Home <i class="fa fa-chevron-right"></i></a></span> <span>Editor <i class="fa fa-chevron-right"></i></span></p>
					<h1 class="mb-0 bread">편집자 리스트</h1>
				</div>
			</div>
		</div>
	</section>

	<section class="ftco-section bg-light">
		<div class="container">
			<div class="row">
			<%
			PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
			int listCount = pageInfo.getListCount();
			int nowPage = pageInfo.getPage();
			int maxPage = pageInfo.getMaxPage();
			int startPage = pageInfo.getStartPage();
			int endPage = pageInfo.getEndPage();
			int pageSize = Constant.BOARD_PAGE_SIZE;
			String pageUrl = "EditorList.bo";
			String contentUrl = "Editor.bo";
			
        if(request.getAttribute("editorList") != null){
        	ArrayList<EditorBean> editorList = (ArrayList<EditorBean>)request.getAttribute("editorList");
	        for(int i = 0; i < editorList.size(); i++){
	        	EditorBean editorBean = editorList.get(i);
	        %>
				<div class="col-md-6 col-lg-3 ftco-animate d-flex align-items-stretch" onclick="location.href='Editor.bo?board_id=<%=editorBean.getBoard_id()%>&page=<%=nowPage%>'">
					<div class="staff">
						<div class="img-wrap d-flex align-items-stretch">
							<div class="img align-self-stretch" style="width:255px; background-size:255px; background-image: url(editorUserPhotoUpload/<%=editorBean.getBoard_ed_photo()%>);"></div>
						</div>
						<div class="text pt-3">
							<h3><%=editorBean.getUser_name() %></h3>
							<span class="position mb-2">편집자</span>
							<div class="faded">
								<p style="height: 80px; text-overflow: ellipsis;"><%=editorBean.getBoard_subject() %></p>
								<p class="star">
				                    <span class="fa fa-star"></span>
				                    <span class="fa fa-star"></span>
				                    <span class="fa fa-star"></span>
				                    <span class="fa fa-star"></span>
				                    <span class="fa fa-star"></span>
                				</p>
							</div>
						</div>
					</div>
				</div>
			<%
	        }
       }
        %>
			</div>
			<div class="row mt-5">
						<div class="col">
							<div class="block-27">
								<ul>
								<%if(startPage > pageSize){ %>
									<li><a href="<%=pageUrl%>?page=<%=startPage-pageSize%>">&lt;</a></li>
									<% 
										}
									for(int i=startPage; i<=endPage; i++){
										if(i == nowPage){
											%>
									<li class="active"><span><%=i %></span></li>
									<% } else { %>
									<li><a href="<%=pageUrl%>?page=<%=i%>"><%=i %></a></li>
									<% } 
									}
									if(endPage < maxPage){ 
									%>
									<li><a href="<%=pageUrl%>?page=<%=startPage+pageSize %>">&gt;</a></li>
									<%
									}
									%>
								</ul>
							</div>
						</div>
					</div>
		</div>
	</section>

	<jsp:include page="../inc/bottom.jsp"/>
		

		<!-- loader -->
		<div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>


		<script src="js/jquery.min.js"></script>
		<script src="js/jquery-migrate-3.0.1.min.js"></script>
		<script src="js/popper.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/jquery.easing.1.3.js"></script>
		<script src="js/jquery.waypoints.min.js"></script>
		<script src="js/jquery.stellar.min.js"></script>
		<script src="js/owl.carousel.min.js"></script>
		<script src="js/jquery.magnific-popup.min.js"></script>
		<script src="js/jquery.animateNumber.min.js"></script>
		<script src="js/bootstrap-datepicker.js"></script>
		<script src="js/scrollax.min.js"></script>
		<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
		<script src="js/google-map.js"></script>
		<script src="js/main.js"></script>
		
	</body>
	</html>