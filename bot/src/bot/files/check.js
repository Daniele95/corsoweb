 $.ajax(
		{
			url: 'async-event.php',
			data: $('.event-save').serialize() + '&action=check',
			type: 'POST',
			dataType: 'JSON',
			success: function (data) {
			
				document.getElementById("form-messages").innerHTML += "Posso salvare: " + data.userMaySaveEvent;
			
				eventIsProvisional = data.eventIsProvisional;
				userMayEditEvent = data.userMayEditEvent;
				userMaySaveEvent = data.userMaySaveEvent;
				userMayCancelEvent = data.userMayCancelEvent;
				userMayReconfirmEvent = false;
				messages = data.messages;
				console.log(data);

				refreshControls();

				displayMessages(messages);
			}
		}
	);