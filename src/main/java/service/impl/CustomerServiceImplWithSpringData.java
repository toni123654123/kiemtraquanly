package service.impl;


import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import repository.CustomerRepository;
import service.CustomerService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CustomerServiceImplWithSpringData implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

//    @Override
//    public List<Customer> findAll() {
//        return streamAll().collect(Collectors.toList());
//    }

    @Override
    public Page<Customer> findAll(Pageable pageInfo) {
        return customerRepository.findAll(pageInfo);
    }

//    @Override
//    public List<Customer> search(String keyword) {
//        Iterable<Customer> searchResult = customerRepository
//                .findAllByNameContainsOrAuthorContains(keyword, keyword);
//        return streamAll(searchResult).collect(Collectors.toList());
//    }

    @Override
    public Page<Customer> search(String keyword, Pageable pageInfo) {
        return customerRepository.findAllByNameContainsOrAuthorContains(keyword, keyword, pageInfo);
    }

    @Override
    public Customer findOne(Long id) {
        Customer customer = customerRepository.findOne(id);
        System.out.println(customer);
        if (customer == null) throw new RuntimeException("Customer not found");
        return customer;
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

//    @Override
//    public List<Customer> save(List<Customer> customers) {
//        Iterable<Customer> updatedCustomers = customerRepository.save(customers);
//        return streamAll(updatedCustomers).collect(Collectors.toList());
//    }
//
//    @Override
//    public boolean exists(Long id) {
//        return customerRepository.exists(id);
//    }
//
//    @Override
//    public List<Customer> findAll(List<Long> ids) {
//        Iterable<Customer> customers = customerRepository.findAll(ids);
//        return streamAll(customers).collect(Collectors.toList());
//    }
//
//    @Override
//    public long count() {
//        return customerRepository.count();
//    }
//
//    @Override
//    public void delete(Long id) {
//        customerRepository.delete(id);
//    }

    @Override
    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }
//
//    @Override
//    public void delete(List<Customer> customers) {
//        customerRepository.delete(customers);
//    }

//    @Override
//    public void deleteAll() {
//        customerRepository.deleteAll();
//    }

    private Stream<Customer> streamAll() {
        return StreamSupport.stream(customerRepository.findAll().spliterator(), false);
    }

    private Stream<Customer> streamAll(Iterable<Customer> customers) {
        return StreamSupport.stream(customers.spliterator(), false);
    }
}