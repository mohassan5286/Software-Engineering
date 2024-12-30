import React, { useState, useEffect } from "react";
import "./Rating.css"; // Optional: Add CSS for styling

const Rating = ({ totalStars = 5, onRate, userId, pid }) => {
  const [selectedStars, setSelectedStars] = useState(0);
   
  // Fetch the previous rating when the component mounts
  useEffect(() => {
    const fetchRating = async () => {
      try {
        const response = await fetch(`http://localhost:8081/rating/get/${userId}/${pid}`);
        if (response.ok) {
          const data = await response.json();
          // If a previous rating exists, set it
          if (data && data.ratingScore) {
            setSelectedStars(data.ratingScore);
          }
        } else {
          console.error("Failed to fetch rating data");
        }
      } catch (error) {
        console.error("Error fetching rating:", error);
      }
    };

    fetchRating();
  }, [userId, pid]); // Re-run this effect if userId or pid changes

  const handleClick = async (rating) => {
    setSelectedStars(rating);
    if (onRate) {
      onRate(rating);
    }

    // Make a fetch call to the backend when a star is selected
    try {
      const response = await fetch("http://localhost:8081/rating/rate", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
            userId: userId,
          pid: pid,
          ratingScore: rating,
        }),
      });

      if (!response.ok) {
        throw new Error("Failed to submit rating");
      }
      const data = await response.json();
      console.log("Rating submitted:", data);
    } catch (error) {
      console.error("Error submitting rating:", error);
    }
  };

  return (
    <div className="rating">
      {Array.from({ length: totalStars }, (_, index) => {
        const starIndex = index + 1;
        return (
          <span
            key={index}
            className={`star ${starIndex <= selectedStars ? "filled" : ""}`}
            onClick={() => handleClick(starIndex)}
          >
            ★
          </span>
        );
      })}
    </div>
  );
};

export default Rating;
