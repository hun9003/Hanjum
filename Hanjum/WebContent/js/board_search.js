$(document).ready(function(){
	$("#search_detail").click(function(){
		var height = $(".search_detail_form").css('height');
		if(height == '0px'){
			$(".search_detail_form").css('height','100%');	
		} else {
			$(".search_detail_form").css('height','0px');
		}
	})
	$(".search_detail_table input").change(function(){
		$(".searchReset").css('display','block');
	})
	$(".searchReset").click(function(){
		$(".searchReset").css('display','none');
	})
})