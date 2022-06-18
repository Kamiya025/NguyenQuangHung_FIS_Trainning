package vn.fis.training.ordermanagement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import vn.fis.training.ordermanagement.domain.*;
import vn.fis.training.ordermanagement.repository.ProductRepository;
import vn.fis.training.ordermanagement.service.CustomerService;
import vn.fis.training.ordermanagement.service.OrderService;
import vn.fis.training.ordermanagement.service.ProductService;

@SpringBootApplication
public class OrderManagementApplication {
    private static final Logger log = LoggerFactory.getLogger(OrderManagementApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(OrderManagementApplication.class, args);
    }


    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;

    @Bean
    CommandLineRunner commandLineRunner() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                log.info("Welcome to spring application. Pls write test method in here to run for testing only");
                //test Customer
				Customer customerNew = new Customer();
				customerNew.setName("Quang Hung");
				customerNew.setAddress("HD");
				customerNew.setMobile("0123456782");
				log.info("Add new customer:"+ customerService.createCustomer(customerNew));
				log.info("Total customer"+customerService.findAll().size());
				Customer customer = customerService.findByMobileNumber("0123456789");
				log.info("Customer find by mobile number: "+customer);
				customer.setAddress("Hai Duong");
				log.info("Update it with address ='Hai Duong'"+customerService.updateCustomer(customer));
//				log.info("And remove it: ");
//				customerService.deleteCustomerById(customer.getId());

                //test order
                Order order = new Order();
                order.setCustomer(customer);
                order.setStatus(OrderStatus.WAITING_APPROVAL);
                order = orderService.createOrder(order);

                OrderItem orderItem = new OrderItem();
                orderItem.setId(1L);

                Product product = new Product();
                product.setName("Test");
                product.setPrice(2000D);
                productService.create(product);
                orderItem.setProduct(product);
                orderItem.setQuantity(1);
                orderItem.setAmount(2000D);
                order = orderService.addOrderItem(order.getId(), orderItem);
                log.info("total item: " +order.getOrderItems().size());


                orderService.removeOrderItem(order.getOrderItems().get(0));
                log.info("total item after remove item: "+order.getOrderItems().size());




            }
        };
    }

    public void initProduct() {
//		productRepository.save(new Product() ...);
    }

}
