package vn.fis.training.ordermanagement.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="tbl_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="price")
    private Double price;

}
