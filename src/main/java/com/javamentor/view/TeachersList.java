package com.javamentor.view;

import com.javamentor.domain.Teacher;
import com.javamentor.service.TeacherService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "/admin/teachers", layout = MainView.class)
@PageTitle("Учителя")
@Theme(Lumo.class)
public class TeachersList extends VerticalLayout {
    private final TeacherService service;
    private final Grid<Teacher> grid = new Grid<>(Teacher.class);

    @Autowired
    public TeachersList(TeacherService service) {
        this.service = service;
        grid.setVerticalScrollingEnabled(true);
        grid.setHeightByRows(true);
        grid.setColumns("email", "firstName", "lastName", "password", "registrationDate");
        grid.getColumnByKey("email").setHeader("e-Mail");
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
