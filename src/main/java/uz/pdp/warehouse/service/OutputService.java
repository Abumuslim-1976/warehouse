package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Client;
import uz.pdp.warehouse.entity.Currency;
import uz.pdp.warehouse.entity.Output;
import uz.pdp.warehouse.entity.Warehouse;
import uz.pdp.warehouse.payload.OutputDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.ClientRepository;
import uz.pdp.warehouse.repository.CurrencyRepository;
import uz.pdp.warehouse.repository.OutputRepository;
import uz.pdp.warehouse.repository.WarehouseRepository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OutputService {

    @Autowired
    OutputRepository outputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    ClientRepository clientRepository;


    public Result createOutput(OutputDto outputDto) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("warehouse not found", false);

        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("currency not found", false);

        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (!optionalClient.isPresent())
            return new Result("client not found", false);

        String code = UUID.randomUUID().toString();

        Output output = new Output();
        output.setTimestamp(outputDto.getTimestamp());
        output.setFactureNumber(outputDto.getFactureNumber());
        output.setCode(code);
        output.setWarehouse(optionalWarehouse.get());
        output.setCurrency(optionalCurrency.get());
        output.setClient(optionalClient.get());
        outputRepository.save(output);
        return new Result("output created", true);
    }


    public List<Output> getAllOutput(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Output> outputPage = outputRepository.findAll(pageable);
        return outputPage.getContent();
    }


    public Output getOutput(Integer id) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        return optionalOutput.orElseGet(Output::new);
    }


    public Result deleteOutput(Integer id) {
        try {
            outputRepository.deleteById(id);
        } catch (Exception e) {
            return new Result("output not found", false);
        }
        return new Result("output deleted", true);
    }


    public Result editOutput(Integer id, OutputDto outputDto) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (!optionalOutput.isPresent())
            return new Result("output not found", false);

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("warehouse not found", false);

        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("currency not found", false);

        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (!optionalClient.isPresent())
            return new Result("client not found", false);

        String code = UUID.randomUUID().toString();

        Output output = optionalOutput.get();
        output.setTimestamp(outputDto.getTimestamp());
        output.setFactureNumber(outputDto.getFactureNumber());
        output.setCode(code);
        output.setWarehouse(optionalWarehouse.get());
        output.setCurrency(optionalCurrency.get());
        output.setClient(optionalClient.get());
        outputRepository.save(output);
        return new Result("output edited", true);
    }

}
