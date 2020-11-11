package com.javamentor.view;

import com.javamentor.domain.Student;
import com.javamentor.service.StudentService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "/admin/students", layout = MainView.class)
@PageTitle("Студенты")
@Theme(Lumo.class)
public class StudentList extends HorizontalLayout {
    private final TextField filterText = new TextField();
    private final Grid<Student> grid = new Grid<>(Student.class);

    private final TextField firstName = new TextField("Имя");
    private final TextField lastName = new TextField("Фамилия");
    private final EmailField email = new EmailField("Email");
    private final TextField password = new TextField("Пароль");

    private final Button cancel = new Button("Отмена");
    private final Button save = new Button("Сохранить");
    private final Button search = new Button("Поиск");

    private final Binder<Student> binder = new Binder(Student.class);

    public StudentList(@Autowired StudentService service) {
        VerticalLayout vl = new VerticalLayout();
        filterText.setPlaceholder("Поиск...");
        filterText.setClearButtonVisible(true);
        grid.setVerticalScrollingEnabled(true);
        grid.setHeightByRows(true);
        grid.setColumns("firstName", "lastName", "email", "registrationDate");
        grid.getColumnByKey("email").setHeader("E-mail");
        grid.getColumnByKey("firstName").setHeader("Имя");
        grid.getColumnByKey("lastName").setHeader("Фамилия");
        grid.getColumnByKey("registrationDate").setHeader("Дата регистрации");
        HorizontalLayout hl = new HorizontalLayout();
        hl.add(filterText, search);
        vl.add(hl, grid);
        add(vl);
        VerticalLayout form = new VerticalLayout();
        form.setMaxWidth(20, Unit.PERCENTAGE);
        form.add(createTitle());
        form.add(createFormLayout());
        form.add(createButtonLayout());
        add(form);
        binder.forField(firstName).withValidator(
                name -> name.length() >= 3,
                "Минимальная длина имени 3 символа")
                .bind(Student::getFirstName, Student::setFirstName);
        binder.forField(lastName).withValidator(
                name -> name.length() >= 3,
                "Минимальная длина фамилии 3 символа")
                .bind(Student::getLastName, Student::setLastName);
        binder.forField(email).withValidator(new EmailValidator(
                "Неверный формат электронной почты"))
                .bind(Student::getEmail, Student::setEmail);
        binder.forField(password).withValidator(
                name -> name.length() >= 3,
                "Минимальная длина пароля 3 символа")
                .bind(Student::getPassword, Student::setPassword);
        clearForm();
        cancel.addClickListener(e -> clearForm());
        save.addClickListener(e -> {
            if ((!firstName.isInvalid())
                    && (!lastName.isInvalid())
                    && (!email.isInvalid())) {
                service.save(binder.getBean());
                clearForm();
                updateList(service);
            } else {
                Notification.show("Введите все данные");
            }

        });
        search.addClickListener(e -> updateList(service));
        updateList(service);
    }


    public void updateList(StudentService service) {
        grid.setItems(service.getAll(filterText.getValue()));
    }

    private Component createTitle() {
        return new H3("Новый студент");
    }

    private void clearForm() {
        binder.setBean(new Student());
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        email.setErrorMessage("Please enter a valid email address");
        formLayout.add(firstName, lastName, email, password);
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save);
        buttonLayout.add(cancel);
        return buttonLayout;
    }
}
