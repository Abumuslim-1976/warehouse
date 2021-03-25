package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Measurement;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.MeasurementService;

import java.util.List;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    @Autowired
    MeasurementService measurementService;

    //create measurement
    @PostMapping
    public Result addMeasurementController(@RequestBody Measurement measurement) {
        Result result = measurementService.addmeasurementService(measurement);
        return result;
    }

    //read measurement
    @GetMapping
    public List<Measurement> getMeasurementController() {
        return measurementService.getMeasurementService();
    }

    //read measurement {id}
    @GetMapping("/{measurementId}")
    public Measurement getMeasurement(@PathVariable Integer measurementId) {
        return measurementService.getMeasurementById(measurementId);
    }

    //delete measurement
    @DeleteMapping("/{measurementId}")
    public Result deleteMeasurement(@PathVariable Integer measurementId) {
        return measurementService.deleteMeasurementService(measurementId);
    }

    //edit measurement
    @PutMapping("/{measurementId}")
    public Result editMeasurement(@PathVariable Integer measurementId,@RequestBody Measurement measurement) {
        Result result = measurementService.editMeasurementService(measurementId, measurement);
        return result;
    }

}
