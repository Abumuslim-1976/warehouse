package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import uz.pdp.warehouse.entity.Measurement;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.MeasurementRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {

    @Autowired
    MeasurementRepository measurementRepository;


    public Result addmeasurementService(Measurement measurement) {
        boolean existsByName = measurementRepository.existsByName(measurement.getName());
        if (existsByName)
            return new Result("Bunday nomli o`lchov birligi bor", false);

        measurementRepository.save(measurement);
        return new Result("Muvaffaqiyatli saqlandi", true);
    }

    public List<Measurement> getMeasurementService() {
        return measurementRepository.findAll();
    }

    public Measurement getMeasurementById(Integer measurementId) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(measurementId);
        return optionalMeasurement.orElseGet(Measurement::new);
    }

    public Result deleteMeasurementService(Integer measurementId) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(measurementId);
        if (!optionalMeasurement.isPresent())
            return new Result("Bunday ID lik o`lchov birligi yo`q",false);

        measurementRepository.deleteById(measurementId);
        return new Result("O`lchov birligi o`chirildi",true);
    }

    public Result editMeasurementService(Integer measurementId , Measurement measurement) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(measurementId);
        if (optionalMeasurement.isPresent()) {

            boolean existsByName = measurementRepository.existsByName(measurement.getName());
            if (existsByName)
                return new Result("Bunday o`lchov birligi nomi mavjud",false);

            Measurement editMeasurement = optionalMeasurement.get();
            editMeasurement.setName(measurement.getName());
            measurementRepository.save(editMeasurement);
            return new Result("O`lchov birligi tahrirlandi",true);
        }
        return new Result("Bunday ID lik o`lchov birligi yo`q",false);
    }


}
