package logistics.service;

import logistics.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudent();

    Integer saveAllStudent(List<Student> studentList);

    Student getStuById(Integer id);

    Integer deleteStuById(Integer id);
    Integer updateStuById(Student student);
}
