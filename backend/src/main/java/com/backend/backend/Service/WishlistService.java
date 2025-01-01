// Service class
package com.backend.backend.Service;

import com.backend.backend.Entity.Wishlist;
import com.backend.backend.Repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    public static final String ITEM_ALREADY_EXISTS_IN_WISHLIST = "Item already exists in wishlist";
    private WishlistRepository wishlistRepository;

    @Autowired
    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    public List<Wishlist> getWishlistByUserId(String userId) {
        return wishlistRepository.findByUserId(userId);
    }

    public Wishlist addToWishlist(Wishlist wishlist) {
        if (doesItemExist(wishlist)) {
//            System.out.println("sdsdsdkjsdkj dssdds ");
            throw new IllegalStateException(ITEM_ALREADY_EXISTS_IN_WISHLIST);
        }
        return wishlistRepository.save(wishlist);
    }

    private boolean doesItemExist(Wishlist wishlist) {
        return wishlistRepository.existsByUserIdAndDestinationId(
                wishlist.getUserId(),
                wishlist.getDestinationId());
    }

    public void removeFromWishlist(String userId, String destinationId) {
        wishlistRepository.deleteByUserIdAndDestinationId(userId, destinationId);
    }

    public boolean isInWishlist(String userId, String destinationId) {
        return wishlistRepository.existsByUserIdAndDestinationId(userId, destinationId);
    }
}
