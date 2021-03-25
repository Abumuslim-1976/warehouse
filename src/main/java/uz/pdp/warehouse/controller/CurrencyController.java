package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Currency;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    CurrencyService currencyService;

    @PostMapping
    public Result create(@RequestBody Currency currency) {
        return currencyService.createCurrency(currency);
    }

    @GetMapping
    public List<Currency> getAll() {
        return currencyService.getAllCurrency();
    }

    @GetMapping("/{id}")
    public Currency get(@PathVariable Integer id) {
        return currencyService.getCurrency(id);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return currencyService.deleteCurrency(id);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody Currency currency) {
        return currencyService.editCurrency(id, currency);
    }

}
