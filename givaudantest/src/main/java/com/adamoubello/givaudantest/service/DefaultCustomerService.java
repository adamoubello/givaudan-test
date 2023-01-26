package com.adamoubello.givaudantest.service;

import com.adamoubello.givaudantest.model.Customer;
import com.adamoubello.givaudantest.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class DefaultCustomerService implements CustomerService {

    private final CustomerRepository customerRepository;

    public DefaultCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer entity) {
        return customerRepository.save(entity);
    }

    @Override
    public Customer getById(Serializable id){ return customerRepository.getOne((Long) id); }

    @Override
    public List<Customer> getAll(){
        return customerRepository.findAll();
    }

    @Override
    public void delete(Serializable id) {
        customerRepository.delete((Long) id);
    }
}
