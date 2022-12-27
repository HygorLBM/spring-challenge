package com.hygorlbm.springchallenge.controllers;

import com.hygorlbm.springchallenge.model.entities.Client;
import com.hygorlbm.springchallenge.services.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    ClientServiceImpl clientService;

    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getClients() {
        try {
            List<Client> _clients = clientService.getClients();
            return new ResponseEntity<>(_clients, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/clients/{name}")
    public ResponseEntity<List<Client>> getClientsByName(@PathVariable("name") String name) {
        try{
            List<Client> _clients = clientService.getClientsByName(name);

            if (_clients.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(_clients, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/clientByCPF/{cpf}")
    public ResponseEntity<Client> getClientByCpf(@PathVariable("cpf") String cpf) {
        try{
            Optional<Client> _client = clientService.getClientByCPF(cpf);

            if (_client.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(_client.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/clients")
    public ResponseEntity<?> createClient(@RequestBody Client client) {
        try {
            Client _client = clientService.createClient(client);
            if(_client == null) {
                return new ResponseEntity<>("E-mail inválido", HttpStatus.BAD_REQUEST);
            } else if (_client.getCpf() != client.getCpf()) {
                return new ResponseEntity<>("CPF já cadastrado", HttpStatus.CONFLICT);
            }

            return new ResponseEntity<>(_client, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/client/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable("id") long id, @RequestBody Client client) {
        try {
            Client _client = clientService.updateClient(id, client);

            if (_client == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(_client, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable("id") long id) {
        try {
            Boolean isClientDeleted = clientService.deleteClient(id);
            if (isClientDeleted == false) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
