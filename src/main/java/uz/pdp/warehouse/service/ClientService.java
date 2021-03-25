package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Client;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public Result createClient(Client client) {
        boolean exists = clientRepository.existsByPhoneNumber(client.getPhoneNumber());
        if (exists)
            return new Result("This is such a phone number", false);

        clientRepository.save(client);
        return new Result("client saved", true);
    }


    public List<Client> getAllClient(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Client> clients = clientRepository.findAll(pageable);
        return clients.getContent();
    }


    public Client getClient(Integer id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        return optionalClient.orElseGet(Client::new);
    }


    public Result deleteClient(Integer id) {
        try {
            clientRepository.deleteById(id);
        } catch (Exception e) {
            return new Result("client not found", false);
        }
        return new Result("client deleted", true);
    }


    public Result editClient(Integer id, Client client) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (!optionalClient.isPresent())
            return new Result("client not found", false);

        boolean exists = clientRepository.existsByPhoneNumberAndIdNot(client.getPhoneNumber(), id);
        if (exists)
            return new Result("This is such a phone number", false);

        Client editClient = optionalClient.get();
        editClient.setName(client.getName());
        editClient.setPhoneNumber(client.getPhoneNumber());
        clientRepository.save(editClient);
        return new Result("client edited", true);
    }

}
