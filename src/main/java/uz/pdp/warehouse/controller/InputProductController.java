package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.InputProduct;
import uz.pdp.warehouse.payload.InputProductDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.InputProductService;

import java.util.List;

@RestController
@RequestMapping("/inputProduct")
public class InputProductController {

    @Autowired
    InputProductService inputProductService;

    @PostMapping
    public Result create(@RequestBody InputProductDto inputProductDto) {
        return inputProductService.createInPr(inputProductDto);
    }

    @GetMapping
    public List<InputProduct> getAll() {
        return inputProductService.getAllInPr();
    }

    @GetMapping("/{id}")
    public InputProduct get(@PathVariable Integer id) {
        return inputProductService.getInPr(id);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return inputProductService.deleteInPr(id);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody InputProductDto inputProductDto) {
        return inputProductService.editInPr(id, inputProductDto);
    }

}
