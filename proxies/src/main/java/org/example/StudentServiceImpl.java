package org.example;

import java.util.List;

public class StudentServiceImpl implements StudentService {


    @Override
    @Cacheable("students")
    public List<StudentDto> findAllStudents() {
        System.out.println("Downloading students...");
        dummyWait();
        System.out.println("Students downloaded...");
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
