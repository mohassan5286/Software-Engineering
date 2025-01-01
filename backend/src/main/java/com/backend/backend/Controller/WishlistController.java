// Controller class
package com.backend.backend.Controller;

//import com.backend.backend.Entity.Wishlist;
import com.backend.backend.Entity.Wishlist;
import com.backend.backend.Service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
@CrossOrigin(value = "http://localhost:3000/")
public class WishlistController {

    private  WishlistService wishlistService;

    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Wishlist>> getUserWishlist(@PathVariable String userId) {
        List<Wishlist> wishlist = wishlistService.getWishlistByUserId(userId);
        return ResponseEntity.ok(wishlist);
    }

    @PostMapping
    public ResponseEntity<Wishlist> addToWishlist(@RequestBody Wishlist wishlist) {
        System.out.println("we are here ");
        try {
            Wishlist savedWishlist = wishlistService.addToWishlist(wishlist);
            return ResponseEntity.ok(savedWishlist);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{userId}/destination/{destinationId}")
    public ResponseEntity<Void> removeFromWishlist(
            @PathVariable String userId,
            @PathVariable String destinationId) {
        wishlistService.removeFromWishlist(userId, destinationId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/check/{destinationId}")
    public ResponseEntity<Boolean> checkWishlist(
            @PathVariable String userId,
            @PathVariable String destinationId) {
        boolean exists = wishlistService.isInWishlist(userId, destinationId);
        return ResponseEntity.ok(exists);
    }
}