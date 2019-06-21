package org.erp.user.vaadin;

import org.erp.user.User;
import org.erp.user.UserDTO;
import org.erp.user.UserRepository;
import org.erp.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@SpringComponent
@UIScope
public class UserEditor extends VerticalLayout implements KeyNotifier {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final UserService service;
	private UserDTO user;
	
	private TextField fldFirstName=new TextField("First name");
	private TextField fldLastName=new TextField("Last name");
	private TextField fldEmail=new TextField("Email");
	
	private Button btnSave=new Button("Save",VaadinIcon.CHECK.create());
	private Button btnCancel=new Button("Cancel");
	
	private HorizontalLayout actions=new HorizontalLayout(btnSave,btnCancel);
	
	private Binder<UserDTO> binder=new Binder(UserDTO.class);
	private ChangeHandler changeHandler;
	
	@Autowired
	public UserEditor(UserService service) {
		this.service=service;
		this.add(fldFirstName,fldLastName,fldEmail,actions);
		binder.bindInstanceFields(this);
		this.setSpacing(true);
		btnSave.getElement().getThemeList().add("primary");
		this.addKeyPressListener(Key.ENTER,e->save());
		btnSave.addClickListener(e->save());
		this.setVisible(false);
	}
	
	public void setChangeHandler(ChangeHandler changeHandler) {
		this.changeHandler=changeHandler;
	}
	
	void save() {
		service.createNewUser(user);
		changeHandler.onChange();
	}
}
