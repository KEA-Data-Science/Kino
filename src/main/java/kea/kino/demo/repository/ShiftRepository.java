package kea.kino.demo.repository;

import kea.kino.demo.model.Shift;
import org.springframework.data.repository.CrudRepository;

public interface ShiftRepository extends CrudRepository<Shift, Integer> {
    Shift findByEmployee_Id(int employee_id);
}
