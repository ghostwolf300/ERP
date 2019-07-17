/**
 * 
 */

$(document).ready(initPage);
 
function initPage(){
 	console.log('TEST: initializing page');
 	var view=$('meta[name="viewId"]').attr('content');
 	globalSetup();
 	if(view=='HOME'){
 		Home.init();
 	}
 	else if(view=='NEW_USER'){
 		NewUser.init();
 	}
 	else if(view=='CHANGE_USER'){
 		ChangeUser.init();
 	}
 	else if(view=='EDIT_USER'){
 		EditUser.init();
 	}
}

function globalSetup(){
	
	var csrf_header;
	var csrf_token;
	
	csrf_header=$('meta[name="_csrf_header"]').attr('content');
 	csrf_token=$('meta[name="_csrf"]').attr('content');
 	console.log(csrf_token);
 	
	$.ajaxSetup({
        headers:
        { 
        	'X-CSRF-TOKEN': csrf_token,
        	'Accept': 'application/json',
	        'Content-Type': 'application/json'
        }
    });
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
	
	function saveUser(user,create,_callback){
		var url='/user/edit/save?create='+create;
		data=JSON.stringify(user);
		
		$.ajax({
			url : url,
			method : "POST",
			data : data,
			dataType : "json"
		}).done(function(usr){
			_callback(STATUS.DONE,usr);
		}).fail(function(){
			alert("ERROR: Couldn't save user.");
			_callback(STATUS.FAIL);
		}).always(function(){
			
		});
		
	}
	
	return{
		STATUS			:STATUS,
		findUserById	:findUserById,
		saveUser		:saveUser
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
 		window.location.assign('/user/new_user');
 	}
 	
 	function _reqChangeUser(){
 		console.log('Change user...');
 		window.location.assign('/user/change_user');
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

var EditUser=(function(){
	
	var fields={
			userId 		:'#user_id',
			firstName 	:'#first_name',
			lastName 	:'#last_name',
			email 		:'#email',
			password1 	:'#password1',
			password2 	:'#password2',
			initialPw 	:'#chkInitialPw',
			locked 		:'#chkLocked',
			validFrom 	:'#dateValidFrom',
			validTo 	:'#dateValidTo'
	}
	var controls={
			save 	:'#btn_save',
			cancel 	:'#btn_cancel'
	}
	
	function init(){
		console.log('TEST: initialize EditUser');
		_bindEventHandlers();
	}
	
	function _bindEventHandlers(){
		$(controls.save).click(_saveUser);
		$(controls.cancel).click(_cancelEdit);
	}
	
	function _saveUser(){
		if(_passwordOk){
			var user=_getJSON();
			//jos uusi käyttäjä...
			console.log(user);
			create=true;
			DAO.saveUser(user,create,function(status,usr){
				if(status==DAO.STATUS.DONE){
					//display success message
				}
				else if(status==DAO.STATUS.FAIL){
					//display error message
				}
			});
		}
		else{
			//display error message
		}
	}
	
	function _cancelEdit(){
		window.location.assign('/user/new_user');
	}
	
	function _passwordOk(){
		if($(fields.password1).val()==$(fields.password2).val()){
			return true;
		}
		else{
			return false;
		}
	}
	
	function _getJSON(){
		var user={
				username	:$(fields.userId).val(),
				firstName	:$(fields.firstName).val(),
				lastName	:$(fields.lastName).val(),
				email		:$(fields.email).val(),
				password	:$(fields.password1).val(),
				initialPw	:$(fields.initialPw).prop('checked'),
				locked		:$(fields.locked).prop('checked'),
				validFrom	:$(fields.validFrom).val(),
				validTo		:$(fields.validTo).val()
		}
		return user;
	}
	
	return{
		init : init
	}
	
})();
 