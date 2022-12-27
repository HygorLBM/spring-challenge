package com.hygorlbm.springchallenge.repositories;

import com.hygorlbm.springchallenge.model.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("SELECT COUNT(*) FROM Client c WHERE c.city.id = :id")
    int countClientsInCity(@Param("id") Long id);

    List<Client> findClientByNameContaining(String name);
    Optional<Client> findClientByCpf(String cpf);

    }

