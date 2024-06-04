package de.gedoplan.showcase.vthread;

import io.quarkus.virtual.threads.VirtualThreads;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.concurrent.ExecutorService;

@RequestScoped
public class CustomerDetailService {
  @Inject
  CustomerRestClient customerRestClient;

  @Inject
  @VirtualThreads
  ExecutorService executorService;

  public void getDetails(Customer customer) {
    customerRestClient.getDetails(customer);
  }

  public void getDetailsVirtual(Customer customer) {
    executorService.execute(() -> customerRestClient.getDetails(customer));
  }
}
