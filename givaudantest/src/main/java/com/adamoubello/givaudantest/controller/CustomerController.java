package com.adamoubello.givaudantest.controller;

import com.adamoubello.givaudantest.model.Customer;
import com.adamoubello.givaudantest.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/customer")
public class CustomerController {

    // final static Logger logger = Logger.getLogger(CustomerController.class);

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            headers = "Accept=application/json; charset=UTF-8;")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        customerService.save(customer);
        //logger.debug("Added:: " + customer);
        return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateCustomer(@RequestBody Customer customer) {
        Customer existingCustomer = customerService.getById(customer.getIdcustomer());
        if (existingCustomer == null) {
            //logger.debug("Customer with idcustomer " + customer.getNumcustomer() + " does not exists");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } else {
            customerService.save(customer);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{idcustomer}", method = RequestMethod.GET)
    public ResponseEntity<Customer> getCustomer(@PathVariable("idcustomer") Long idcustomer) {
        Customer customer = customerService.getById(idcustomer);
        if (customer == null) {
            //logger.debug("Customer with idcustomer " + idcustomer + " does not exists");
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }
        //logger.debug("Found Customer:: " + customer);
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAll();
        if (customers.isEmpty()) {
            //logger.debug("Customers does not exists");
            return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{idcustomer}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCustomer(@PathVariable("idcustomer") Long idcustomer) {
        Customer customer = customerService.getById(idcustomer);
        if (customer == null) {
            //logger.debug("Customer with id " + idcustomer + " does not exists");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } else {
            ModelMapper modelMapper = new ModelMapper();
            customerService.delete(idcustomer);
            //logger.debug("Customer with id " + idcustomer + " deleted");
            return new ResponseEntity<Void>(HttpStatus.GONE);
        }
    }


}
