package com.dao;
import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entities.Department;

@Repository
@Transactional
public class DepartmentDAO {
    private final HibernateTemplate hibernateTemplate;

    public DepartmentDAO(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
  
    public List<Department> getAllDepartments() {
        return hibernateTemplate.loadAll(Department.class);
    }


    public void addDepartment(Department department) {
        hibernateTemplate.save(department);
    }

    public Department getDepartmentById(Long id) {
        return hibernateTemplate.get(Department.class, id);
    }

    public void deleteDepartment(Long id) {
        Department department = hibernateTemplate.load(Department.class, id);
        hibernateTemplate.delete(department);
    }

    public void updateDepartment(Department department) {
        hibernateTemplate.update(department);
    }
}
