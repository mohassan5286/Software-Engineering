package com.backend.backend.Repository;

import com.backend.backend.Entity.NormalUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface NormalUserRepository extends MongoRepository<NormalUser, String> {
}
