// Service class
package com.backend.backend.Service;

import com.backend.backend.Entity.Wishlist;
import com.backend.backend.Repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    private WishlistRepository wishlistRepository;

    @Autowired
    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    public List<Wishlist> getWishlistByUserId(String userId) {
        return wishlistRepository.findByUserId(userId);
    }

    public Wishlist addToWishlist(Wishlist wishlist) {
        if (wishlistRepository.existsByUserIdAndDestinationId(
                wishlist.getUserId(),
                wishlist.getDestinationId())) {
//            System.out.println("sdsdsdkjsdkj dssdds ");
            throw new IllegalStateException("Item already exists in wishlist");
        }
        return wishlistRepository.save(wishlist);
    }

    public void removeFromWishlist(String userId, String destinationId) {
        wishlistRepository.deleteByUserIdAndDestinationId(userId, destinationId);
    }

    public boolean isInWishlist(String userId, String destinationId) {
        return wishlistRepository.existsByUserIdAndDestinationId(userId, destinationId);
    }
}
