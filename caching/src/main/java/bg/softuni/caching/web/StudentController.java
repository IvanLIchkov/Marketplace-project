package bg.softuni.caching.web;

import bg.softuni.caching.model.StudentDto;
import bg.softuni.caching.service.StudentService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }


    @GetMapping("/students/all")
    public ResponseEntity<List<StudentDto>> findAll() {

        List<StudentDto> studentDtos = service.getAllStudents();

        studentDtos.forEach(System.out::println);

        return ResponseEntity.ok(service.getAllStudents());
    }

    @GetMapping("/students/find")
    public ResponseEntity<StudentDto> findStudentByName(@RequestParam("q") String q){
        StudentDto studentByName = service.getStudentByName(q);
        return ResponseEntity.ok(studentByName);
    }
    @GetMapping("/students/all/evict")
    public ResponseEntity<List<StudentDto>> findAllAndEvict() {

        List<StudentDto> studentDtos = service.getAllStudents();
        service.refreshStudents();

        return ResponseEntity.ok(service.getAllStudents());
    }
}
