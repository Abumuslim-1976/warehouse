package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Output;
import uz.pdp.warehouse.payload.OutputDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.OutputService;

import java.util.List;

@RestController
@RequestMapping("/output")
public class OutputController {

    @Autowired
    OutputService outputService;

    @PostMapping
    public Result create(@RequestBody OutputDto outputDto) {
        return outputService.createOutput(outputDto);
    }

    @GetMapping
    public List<Output> getAll(int page, int size) {
        return outputService.getAllOutput(page, size);
    }

    @GetMapping("/{id}")
    public Output get(@PathVariable Integer id) {
        return outputService.getOutput(id);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return outputService.deleteOutput(id);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody OutputDto outputDto) {
        return outputService.editOutput(id, outputDto);
    }

}
