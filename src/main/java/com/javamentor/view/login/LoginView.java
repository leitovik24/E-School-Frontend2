package com.javamentor.view.login;

import com.javamentor.service.LoginService;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.AbstractLogin;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("login")
public class LoginView extends VerticalLayout {

    @Autowired
    private LoginService loginService;

    private LoginForm loginForm = new LoginForm();

    public LoginView() {

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        add(new H1("E-School"), loginForm);

        loginForm.addLoginListener(new ComponentEventListener<AbstractLogin.LoginEvent>() {
            @Override
            public void onComponentEvent(AbstractLogin.LoginEvent loginEvent) {
                loginService.sendLogin(loginEvent.getUsername(), loginEvent.getPassword(), true);
            }
        });
    }
}
