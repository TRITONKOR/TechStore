package com.tritonkor.techstore.domain.contracts;

import com.tritonkor.techstore.domain.Service;
import com.tritonkor.techstore.domain.dto.ReviewAddDTO;
import com.tritonkor.techstore.persistence.entity.impl.Client;
import com.tritonkor.techstore.persistence.entity.impl.Review;
import java.io.IOException;

public interface ReviewService extends Service<Review> {

    Review findByClient(Client client) throws IOException;

    Review add(ReviewAddDTO reviewAddDTO) throws IOException;
}
