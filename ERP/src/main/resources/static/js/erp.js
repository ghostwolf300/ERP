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

var DAO=(function(){
	var STATUS={
		DONE : 'done',
		NA : 'na',
		UNKNOWN : 'unknown',
		FAIL : 'fail'
	}
	
	function findUserById(userId,_callback){
		
		var url='/user/findById?userId='+userId;
		var user;
		
		$.getJSON(url,function(u,statusText,jqxhr){

		}).done(function(u,statusText,jqxhr){
			if(jqxhr.status==200){
				user=u;
				_callback(STATUS.DONE,user);
			}
			else if(jqxhr.status==204){
				_callback(STATUS.NA);
			}
			else{
				_callback(STATUS.UNKNOWN);
			}

		}).fail(function(jqxhr, textStatus, error){
			_callback(STATUS.FAIL);
		}).always(function(){

		});
	}
	
	return{
		STATUS : STATUS,
		findUserById : findUserById
	}
	
})();
 
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
			userId : '#user_id',
			errorMsg : '#error_msg'
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
 		$(fields.errorMsg).text('');
 		console.log('TEST: trying to create new user...');
 		//test if user id exists
 		var userId=$(fields.userId).val();
 		DAO.findUserById(userId,function(status,user){
			if(status==DAO.STATUS.DONE){
				//user already exists. display error message
				console.log(user);
				$(fields.errorMsg).text('User '+userId+' already exists!');
			}
			else if(status==DAO.STATUS.NA){
				//no user found. can be created.
				_showUserEditor(userId);
			}
		});
 		
 	}
 	
 	function _showUserEditor(userId){
 		console.log('show user editor...');
 		window.location.assign('/user/edit/'+userId+'/true');
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
 