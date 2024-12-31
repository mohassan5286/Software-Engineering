import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const Wishlist = () => {
  const [wishlistItems, setWishlistItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();
  const userId = localStorage.getItem('user_id');

  useEffect(() => {
    fetchWishlist();
  }, []);

  const fetchWishlist = async () => {
    try {
    
      const response = await axios.get(`http://localhost:8081/api/wishlist/user/${userId}`);
      const wishlistWithDetails = await Promise.all(
        response.data.map(async (item) => {
          const destResponse = await axios.get(`http://localhost:8081/destination/get/${item.destinationId}`);
          return {
            ...item,
            destination: destResponse.data
          };
        })
      );
      setWishlistItems(wishlistWithDetails);
      setLoading(false);
    } catch (error) {
      console.error('Error fetching wishlist:', error);
      setLoading(false);
    }
  };

  const removeFromWishlist = async (destinationId) => {
    try {
    
      await axios.delete(`http://localhost:8081/api/wishlist/${userId}/destination/${destinationId}`);
      setWishlistItems(items => items.filter(item => item.destinationId !== destinationId));
    } catch (error) {
      console.error('Error removing from wishlist:', error);
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
            <div key={item.id} className="border rounded-lg shadow-lg overflow-hidden">
              <img 
                src={item.destination.photo_Url} 
                alt={item.destination.title}
                className="w-full h-48 object-cover"
              />
              <div className="p-4">
                <h2 className="text-xl font-semibold mb-2">{item.destination.title}</h2>
                <p className="text-gray-600 mb-2">{item.destination.location}</p>
                <p className="text-lg font-bold mb-4">${item.destination.price}</p>
                <div className="flex justify-between">
                  <button
                    onClick={() => viewDetails(item.destination.pid)}
                    className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600"
                  >
                    View Details
                  </button>
                  <button
                    onClick={() => removeFromWishlist(item.destinationId)}
                    className="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600"
                  >
                    Remove
                  </button>
                </div>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default Wishlist;