import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import ProductCard from "../components/ProductCard"; // Adjust the import path as necessary

const Wishlist = () => {
  const [wishlistItems, setWishlistItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    fetchWishlist();
  }, []);

  const fetchWishlist = async () => {
    try {
      const userId = 40; // This should be dynamically set, possibly from user state or context
      const response = await axios.get(
        `http://localhost:8081/api/wishlist/user/${userId}`
      );
      const wishlistWithDetails = await Promise.all(
        response.data.map(async (item) => {
          const destResponse = await axios.get(
            `http://localhost:8081/destination/get/${item.destinationId}`
          );
          return {
            ...item,
            destination: destResponse.data,
          };
        })
      );
      setWishlistItems(wishlistWithDetails);
      setLoading(false);
    } catch (error) {
      console.error("Error fetching wishlist:", error);
      setLoading(false);
    }
  };

  const removeFromWishlist = async (destinationId) => {
    try {
      const userId = 40; // This should also be dynamically set
      await axios.delete(
        `http://localhost:8081/api/wishlist/${userId}/destination/${destinationId}`
      );
      setWishlistItems((items) =>
        items.filter((item) => item.destinationId !== destinationId)
      );
    } catch (error) {
      console.error("Error removing from wishlist:", error);
    }
  };

  const viewDetails = (pid) => {
    navigate(`/destination-page/${pid}`);
  };

  if (loading) {
    return <div>Loading wishlist...</div>;
  }

  return (
    <div className="p-6">
      <h1 className="text-3xl font-bold mb-6">My Favorites</h1>
      {wishlistItems.length === 0 ? (
        <p>No items in your favorites list.</p>
      ) : (
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {wishlistItems.map((item) => (
            <ProductCard
              key={item.id}
              pid={item.destination.id}
              location={item.destination.location}
              photoUrl={item.destination.photo_Url}
              price={item.destination.price}
              title={item.destination.title}
              // Assuming ProductCard does not handle removing from wishlist, so we pass a custom prop for that
              onRemove={() => removeFromWishlist(item.destinationId)}
              onViewDetails={() => viewDetails(item.destination.id)}
            />
          ))}
        </div>
      )}
    </div>
  );
};

export default Wishlist;
