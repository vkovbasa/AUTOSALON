package com.example.hotelfullstack.controllers;

import com.example.hotelfullstack.dtos.ClientDTO;
import com.example.hotelfullstack.mapper.ClientMapper;
import com.example.hotelfullstack.models.Client;
import com.example.hotelfullstack.services.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;
    @Autowired
    private final ClientMapper clientMapper;

    @GetMapping
    public List<ClientDTO> getAllClients() {
        return clientMapper.toListEntity(clientService.getAllClients());
    }

    @GetMapping("/{id}")
    public ClientDTO getClientById(@PathVariable Long id) {
        return clientMapper.toDto(clientService.getClientById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Client>> getClientsByFullNameLike(@RequestParam("keyword") String keyword) {
        List<Client> clients = clientService.getClientsByFullNameLike(keyword);
        return ResponseEntity.ok(clients);
    }

    @PostMapping
    public Client createClient(@RequestBody @Valid Client client) {
        return clientService.createClient(client);
    }

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody @Valid ClientDTO clientDTO) {
        return clientService.updateClient(id, clientDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}
