package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Warehouse;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.WarehouseService;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {


    @Autowired
    WarehouseService warehouseService;

    @PostMapping
    public Result add(@RequestBody Warehouse warehouse) {
        return warehouseService.addWarehouse(warehouse);
    }

    @GetMapping
    public List<Warehouse> getAll() {
        return warehouseService.getAllWarehouse();
    }

    @GetMapping("/{id}")
    public Warehouse get(@PathVariable Integer id) {
        return warehouseService.getWarehouse(id);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return warehouseService.deleteWarehouse(id);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody Warehouse warehouse) {
        return warehouseService.editWarehouse(id,warehouse);
    }

}
