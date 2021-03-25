package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Currency;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.CurrencyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;

    public Result createCurrency(Currency currency) {
        boolean existsByName = currencyRepository.existsByName(currency.getName());
        if (existsByName)
            return new Result("Bunday currency mavjud", false);

        currencyRepository.save(currency);
        return new Result("Currency created", true);
    }

    public List<Currency> getAllCurrency() {
        return currencyRepository.findAll();
    }

    public Currency getCurrency(Integer id) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        return optionalCurrency.orElseGet(Currency::new);
    }

    public Result deleteCurrency(Integer id) {
        try {
            currencyRepository.deleteById(id);
        } catch (Exception e) {
            return new Result("Bunday currency topilmadi", false);
        }
        return new Result("currency o`chirildi", true);
    }

    public Result editCurrency(Integer id,Currency currency) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (!optionalCurrency.isPresent())
            return new Result("currency topilmadi",false);

        boolean existsByName = currencyRepository.existsByName(currency.getName());
        if (existsByName)
            return new Result("Bunday currency mavjud",false);

        Currency editCurrency = optionalCurrency.get();
        editCurrency.setName(currency.getName());
        currencyRepository.save(editCurrency);
        return new Result("currency tahrirlandi",true);
    }

}
