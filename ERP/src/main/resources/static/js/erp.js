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
	
	var fields={
			userId : '#user_id'
	}
	var controls={
			createUser :'#btn_create'
	}
	
 	function init(){
 		console.log('TEST: initialize NewUser');
 		_bindEventHandlers();
 	}
 	
 	function _bindEventHandlers(){
		$(controls.createUser).click(_createUser);
	}
 	
 	function _createUser(){
 		console.log('TEST: trying to create new user...');
 		//test if user id exists
 		var userId=$(fields.userId).val();
 		var user;
 		if(userId!=null){
	 		var url='/user/findById?userId='+userId;
	 		$.getJSON(url,function(u,statusText,jqxhr){
				
			}).done(function(u,statusText,jqxhr){
				if(jqxhr.status==200){
					//if exists then display error
					user=u;
					console.log(user);
				}
				else if(jqxhr.status==204){
					console.log('Status: NA');
				}
				else{
					console.log('Status: UNKNOWN');
				}
				
			}).fail(function(){
				//Show error message
				console.log('Status: FAIL');
			}).always(function(){
				
			});
	 		
	 		
 		}
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
 