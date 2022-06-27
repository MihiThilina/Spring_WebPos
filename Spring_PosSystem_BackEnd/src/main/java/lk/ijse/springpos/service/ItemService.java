package lk.ijse.springpos.service;

import lk.ijse.springpos.dto.CustomerDto;
import lk.ijse.springpos.dto.ItemDto;

import java.util.List;

public interface ItemService {

    void saveItem(ItemDto itemDto);
    void deleteItem(String id);
    void updateItem(ItemDto entity);
    ItemDto searchItem(String id);
    List<ItemDto> getAllItem();

}
