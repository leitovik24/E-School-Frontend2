package com.javamentor.view;

import com.javamentor.domain.Student;
import com.javamentor.domain.StudentClass;
import com.javamentor.service.StudentClassService;
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
        List<StudentClass> studentClass = studentClassService.getAllStudentClass();

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setAlignItems(Alignment.STRETCH);

        for(StudentClass sc : studentClass){
            HorizontalLayout layout = new HorizontalLayout();
            List<Student> students = studentClassService.getAllStudentsOfStudentClassById(sc.getId());

            Grid<Student> grid = new Grid<>(Student.class);
            grid.setItems(students);
            grid.setVerticalScrollingEnabled(true);
            grid.setHeightByRows(true);
            grid.setColumns("firstName", "lastName", "email", "registrationDate");
            grid.getColumnByKey("email").setHeader("E-mail");
            grid.getColumnByKey("firstName").setHeader("Имя");
            grid.getColumnByKey("lastName").setHeader("Фамилия");
            grid.getColumnByKey("registrationDate").setHeader("Дата регистрации");
            layout.add(grid);

            Details details = new Details(parseStringClassLevelToNumber(sc.getClassLevel().getNumberClass()) + " " + sc.getSymbolClass(),
                     layout);
            verticalLayout.add(details);
        }

        add(verticalLayout);
    }

    /**
     * Метод вывода списка классов с учениками
     * @param classLevel - Номер класса, пришедший с сервера
     * @return - Номер класса, в виде числа
     */
    public int parseStringClassLevelToNumber(String classLevel){
        int result = 0;

        switch (classLevel){
            case ("ПЕРВЫЙ") :
                result = 1;
                break;
            case ("ВТОРОЙ") :
                result = 2;
                break;
            case ("ТРЕТИЙ") :
                result = 3;
                break;
            case ("ЧЕТВЕРТЫЙ") :
                result = 4;
                break;
            case ("ПЯТЫЙ") :
                result = 5;
                break;
            case ("ШЕСТОЙ") :
                result = 6;
                break;
            case ("СЕДЬМОЙ") :
                result = 7;
                break;
            case ("ВОСЬМОЙ") :
                result = 8;
                break;
            case ("ДЕВЯТЫЙ") :
                result = 9;
                break;
            case ("ДЕСЯТЫЙ") :
                result = 10;
                break;
            case ("ОДИНАДЦАТЫЙ") :
                result = 11;
                break;
            case ("ДВЕНАДЦАТЫЙ") :
                result = 12;
                break;
        }
        return result;
    }

}
