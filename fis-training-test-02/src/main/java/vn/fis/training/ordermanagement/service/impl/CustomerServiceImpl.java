package vn.fis.training.ordermanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fis.training.ordermanagement.domain.Customer;
import vn.fis.training.ordermanagement.exception.CustomerException;
import vn.fis.training.ordermanagement.repository.CustomerRepository;
import vn.fis.training.ordermanagement.service.CustomerService;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer findById(Long customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        return optionalCustomer.get();
    }

    @Override
    public Customer createCustomer(Customer customer) {
        if(findByMobileNumber(customer.getMobile())!=null)
            throw new CustomerException("Number Mobile exited");
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer)
    {
        Customer customerCheck = findByMobileNumber(customer.getMobile());
        if(customerCheck!=null){
            if(!customerCheck.getId().equals(customer.getId()))
            throw new CustomerException("Number Mobile exited");
        }
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomerById(Long customerId) {
        customerRepository.delete(findById(customerId));
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findByMobileNumber(String mobileNumber) {
        return customerRepository.findBymobile(mobileNumber);
    }
}
