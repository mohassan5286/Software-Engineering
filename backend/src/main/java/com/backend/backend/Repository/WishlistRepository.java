// Repository interface
package com.backend.backend.Repository;

import com.backend.backend.Entity.Wishlist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WishlistRepository extends MongoRepository<Wishlist, String> {
    List<Wishlist> findByUserId(String userId);
    boolean existsByUserIdAndDestinationId(String userId, String destinationId);
    void deleteByUserIdAndDestinationId(String userId, String destinationId);
}