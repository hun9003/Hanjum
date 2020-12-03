/**
 * 
 */
		 var modal = $(".layerForm"); 
		 var $container = $('.layer-container');
		 var $content = $('#write-layer-content'); // 회원가입 페이지가 들어갈 공간
	
		function join(){
			$content.removeClass('layer-content'); // 회원가입 페이지만큼 공간을 키워야 돼서 미리 만들어놓은 css 적용
												   // 있던 클래스 삭제
			$content.addClass('layer-join-content'); // 미리 설정해놓은 join 전용 클래스 삽입
			modal.addClass('show-layer');			// 그리고 숨겨놓은 공간을 열어야 해서 display: block 로
													// 설정을 바꿔주는 show-layer 클래스 삽입
			$container.load("Join.uo");				// 빈 공간에 Join.uo 페이지를 삽입시키는 함수
		}
		function joinEditor(){
			$content.removeClass('layer-content');
			$content.addClass('layer-join-content');
			modal.addClass('show-layer');
			$container.load("JoinEditor.uo");
		}
		function login(){
			$content.removeClass('layer-content');
			$content.addClass('layer-login-content');
			modal.addClass('show-layer');
			$container.load("Login.uo");
		}
		function toggleModal() { 
//             var show = modal.classList.toggle("show-layer");
			if(!modal.attr("class").includes("show-layer")){  // 이거는 페이지를 여는 스위치 show-layer가 만약 없다면
				modal.addClass('show-layer'); // 클래스 삽입
				$container.load('ProjectWrite.bo');
			} else { // 만약 있다면 닫아야 하니까
			 	$container.empty();	 // 불러왔던 페이지를 다 없애고 (empty() 함수)
				modal.removeClass('show-layer'); // 삽입했던 show-layer 클래스 삭제
				if ($content.attr("class") == "layer-login-content") {
					$content.removeClass('layer-login-content');
					$content.addClass('layer-content');			
				} else if ($content.attr("class") == "layer-join-content") {
					$content.removeClass('layer-join-content');
					$content.addClass('layer-content');			
				}
			}
         }
		
	        function windowOnClick(event) {
				var target = $(event.target);
					if(target.is(".layerForm")){
						toggleModal()
					}
	         }
			function loginConfirm(){
				var Confirm = confirm("로그인이 필요합니다 로그인 하시겠습니까?");
				if(Confirm){
					login();
				}
			}
	$(document).ready(function(){
		
			$(".layer-btn").click(function(){
				toggleModal();
			});
			$(".close").click(function(){
				toggleModal();
			});
			$(window).click(function(e){
				windowOnClick(e);
			});
			$('.item-link').click(function(){
				modal.addClass('show-layer');
			    $container.load($(this).attr('data-href'));
			});
			$('.layer-container').on("click","input[name=update_btn]",function(){
				$container.empty();
				$container.load($(this).attr('data-href'));
			});
			$('body').on("click","#join_btn",function(){
				join();
			});
			// 게시글 수정
			$('.layer-container').on("click","input[name=update-submit]",function(){
			 	var formData = $("#UpdateForm").serialize();
		        $.ajax({
		            cache : false,
		            url : "ProjectUpdatePro.bo", // 요기에
		            type : 'POST', 
		            data : formData, 
		            success : function(data) {
		                $container.empty();
						$container.load(data);
		            }, // success 
		    
		            error : function(xhr, status) {
		                alert(xhr + " : " + status);
		            }
		        }); // $.ajax */
			});
			
			

		});
		
		
