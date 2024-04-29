package com.dao;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entities.Student;

@Repository
@Transactional
public class StudentDAO {
    private final HibernateTemplate hibernateTemplate;

    public StudentDAO(HibernateTemplate hibernateTemplate) {       // constructor
        this.hibernateTemplate = hibernateTemplate;
    }

    public void addStudent(Student student) {
        hibernateTemplate.save(student);
    }

    public Student getStudentById(Long id) {
        return hibernateTemplate.get(Student.class, id);
    }

    public void deleteStudent(Long id) {
        Student student = hibernateTemplate.load(Student.class, id);
        hibernateTemplate.delete(student);
    }

    public void updateStudent(Student student) {
        hibernateTemplate.update(student);
    }
}
