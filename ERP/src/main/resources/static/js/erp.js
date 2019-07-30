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
	
	function getLastMessage(_callback){
		var url='/messages/getLast';
		var message;
		
		$.getJSON(url,function(u,statusText,jqxhr){

		}).done(function(msg,statusText,jqxhr){
			if(jqxhr.status==200){
				message=msg;
				_callback(STATUS.DONE,message);
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
		STATUS			:STATUS,
		findUserById	:findUserById,
		saveUser		:saveUser,
		getLastMessage	:getLastMessage
	}
	
})();
 
var Home=(function(){
	
	var tiles={
		users		: '#users',
		newUser 	: '#new_user',
		changeUser 	: '#change_user',
		roles		: '#roles'
	}

 	function init(){
 		console.log('TEST: initialize Home');
 		_bindEventHandlers();
 	}
 	
 	function _bindEventHandlers(){
 		$(tiles.users).click(_reqUsers);
		$(tiles.newUser).click(_reqNewUser);
		$(tiles.changeUser).click(_reqChangeUser);
		$(tiles.roles).click(_reqRoles);
	}
	
 	function _reqUsers(){
 		console.log('Show users...');
 		window.location.assign('/user/view_users');
 	}
 	
 	function _reqNewUser(){
 		console.log('Create new user...');
 		window.location.assign('/user/new_user');
 	}
 	
 	function _reqChangeUser(){
 		console.log('Change user...');
 		window.location.assign('/user/change_user');
 	}
 	
 	function _reqRoles(){
 		console.log('Show auth roles...');
 		window.location.assign('/role/view_roles');
 		
 	}
 	
	return{
		init : init
	} 
	
})();
 
var NewUser=(function(){
	
	var fields={
			userId		:'#user_id',
			errorMsg	:'#error_msg'
	}
	var controls={
			createUser 	:'#btn_create',
			back		:'#btn_back'
	}
	
 	function init(){
 		console.log('TEST: initialize NewUser');
 		_bindEventHandlers();
 		MessageHandler.refreshMessageBox();
 	}
 	
 	function _bindEventHandlers(){
		$(controls.createUser).click(_createUser);
		$(controls.back).click(_back);
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
 	
 	function _back(){
 		window.location.assign('/home');
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
	
	var fields={
		userId		:'#user_id',
		errorMsg	:'#error_msg'
	}
	
	var controls={
			changeUser 	:'#btn_change',
			back		:'#btn_back'
	}
	
 	function init(){
 		console.log('TEST: initialize ChangeUser');
 		_bindEventHandlers();
 		MessageHandler.refreshMessageBox();
 	}
	
	function _bindEventHandlers(){
		$(controls.changeUser).click(_changeUser);
		$(controls.back).click(_back);
	}
 	
 	function _changeUser(){
 		$(fields.errorMsg).text('');
 		console.log('TEST: trying to change user...');
 		//test if user id exists
 		var userId=$(fields.userId).val();
 		DAO.findUserById(userId,function(status,user){
			if(status==DAO.STATUS.DONE){
				//user exists
				console.log(user);
				_showUserEditor(userId);
			}
			else if(status==DAO.STATUS.NA){
				//no user found. display error message.
				$(fields.errorMsg).text('User '+userId+' not found!');
			}
		});	
 	}
 	
 	function _showUserEditor(userId){
 		console.log('show user editor...');
 		window.location.assign('/user/edit/'+userId+'/false');
 	}
 	
 	function _back(){
 		window.location.assign('/home');
 	}
 	
 	return{
		init : init
	} 
 	
})();

var EditUser=(function(){
	
	var mode;
	var form='#user_editor_form';
	var listRolesAssigned='#list_roles_assigned';
	var listRolesAvailable='#list_roles_available';
	
	var fields={
			userId 		:'user_id',
			firstName 	:'firstName',
			lastName 	:'lastName',
			email 		:'email',
			password1 	:'password1',
			password2 	:'password2',
			initialPw 	:'chkInitialPw',
			locked 		:'chkLocked',
			validFrom 	:'dateValidFrom',
			validTo 	:'dateValidTo'
	}
	var controls={
			save 		:'#btn_save',
			cancel 		:'#btn_cancel',
			resetPw		:'#resetPw',
			addRole 	:'#btn_add_role',
			removeRole	:'#btn_remove_role'
				
	}
	
	function init(){
		mode=$('#edit_mode').val();
		console.log('TEST: initialize EditUser. edit_mode='+mode);
		//JQuery UI tabs cause problem with validation (hidden elements). This fixes that.
		$.validator.setDefaults({
			ignore:""
		});
		
		_initFormValidation();
		_bindEventHandlers();
		
		if(mode=='new'){
			_resetPw();
		}
		else if(mode=='change'){
			_setPwMandatory(false);
		}
	}
	
	function _initFormValidation(){
		$(form).validate({
			rules:{
				firstName	:{
					required	:true
				},
				lastName	:{
					required	:true
				},
				email		:{
					required	:true,
					email		:true,
					maxlength	:255
				},
				/*password1	:{
					required	:true,
					minlength	:5
				},
				password2	:{
					required	:true,
					minlength	:5,
					equalTo		:password1
				},*/
				validFrom	:{
					required:	true
				}
			},
			messages:{
				firstName	:'First name is required',
				lastName	:'Last name is required',
				email		:'Enter valid email',
				validFrom	:'Start date is required',
				password1	:{
					required	:'Please provide password',
					minlength	:'Password must be atleast 5 characters long'
				},
				password2	:{
					required	:'Please confirm password',
					equalTo		:'Passwords are not matching'
				}
			},
			submitHandler	:function(form){
				$(controls.save).prop('disabled',false);
				$(controls.save).focus();
			},
			invalidHandler	:function(form,validator){
				var errors=validator.numberOfInvalids();
				if(errors){
					$(controls.save).prop('disabled',true);
				}
			}
		});
	}
	
	function _setPwMandatory(isMandatory){
		if(isMandatory){
			$(password1).rules('add',{
				required	:true,
				minlength	:5
			});
			$(password2).rules('add',{
				required	:true,
				minlength	:5,
				equalTo:	password1
			});
		}
		else{
			$(password1).rules('remove');
			$(password2).rules('remove');
		}
	}
	
	function _bindEventHandlers(){
		$(form).submit(_noAutoSubmit);
		$(controls.save).click(_saveUser);
		$(controls.cancel).click(_cancelEdit);
		$(controls.resetPw).click(_resetPw);
		$(controls.addRole).click(_addRole);
		$(controls.removeRole).click(_removeRole);
	}
	
	function _noAutoSubmit(e){
		e.preventDefault();
		return false;
	}
	
	function _resetPw(){
		$('#pw1').attr('class','tableRow');
		$('#pw2').attr('class','tableRow');
		$('#resetPw').attr('class','hiddenRow');
		_setPwMandatory(true);
	}
	
	function _addRole(){
		var selectedRoles=$(listRolesAvailable).find(".ui-selected");
		Array.from(selectedRoles).forEach(function(item){
			console.log(item);
			$(listRolesAssigned).append(item);
		});
		var roles=_getAssignedRoles();
		roles.forEach(function(role){
			console.log(role);
		});
		
	}
	
	function _removeRole(){
		var selectedRoles=$(listRolesAssigned).find(".ui-selected");
		Array.from(selectedRoles).forEach(function(item){
			console.log(item);
			$(listRolesAvailable).append(item);
		});
	}
	
	function _getAssignedRoles(){
		var elements=$(listRolesAssigned).find("li");
		var roles=[];
		var role;
		Array.from(elements).forEach(function(el){
			role={
					id			: parseInt($(el).attr("data-id")),
					name		:$(el).text(),
					description	:""
			}
			roles.push(role);
		});
		
		return roles;
		
	}
	
	function _saveUser(){
		var isValid=$(form).valid();
		if(isValid){
			var user=_getJSON();
			var create=false;
			console.log(user);
			//jos uusi käyttäjä...
			if(mode=='new'){
				create=true;
			}
			DAO.saveUser(user,create,function(status,usr){
				if(status==DAO.STATUS.DONE){
					if(mode=='new'){
						window.location.assign('/user/new_user?status=user_created');
					}
					else{
						window.location.assign('/user/change_user?status=user_changed');
					}
				}
				else if(status==DAO.STATUS.FAIL){
					//display error message
				}
			});
		}
	}
	
	function _cancelEdit(){
		if(mode=='new'){
			window.location.assign('/user/new_user?status=cancel');
		}
		else{
			window.location.assign('/user/change_user?status=cancel');
		}
	}
	
	function _validate(){
		
	}
	
	function _getJSON(){
		var user={
				username	:$('#'+fields.userId).val(),
				firstName	:$('#'+fields.firstName).val(),
				lastName	:$('#'+fields.lastName).val(),
				email		:$('#'+fields.email).val(),
				password	:$('#'+fields.password1).val(),
				initialPw	:$('#'+fields.initialPw).prop('checked'),
				locked		:$('#'+fields.locked).prop('checked'),
				validFrom	:$('#'+fields.validFrom).val(),
				validTo		:$('#'+fields.validTo).val(),
				roles		:_getAssignedRoles()
		}
		return user;
	}
	
	return{
		init : init
	}
	
})();

var MessageHandler=(function(){
	
	var messageBox='#message_container';
	var messageText='#message_text';
	
	function refreshMessageBox(){
		DAO.getLastMessage(function(status,message){
			if(status==DAO.STATUS.DONE){
				//display message
				_displayMessage(message);
			}
			else if(status==DAO.STATUS.NA){
				//no message found
				_hideMessages();
			}
		});
	}
	
	function _displayMessage(message){
		console.log(message);
		if(message.type=='SUCCESS'){
			$(messageBox).attr('class','success');
		}
		else if(message.type=='FAILURE'){
			$(messageBox).attr('class','failure');
		}
		else if(message.type=='WARNING'){
			$(messageBox).attr('class','warning');
		}
		else if(message.type=='INFO'){
			$(messageBox).attr('class','info');
		}
		$(messageText).text(message.messageText);
		
	}
	
	function _hideMessages(){
		console.log('No messages');
		$(messageBox).attr('class','hidden');
		$(messageText).text('');
	}
	
	return{
		refreshMessageBox : refreshMessageBox
	}
	
})();
 