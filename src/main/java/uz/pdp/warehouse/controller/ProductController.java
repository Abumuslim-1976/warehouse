package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Product;
import uz.pdp.warehouse.payload.ProductDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public Result add(@RequestBody ProductDto productDto) {
        return productService.addProduct(productDto);
    }

    @GetMapping
    public List<Product> getAll() {
        return productService.getAllProduct();
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable Integer id) {
        return productService.getProduct(id);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return productService.deleteProduct(id);
    }

    @PutMapping("/{editProductId}")
    public Result edit(@PathVariable Integer editProductId,@RequestBody ProductDto productDto) {
        return productService.editProduct(editProductId,productDto);
    }
}
