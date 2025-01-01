package com.backend.backend;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.backend.backend.Controller.WishlistController;
import com.backend.backend.Service.WishlistService;
import com.backend.backend.Entity.Wishlist;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

public class WishlistControllerTest {

//    private MockMvc mockMvc;
//    private WishlistService wishlistService = mock(WishlistService.class);
//
//    @BeforeEach
//    public void setup(WebApplicationContext context) {
//        mockMvc = MockMvcBuilders
//                .standaloneSetup(new WishlistController(wishlistService))
//                .build();
//    }
//
//    @Test
//    public void getUserWishlist_returnsWishlist() throws Exception {
//        String userId = "user1";
//        List<Wishlist> mockWishlist = Arrays.asList(new Wishlist()); // Assume Wishlist is suitably constructed
//        when(wishlistService.getWishlistByUserId(userId)).thenReturn(mockWishlist);
//
//        mockMvc.perform(get("/api/wishlist/user/{userId}", userId))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)));
//    }
//    @Test
//    public void addToWishlist_returnsSavedWishlist() throws Exception {
//        Wishlist wishlist = new Wishlist(); // Assume Wishlist is suitably constructed
//        when(wishlistService.addToWishlist(any(Wishlist.class))).thenReturn(wishlist);
//
//        mockMvc.perform(post("/api/wishlist")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(wishlist)))
//                .andExpect(status().isOk())
//                .andExpect((ResultMatcher) jsonPath("$.id", is(wishlist.getId())));
//    }
//
//    @Test
//    public void addToWishlist_handlesException() throws Exception {
//        Wishlist wishlist = new Wishlist();
//        when(wishlistService.addToWishlist(any(Wishlist.class))).thenThrow(new IllegalStateException());
//
//        mockMvc.perform(post("/api/wishlist")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(wishlist)))
//                .andExpect(status().isBadRequest());
//    }
//    @Test
//    public void removeFromWishlist_returnsOk() throws Exception {
//        doNothing().when(wishlistService).removeFromWishlist(anyString(), anyString());
//
//        mockMvc.perform(delete("/api/wishlist/{userId}/destination/{destinationId}", "user1", "dest1"))
//                .andExpect(status().isOk());
//    }
//    @Test
//    public void checkWishlist_returnsTrueIfInWishlist() throws Exception {
//        when(wishlistService.isInWishlist("user1", "dest1")).thenReturn(true);
//
//        mockMvc.perform(get("/api/wishlist/{userId}/check/{destinationId}", "user1", "dest1"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("true"));
//    }
//    public static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
}
