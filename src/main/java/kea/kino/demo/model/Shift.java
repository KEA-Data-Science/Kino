package kea.kino.demo.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    public Date date;

    public Shift(){}

    @NotNull
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    public Employee employee;


    public int getId() {
        return id;
    }

    public void setId(int shift_id) {
        this.id = shift_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
