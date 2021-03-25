package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Currency;
import uz.pdp.warehouse.entity.Input;
import uz.pdp.warehouse.entity.Supplier;
import uz.pdp.warehouse.entity.Warehouse;
import uz.pdp.warehouse.payload.InputDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.CurrencyRepository;
import uz.pdp.warehouse.repository.InputRepository;
import uz.pdp.warehouse.repository.SupplierRepository;
import uz.pdp.warehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InputService {

    @Autowired
    InputRepository inputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    CurrencyRepository currencyRepository;


    public Result createInput(InputDto inputDto) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("warehouse not found", false);

        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (!optionalSupplier.isPresent())
            return new Result("supplier not found", false);

        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("There is no currency for this product", false);

        String code = UUID.randomUUID().toString();

        Input input = new Input();
        input.setTimestamp(inputDto.getTimestamp());
        input.setFactureNumber(inputDto.getFactureNumber());
        input.setWarehouse(optionalWarehouse.get());
        input.setSupplier(optionalSupplier.get());
        input.setCurrency(optionalCurrency.get());
        input.setCode(code);
        inputRepository.save(input);
        return new Result("Input created", true);

    }


    public List<Input> getAllInput() {
        return inputRepository.findAll();
    }


    public Input getInput(Integer id) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        return optionalInput.orElseGet(Input::new);
    }


    public Result deleteInput(Integer id) {
        try {
            inputRepository.deleteById(id);
        } catch (Exception e) {
            return new Result("ERROR !!! , Input not found", false);
        }
        return new Result("Input deleted", true);
    }


    public Result editInput(Integer id, InputDto inputDto) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (!optionalInput.isPresent())
            return new Result("Input not found", false);

        Optional<Warehouse> warehouseOptional = warehouseRepository.findById(inputDto.getWarehouseId());
        if (!warehouseOptional.isPresent())
            return new Result("warehouse not found", false);

        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (!optionalSupplier.isPresent())
            return new Result("supplier not found", false);

        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("There is no currency for this product", false);

        String code = UUID.randomUUID().toString();

        Input input = optionalInput.get();
        input.setTimestamp(inputDto.getTimestamp());
        input.setFactureNumber(inputDto.getFactureNumber());
        input.setWarehouse(warehouseOptional.get());
        input.setSupplier(optionalSupplier.get());
        input.setCurrency(optionalCurrency.get());
        input.setCode(code);
        inputRepository.save(input);
        return new Result("Input edited", true);
    }

}
