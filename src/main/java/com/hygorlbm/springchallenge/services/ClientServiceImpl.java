package com.hygorlbm.springchallenge.services;

import com.hygorlbm.springchallenge.model.entities.City;
import com.hygorlbm.springchallenge.model.entities.Client;
import com.hygorlbm.springchallenge.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class ClientServiceImpl implements ClientService {

@Autowired
    ClientRepository clientRepository;

    @Override
    public Client createClient(Client client) {
        Optional<Client> alreadyClient = clientRepository.findClientByCpf(client.getCpf());

        if(alreadyClient.isPresent()) {
            return new Client();
        }

        if(!Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
                .matcher(client.getEmail())
                .matches()) {
            return null;
        }

        Client _client = clientRepository.save(client);
        return _client;
    }

    @Override
    public List<Client> getClients() {
        List<Client> _clients = clientRepository.findAll();

        return _clients;
    }

    @Override
    public List<Client> getClientsByName(String name) {
        List<Client> _clients = clientRepository.findClientByNameContaining(name);

        return _clients;
    }

    @Override
    public Optional<Client> getClientByCPF(String cpf) {
        Optional<Client> _client = clientRepository.findClientByCpf(cpf);

        return _client;
    }

    @Override
    public Client updateClient(Long id, Client client) {
        Optional<Client> _client = clientRepository.findById(id);

        if(!_client.isPresent()) {
            return null;
        }
        client.setId(id);
        Client updatedClient = clientRepository.save(client);

        return updatedClient;
    }

    @Override
    public Boolean deleteClient(Long id) {
        Optional<Client> _client = clientRepository.findById(id);
        if (_client.isPresent()) {
            clientRepository.delete(_client.get());
            return true;
        }

        return false;
    }
}
