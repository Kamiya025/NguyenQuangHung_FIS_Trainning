package com.hung.testExcel;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Transaction {

    private LocalDate executionDate;
    private LocalDate validDate;
    private double debit;
    private double crebit;
    private double balance;
    private String beneficiaryAccount;
    private String beneficiaryName;
    private String description;
    private String code;

}
