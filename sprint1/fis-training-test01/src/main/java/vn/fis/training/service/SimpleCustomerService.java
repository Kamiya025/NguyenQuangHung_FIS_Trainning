package vn.fis.training.service;

import vn.fis.training.domain.Customer;
import vn.fis.training.domain.CustomerStatus;
import vn.fis.training.exception.*;
import vn.fis.training.store.InMemoryCustomerStore;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleCustomerService implements CustomerService {

    private InMemoryCustomerStore customerStore;

    @Override
    public Customer findById(String id) {
        //TODO: Implement method tho dung dac ta cua CustomerService interface
        Customer customer = customerStore.findAll()
                .stream()
                .filter(c -> c.getId().equals(id))
                .findFirst().get();
        if (!customer.equals(null)) throw new CustomerNotFoundException(AppConstant.NOT_FOUND_CUSTOMER);

        return customer;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        //TODO: Implement method tho dung dac ta cua CustomerService interface
        validate(customer);
        if(findById(customer.getId()) != null) throw new ApplicationException("CUSTOMER EXIST") {
            @Override
            public String getErrorCode() {
                return getMessage();
            }
        };
        Customer c = customerStore.insertOrUpdate(customer);
        return c;
    }

    private void validate(Customer customer) {
        //validate
        Pattern pattern = Pattern.compile("[A-Za-z ]{0,50}", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(customer.getName());
        if (!matcher.find()) throw new InvalidCustomerException(customer, "Input name invalid");

        pattern = Pattern.compile("0[0-9]{9}", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(customer.getMobile());
        if (!matcher.find()) throw new InvalidCustomerException(customer, "Input mobie invalid");

        if (Period.between(customer.getBirthDay(), LocalDate.now()).getYears() < 10)
            throw new InvalidCustomerException(customer, "input BirthDay invalid");

        if(!(customer.getStatus() instanceof CustomerStatus))
            throw new InvalidCustomerStatusException(customer, "input status invalid");




    }

    @Override
    public Customer updateCustomer(Customer customer) {
        //TODO: Implement method tho dung dac ta cua CustomerService interface
        validate(customer);
        if(findById(customer.getId()) == null) throw new DuplicateCustomerException(customer,"CUSTOMER NOT EXIST");
        Customer c = customerStore.insertOrUpdate(customer);
        return c;
    }

    @Override
    public void deleteCustomerById(String id) {
        //TODO: Implement method tho dung dac ta cua CustomerService interface
        if(findById(id) == null) throw new CustomerNotFoundException("CUSTOMER NOT EXIST");
        customerStore.deleteById(id);
    }

    @Override
    public List<Customer> findAllOrderByNameAsc() {
        //TODO: Implement method tho dung dac ta cua CustomerService interface
        List<Customer> customers = customerStore.findAll().stream().sorted().toList();
        return customers;
    }

    @Override
    public List<Customer> findAllOrderByNameLimit(int limit) {
        //TODO: Implement method tho dung dac ta cua CustomerService interface
        List<Customer> customers = customerStore.findAll().stream().limit(limit).toList();
        return customers;
    }

    @Override
    public List<Customer> findAllCustomerByNameLikeOrderByNameAsc(String custName, String limit) {
        //TODO: Implement method tho dung dac ta cua CustomerService interface
        List<Customer> customers = customerStore.findAll().stream()
                .filter(c -> c.getName().contains(custName))
                .sorted()
                .limit(Integer.valueOf(limit)).toList();
        return customers;
    }

    @Override
    public List<SummaryCustomerByAgeDTO> summaryCustomerByAgeOrderByAgeDesc() {
        //TODO: Implement method tho dung dac ta cua CustomerService interface
        List<SummaryCustomerByAgeDTO> summary = new ArrayList<>();
        for (Customer customer : customerStore.findAll()) {
            int yearOld = Period.between(customer.getBirthDay(), LocalDate.now()).getYears();
            for (int i = 0; i <= summary.size(); i++) {
                if (summary.get(i).getAge() == yearOld) {
                    summary.get(i).setCount(summary.get(i).getCount() + 1);
                    break;
                }
                if (i == summary.size()) summary.add(new SummaryCustomerByAgeDTO(yearOld, 1));
            }
        }
        return summary;
    }

}
