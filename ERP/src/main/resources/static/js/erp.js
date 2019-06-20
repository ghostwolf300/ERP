/**
 * 
 */
 $(document).ready(initPage);
 
 function initPage(){
 	console.log('TEST: initializing page');
 	var view=$('meta[name="viewId"]').attr('content');
 	if(view=='HOME'){
 		Home.init();
 	}
 	else if(view=='NEW_USER'){
 		NewUser.init();
 	}
 	else if(view=='CHANGE_USER'){
 		ChangeUser.init();
 	}
 	
 }
 
var Home=(function(){
	
	var tiles={
		newUser 	: '#new_user',
		changeUser 	: '#change_user' 
	}

 	function init(){
 		console.log('TEST: initialize Home');
 		_bindEventHandlers();
 	}
 	
 	function _bindEventHandlers(){
		$(tiles.newUser).click(_reqNewUser);
		$(tiles.changeUser).click(_reqChangeUser);
	}
	
 	function _reqNewUser(){
 		console.log('Create new user...');
 		window.location.assign('/new_user');
 	}
 	
 	function _reqChangeUser(){
 		console.log('Change user...');
 		window.location.assign('/change_user');
 	}
 	
	return{
		init : init
	} 
	
})();
 
var NewUser=(function(){
 	function init(){
 		console.log('TEST: initialize NewUser');
 	}
 	
 	return{
		init : init
	} 
 	
})();
 
var ChangeUser=(function(){
 	function init(){
 		console.log('TEST: initialize ChangeUser');
 	}
 	
 	return{
		init : init
	} 
 	
})();
 