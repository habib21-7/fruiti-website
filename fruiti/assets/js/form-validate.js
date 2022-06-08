function valid_datas( f ){
	
	if( f.username.value == '' ){
		jQuery('#form_status').html('<span class="wrong">Your User Name Empty !</span>');
		notice( f.username );
	}else if( f.password.value == '' ){
		jQuery('#form_status').html('<span class="wrong">Your Password empty !</span>');
		notice( f.password );
	}else{
		 jQuery.ajax({
			url: 'mail.php',
			type: 'post',
			data: jQuery('form#fruiti-contact').serialize(),
			complete: function(data) {
				jQuery('#form_status').html(data.responseText);
				jQuery('#fruiti-contact').find('input').attr({value:''});
				
			}
		});
		jQuery('#form_status').html('<span class="loading">Sending your message...</span>');
		jQuery('#fruiti-contact').animate({opacity:0.3});
		jQuery('#fruiti-contact').find('input,button').css('border','none').attr({'disabled':''});
	}
	
	return false;
}

function notice( f ){
	jQuery('#fruiti-contact').find('input').css('border','none');
	f.style.border = '1px solid red';
	f.focus();
}