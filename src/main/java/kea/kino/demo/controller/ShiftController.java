package kea.kino.demo.controller;

import kea.kino.demo.model.Employee;
import kea.kino.demo.model.Shift;
import kea.kino.demo.repository.EmployeeRepository;
import kea.kino.demo.repository.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.Optional;

@Controller
public class ShiftController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ShiftRepository shiftRepository;

    @GetMapping("/dutySchedule")
    public String showSchedule(Model model){

        model.addAttribute("shifts", shiftRepository.findAll());

        return "duty-schedule";
    }


    @GetMapping("/create-duty")
    public String createNewShift(Model model){

        model.addAttribute("employees", employeeRepository.findAll());

        return "create-duty";
    }

    @PostMapping("/save-duty")
    public String saveNewShift(Model model,
                               @RequestParam int id,
                               @RequestParam String date,
                               @RequestParam int employeeID){

        Shift shift = new Shift();

        Optional<Shift> optShift = shiftRepository.findById(id);
        if(optShift.isPresent()){
            shift = optShift.get();
        }


        shift.setDate(Date.valueOf(date));

        Optional<Employee> optEmp = employeeRepository.findById(employeeID);

        if(optEmp.isPresent()){
            shift.setEmployee(optEmp.get());
        }


        shiftRepository.save(shift);

        return "duty-schedule";
    }


    @PostMapping("edit-duty")
    public String updateShift(Model model,
                              @RequestParam int id){

        model.addAttribute("employees", employeeRepository.findAll());
        model.addAttribute("shift", shiftRepository.findById(id).get());

        return "edit-duty";
    }




}
