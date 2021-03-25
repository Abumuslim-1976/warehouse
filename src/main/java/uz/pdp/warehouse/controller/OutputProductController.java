package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.OutputProduct;
import uz.pdp.warehouse.payload.OutputProductDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.OutputProductService;

import java.util.List;

@RestController
@RequestMapping("/outputProduct")
public class OutputProductController {

    @Autowired
    OutputProductService outputProductService;

    @GetMapping
    public List<OutputProduct> getAll(int page, int size) {
        return outputProductService.getAllOutPro(page, size);
    }

    @GetMapping("/{id}")
    public OutputProduct get(@PathVariable Integer id) {
        return outputProductService.getOutPro(id);
    }

    @PostMapping
    public Result create(@RequestBody OutputProductDto outputProductDto) {
        return outputProductService.createOutPro(outputProductDto);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return outputProductService.deleteOutPro(id);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody OutputProductDto outputProductDto) {
        return outputProductService.editOutPro(id, outputProductDto);
    }

}
