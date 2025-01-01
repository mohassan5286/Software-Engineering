import React, { useState } from "react";
import { Heart } from "lucide-react";

const FavoriteButton = ({ destinationId }) => {
  const [isFavorite, setIsFavorite] = useState(false);
  const [loading, setLoading] = useState(false); // Added to handle loading state

  const toggleFavorite = async () => {
    const userId = 40;  // Assuming a static user ID for demonstration purposes
    setLoading(true);  // Set loading to true at the start of the operation

    try {
      if (isFavorite) {
        await fetch(
          `http://localhost:8081/api/wishlist/${userId}/destination/${destinationId}`,
          { method: "DELETE" }
        );
        setIsFavorite(false);
      } else {
        await fetch("http://localhost:8081/api/wishlist", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            userId: userId,
            destinationId: destinationId,
          }),
        });
        setIsFavorite(true);
      }
    } catch (error) {
      console.error("Error toggling wishlist:", error);
      alert('Failed to update the wishlist.');  // Inform the user of a failure
    }
    setLoading(false);  // Set loading to false after the operation is complete
  };

  // Disable the button while loading
  return (
    <button
      type="button"
      onClick={toggleFavorite}
      disabled={loading}  // Disable the button when loading
      className="inline-flex items-center justify-center p-2 rounded-full transition-colors hover:bg-gray-100"
      aria-label={isFavorite ? "Remove from favorites" : "Add to favorites"}
    >
      {loading ? (
        <div>Loading...</div>  // Display loading feedback
      ) : (
        <Heart
          className={`w-6 h-6 ${
            isFavorite ? "fill-red-500 text-red-500" : "text-gray-500 hover:text-red-500"
          }`}
        />
      )}
    </button>
  );
};

export default FavoriteButton;
