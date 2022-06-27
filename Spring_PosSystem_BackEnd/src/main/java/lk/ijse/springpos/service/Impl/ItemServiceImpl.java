package lk.ijse.springpos.service.Impl;

import lk.ijse.springpos.dto.ItemDto;
import lk.ijse.springpos.entity.Customer;
import lk.ijse.springpos.entity.Item;
import lk.ijse.springpos.repo.ItemRepo;
import lk.ijse.springpos.service.ItemService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void saveItem(ItemDto itemDto) {
        if(!itemRepo.existsById(itemDto.getItemCode())){
            itemRepo.save(modelMapper.map(itemDto, Item.class));
        }else {
            throw new RuntimeException("Item Already Exist..!");
        }
    }

    @Override
    public void deleteItem(String id) {
        if (itemRepo.existsById(id)) {
            itemRepo.deleteById(id);
        } else {
            throw new RuntimeException("Delete Failed, No Item Available For " + id);
        }
    }

    @Override
    public void updateItem(ItemDto entity) {
        if (itemRepo.existsById(entity.getItemCode())) {
            itemRepo.save(modelMapper.map(entity, Item.class));
        } else {
            throw new RuntimeException("Update Failed, No Item Available For " + entity.getItemCode());
        }
    }

    @Override
    public ItemDto searchItem(String id) {
        if (itemRepo.existsById(id)) {
            return modelMapper.map(itemRepo.findById(id), ItemDto.class);
        } else {
            throw new RuntimeException("Search Failed, No Item Available For " + id);
        }
    }

    @Override
    public List<ItemDto> getAllItem() {
        return modelMapper.map(itemRepo.findAll(), new TypeToken<List<ItemDto>>() {
        }.getType());
    }
}
