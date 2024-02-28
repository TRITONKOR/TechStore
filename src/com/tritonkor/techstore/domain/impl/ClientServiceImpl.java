package com.tritonkor.techstore.domain.impl;

import com.tritonkor.techstore.domain.contracts.ClientService;
import com.tritonkor.techstore.domain.dto.ClientAddDTO;
import com.tritonkor.techstore.domain.exception.SignUpException;
import com.tritonkor.techstore.persistence.dao.contracts.ClientDAO;
import com.tritonkor.techstore.persistence.entity.impl.Client;
import java.io.IOException;

public class ClientServiceImpl extends GenericService<Client> implements ClientService {

    private ClientDAO clientDAO;

    public ClientServiceImpl(ClientDAO clientDAO) {
        super(clientDAO);
        this.clientDAO = clientDAO;
    }

    @Override
    public Client findByUsername(String username) throws IOException {
        return clientDAO.findByUsername(username);
    }

    @Override
    public Client add(ClientAddDTO clientAddDTO) throws IOException {
        try {
            var client = new Client(clientAddDTO.getId(), clientAddDTO.getUsername(),
                    clientAddDTO.getHashPassword());
            clientDAO.save(client);
            return client;
        } catch (RuntimeException e) {
            throw new SignUpException("Error when saving a client: %s"
                    .formatted(e.getMessage()));
        }
    }
}
