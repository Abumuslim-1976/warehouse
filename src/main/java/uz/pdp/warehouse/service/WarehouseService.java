package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Warehouse;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {

    @Autowired
    WarehouseRepository warehouseRepository;

    public Result addWarehouse(Warehouse warehouse) {
        boolean exists = warehouseRepository.existsByName(warehouse.getName());
        if (exists)
            return new Result("Bunday omborxona mavjud", false);

        warehouseRepository.save(warehouse);
        return new Result("Omborxona saqlandi", true);
    }


    public List<Warehouse> getAllWarehouse() {
        return warehouseRepository.findAll();
    }


    public Warehouse getWarehouse(Integer id) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        return optionalWarehouse.orElse(null);
    }


    public Result deleteWarehouse(Integer id) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (!optionalWarehouse.isPresent())
            return new Result("Bunday Warehouse yo`q", false);

        warehouseRepository.deleteById(id);
        return new Result("Warehouse o`chirildi", true);
    }


    public Result editWarehouse(Integer id, Warehouse warehouse) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (!optionalWarehouse.isPresent())
            return new Result("Bunday Warehouse mavjud emas",false);

        boolean exists = warehouseRepository.existsByNameAndIdNot(warehouse.getName(), id);
        if (exists)
            return new Result("Bunday nomli mijoz mavjud",false);

        Warehouse editWarehouse = optionalWarehouse.get();
        editWarehouse.setName(warehouse.getName());
        warehouseRepository.save(editWarehouse);
        return new Result("warehouse tahrirlandi",true);
    }

}
