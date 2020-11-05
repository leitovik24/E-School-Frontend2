package com.javamentor.view;

import com.javamentor.domain.Teacher;
import com.javamentor.service.TeacherService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Route(value = "/admin/teacher")
@Theme(Lumo.class)
public class TeachersList extends VerticalLayout {
    private final TeacherService service = new TeacherService();
    private final Grid<Teacher> grid = new Grid<>(Teacher.class);

    public TeachersList() {
        grid.setColumns("email", "firstName", "lastName", "password", "registrationDate");
        add(grid);
        setSizeFull();
        updateList();
    }

    public void updateList() {
        grid.setItems(service.getAll());
    }
}
