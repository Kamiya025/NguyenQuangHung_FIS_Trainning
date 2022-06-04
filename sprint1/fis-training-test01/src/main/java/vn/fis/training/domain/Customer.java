package vn.fis.training.domain;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Customer implements Comparable<Customer> {



    /**
     * ID cua Customer la duy nhat trong toan bo he thong
     * Su dung @java.util.UUID de tao id gan cho tung customer
     */
    private String id;

    /**
     * Constraints:
     * 1. Khong duoc trong (NOT NULL OR BLANK)
     * 2. Do dai tu tu 5 den 50 ky tu, chi bao gom ky tu a->z, A->Z va khong trang (Blank).
     * 3. Truoc khi cap nhat vao he thong truong ten phai duoc chuan hoa.
     * Qui tac chuan hoa:
     * . Bo cac ky tu trang o dau va cuoi
     * . Upper case chu cai dau tien truoc cac tu
     * VD: nGuyen van    BinH      -> Nguyen Van Binh
     **/
    private String name;
    /**
     * Constraints:
     * 1. Khong duoc trong
     * 2. Tinh toan de khong cho phep nhap Customer nho hon 10 tuoi so voi thoi diem hien tai
     **/
    private LocalDate birthDay;
    /**
     * Constraints:
     * 1. Do dai 10 ky tu bat dau bang ky tu 0, chi bao gom cac so tu 0->9
     * 2. So dien thoai la duy nhat trong toan bo he thong. Duoc su dung de kiem tra duplicate khach hang
     * 3. Chuan hoa lai truong so dien thoai truoc khi cap nhat vao he thong. Bo cac ky tu trang o dau, giua va cuoi
     * Vidu: 0921 000 008 --> 0921000008
     */
    private String mobile;

    /**
     * Constraints:
     * NOT NULL
     */

    private CustomerStatus status;

    /**
     * Thoi gian tao doi tuong. mac dinh la thoi diem hien tai
     */

    private LocalDateTime createDateTime;

    // TODO: Implement Getters, Setters, Constructors, Equals


    public Customer(String id, String name, LocalDate birthDay, String mobile, CustomerStatus status, LocalDateTime createDateTime) {
        this.id = id;
        this.name = name;
        this.birthDay = birthDay;
        this.mobile = mobile;
        this.status = status;
        this.createDateTime = createDateTime;
    }
    public Customer(String name, LocalDate birthDay, String mobile, CustomerStatus status) {
        this(UUID.randomUUID().toString(),name,birthDay,mobile,status,LocalDateTime.now());

    }

        public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String[] arr = name.split(" ");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            sb.append(Character.toUpperCase(arr[i].charAt(0))).append(arr[i].substring(1)).append(" ");
        }
        name = sb.toString().trim();
        this.name = name;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public CustomerStatus getStatus() {
        return status;
    }

    public void setStatus(CustomerStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(name, customer.name) && Objects.equals(birthDay, customer.birthDay) && Objects.equals(mobile, customer.mobile) && status == customer.status && Objects.equals(createDateTime, customer.createDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthDay, mobile, status, createDateTime);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", birthDay=" + birthDay +
                ", mobile='" + mobile + '\'' +
                ", status=" + status +
                ", createDateTime=" + createDateTime +
                '}';
    }


    @Override
    public int compareTo(@NotNull Customer that) {
        return this.compareTo(that);
    }
}
