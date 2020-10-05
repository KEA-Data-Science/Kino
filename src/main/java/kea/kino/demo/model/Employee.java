package kea.kino.demo.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    public String name;

    public Employee(int emp_id, String name){
        this.id = emp_id;
        this.name = name;
    }
    public Employee(){}

    //@OneToMany(mappedBy = "shift")
    //public Set<Shift> shifts;



    public int getId() {
        return id;
    }

    public void setId(int emp_id) {
        this.id = emp_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Set<Shift> getShifts() {
//        return shifts;
//    }
//
//    public void setShifts(Set<Shift> shifts) {
//        this.shifts = shifts;
//    }

}
