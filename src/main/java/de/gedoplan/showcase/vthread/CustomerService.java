package de.gedoplan.showcase.vthread;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import lombok.extern.java.Log;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

@Getter
@Log
@ApplicationScoped
public class CustomerService {
  private List<Customer> customers;

  @Inject
  @ConfigProperty(name = "app.number.of.customers", defaultValue = "4")
  Integer numberOfCustomers;

  @PostConstruct
  void init() {
    customers = IntStream.range(1, numberOfCustomers + 1).mapToObj(Customer::new).sorted(Comparator.comparing(Customer::getNumber)).toList();
  }
}
