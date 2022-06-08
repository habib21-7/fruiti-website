function valid_datas( f ){
	
	if( f.username.value == '' ){
		jQuery('#form_status_register').html('<span class="wrong">Your User Name Empty !</span>');
		notice( f.username );
	}else if( f.phone.value == '' ){
		jQuery('#form_status_register').html('<span class="wrong">Your Phone empty !</span>');
		notice( f.phone );
	}else if( f.email.value == '' ){
		jQuery('#form_status_register').html('<span class="wrong">Your Email empty !</span>');
		notice( f.email );
	}else if( f.password.value == '' ){
		jQuery('#form_status_register').html('<span class="wrong">Your Password empty !</span>');
		notice( f.password );
	}else if( f.repassword.value == '' ){
		jQuery('#form_status_register').html('<span class="wrong">Your RePassword empty !</span>');
		notice( f.repassword );
	}else{
		 jQuery.ajax({
			url: 'mail.php',
			type: 'post',
			data: jQuery('form#fruiti-contact-register').serialize(),
			complete: function(data) {
				jQuery('#form_status_register').html(data.responseText);
				jQuery('#fruiti-contact-register').find('input').attr({value:''});
				
			}
		});
		jQuery('#form_status_register').html('<span class="loading">Sending your message...</span>');
		jQuery('#fruiti-contact-register').animate({opacity:0.3});
		jQuery('#fruiti-contact-register').find('input,button').css('border','none').attr({'disabled':''});
	}
	
	return false;
}

function notice( f ){
	jQuery('#fruiti-contact-register').find('input').css('border','none');
	f.style.border = '1px solid red';
	f.focus();
}