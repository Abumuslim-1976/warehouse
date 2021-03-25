package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Input;
import uz.pdp.warehouse.payload.InputDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.InputService;

import java.util.List;

@RestController
@RequestMapping("/input")
public class InputController {

    @Autowired
    InputService inputService;

    @PostMapping
    public Result create(@RequestBody InputDto inputDto) {
        return inputService.createInput(inputDto);
    }

    @GetMapping
    public List<Input> getAll() {
        return inputService.getAllInput();
    }

    @GetMapping("/{id}")
    public Input get(@PathVariable Integer id) {
        return inputService.getInput(id);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return inputService.deleteInput(id);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody InputDto inputDto) {
        return inputService.editInput(id, inputDto);
    }


}
