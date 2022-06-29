package lk.ijse.springpos.service.Impl;

import lk.ijse.springpos.dto.CustomerDto;
import lk.ijse.springpos.entity.Customer;
import lk.ijse.springpos.repo.CustomerRepo;
import lk.ijse.springpos.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void saveCustomer(CustomerDto entity) {
        if(!customerRepo.existsById(entity.getCustID())){
            customerRepo.save(modelMapper.map(entity,Customer.class));
        }else {
            throw new RuntimeException("Customer Already Exist..!");
        }
    }


    @Override
    public void deleteCustomer(String id) {
       if(customerRepo.existsById(id)){
           customerRepo.deleteById(id);
       }else {
           throw new RuntimeException("Please check the Customer ID.. No Such Customer..!");
       }
    }
    @Override
    public void updateCustomer(CustomerDto entity) {
        if (customerRepo.existsById(entity.getCustID())) {
            //Customer customer = new Customer(entity.getId(),entity.getName(),entity.getAddress(),entity.getSalary());
            customerRepo.save(modelMapper.map(entity,Customer.class));
        } else {
            throw new RuntimeException("No Such Customer To Update..! Please Check the ID..!");
        }
    }

    @Override
    public CustomerDto searchCustomer(String id) {
        if (customerRepo.existsById(id)){
            return modelMapper.map(customerRepo.findById(id).get(),CustomerDto.class);
        }else{
            throw new RuntimeException("No Customer For "+id+" ..!");
        }
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return modelMapper.map(customerRepo.findAll(), new TypeToken<List<CustomerDto>>() {}.getType());
    }


}
