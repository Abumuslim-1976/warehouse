package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.User;
import uz.pdp.warehouse.entity.Warehouse;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.payload.UserDto;
import uz.pdp.warehouse.repository.UserRepository;
import uz.pdp.warehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    WarehouseRepository warehouseRepository;

    public Result createUser(UserDto userDto) {
        boolean exists = userRepository.existsByPhoneNumber(userDto.getPhoneNumber());
        if (exists)
            return new Result("Bunday phone number bor", false);

        List<Warehouse> warehouses = warehouseRepository.findAllById(userDto.getWarehouseId());

        String code = UUID.randomUUID().toString();

        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setCode(code);
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setWarehouses(warehouses);
        userRepository.save(user);
        return new Result("saqlandi", true);
    }


    public List<User> getUsers() {
        return userRepository.findAll();
    }


    public Result deleteUser(Integer id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            return new Result("Xatolik !!! , User topilmadi", false);
        }
        return new Result("User o`chirildi", true);
    }


    public Result editUser(Integer id, UserDto userDto) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent())
            return new Result("Bunday user yo`q", false);

        boolean exists = userRepository.existsByPhoneNumberAndIdNot(userDto.getPhoneNumber(), id);
        if (exists)
            return new Result("Bunday telefon raqam bor", false);

        List<Warehouse> warehouseList = warehouseRepository.findAllById(userDto.getWarehouseId());

        String code = UUID.randomUUID().toString();

        User user = userOptional.get();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setCode(code);
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setWarehouses(warehouseList);
        userRepository.save(user);
        return new Result("Saqlandi", true);
    }

}
