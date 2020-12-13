/**
 * 
 */


$(document).ready(function(){
	$("#portfolioArea").load("UserPortfolioList.uo");
	$(".toggle_label").click(function(){
		var status = 0;
		var user_id = $("#user_id").val();
		if($("input:checkbox[name=editor_status]").is(":checked") == true) {
			status = 0;
		} else {
			status = 1;
		}
		$.ajax({
			cache : false,
			url : "ChangeStatus.uo?user_id="+user_id+"&editor_status="+status,
			type : "GET",
			success : function(data) {
				if(data.indexOf("true") != -1){
					if(status == 0){
						alert("편집자 정보가 비공개처리 되었습니다.");
					} else {
						alert("편집자 정보가 공개처리 되었습니다.");
					}
				} else {
					alert("상태변경에 실패했습니다 관리자에게 문의하세요.");
					if(status == 0){
						$("input:checkbox[name=editor_status]").prop("checked", true);
					} else {
						$("input:checkbox[name=editor_status]").prop("checked", false);
					}
				}
			},
			error : function() {
		                alert("상태변경에 실패했습니다 관리자에게 문의하세요.");
						if(status == 0){
							$("input:checkbox[name=editor_status]").prop("checked", true);
						} else {
							$("input:checkbox[name=editor_status]").prop("checked", false);
						}
		            }
		});
		
	})

})
