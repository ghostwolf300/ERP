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
 	else if(view=='ROLES'){
 		Roles.init();
 	}
 	else if(view=='ROLE_SELECT'){
 		RoleSelect.init();
 	}
 	else if(view=='ROLE_DATA'){
 		RoleData.init();
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
	
	function findRoleObjects(roleId,_callback){
		var url='/role/getRoleObjects?roleId='+roleId;
		var objects;
		
		$.getJSON(url,function(u,statusText,jqxhr){

		}).done(function(objs,statusText,jqxhr){
			if(jqxhr.status==200){
				objects=objs;
				_callback(STATUS.DONE,objects);
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
		getLastMessage	:getLastMessage,
		findRoleObjects	:findRoleObjects
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
		$(tiles.roles).click(_reqRoleSelect);
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
 	
 	function _reqRoleSelect(){
 		console.log('Role selection');
 		window.location.assign('/role/select');
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


var RoleSelect=(function(){
	
	var controls={
			display		:'#btn_display',
			edit		:'#btn_edit',
			create		:'#btn_create',
			cancel		:'#btn_cancel',
			roleSelect	:'#cmb_roles'
	}
	
	function init(){
		console.log("TEST: Initialize RoleSelect");
		_initJQueryUI();
		_bindEventHandlers();
	}
	
	function _initJQueryUI(){
		$(controls.display).button();
		$(controls.edit).button();
		$(controls.create).button();
		$(controls.cancel).button();
		_initCombobox();
		
	}
	
	function _initCombobox(){
		$.widget("custom.combobox", {
		      _create: function() {
		        this.wrapper = $( "<span>" )
		          .addClass("custom-combobox" )
		          .insertAfter( this.element );
		 
		        this.element.hide();
		        this._createAutocomplete();
		        this._createShowAllButton();
		      },

		      _createAutocomplete: function() {
		        var selected = this.element.children( ":selected" ),
		          value = selected.val() ? selected.text() : "";
		 
		        this.input = $( "<input>" )
		          .appendTo( this.wrapper )
		          .val( value )
		          .attr( "title", "" )
		          .addClass( "custom-combobox-input ui-widget ui-widget-content ui-state-default ui-corner-left" )
		          .autocomplete({
		            delay: 0,
		            minLength: 0,
		            source: $.proxy( this, "_source" )
		          })
		          .tooltip({
		            classes: {
		              "ui-tooltip": "ui-state-highlight"
		            }
		          });
		 
		        this._on( this.input, {
		          autocompleteselect: function( event, ui ) {
		            ui.item.option.selected = true;
		            this._trigger( "select", event, {
		              item: ui.item.option
		            });
		          },
		 
		          autocompletechange: "_removeIfInvalid"
		        });
		      },
		      
		      _createShowAllButton: function() {
		        var input = this.input,
		          wasOpen = false;
		 
		        $("<a>")
		          .attr( "tabIndex", -1 )
		          .attr( "title", "Show All Items" )
		          .tooltip()
		          .appendTo( this.wrapper )
		          .button({
		            icons: {
		              primary: "ui-icon-triangle-1-s"
		            },
		            text: false
		          })
		          .removeClass("ui-button")
		          .removeClass("ui-button-icon-only")
		          .removeClass("ui-corner-all")		 
		          .addClass("ui-corner-right")
		          .addClass("custom-combobox-toggle")
		          .addClass("ui-button")
		          .addClass("ui-button-icon-only")
		          .on( "mousedown", function() {
		            wasOpen = input.autocomplete("widget").is(":visible");
		          })
		          .on( "click", function() {
		            input.trigger( "focus" );
		 
		            // Close if already visible
		            if ( wasOpen ) {
		              return;
		            }
		 
		            // Pass empty string as value to search for, displaying all results
		            input.autocomplete( "search", "" );
		          });
		      },
		 
		      _source: function( request, response ) {
		        var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );
		        response( this.element.children( "option" ).map(function() {
		          var text = $( this ).text();
		          if ( this.value && ( !request.term || matcher.test(text) ) )
		            return {
		              label: text,
		              value: text,
		              option: this
		            };
		        }) );
		      },
		 
		      _removeIfInvalid: function( event, ui ) {
		 
		        // Selected an item, nothing to do
		        if ( ui.item ) {
		          return;
		        }
		 
		        // Search for a match (case-insensitive)
		        var value = this.input.val(),
		          valueLowerCase = value.toLowerCase(),
		          valid = false;
		        this.element.children( "option" ).each(function() {
		          if ( $( this ).text().toLowerCase() === valueLowerCase ) {
		            this.selected = valid = true;
		            return false;
		          }
		        });
		 
		        // Found a match, nothing to do
		        if ( valid ) {
		          return;
		        }
		 
		        // Remove invalid value
		        this.input
		          .val( "" )
		          .attr( "title", value + " didn't match any item" )
		          .tooltip( "open" );
		        this.element.val( "" );
		        this._delay(function() {
		          this.input.tooltip( "close" ).attr( "title", "" );
		        }, 2500 );
		        this.input.autocomplete( "instance" ).term = "";
		      },
		 
		      _destroy: function() {
		        this.wrapper.remove();
		        this.element.show();
		      }
		    });
		    $(controls.roleSelect).combobox(); 
	}
	
	function _bindEventHandlers(){
		$(controls.cancel).click(_cancel);
		$(controls.display).click(_display);
		$(controls.edit).click(_edit);
		$(controls.create).click(_create);
	}
	
	function _cancel(){
 		window.location.assign('/home');
 	}
	
	function _display(){
		var roleId=$(controls.roleSelect).val();
		console.log("selected role id: "+roleId);
		if(roleId!==""){
			window.location.assign('/role/display?roleId='+roleId);
		}
		else{
			console.log("no role selected");
		}
	}
	
	function _edit(){
		var roleId=$(controls.roleSelect).val();
		console.log("selected role id: "+roleId);
		if(roleId!==""){
			window.location.assign('/role/edit?roleId='+roleId);
		}
		else{
			console.log("no role selected");
		}
	}
	
	function _create(){
		
	}
	
	return{
		init	:init
	}
	
})();

var RoleData=(function(){
	
	var controls={
			cancel	:'#btn_cancel',
			save	:'#btn_save'
	}
	
	var checkboxes={
			readRights	: '.chk-read-rights',
			updateRights: '.chk-update-rights',
			createRights: '.chk-create-rights',
			deleteRights: '.chk-delete-rights'
	}
	
	function init(){
		_initJQueryUI();
		_bindEventHandlers();
	}
	
	function _initJQueryUI(){
		$(controls.cancel).button();
		$(controls.save).button();
		$(checkboxes.readRights).checkboxradio();
		$(checkboxes.updateRights).checkboxradio();
		$(checkboxes.createRights).checkboxradio();
		$(checkboxes.deleteRights).checkboxradio();
	}
	
	function _bindEventHandlers(){
		$(controls.cancel).click(_cancel);
		$(controls.save).click(_save);
	}
	
	function _cancel(){
		window.location.assign('/role/select');
	}
	
	function _save(){
		
	}
	
	return{
		init	:init
	}
	
})();


var Roles=(function(){
	
	var lists={
		roles 	: '#list_roles'
	}
	
	var objectList='#auth-object-list';
	
	var checkboxes={
		readRights	: '.chk-read-rights',
		updateRights: '.chk-update-rights',
		createRights: '.chk-create-rights',
		deleteRights: '.chk-delete-rights'
	}
	
	function init(){
		console.log('TEST: initialize Roles');
		_initJQueryUI();
		_bindEventHandlers();
	}
	
	function _initJQueryUI(){
		$(lists.roles).selectable({
			selecting	: _singleSelectOnly,
			selected	: _roleSelected
		});
		$(checkboxes.readRights).checkboxradio();
		$(checkboxes.updateRights).checkboxradio();
		$(checkboxes.createRights).checkboxradio();
		$(checkboxes.deleteRights).checkboxradio();
	}
	
	function _bindEventHandlers(){
		
	}
	
	function _singleSelectOnly(event,ui){
		//force single select
		$(event.target).find('.ui-selectee.ui-selecting').not(ui.selecting).removeClass('ui-selecting');
        $(event.target).find('.ui-selectee.ui-selected').not(ui.selecting).removeClass('ui-selected');
	}
	
	function _roleSelected(event, ui){
		console.log('Role selected '+event.target);
		var roleId=_getSelectedRoleId();
		console.log('Selected roleId: '+roleId);
		_loadRoleObjects(roleId);
	}
	
	function _getSelectedRoleId(){
		var roleId;
		var selectedRoles=$(lists.roles).find(".ui-selected");
		Array.from(selectedRoles).forEach(function(item){
			roleId=parseInt($(item).attr('data-id'));
		});
		return roleId;
	}
	
	function _loadRoleObjects(roleId){
		DAO.findRoleObjects(roleId,function(status,objects){
			if(status==DAO.STATUS.DONE){
				_clearRoleObjects();
				_insertRoleObjects(objects);
			}
			else{
				console.log('No role objects received');
			}
		});
	}
	
	function _clearRoleObjects(){
		$(objectList).empty();
	}
	
	function _insertRoleObjects(roleObjects){
		roleObjects.forEach(function(ro){
			console.log(ro);
			_createRoleObjectElement(ro);
		});
//		$(checkboxes.readRights).checkboxradio();
//		$(checkboxes.updateRights).checkboxradio();
//		$(checkboxes.createRights).checkboxradio();
//		$(checkboxes.deleteRights).checkboxradio();
	}
	
	function _createRoleObjectElement(roleObject){
		var ro=roleObject.object;
		var rl=roleObject.role;
		var chkRead=_createCheckbox(roleObject,'Read');
		var chkUpdate=_createCheckbox(roleObject,'Update');
		var chkCreate=_createCheckbox(roleObject,'Create');
		var chkDelete=_createCheckbox(roleObject,'Delete');
		
		var el='<div class="auth-object-row" data-roleId="'+rl.id+'" data-objectId="'+ro.id+'">'
		+'<div class="auth-object-text">'+ro.name+'</div>'
		+'<div class="auth-object-rights">'
		+_createLabel(roleObject,'Read')
		+chkRead
		+_createLabel(roleObject,'Update')
		+chkUpdate
		+_createLabel(roleObject,'Create')
		+chkCreate
		+_createLabel(roleObject,'Delete')
		+chkDelete
		+'</div>'
		+'</div>';
		console.log(el);
		$(objectList).append(el);
		
		$('#chk-read-rights-'+roleObject.object.id).checkboxradio();
		$('#chk-update-rights-'+roleObject.object.id).checkboxradio();
		$('#chk-create-rights-'+roleObject.object.id).checkboxradio();
		$('#chk-delete-rights-'+roleObject.object.id).checkboxradio();
		
		$('#chk-read-rights-'+roleObject.object.id).prop('checked',roleObject.readRights).checkboxradio('refresh');
		$('#chk-update-rights-'+roleObject.object.id).prop('checked',roleObject.updateRights).checkboxradio('refresh');
		$('#chk-create-rights-'+roleObject.object.id).prop('checked',roleObject.createRights).checkboxradio('refresh');
		$('#chk-delete-rights-'+roleObject.object.id).prop('checked',roleObject.deleteRights).checkboxradio('refresh');
	}
	
	function _createCheckbox(ro,right){
		var checked=false;
		if(right.toLowerCase()=='read'){
			checked=ro.readRights;
		}
		else if(right.toLowerCase()=='update'){
			checked=ro.updateRights;
		}
		else if(right.toLowerCase()=='create'){
			checked=ro.createRights;
		}
		else if(right.toLowerCase()=='delete'){
			checked=ro.deleteRights;
		}
		console.log('right: '+right+' checked: '+checked);
		var el='<input id="chk-'+right.toLowerCase()+'-rights-'+ro.object.id+'" class="chk-'+right.toLowerCase()+'-rights" type="checkbox" checked='+checked+'/>';
		return el;
	}
	
	function _createLabel(ro,right){
		var el='<label for="chk-'+right.toLowerCase()+'-rights-'+ro.object.id+'">'+right+'</label>';
		return el;
	}
	
	function _objectSelected(event, ui){
		console.log('Object selected '+event.target);
	}
	
	return{
		init	:init
	}
	
})();
 