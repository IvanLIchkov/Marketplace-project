package org.example;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        StudentService service = createStudentService();

        service.findAllStudents().forEach(System.out::println);


        System.out.printf(".....................");


        service.findAllStudents().forEach(System.out::println);
    }


    private static StudentService createStudentService(){
      return (StudentService)  Proxy.newProxyInstance(
                Main.class.getClassLoader(),
                new Class[]{StudentService.class},
                new CacheableInvocationHandler(new StudentServiceImpl())
        );
    }
}