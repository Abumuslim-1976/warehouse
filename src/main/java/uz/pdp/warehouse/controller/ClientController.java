package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Client;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping
    public Result create(@RequestBody Client client) {
        return clientService.createClient(client);
    }

    @GetMapping
    public List<Client> getAll(int page,int size) {
        return clientService.getAllClient(page, size);
    }

    @GetMapping("/{id}")
    public Client get(@PathVariable Integer id) {
        return clientService.getClient(id);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return clientService.deleteClient(id);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody Client client) {
        return clientService.editClient(id, client);
    }

}
