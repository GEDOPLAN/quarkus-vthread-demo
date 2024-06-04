package de.gedoplan.showcase.vthread;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Customer {
  private Integer number;
  private String name;
  private BigDecimal sales;

  public Customer(Integer number) {
    this.number = number;
    this.name = "Customer" + number;
  }
}
