package com.javamentor.view.login;

import com.javamentor.service.LoginService;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Route("/login")
public class LoginView extends VerticalLayout {

    @Autowired
    private LoginService loginService;

    private LoginForm loginForm = new LoginForm();

    private Boolean rememberMe = false;

    private LoginI18n loginI18n = createRussianI18n();

    public LoginView() {

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        Checkbox checkbox = new Checkbox();
        checkbox.setLabel("Запомнить меня");

        loginForm.setI18n(loginI18n);

        loginForm.addLoginListener(event -> {
            ResponseEntity responseEntity = loginService.sendLogin(event.getUsername(), event.getPassword(), rememberMe);
            if (responseEntity.getStatusCode() != HttpStatus.BAD_REQUEST) {
                Notification.show("Вы аутентифицированны");
            } else {
                loginI18n.getErrorMessage().setMessage(responseEntity.getBody().toString());
                loginForm.setI18n(loginI18n);
                loginForm.setError(true);
            }
        });

        loginForm.addForgotPasswordListener(event -> {
            Notification.show("Страница в разработке");
        });

        checkbox.addValueChangeListener(event -> {
            if (event.getValue()) {
                rememberMe = true;
            } else {
                rememberMe = false;
            }
        });

        add(new H1("Онлайн Школа"), loginForm, checkbox);
    }

    private LoginI18n createRussianI18n() {
        final LoginI18n i18n = LoginI18n.createDefault();
        i18n.setHeader(new LoginI18n.Header());
        i18n.getHeader().setTitle("Имя приложения");
        i18n.getHeader().setDescription("Описание приложения");
        i18n.getForm().setUsername("Пользователь");
        i18n.getForm().setTitle("Войдите в свой аккаунт");
        i18n.getForm().setSubmit("Авторизоваться");
        i18n.getForm().setPassword("Пароль");
        i18n.getForm().setForgotPassword("Я забыл свой пароль");
        i18n.getErrorMessage().setTitle("Неверное имя пользователя / пароль");
        return i18n;
    }

}
