import React, { useState } from 'react';

import useDestinationPageInformation from '../GetDestinationPageInformation.js';

const ProductCard = ({
  pid,
  location,
  photo_Url,
  price,
  rating,
  title,
  setId,
  setInformation,
}) => {
  const [isHovered, setIsHovered] = useState(false);

  // Use the custom hook to get the function for fetching destination information
  const { getDestinationPageInformation } = useDestinationPageInformation();

  const handleSeeMore = () => {
    getDestinationPageInformation(setInformation, setId, pid);
  };

  return (
    <div
      style={{
        border: '1px solid #ccc',
        borderRadius: '8px',
        padding: '16px',
        margin: '30px',
        maxWidth: '3000px',
        color: 'black',
        textAlign: 'center',
        background: 'white',
        boxShadow: '0 4px 8px rgba(0, 0, 0, 1)',
      }}
    >
      <h2>{title}</h2>
      <img
        src={photo_Url}
        alt={title}
        style={{
          width: '100%',
          height: '220px',
          borderRadius: '8px',
        }}
      />
      <div style={{ textAlign: 'left', marginTop: '10px' }}>
        <p style={{ fontWeight: 'bold' }}>
          Price: <span style={{ fontWeight: 'normal' }}>${price}</span>
        </p>
        <p style={{ fontWeight: 'bold' }}>
          Destination: <span style={{ fontWeight: 'normal' }}>{location}</span>
        </p>
        <p style={{ fontWeight: 'bold' }}>
          Rating: <span style={{ fontWeight: 'normal' }}>{rating}</span>
        </p>
      </div>
      <button
        style={{
          cursor: 'pointer',
          padding: '8px 16px',
          marginTop: '5%',
          width: "40%",
          backgroundColor: isHovered ? '#4CAF50' : '#007BFF',
          color: 'white',
          border: 'none',
          borderRadius: '4px',
          transition: 'background-color 0.3s ease',
        }}
        onMouseEnter={() => setIsHovered(true)}
        onMouseLeave={() => setIsHovered(false)}
        onClick={handleSeeMore}
      >
        View More Details
      </button>
    </div>
  );
};

export default ProductCard;
