package Structural.composite;

import java.util.ArrayList;
import java.util.List;

// Composite
public class HeadDepartment implements Department {
    private final List<Department> childDepartments;
    private Integer id;
    private String name;

    public HeadDepartment(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.childDepartments = new ArrayList<>();
    }

    @Override
    public void printDepartmentName() {
        childDepartments.forEach(Department::printDepartmentName);
    }

    public void addDepartment(Department department) {
        childDepartments.add(department);
    }

    public void removeDepartment(Department department) {
        childDepartments.remove(department);
    }
}
