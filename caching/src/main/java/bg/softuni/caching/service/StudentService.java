package bg.softuni.caching.service;

import bg.softuni.caching.model.StudentDto;

import java.util.List;

public interface StudentService {

    List<StudentDto> getAllStudents();

    StudentDto getStudentByName(String name);

    void refreshStudents();
}
