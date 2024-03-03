package com.tritonkor.techstore.domain.impl;

import com.tritonkor.techstore.domain.contracts.ClientService;
import com.tritonkor.techstore.domain.dto.ClientAddDTO;
import com.tritonkor.techstore.domain.exception.EntityNotFoundException;
import com.tritonkor.techstore.domain.exception.SignUpException;
import com.tritonkor.techstore.persistence.dao.contracts.ClientDAO;
import com.tritonkor.techstore.persistence.entity.impl.Client;
import java.io.IOException;
import java.util.UUID;
import org.mindrot.bcrypt.BCrypt;

public class ClientServiceImpl extends GenericService<Client> implements ClientService {

    private ClientDAO clientDAO;

    public ClientServiceImpl(ClientDAO clientDAO) {
        super(clientDAO);
        this.clientDAO = clientDAO;
    }

    public ClientAddDTO createClient(String username, String rawPassword) throws IOException {
        ClientAddDTO clientAddDTO = new ClientAddDTO(UUID.randomUUID(), username, rawPassword);

        return clientAddDTO;
    }

    @Override
    public Client findByUsername(String username) throws IOException {
        return clientDAO.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("This user does not exist"));
    }

    @Override
    public Client add(ClientAddDTO clientAddDTO) throws IOException {
        try {
            var client = new Client(clientAddDTO.getId(), clientAddDTO.getUsername(),
                    BCrypt.hashpw(clientAddDTO.getRawPassword(), BCrypt.gensalt()));
            clientDAO.save(client);
            return client;
        } catch (RuntimeException e) {
            throw new SignUpException("Error when saving a client: %s"
                    .formatted(e.getMessage()));
        }
    }
}
