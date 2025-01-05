import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Heart, HeartOff } from 'lucide-react';
import './FavoriteButton.css';

const FavoriteButton = ({ destinationId }) => {
  const [isFavorite, setIsFavorite] = useState(false);
  const userId = localStorage.getItem('user_id');

  useEffect(() => {
    checkIfFavorite();
  }, [destinationId]);

  const checkIfFavorite = async () => {
    try {
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
      if (isFavorite) {
        await axios.delete(
          `http://localhost:8081/api/wishlist/${userId}/destination/${destinationId}`
        );
        setIsFavorite(false);
      } else {
        await axios.post('http://localhost:8081/api/wishlist', {
          userId: userId,
          destinationId: destinationId,
          createdAt: new Date().toISOString(),
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
      className={`favorite-button ${isFavorite ? 'favorite' : ''}`}
    >
      {isFavorite ? <Heart className="icon" /> : <HeartOff className="icon" />}
    </button>
  );
};

export default FavoriteButton;
