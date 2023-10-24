package bg.softuni.caching.service;

import bg.softuni.caching.model.StudentDto;
import bg.softuni.caching.repository.StudentRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Cacheable("students")
    @Override
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAllStudents();
    }

    @Override
    @Cacheable("studentByName")
    public StudentDto getStudentByName(String name) {
        return studentRepository.findAllStudents()
                .stream()
                .filter(s -> s.getName().equals(name))
                .findAny()
                .orElse(new StudentDto().setName("No Such student be!!"));
    }

    @CacheEvict(cacheNames = "students", allEntries = true)
    @Override
    public void refreshStudents() {

    }
}
