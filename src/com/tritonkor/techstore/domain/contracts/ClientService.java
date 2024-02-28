package com.tritonkor.techstore.domain.contracts;

import com.tritonkor.techstore.domain.Service;
import com.tritonkor.techstore.domain.dto.ClientAddDTO;
import com.tritonkor.techstore.persistence.entity.impl.Client;
import java.io.IOException;

public interface ClientService extends Service<Client> {

    Client findByUsername(String username) throws IOException;

    Client add(ClientAddDTO clientAddDTO) throws IOException;
}
