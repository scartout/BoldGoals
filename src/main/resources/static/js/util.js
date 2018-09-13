jQuery(document).ready(function($){

$(".login_field").keyup(function(e) {
	checkEmptyFields(e);
});

$(".register_field").keyup(function(e) {
	checkEmptyFields(e);
	validateAndDisable('register_field', 'button_register');
});

function checkEmptyFields(e){
	if (_.isEmpty($('#'+e.target.id).val().trim())){
		$('#'+e.target.id).addClass('empty_input');
	}
	else{
		$('#'+e.target.id).removeClass('empty_input');
	}
}

function validateAndDisable(inputClass, buttonId){
	var fields = $('.'+inputClass);
	var result = true;
	_.each(fields, function(e){
		if (_.isEmpty(e.value)){
			result = false;
		}
	});
	if (result == false){
		$('#'+buttonId).attr('disabled','true');
	}
	else {
		$('#'+buttonId).removeAttr('disabled');
	}
}

})