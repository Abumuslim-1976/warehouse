package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Supplier;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    public Result createSupplier(Supplier supplier) {
        boolean exists = supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber());
        if (exists)
            return new Result("Bunday telefon raqam bor", false);

        Supplier createSupplier = new Supplier();
        createSupplier.setName(supplier.getName());
        createSupplier.setPhoneNumber(supplier.getPhoneNumber());
        supplierRepository.save(createSupplier);
        return new Result("saqlandi", true);
    }


    public List<Supplier> getAllSupplier() {
        return supplierRepository.findAll();
    }


    public Supplier getSupplier(Integer id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        return optionalSupplier.orElseGet(Supplier::new);
    }


    public Result deleteSupplier(Integer id) {
        try {
            supplierRepository.deleteById(id);
        } catch (Exception e) {
            return new Result("xatolik!!! , supplier topilmadi", false);
        }
        return new Result("supplier o`chirildi", true);
    }


    public Result editSupplier(Integer id, Supplier supplier) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (!optionalSupplier.isPresent())
            return new Result("Bunday supplier yo`q",false);

        boolean exists = supplierRepository.existsByPhoneNumberAndIdNot(supplier.getPhoneNumber(), id);
        if (exists)
            return new Result("bunday telefon raqam bor",false);

        Supplier editSupplier = optionalSupplier.get();
        editSupplier.setName(supplier.getName());
        editSupplier.setPhoneNumber(supplier.getPhoneNumber());
        supplierRepository.save(editSupplier);
        return new Result("tahrirlandi",true);
    }



}
