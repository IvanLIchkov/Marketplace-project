package bg.softuni.caching.repository;

import bg.softuni.caching.model.StudentDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {

    private Logger logger = LoggerFactory.getLogger(StudentRepository.class);

    public List<StudentDto> findAllStudents(){
        logger.info("Downloading students...");
        dummyWait();
        logger.info("Students downloaded...");
        return List.of(new StudentDto().setName("Pesho").setAge(23), new StudentDto().setName("Ana").setAge(22));
    }

    private void dummyWait(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
