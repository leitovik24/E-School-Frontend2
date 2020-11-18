package com.javamentor.view;

import com.javamentor.domain.Student;
import com.javamentor.domain.StudentClass;
import com.javamentor.service.StudentClassService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "/admin/classes", layout = MainView.class)
@PageTitle("Классы")
@Theme(Lumo.class)
public class StudentClassList extends HorizontalLayout {

    /**
     * Метод вывода списка классов с учениками
     * @param studentClassService - Сервис, для получения данных с сервера
     */
    public StudentClassList(@Autowired StudentClassService studentClassService){
        List<StudentClass> studentClasses = studentClassService.getAllStudentClass();

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setAlignItems(Alignment.STRETCH);

        studentClasses.forEach(studentClass -> {
            List<Student> students = studentClassService.getAllStudentsOfStudentClassById(studentClass.getId());

            HorizontalLayout layout = new HorizontalLayout();
            layout.add(printTableStudents(students));

            System.out.println(studentClass.getClassLevel().getNumberClass().getClass());

            Details details = new Details(studentClass.getClassLevel().getNumberClass().ordinal() + 1 + " " + studentClass.getSymbolClass(),
                    layout);

            verticalLayout.add(details);
        });

        add(verticalLayout);
    }

    /**
     * Метод построения таблицы студентов
     * @param students - Список студентов
     * @return - компонент "таблица"
     */
    public Component printTableStudents(List<Student> students){
        Grid<Student> grid = new Grid<>(Student.class);
        grid.setItems(students);
        grid.setVerticalScrollingEnabled(true);
        grid.setHeightByRows(true);
        grid.setColumns("firstName", "lastName", "email", "registrationDate");
        grid.getColumnByKey("email").setHeader("E-mail");
        grid.getColumnByKey("firstName").setHeader("Имя");
        grid.getColumnByKey("lastName").setHeader("Фамилия");
        grid.getColumnByKey("registrationDate").setHeader("Дата регистрации");

        return grid;
    }

}
