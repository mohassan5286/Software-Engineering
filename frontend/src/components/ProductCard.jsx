import React, { useState } from 'react';
import useDestinationPageInformation from '../GetDestinationPageInformation.js';
import FavoriteButton from './FavoriteButton';


const ProductCard = ({
  pid,
  location,
  photo_Url,
  price,
  rating,
  title,
  setPid,
  setInformation,
}) => {
  const [isHovered, setIsHovered] = useState(false);
  const { getDestinationPageInformation } = useDestinationPageInformation();

  const handleSeeMore = () => {
    getDestinationPageInformation(setInformation, setPid, pid);
  };

  const cardStyles = {
    border: '1px solid #ccc',
    borderRadius: '8px',
    padding: '16px',
    margin: '30px',
    maxWidth: '3000px',
    color: 'black',
    textAlign: 'center',
    background: 'white',
    boxShadow: '0 4px 8px rgba(0, 0, 0, 1)',
    position: 'relative',
  };

  const imgStyles = {
    width: '100%',
    height: '220px',
    borderRadius: '8px',
  };

  const textContainerStyles = {
    textAlign: 'left',
    marginTop: '10px',
  };

  const priceStyles = {
    fontWeight: 'bold',
  };

  const buttonStyles = {
    cursor: 'pointer',
    padding: '8px 16px',
    marginTop: '5%',
    width: '40%',
    backgroundColor: isHovered ? '#4CAF50' : '#007BFF',
    color: 'white',
    border: 'none',
    borderRadius: '4px',
    transition: 'background-color 0.3s ease',
  };

  return (
    <div style={cardStyles}>
      <div style={{ position: 'absolute', top: '8px', right: '8px' }}>
        <FavoriteButton destinationId={pid} />
      </div>
      <h2>{title}</h2>
      <img src={photo_Url} alt={title} style={imgStyles} />
      <div style={textContainerStyles}>
        <p style={priceStyles}>
          Price: <span style={{ fontWeight: 'normal' }}>${price}</span>
        </p>
        <p style={priceStyles}>
          Destination: <span style={{ fontWeight: 'normal' }}>{location}</span>
        </p>
        <p style={priceStyles}>
          Rating: <span style={{ fontWeight: 'normal' }}>{rating}</span>
        </p>
      </div>
      <button
        style={buttonStyles}
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