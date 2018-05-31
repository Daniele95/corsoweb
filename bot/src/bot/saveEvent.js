function saveEvent()
{

	$.ajax(
		{
			url: 'async-event.php',
			data: $('.event-save').serialize() + '&action=save',
			type: 'POST',
			dataType: 'JSON',
			success: function (messages) {
				displayMessages(messages);
			}
		}
	);

}