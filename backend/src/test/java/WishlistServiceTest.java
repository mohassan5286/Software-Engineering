import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import com.backend.backend.Entity.Wishlist;
import  com.backend.backend.Repository.WishlistRepository;
import  com.backend.backend.Service.WishlistService;

@ExtendWith(MockitoExtension.class)
public class WishlistServiceTest {

    @Mock
    private WishlistRepository wishlistRepository;

    @InjectMocks
    private WishlistService wishlistService;

    @Test
    public void testRemoveWishlistEntry() {
        String userId = "user123";
        String destinationId = "dest123";

        wishlistService.removeFromWishlist(userId, destinationId);

        verify(wishlistRepository).deleteByUserIdAndDestinationId(userId, destinationId);
    }

    @Test
    public void testAddWishlist() {
        Wishlist wishlist = new Wishlist();
        wishlist.setUserId("user123");
        wishlist.setDestinationId("dest123");

        when(wishlistRepository.save(any(Wishlist.class))).thenReturn(wishlist);

        Wishlist returnedWishlist = wishlistService.addToWishlist(wishlist);

        assertNotNull(returnedWishlist);
        assertEquals(wishlist.getUserId(), returnedWishlist.getUserId());
        assertEquals(wishlist.getDestinationId(), returnedWishlist.getDestinationId());
        verify(wishlistRepository).save(wishlist);
    }

    @Test
    public void testCheckIfExist() {
        String userId = "user123";
        String destinationId = "dest123";

        when(wishlistRepository.existsByUserIdAndDestinationId(userId, destinationId)).thenReturn(true);

        boolean exists = wishlistService.isInWishlist(userId, destinationId);

        assertTrue(exists);
        verify(wishlistRepository).existsByUserIdAndDestinationId(userId, destinationId);
    }
}
