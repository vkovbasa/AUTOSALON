package com.example.hotelfullstack.services;

import com.example.hotelfullstack.dtos.ClientDTO;
import com.example.hotelfullstack.exceptions.ResourceNotFoundException;
import com.example.hotelfullstack.models.Client;
import com.example.hotelfullstack.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public List<Client> getClientsByFullNameLike(String keyword) {
        return clientRepository.findByFullNameContaining(keyword);
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Client not found id : " + id)
        );
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client updateClient(Long id, ClientDTO clientDTO) {
        Client updatedClient = clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Client not found id : " + id)
        );
        modelMapper.map(clientDTO, updatedClient); // copy field
        return clientRepository.save(updatedClient);
    }

    public void deleteClient(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Client not found id : " + id)
        );
        clientRepository.delete(client);
    }
}
