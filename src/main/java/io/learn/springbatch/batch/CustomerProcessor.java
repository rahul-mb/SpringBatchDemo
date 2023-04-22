package io.learn.springbatch.batch;

import io.learn.springbatch.entity.Customer;
import org.springframework.batch.item.ItemProcessor;

public class CustomerProcessor implements ItemProcessor<Customer, Customer> {
    /**
     * @param customer 
     * @return Customer object
     * @throws Exception
     */
    @Override
    public Customer process(Customer customer) throws Exception {
        return customer;
    }
}
