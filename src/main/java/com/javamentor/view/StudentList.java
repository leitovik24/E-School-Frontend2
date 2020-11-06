package com.javamentor.view;

import com.javamentor.domain.Student;
import com.javamentor.service.StudentService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Route(value = "/admin/student")
@Theme(Lumo.class)
public class StudentList extends VerticalLayout {
    private final StudentService service = new StudentService();
    private final Grid<Student> grid = new Grid<>(Student.class);

    public StudentList() {
        grid.setColumns("email", "firstName", "lastName", "password", "registrationDate");
        grid.getColumnByKey("email").setHeader("E-mail");
        grid.getColumnByKey("firstName").setHeader("Имя");
        grid.getColumnByKey("lastName").setHeader("Фамилия");
        grid.getColumnByKey("password").setHeader("Пароль");
        grid.getColumnByKey("registrationDate").setHeader("Дата регистрации");
        add(grid);
        setSizeFull();
        updateList();
    }

    public void updateList() {
        grid.setItems(service.getAll());
    }
}
