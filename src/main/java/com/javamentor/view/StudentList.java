package com.javamentor.view;

import com.javamentor.domain.Student;
import com.javamentor.service.StudentService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "/admin/student", layout = MainView.class)
@PageTitle("Студенты")
@Theme(Lumo.class)
public class StudentList extends Div {
    private final StudentService service;
    private final Grid<Student> grid = new Grid<>(Student.class);

    @Autowired
    public StudentList(StudentService service) {
        this.service = service;
        grid.setVerticalScrollingEnabled(true);
        grid.setHeightByRows(true);
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
