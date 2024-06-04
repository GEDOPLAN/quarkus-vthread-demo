package de.gedoplan.showcase.vthread;

import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

import java.util.List;

@Log
@Path("customer")
public class CustomerResource {
  @Inject
  CustomerService customerService;

  @Inject
  CustomerDetailService customerDetailService;

  @GET
  public List<Customer> getAllCustomers() {
    log.info(Thread.currentThread().getName());
    return getCustomers(false);
  }

  @GET
  @Path("virtual")
  @RunOnVirtualThread
  @SneakyThrows
  public List<Customer> getAllCustomersVirtual() {
    log.info(Thread.currentThread().getName());
    return getCustomers(true);
  }

  private List<Customer> getCustomers(boolean virtual) {
    var customers = customerService.getCustomers();
    if (virtual) {
      customers.forEach(customerDetailService::getDetailsVirtual);
    } else {
      customers.forEach(customerDetailService::getDetails);
    }
    return customers;
  }
}
