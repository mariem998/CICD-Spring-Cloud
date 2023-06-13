package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaireService {

    @Autowired
    private SalaireRepository salaireRepository;

    public List<Salaire> getAllSalaire() {
        return salaireRepository.findAll();
    }

    public Salaire getSalaireById(Long id) {
        return salaireRepository.findById(id).get();
    }

    public Salaire createSalaire(Salaire customer) {
        return salaireRepository.save(customer);
    }

    public Salaire updateSalaire(Long id, Salaire customer) {
        Salaire existingCustomer = salaireRepository.findById(id).get();
        existingCustomer.setJob(customer.getJob());
        existingCustomer.setSalary(customer.getSalary());
        return salaireRepository.save(existingCustomer);
    }

    public void deleteSalaire(Long id) {
        Salaire customer = salaireRepository.findById(id).get();
        salaireRepository.delete(customer);
    }
}
