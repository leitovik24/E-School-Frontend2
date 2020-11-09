package com.javamentor.view;

import com.javamentor.domain.Student;
import com.javamentor.service.StudentService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "/admin/student", layout = MainView.class)
@PageTitle("Студенты")
@Theme(Lumo.class)
public class StudentList extends VerticalLayout {
    private final TextField filterText = new TextField();
    private final Grid<Student> grid = new Grid<>(Student.class);

    public StudentList(@Autowired StudentService service) {
        filterText.setPlaceholder("Поиск...");
        filterText.setClearButtonVisible(true);
        grid.setVerticalScrollingEnabled(true);
        grid.setHeightByRows(true);
        grid.setColumns("firstName", "lastName", "email", "registrationDate");
        grid.getColumnByKey("email").setHeader("E-mail");
        grid.getColumnByKey("firstName").setHeader("Имя");
        grid.getColumnByKey("lastName").setHeader("Фамилия");
        grid.getColumnByKey("registrationDate").setHeader("Дата регистрации");
        add(filterText, grid);
        filterText.setValueChangeMode(ValueChangeMode.EAGER);
        filterText.addValueChangeListener(e -> updateList(service));
        add(filterText, grid);
        updateList(service);
    }

    public void updateList(StudentService service) {
        grid.setItems(service.getAll(filterText.getValue()));
    }
}
