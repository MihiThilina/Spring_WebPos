package lk.ijse.springpos.controller;

import lk.ijse.springpos.dto.ItemDto;
import lk.ijse.springpos.service.ItemService;
import lk.ijse.springpos.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("item")
@CrossOrigin
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllItems() {
        return new ResponseUtil(200,"Ok",itemService.getAllItem());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil saveItem(@ModelAttribute ItemDto itemDTO) {
        itemService.saveItem(itemDTO);
        return new ResponseUtil(200,"Save",null);
    }


    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateItem(@RequestBody ItemDto itemDTO) {
        itemService.updateItem(itemDTO);
        return new ResponseUtil(200,"Updated",null);
    }

    @DeleteMapping(params = {"code"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil deleteItem(@RequestParam String code) {
        itemService.deleteItem(code);
        return new ResponseUtil(200,"Deleted",null);
    }

    @GetMapping(path = "/{code}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil searchItem(@PathVariable String code) {
        return new ResponseUtil(200,"Ok",itemService.searchItem(code));
    }


}
