package com;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salaire")
public class SalaireController {


    @Autowired
    private SalaireService salaireService;

    @GetMapping
    public List<Salaire> getAllCustomers() {
        return salaireService.getAllSalaire();
    }

    @GetMapping("/{id}")
    public Salaire getCustomerById(@PathVariable Long id) {
        return salaireService.getSalaireById(id);
    }

    @PostMapping
    public Salaire createCustomer(@RequestBody Salaire salaire) {
        return salaireService.createSalaire(salaire);
    }

    @PutMapping("/{id}")
    public Salaire updateCustomer(@PathVariable Long id, @RequestBody Salaire salaire) {
        return salaireService.updateSalaire(id, salaire);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        salaireService.deleteSalaire(id);
    }
}
