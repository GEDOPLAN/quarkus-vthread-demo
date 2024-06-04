package de.gedoplan.showcase.vthread;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import lombok.extern.java.Log;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.math.BigDecimal;

/**
 * Dieser RestClient simuliert einen Aufruf, der mit einer gewissen Verzögerung ein Ergebnis zurückliefert.
 */
@Log
@RequestScoped
public class CustomerRestClient {
  @Inject
  @ConfigProperty(name = "app.customer.detail.delay", defaultValue = "200")
  long delay;

  public void getDetails(Customer customer) {
    var random = Math.random() * 1000;
    customer.setSales(BigDecimal.valueOf(random));
    log.info("Starte " + customer);
    try {
      Thread.sleep(delay);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    log.info("Beende " + customer);
  }
}
