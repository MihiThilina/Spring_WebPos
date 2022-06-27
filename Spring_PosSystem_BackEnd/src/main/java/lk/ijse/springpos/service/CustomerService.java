package lk.ijse.springpos.service;

import lk.ijse.springpos.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    void saveCustomer(CustomerDto entity);
    void deleteCustomer(String id);
    void updateCustomer(CustomerDto entity);
    CustomerDto searchCustomer(String id);
    List<CustomerDto> getAllCustomers();

}
