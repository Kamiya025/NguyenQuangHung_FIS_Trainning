package vn.fis.training.ordermanagement.service;

import vn.fis.training.ordermanagement.domain.Customer;
import vn.fis.training.ordermanagement.domain.Order;
import vn.fis.training.ordermanagement.domain.OrderItem;
import vn.fis.training.ordermanagement.domain.Product;

import java.util.List;

public interface ProductService {
    Product findById(Long productId);
    Product create(Product product);
    Product update(Product product);
    void remove(Long productId);
    List<Product> findAll();
}
