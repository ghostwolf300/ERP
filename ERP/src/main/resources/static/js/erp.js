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
 	else if(view=='USER_SELECT'){
 		UserSelect.init();
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
 	else if(view=='MATERIAL_SELECT'){
 		MaterialSelect.init();
 	}
 	else if(view=='MATERIAL_DATA'){
 		MaterialData.init();
 	}
 	else if(view=='BP_SELECT'){
 		BPSelect.init();
 	}
 	else if(view=='BP_DATA'){
 		BPData.init();
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
		var data=JSON.stringify(user);
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
	
	function searchMaterials(param,_callback){
		var url='/material/search';
		var data=JSON.stringify(param);
		var results;
		
		$.ajax({
			url : url,
			method : "POST",
			data : data,
			dataType : "json"
		}).done(function(rs){
			_callback(STATUS.DONE,rs);
		}).fail(function(){
			_callback(STATUS.FAIL);
		}).always(function(){
			
		});
		
	}
	
	function searchUsers(param,_callback){
		var url='/user/search';
		var data=JSON.stringify(param);
		var results;
		
		$.ajax({
			url : url,
			method : "POST",
			data : data,
			dataType : "json"
		}).done(function(rs){
			_callback(STATUS.DONE,rs);
		}).fail(function(){
			_callback(STATUS.FAIL);
		}).always(function(){
			
		});
	}
	
	function saveRole(role,create,_callback){
		var url='/role/save?create='+create;
		data=JSON.stringify(role);
		$.ajax({
			url : url,
			method : "POST",
			data : data,
			dataType : "json"
		}).done(function(r){
			_callback(STATUS.DONE,r);
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
	
	function findMaterialById(materialId,_callback){

		var url='/material/findById?materialId='+materialId;
		var material;
		
		$.getJSON(url,function(m,statusText,jqxhr){
		}).done(function(m,statusText,jqxhr){
			if(jqxhr.status==200){
				material=m;
				_callback(STATUS.DONE,material);
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
	
	function saveMaterial(material,create,_callback){
		var data=JSON.stringify(material);
		var url='/material/save?create='+create;;
		$.ajax({
			url : url,
			method : "POST",
			data : data,
			dataType : "json"
		}).done(function(mat){
			_callback(STATUS.DONE,mat);
		}).fail(function(){
			alert("ERROR: Couldn't save material.");
			_callback(STATUS.FAIL);
		}).always(function(){
			
		});
	}
	
	return{
		STATUS				:STATUS,
		findUserById		:findUserById,
		saveUser			:saveUser,
		saveRole			:saveRole,
		saveMaterial		:saveMaterial,
		getLastMessage		:getLastMessage,
		findRoleObjects		:findRoleObjects,
		findMaterialById	:findMaterialById,
		searchMaterials		:searchMaterials,
		searchUsers			:searchUsers
	}
	
})();
 
var Home=(function(){
	
	var tiles={
		users		: '#users',
		newUser 	: '#new_user',
		changeUser 	: '#change_user',
		roles		: '#roles',
		materials	: '#materials',
		bps			: '#business_partners'
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
		$(tiles.materials).click(_reqMaterialSelect);
		$(tiles.bps).click(_reqBPSelect);
	}
	
 	function _reqUsers(){
 		console.log('Show user select...');
 		window.location.assign('/user/select');
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
 	
 	function _reqMaterialSelect(){
 		console.log('Material selection');
 		window.location.assign('/material/select');
 	}
 	
 	function _reqBPSelect(){
 		console.log('BP selection');
 		window.location.assign('/bp/select');
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
						window.location.assign('/user/select?status=user_created');
					}
					else{
						window.location.assign('/user/select?status=user_changed');
					}
				}
				else if(status==DAO.STATUS.FAIL){
					//display error message
				}
			});
		}
	}
	
	function _cancelEdit(){
		window.location.assign('/user/select?status=cancel');
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
			deleteRights: '.chk-delete-rights',
			rights		: '.chk-rights'
	}
	
	var buttons={
		removeObject	:'.btn-remove-object',
		removeObjects	:'#btn_remove_objects',
		addObjects		:'#btn_add_objects'
	}
	
	var lists={
			unassignedObjects:'#objects-available-list'
	}
	
	var tables={
			assignedObjects:'#tbl_assigned_objects'
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
		$(buttons.removeObject).button();
		$(lists.unassignedObjects).selectable();
	}
	
	function _bindEventHandlers(){
		$(controls.cancel).click(_cancel);
		$(controls.save).click(_save);
		$(buttons.addObjects).click(_addObjects);
		$(buttons.removeObjects).click(_removeObjects);
		$(tables.assignedObjects+' tbody td.col-object').click(_selectAssignedObject);
	}
	
	function _selectAssignedObject(event){
		var td=event.target;
		var tr=$(event.target).parent()[0];
		var id=$(tr).attr('id');
		var name=$(td).find('span').text();
		console.log(id+' '+name);
		$(tr).addClass('object-selected');
	}
	
	function _addObjects(){
		var selectedObjects=$(lists.unassignedObjects).find(".ui-selected");
		Array.from(selectedObjects).forEach(function(item){
			console.log(item);
			_createObjectTableRow(item);
			$(item).remove();
		});
	}
	
	function _createObjectTableRow(el){
		var id=$(el).attr('data-id');
		var text=$(el).text();
		console.log(id+'\t'+text);
		var row='<tr id="'+id+'">'
			+'<td class="col-object"><span id="obj-'+id+'-name" class="disabled">'+text+'</span></td>'
			+'<td class="col-rights">'
			+'<label for="chk-read-rights-'+id+'"></label>'
			+'<input id="chk-read-rights-'+id+'" class="chk-read-rights" type="checkbox" checked="true"/>'
			+'</td>'
			+'<td class="col-rights">'
			+'<label for="chk-update-rights-'+id+'"></label>'
			+'<input id="chk-update-rights-'+id+'" class="chk-update-rights" type="checkbox" checked="false"/>'
			+'</td>'
			+'<td class="col-rights">'
			+'<label for="chk-create-rights-'+id+'"></label>'
			+'<input id="chk-create-rights-'+id+'" class="chk-create-rights" type="checkbox" checked="false"/>'
			+'</td>'
			+'<td class="col-rights">'
			+'<label for="chk-delete-rights-'+id+'"></label>'
			+'<input id="chk-delete-rights-'+id+'" class="chk-delete-rights" type="checkbox" checked="false"/>'
			+'</td>'
			+'<td>'
			+'<button id="btn_remove_'+id+'" onclick=RoleData.removeAssignedObject('+id+') class="ui-button ui-widget ui-corner-all ui-button-icon-only btn-remove-object">'
			+'<span class="ui-icon  ui-icon-circle-minus"></span>Remove Object'
			+'</button>'
			+'</td>'
			+'</tr>';
		$(tables.assignedObjects).find('tbody:last').append(row);
		
		$('#chk-read-rights-'+id).checkboxradio();
		$('#chk-read-rights-'+id).prop('checked',true).checkboxradio('refresh');
		$('#chk-update-rights-'+id).checkboxradio();
		$('#chk-update-rights-'+id).prop('checked',false).checkboxradio('refresh');
		$('#chk-create-rights-'+id).checkboxradio();
		$('#chk-create-rights-'+id).prop('checked',false).checkboxradio('refresh');
		$('#chk-delete-rights-'+id).checkboxradio();
		$('#chk-delete-rights-'+id).prop('checked',false).checkboxradio('refresh');
		
	}
	
	function _removeObjects(){
		
	}
	
	function _cancel(){
		window.location.assign('/role/select');
	}
	
	function _save(){
		console.log(_getAssignedObjects());
		var role=_getJSON();
		var create=false;
		console.log(role);
		DAO.saveRole(role,create,function(status,r){
			if(status==DAO.STATUS.DONE){
				console.log('Role saved');
				window.location.assign('/role/select');
			}
			else if(status==DAO.STATUS.FAIL){
				console.log('Role save failed!');
			}
		});
	}
	
	function _getAssignedObjects(){
		var rows=$(tables.assignedObjects+' tbody').find('tr');
		var roleObjects=[];
		var ro;
		Array.from(rows).forEach(function(tr){
			var role=_getRole();
			var object=_getAuthObject(tr);
			//console.log('object id:'+object.id);
			ro={
					role			:role,
					object			:object,
					readRights		:$('#chk-read-rights-'+object.id).prop('checked'),
					updateRights	:$('#chk-update-rights-'+object.id).prop('checked'),
					createRights	:$('#chk-create-rights-'+object.id).prop('checked'),
					deleteRights	:$('#chk-delete-rights-'+object.id).prop('checked')
			}
			roleObjects.push(ro);
		});
		
		return roleObjects;
	}
	
	function _getRole(){
		var role={
				id			:$('#role_id').val(),
				name		:$('#role_name').text(),
				description	:''
		}
		return role;
	}
	
	function _getAuthObject(tr){
		var id=$(tr).attr('id');
		var span=$(tr).find('#obj-'+id+'-name')[0];
		var name=$(span).text();
		
		var object={
				id		:id,
				name	:name
		}
		return object;
	}
	
	function _getJSON(){
		var role={
				id			:$('#role_id').val(),
				name		:$('#role_name').text(),
				description	:"",
				roleObjects	:_getAssignedObjects()
		}
		return role;
	}
	
	function removeAssignedObject(objectId){
		console.log('Removing object '+objectId);
		var span=$(tables.assignedObjects).find('#obj-'+objectId+'-name')[0];
		var name=$(span).text();
		console.log(name);
		var li='<li data-id="'+objectId+'" class="ui-widget-content">'+name+'</li>';
		$(lists.unassignedObjects).append(li);
		$(tables.assignedObjects).find('#'+objectId).remove();
	}
	
	return{
		init					:init,
		removeAssignedObject	:removeAssignedObject
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

var UserSelect=(function(){
	
	var controls={
			display		:'#btn_display',
			edit		:'#btn_edit',
			create		:'#btn_create',
			cancel		:'#btn_cancel',
			search		:'#btn_search'
	}
	
	var fields={
			userId		:'#user_id',
			errorMsg	:'#error_msg'
	}
	
	var searchDialog="#user_search_dialog";
	
	function init(){
		console.log("TEST: Initialize UserSelect");
		_initJQueryUI();
		_bindEventHandlers();
		MessageHandler.refreshMessageBox();
	}
	
	function _initJQueryUI(){
		$(controls.display).button();
		$(controls.edit).button();
		$(controls.create).button();
		$(controls.cancel).button();
		$(controls.search).button();
		$(searchDialog).dialog({
			autoOpen: false,
		    height: 460,
		    width: 660,
		    modal: true,
		    buttons:{
		    	Cancel		: function(){
		    		$(searchDialog).dialog('close');
		    	},
		    	"Select"	: _selectSearchResult
		    }
		});
	
	}
	
	function _bindEventHandlers(){
		$(controls.cancel).click(_cancel);
		$(controls.display).click(_display);
		$(controls.edit).click(_edit);
		$(controls.create).click(_create);
		$(controls.search).click(_search);
	}
	
	function _cancel(){
		window.location.assign('/home');
	}
	
	function _display(){
		console.log('User display');
		//only display
		$(fields.errorMsg).text('');
 		//test if user id exists
 		var userId=$(fields.userId).val();
 		DAO.findUserById(userId,function(status,user){
			if(status==DAO.STATUS.DONE){
				//user exists
				console.log(user);
				_showUserEditor(userId,false);
			}
			else if(status==DAO.STATUS.NA){
				//no user found. display error message.
				$(fields.errorMsg).text('User '+userId+' not found!');
			}
		});	
		
	}
	
	function _edit(){
		console.log('User edit');
		$(fields.errorMsg).text('');
 		//test if user id exists
 		var userId=$(fields.userId).val();
 		DAO.findUserById(userId,function(status,user){
			if(status==DAO.STATUS.DONE){
				//user exists
				console.log(user);
				_showUserEditor(userId,false);
			}
			else if(status==DAO.STATUS.NA){
				//no user found. display error message.
				$(fields.errorMsg).text('User '+userId+' not found!');
			}
		});	
	}
	
	function _create(){
		console.log('User create');
		$(fields.errorMsg).text('');
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
				_showUserEditor(userId,true);
			}
		});	
	}
	
	function _search(){
		console.log('Search users');
		$(searchDialog).dialog('open');
		console.log("Search initialized: "+UserSearch.isInitialized());
		if(UserSearch.isInitialized()==false){
			UserSearch.init();
		}
	}
	
	function _selectSearchResult(){
		var id=UserSearch.getSelectedUser();
		console.log('Select search result '+id);
		$(fields.userId).val(id);
		$(searchDialog).dialog('close');
	}
	
	function _showUserEditor(userId,create){
 		console.log('show user editor...');
 		window.location.assign('/user/edit/'+userId+'/'+create);
 	}
	
	return{
		init	:init
	}
	
})();

var MaterialSelect=(function(){
	
	var controls={
			display		:'#btn_display',
			edit		:'#btn_edit',
			create		:'#btn_create',
			cancel		:'#btn_cancel',
			search		:'#btn_open_search'
	}
	
	var fields={
			materialId		:'#material_id',
			errorMsg		:'#error_msg'
	}
	
	var searchDialog='#material_search_dialog';
	
	function init(){
		console.log("TEST: Initialize MaterialSelect");
		_initJQueryUI();
		_bindEventHandlers();
		MessageHandler.refreshMessageBox();
	}
	
	function _initJQueryUI(){
		$(controls.display).button();
		$(controls.edit).button();
		$(controls.create).button();
		$(controls.cancel).button();
		$(controls.search).button();
		$(searchDialog).dialog({
			autoOpen: false,
		    height: 460,
		    width: 660,
		    modal: true,
		    buttons:{
		    	Cancel		: function(){
		    		$(searchDialog).dialog('close');
		    	},
		    	"Select"	: _selectSearchResult
		    }
		});
	
	}
	
	function _bindEventHandlers(){
		$(controls.cancel).click(_cancel);
		$(controls.display).click(_display);
		$(controls.edit).click(_edit);
		$(controls.create).click(_create);
		$(controls.search).click(_search);
	}
	
	function _cancel(){
		window.location.assign('/home');
	}
	
	function _display(){
		console.log('Material display');
		//only display
		$(fields.errorMsg).text('');
 		//test if material id exists
 		var materialId=$(fields.materialId).val();
 		console.log('Material id: '+materialId);
 		
 		_materialExists(materialId,function(exists){
 			if(exists){
 				console.log('material found');
 				window.location.assign('/material/display?materialId='+materialId);
 			}
 			else{
 				$(fields.errorMsg).text('Material '+materialId+' not found!');
 			}
 		});
 		
	}
	
	function _edit(){
		console.log('Material edit');
		$(fields.errorMsg).text('');
 		//test if material id exists
 		var materialId=$(fields.materialId).val();
 		console.log('Material id: '+materialId);
 		_materialExists(materialId,function(exists){
 			if(exists){
 				window.location.assign('/material/edit?materialId='+materialId);
 			}
 			else{
 				$(fields.errorMsg).text('Material '+materialId+' not found!');
 			}
 		});
	}
	
	function _create(){
		console.log('Material create');
		$(fields.errorMsg).text('');
 		//test if material id exists
 		var materialId=$(fields.materialId).val();
 		console.log('Material id: '+materialId);
 		_materialExists(materialId,function(exists){
 			if(exists){
 				$(fields.errorMsg).text('Material '+materialId+' already exists!');
 			}
 			else{
 				window.location.assign('/material/create?materialId='+materialId);
 			}
 		});
 		
	}
	
	function _search(){
		console.log('Search materials');
		$(searchDialog).dialog('open');
		console.log("Search initialized: "+MaterialSearch.isInitialized());
		if(MaterialSearch.isInitialized()==false){
			MaterialSearch.init();
		}
	}
	
	function _selectSearchResult(){
		var id=MaterialSearch.getSelectedMaterial();
		console.log('Select search result '+id);
		$(fields.materialId).val(id);
		$(searchDialog).dialog('close');
	}
	
	function _materialExists(materialId,_callback){
 		DAO.findMaterialById(materialId,function(status,material){
			if(status==DAO.STATUS.DONE){
				//material exists
				console.log(material);
				_callback(true)
			}
			else if(status==DAO.STATUS.NA){
				//no material found. display error message.
				_callback(false)
			}
			else{
				//some other error
				_callback(false)
			}
 		});	
	}
	
	return{
		init	:init
	}
	
})();

var MaterialData=(function(){
	
	var controls={
			cancel	:'#btn_cancel',
			save	:'#btn_save'
	}
	
	var form='#material_form';
	
	var texts={
			material	:'#material_id'
	}
	
	var fields={
			typeId		:'#type_id',
			action		:'#action_id',
			name		:'#name',
			ean13		:'#ean13',
			legacyId	:'#legacy_id',
			grossWeight	:'#gross_weight',
			netWeight	:'#net_weight',
			length		:'#length',
			width		:'#width',
			height		:'#height'
	}
	
	var select={
			baseUomId	:'#base_uom_id',
			typeId		:'#type_id',
			groupId		:'#group_id',
			weightUomId	:'#wt_uom_id',
			dimUomId	:'#dim_uom_id'	
	}
	
	var action;
	
	function init(){
		_initJQueryUI();
		_initFormValidation();
		_bindEventHandlers();
		action=$(fields.action).val();
	}
	
	function _initJQueryUI(){
		$('#tabs').tabs();
	}
	
	function _initFormValidation(){
		$.validator.setDefaults({
			ignore:""
		});
		
		$(form).validate({
			rules:{
				name:{
					required:true
				}
			},
			messages:{
				name:'Name/description is required!'
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
	
	function _bindEventHandlers(){
		$(form).submit(_noAutoSubmit);
		$(controls.cancel).click(_cancel);
		$(controls.save).click(_save);
	}
	
	function _noAutoSubmit(e){
		e.preventDefault();
		return false;
	}
	
	function _cancel(){
		var materialId=$(texts.material).text();
		window.location.assign('/material/select?materialId='+materialId);
	}
	
	function _save(){
		console.log('Saving material');
		var material=_getJSON();
		console.log(material);
		var create=false;
		if(action==1){
			create=true;
		}
		DAO.saveMaterial(material,create,function(status,m){
			if(status==DAO.STATUS.DONE){
				console.log('Material saved ok!');
				window.location.assign('/material/select?materialId='+m.id);
			}
			else if(status==DAO.STATUS.FAIL){
				console.log('Material save failed!');
			}
		});
	}
	
	function _getJSON(){
		var material={
				id			:$(texts.material).text(),
				name		:$(fields.name).val(),
				ean13		:$(fields.ean13).val(),
				legacyId	:$(fields.legacyId).val(),
				baseUom		:{
					id	:parseInt($(select.baseUomId).val())
				},
				materialType:{
					id	:(action==1 ? parseInt($(select.typeId).val()) : parseInt($fields.typeId).val())
				},
				materialGroup:{
					id	:parseInt($(select.groupId).val())
				},
				grossWeight	:$(fields.grossWeight).val(),
				netWeight	:$(fields.netWeight).val(),
				weightUom	:{
					id	:parseInt($(select.weightUomId).val())
				},
				length		:$(fields.length).val(),
				width		:$(fields.width).val(),
				height		:$(fields.height).val(),
				dimUom		:{
					id	:parseInt($(select.dimUomId).val())
				}
		}
		return material;
	}
	
	return{
		init	:init
	}
	
})();

var MaterialSearch=(function(){
	var initialized=false;
	
	var controls={
			search	:'#btn_material_search'
	}
	
	var fields={
			id			:'#p_id',
			name		:'#p_name',
			legacyId	:'#p_legacy_id'
	}
	
	var resultList='#search_result_list';
	
	function init(){
		console.log('TEST: Initialize MaterialSearch');
		_initJQueryUI();
		_bindEventHandlers();
		initialized=true;
	}
	
	function _initJQueryUI(){
		
	}
	
	function _bindEventHandlers(){
		console.log('Binding...');
		$(controls.search).click(_search);
	}
	
	function _search(){
		_clearResults();
		console.log('Do search...');
		var param=_getParam();
		console.log(param);
		
		DAO.searchMaterials(param,function(status,results){
			if(status==DAO.STATUS.DONE){
				//got results
				console.log(results);
				_showResults(results);
			}
			else if(status==DAO.STATUS.NA){
				//no results.
				console.log('No results');
			}
			else{
				//some other error
			}
 		});	
	}
	
	function _clearResults(){
		$(resultList).empty();
	}
	
	function _showResults(results){
		var tr;
		results.forEach(function(r){
			$(resultList).append(_createResultRow(r));
		});
		var rows=$(resultList).find('tr');
		Array.from(rows).forEach(function(r){
			console.log('row '+r);
			$(r).click(_selectResult);
		});
		
	}
	
	function _selectResult(event){
		var tr=$(event.target).parent()[0];
		$(resultList).find('tr').removeClass('selected-material');
		$(tr).addClass('selected-material');
	}
	
	function _createResultRow(result){
		var tr='<tr>'
			+'<td class="id">'+result.id+'</td>'
			+'<td class="name">'+result.name+'</td>'
			+'<td class="legacy-id">'+result.legacyId+'</td>'
			+'<td class="ean13">'+result.ean13+'</td>'
			+'<td class="type-name">'+result.typeName+'</td>'
			+'<td class="group-name">'+result.groupName+'</td>'
			+'</tr>';
		return tr;
	}
	
	function _getParam(){
		var param={
				id			: ($(fields.id).val().trim()==='' ? null : $(fields.id).val()),
				name		: ($(fields.name).val().trim()==='' ? null : $(fields.name).val()),
				legacyId	: ($(fields.legacyId).val().trim()==='' ? null : $(fields.legacyId).val())
		}
		return param;
	}
	
	function isInitialized(){
		return initialized;
	}
	
	function getSelectedMaterial(){
		var tr=$(resultList).find('tr.selected-material')[0];
		var td=$(tr).find('td.id')[0];
		var id=$(td).text();
		return id;
	}
	
	return{
		init				:init,
		isInitialized		:isInitialized,
		getSelectedMaterial	:getSelectedMaterial
	}
	
})(); 

var UserSearch=(function(){
	
	var initialized=false;
	
	var controls={
			search		:'#btn_user_search'
	}
	
	var fields={
			id			:'#p_id',
			firstName	:'#p_first_name',
			lastName	:'#p_last_name',
			email		:'#p_email'
	}
	
	var resultList='#search_result_list';
	
	function init(){
		_initJQueryUI();
		_bindEventHandlers();
		initialized=true;
	}
	
	function _initJQueryUI(){
		
	}
	
	function _bindEventHandlers(){
		$(controls.search).click(_search);
	}
	
	function _search(){
		_clearResults();
		console.log('Do search...');
		var param=_getParam();
		console.log(param);
		
		DAO.searchUsers(param,function(status,results){
			if(status==DAO.STATUS.DONE){
				//got results
				console.log(results);
				_showResults(results);
			}
			else if(status==DAO.STATUS.NA){
				//no results.
				console.log('No results');
			}
			else{
				//some other error
			}
 		});	
	}
	
	function _clearResults(){
		$(resultList).empty();
	}
	
	function _showResults(results){
		var tr;
		results.forEach(function(r){
			$(resultList).append(_createResultRow(r));
		});
		var rows=$(resultList).find('tr');
		Array.from(rows).forEach(function(r){
			console.log('row '+r);
			$(r).click(_selectResult);
		});
	}
	
	function _createResultRow(result){
		var tr='<tr>'
			+'<td class="id">'+result.id+'</td>'
			+'<td class="first-name">'+result.firstName+'</td>'
			+'<td class="last-name">'+result.lastName+'</td>'
			+'<td class="email">'+result.email+'</td>'
			+'</tr>';
		return tr;
	}
	
	function _selectResult(event){
		var tr=$(event.target).parent()[0];
		$(resultList).find('tr').removeClass('selected-user');
		$(tr).addClass('selected-user');
	}
	
	function _getParam(){
		var param={
				id			: ($(fields.id).val().trim()==='' ? null : $(fields.id).val()),
				firstName	: ($(fields.firstName).val().trim()==='' ? null : $(fields.firstName).val()),
				lastName	: ($(fields.lastName).val().trim()==='' ? null : $(fields.lastName).val()),
				email		: ($(fields.email).val().trim()==='' ? null : $(fields.email).val()),	
		}
		return param;
	}
	
	function isInitialized(){
		return initialized;
	}
	
	function getSelectedUser(){
		var tr=$(resultList).find('tr.selected-user')[0];
		var td=$(tr).find('td.id')[0];
		var id=$(td).text();
		return id;
	}
	
	return{
		init			:init,
		isInitialized	:isInitialized,
		getSelectedUser	:getSelectedUser
	}
	
})();

var BPSelect=(function(){
	
	var controls={
			display		:'#btn_display',
			edit		:'#btn_edit',
			create		:'#btn_create',
			cancel		:'#btn_cancel',
			search		:'#btn_open_search'
	}
	
	var fields={
			bpId			:'#bp_id',
			errorMsg		:'#error_msg'
	}
	
	var searchDialog='#bp_search_dialog';
	
	function init(){
		console.log('TEST: Initialize BPSelect');
		_initJQueryUI();
		_bindEventHandlers();
		MessageHandler.refreshMessageBox();
	}
	
	function _initJQueryUI(){
		$(controls.display).button();
		$(controls.edit).button();
		$(controls.create).button();
		$(controls.cancel).button();
		$(controls.search).button();
		$(searchDialog).dialog({
			autoOpen: false,
		    height: 460,
		    width: 660,
		    modal: true,
		    buttons:{
		    	Cancel		: function(){
		    		$(searchDialog).dialog('close');
		    	},
		    	"Select"	: _selectSearchResult
		    }
		});
	
	}
	
	function _bindEventHandlers(){
		$(controls.cancel).click(_cancel);
		$(controls.display).click(_display);
		$(controls.edit).click(_edit);
		$(controls.create).click(_create);
		$(controls.search).click(_search);
	}
	
	function _cancel(){
		window.location.assign('/home');
	}
	
	function _display(){
		console.log('BP Display');
	}
	
	function _edit(){
		console.log('BP Edit');
	}
	
	function _create(){
		console.log('BP Create');
		$(fields.errorMsg).text('');
		var bpId=$(fields.bpId).val();
		console.log('BP ID: '+bpId);
		_bpExists(bpId,function(exists){
			if(exists){
				//bp löytyy jo
			}
			else{
				//voidaan luoda
			}
		});
		
	}
	
	function _search(){
		console.log('BP Search');
	}
	
	function _selectSearchResult(){
		console.log('Select BP search result...');
		
	}
	
	function _bpExists(bpId,_callback){
		//jatka tästä
	}
	
	return{
		init			:init
	}
	
})();

var BPData=(function(){
	
	var action;
	
	var controls={
			cancel	:'#btn_cancel',
			save	:'#btn_save'
	}
	
	var form='#bp_form';
	
	var texts={
			bp	:'#bp_id'
	}
	
	function init(){
		console.log('TEST: BPData initialize...')
		_initJQueryUI();
		_bindEventHandlers();
		action=$(fields.action).val();
	}
	
	function _initJQueryUI(){
		$('#tabs').tabs();
	}
	
	function _bindEventHandlers(){
		$(form).submit(_noAutoSubmit);
		$(controls.cancel).click(_cancel);
		$(controls.save).click(_save);
	}
	
	function _noAutoSubmit(e){
		e.preventDefault();
		return false;
	}
	
	function _cancel(){
		var bp_id=$(texts.bp).text();
		console.log('cancel');
		
	}
	
	function _save(){
		console.log('save BP');
	}
	
	return{
		init	:init
	}
	
})();

