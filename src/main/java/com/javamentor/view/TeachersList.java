package com.javamentor.view;

import com.javamentor.domain.Teacher;
import com.javamentor.service.TeacherService;
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
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "/admin/teachers", layout = MainView.class)
@PageTitle("Учителя")
@Theme(Lumo.class)
public class TeachersList extends HorizontalLayout {
    private final TextField filterText = new TextField();
    private final Grid<Teacher> grid = new Grid<>(Teacher.class);

    private final TextField firstName = new TextField("Имя");
    private final TextField lastName = new TextField("Фамилия");
    private final EmailField email = new EmailField("Email");
    private final TextField password = new TextField("Пароль");

    private final Button cancel = new Button("Отмена");
    private final Button save = new Button("Сохранить");

    private final Binder<Teacher> binder = new Binder(Teacher.class);

    public TeachersList(@Autowired TeacherService service) {
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
        filterText.setValueChangeMode(ValueChangeMode.EAGER);
        filterText.addValueChangeListener(e -> updateList(service));
        vl.add(filterText, grid);
        add(vl);
        VerticalLayout form = new VerticalLayout();
        form.setMaxWidth(20, Unit.PERCENTAGE);
        form.add(createTitle());
        form.add(createFormLayout());
        form.add(createButtonLayout());
        add(form);
        binder.bindInstanceFields(this);
        clearForm();

        cancel.addClickListener(e -> clearForm());
        save.addClickListener(e -> {
            if ((!firstName.isEmpty()) && (!lastName.isEmpty()) && (!email.isEmpty())) {
                service.save(binder.getBean());
                clearForm();
                updateList(service);
            } else {
                Notification.show("Введите все данные");
            }

        });
        updateList(service);
    }

    private Component createTitle() {
        return new H3("Новый преподаватель");
    }

    private void clearForm() {
        binder.setBean(new Teacher());
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


    public void updateList(TeacherService service) {
        grid.setItems(service.getAll((filterText.getValue())));
    }
}
