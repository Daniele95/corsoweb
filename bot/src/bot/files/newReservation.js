//$('.newReservation').click(
			//function (event) {

				var x = event.pageX - $(this).offset().left;
				var alt = $(this).attr('alt').split(',');

				var dato = alt[0];
				var tid = alt[1];
				var l = alt[2];

				tid = (tid * 1) + ((x / pixPerHour) * 60);
				tid = Math.round(tid / 15) * 15;

				var hours = Math.floor(tid / 60);
				var minuts = '0' + (tid - (hours * 60));

				tid = hours + ':' + minuts.substring(minuts.length - 2, minuts.length);

				document.mainform.action = 'reservation.php';
				document.mainform.tid_fra.value = tid;
				document.mainform.lokaler_id.value = l;
				document.mainform.submit();

			//}
		//);
