import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Heart, HeartOff } from 'lucide-react';

const FavoriteButton = ({ destinationId }) => {
  const [isFavorite, setIsFavorite] = useState(false);
  const userId = localStorage.getItem('user_id');

  useEffect(() => {
    checkIfFavorite();
  }, [destinationId]);

  const checkIfFavorite = async () => {
    try {
        // userId = 1 ;
      const response = await axios.get(
        `http://localhost:8081/api/wishlist/${userId}/check/${destinationId}`
      );
      setIsFavorite(response.data);
    } catch (error) {
      console.error('Error checking wishlist status:', error);
    }
  };

  const toggleFavorite = async () => {
    try {
        // userId = 1 ;
      if (isFavorite) {
        
        await axios.delete(
          `http://localhost:8081/api/wishlist/${userId}/destination/${destinationId}`
        );
        setIsFavorite(false);
      } else {
        await axios.post('http://localhost:8081/api/wishlist', {
          userId: userId,
          destinationId: destinationId,
          createdAt: new Date().toISOString()
        });
        setIsFavorite(true);
      }
    } catch (error) {
      console.error('Error toggling wishlist:', error);
    }
  };

  return (
    <button
      onClick={toggleFavorite}
      className={`p-2 rounded-full transition-colors ${
        isFavorite ? 'text-red-500 hover:text-red-600' : 'text-gray-400 hover:text-gray-500'
      }`}
    >
      {isFavorite ? <Heart className="w-6 h-6 fill-current" /> : <HeartOff className="w-6 h-6" />}
    </button>
  );
};

export default FavoriteButton;