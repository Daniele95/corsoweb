$.ajax(
		{
			url: 'async-event.php',
			data: $('.event-save').serialize() + '&action=check',
			type: 'POST',
			dataType: 'JSON',
			success: function (data) {

				eventIsProvisional = data.eventIsProvisional;
				userMayEditEvent = data.userMayEditEvent;
				userMaySaveEvent = data.userMaySaveEvent;
				userMayCancelEvent = data.userMayCancelEvent;
				userMayReconfirmEvent = false;
				messages = data.messages;
				console.log(data);

				refreshControls();

				displayMessages(messages);

			},
			beforeSend: function( a,b) {
				//console.log(data);
			//	document.getElementById("location-description").innerHTML=   "ciaoneeee";
			}
			
		}
	);