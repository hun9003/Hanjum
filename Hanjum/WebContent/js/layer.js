/**
 * 
 */
         var modal = document.querySelector(".layerForm"); 
         var trigger = document.querySelector(".layer-btn"); 
         var closeButton = document.querySelector(".close"); 
		 var $container = $('.layer-container');
		 var $content = $('#write-layer-content');
		// 게시글 작성        
		function toggleModal() { 
             var show = modal.classList.toggle("show-layer");
			if(show){
				$container.load('ProjectWrite.bo');
			} else {
			 	$container.empty();	
				if ($content.attr("class") == "layer-login-content") {
					$content.removeClass('layer-login-content');
					$content.addClass('layer-content');			
				} else if ($content.attr("class") == "layer-join-content") {
					$content.removeClass('layer-join-content');
					$content.addClass('layer-content');			
				}
			}
         }
		function login(){
			$content.removeClass('layer-content');
			$content.addClass('layer-login-content');
			modal.classList.toggle("show-layer");
			$container.load("Login.uo");
			
		}
		function join(){
			$content.removeClass('layer-content');
			$content.addClass('layer-join-content');
			modal.classList.toggle("show-layer");
			$container.load("Join.uo");
			
		}
        function windowOnClick(event) { 
             if (event.target === modal) { 
                 toggleModal(); 
             }
			 
         }
		
        trigger.addEventListener("click", toggleModal); 
        closeButton.addEventListener("click", toggleModal); 
        window.addEventListener("click", windowOnClick); 
		
		
		// 게시글 조회 
		$(document).ready(function(){
			$('.item-link').click(function(){
				modal.classList.toggle("show-layer");
			    $container.load($(this).attr('data-href'));
			});
			$('.layer-container').on("click","input[name=update_btn]",function(){
				$container.empty();
				$container.load($(this).attr('data-href'));
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
		
		
		
