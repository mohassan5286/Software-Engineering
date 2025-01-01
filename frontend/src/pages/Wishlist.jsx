import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./Wishlist.css"; // Import the custom CSS file
import useDestinationPageInformation from "../GetDestinationPageInformation";
import { use } from "react";

const Wishlist = ({ setPid, setInformation }) => {
  const [wishlistItems, setWishlistItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();
  const userId = localStorage.getItem("user_id");
  const { getDestinationPageInformation } = useDestinationPageInformation();

  useEffect(() => {
    fetchWishlist();
  }, []);

  const fetchWishlist = async () => {
    try {
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
    console.log("pid", pid);
    getDestinationPageInformation(setInformation, setPid, pid);
    // navigate(`/destination-page/${pid}`);
  };

  if (loading) {
    return <div className="loading">Loading your favorites...</div>;
  }

  return (
    <div className="wishlist-container">
      <h1 className="wishlist-title">My Favorites</h1>
      {wishlistItems.length === 0 ? (
        <p className="empty-message">No items in your favorites list.</p>
      ) : (
        <div className="wishlist-grid">
          {wishlistItems.map((item) => (
            <div key={item.id} className="wishlist-card">
              <img
                src={item.destination.photo_Url}
                alt={item.destination.title}
                className="wishlist-image"
              />
              <div className="wishlist-content">
                <h2 className="wishlist-item-title">
                  {item.destination.title}
                </h2>
                <p className="wishlist-item-location">
                  {item.destination.location}
                </p>
                <p className="wishlist-item-price">${item.destination.price}</p>
                <div className="wishlist-buttons">
                  <button
                    onClick={() => viewDetails(item.destination.pid)}
                    className="btn btn-view"
                  >
                    View Details
                  </button>
                  <button
                    onClick={() => removeFromWishlist(item.destinationId)}
                    className="btn btn-remove"
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
