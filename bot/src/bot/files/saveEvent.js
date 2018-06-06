
					$.ajax(
						{
							url: 'async-event.php',
							data: $('.event-save').serialize() + '&action=save',
					/*
							data: 'event-id=0'	    +							
							'&location-id=32' 		+							
							'&category=2'			+							
							'&date=6%2F6%2F2018' 	+
							'&starttime=08%3A30' 	+
							'&endtime=09%3A00' 		+
							'&location=Aula%20114'	+							
							'&description='			+
							'&action=save'			,							
					*/
							
							
							
							type: 'POST',			
							dataType: 'JSON',
							success: function (data) {			
							
								document.getElementById("topbar-menu").innerHTML=  "ciaoneeee ";
						
							
							},
							
							error: function(XMLHttpRequest, textStatus, errorThrown) { 
				  		    },
				  		    beforeSend: function( a,b) {
				  		    }
						}			
					);				
