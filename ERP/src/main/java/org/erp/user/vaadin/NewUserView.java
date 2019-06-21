package org.erp.user.vaadin;

import org.erp.user.UserRepository;
import org.erp.user.UserService;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("new_user")
public class NewUserView extends VerticalLayout {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final UserService userService;
	//private final UserEditor editor;
	private TextField fldUserId=new TextField("User ID:");
	private Button btnCreate=new Button("Create");
	
	public NewUserView(UserService userService) {
		this.userService=userService;
		//this.editor=editor;
		this.fldUserId=new TextField();
		this.btnCreate=new Button("Create",VaadinIcon.PLUS.create());
		
		HorizontalLayout actions=new HorizontalLayout(fldUserId,btnCreate);
		//add(actions,editor);
		add(actions);
	}

}
