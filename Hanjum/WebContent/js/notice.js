/**
 * 
 */

$(document).ready(function () {
	
		$('#btn').mouseover(function () {
			$.getJSON('getNewNotice.nt', function (rdata) {
				
				$('#new_notice_list *').remove();
			
				$.each(rdata, function (index, item) {
					
					if(item.notice_content == 1){
						// 주소값넣기 -> 수락 / 거절시에만
						$('#new_notice_list').append('<tr><td id="new_notice_td"><a href="updateStatus.nt?notice_id='+item.notice_id+'"><b>'+item.user_id+' </b>님 '+item.notice_from_id+' 님께 프로젝트를<br> 성공적으로 요청하였습니다</a><br><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> >> 삭제</a></td><tr>');
					}else if(item.notice_content == 2 && item.notice_read == 0){
// 					$('#new_notice_list').append('<tr><td id="new_notice_td"><a href="http://'+item.notice_url+'">'+item.notice_from_id+'님이 프로젝트를 요청하였습니다</a><br><div id="btns"><form action="matchNotice.nt"><input type="hidden" value="'+item.notice_id+'"><button type="submit" id="submit">수락</button></form><form action="declineNotice.nt"><input type="hidden" value="'+item.notice_id+'"><button type="submit" id="decline">거절</button></form></div></td><tr>');
						$('#new_notice_list').append('<tr><td id="new_notice_td">'+item.notice_from_id+'님이 프로젝트를 요청하였습니다</a><br><div id="btns"><a href="matchNotice.nt?notice_id='+item.notice_id+'" id="accept">수락</a>　*　<a href="declineNotice.nt?notice_id='+item.notice_id+'"id="decline">거절</a></div><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> >> 삭제</a></td><tr>');
					}else if(item.notice_content == 3){
						$('#new_notice_list').append('<tr><td id="new_notice_td"><a href="updateStatus.nt?notice_id='+item.notice_id+'"><b>'+item.user_id+'</b> 님, '+item.notice_from_id+' 님의 프로젝트를 요청을 수락하였습니다</a><br><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> >> 삭제</a></td><tr>');
					}else if(item.notice_content == 4){
						$('#new_notice_list').append('<tr><td id="new_notice_td"><a href="updateStatus.nt?notice_id='+item.notice_id+'">'+item.notice_from_id+' </b>님이 프로젝트를 요청을 수락하였습니다</a><br><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> >> 삭제</a></td><tr>');
					}else if(item.notice_content == 5){
						$('#new_notice_list').append('<tr><td id="new_notice_td"><a href="updateStatus.nt?notice_id='+item.notice_id+'"><b>'+item.user_id+' </b>님의 프로젝트를 요청을 거절하였습니다</a><br><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> >> 삭제</a></td><tr>');
					}else if(item.notice_content == 6){
						$('#new_notice_list').append('<tr><td id="new_notice_td"><a href="updateStatus.nt?notice_id='+item.notice_id+'">'+item.notice_from_id+' </b>님이 프로젝트를 요청을 거절하였습니다</a><br><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> >> 삭제</a></td><tr>');
					}
				});	
			}); //getJSON
		}); //click - 새알람
		
		$('#get_notice_btn').click(function () {
			$.getJSON('getOldNotice.nt', function (rdata) {
				
				$('#old_notice_list *').remove();
				
				$.each(rdata, function (index, item) {
					
					if(item.notice_content == 1){
						// 주소값넣기
						$('#old_notice_list').append('<tr><td id="old_notice_td"><b>'+item.user_id+' </b>님 '+item.notice_from_id+' 님께 프로젝트를<br> 성공적으로 요청하였습니다</a><br><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> >> 삭제</a></td><tr>');
					}else if(item.notice_content == 2){
						$('#old_notice_list').append('<tr><td id="old_notice_td">'+item.notice_from_id+'님이 프로젝트를 요청하였습니다</a><br><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> >> 삭제</a></td><tr>');
					}else if(item.notice_content == 3){
						$('#old_notice_list').append('<tr><td id="old_notice_td"><b>'+item.user_id+' 님, '+item.notice_from_id+' 님의 프로젝트를 요청을 수락하였습니다</a><br><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> >> 삭제</a></td><tr>');
					}else if(item.notice_content == 4){
						$('#old_notice_list').append('<tr><td id="old_notice_td">'+item.notice_from_id+' </b>님이 프로젝트를 요청을 수락하였습니다</a><br><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> >> 삭제</a></td><tr>');
					}else if(item.notice_content == 5){
						$('#old_notice_list').append('<tr><td id="old_notice_td"><b>'+item.user_id+' </b>님의 프로젝트를 요청을 거절하였습니다</a><br><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> >> 삭제</a></td><tr>');
					}else if(item.notice_content == 6){
						$('#old_notice_list').append('<tr><td id="old_notice_td">'+item.notice_from_id+' </b>님이 프로젝트를 요청을 거절하였습니다</a><br><a id="delete" href="deleteNotice.nt?notice_id='+item.notice_id+'"> >> 삭제</a></td><tr>');
					}

				});	
			}); //getJSON
		}); //click - 새알람
		
}); //ready	