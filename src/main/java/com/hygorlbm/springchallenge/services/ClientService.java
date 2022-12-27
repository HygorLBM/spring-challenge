package com.hygorlbm.springchallenge.services;

import com.hygorlbm.springchallenge.model.entities.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    Client createClient(Client client);
    List<Client> getClients();
    List<Client> getClientsByName(String name);
    Optional<Client> getClientByCPF(String cpf);
    Client updateClient(Long id, Client client);
    Boolean deleteClient(Long id);

}
