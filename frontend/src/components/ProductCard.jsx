import React, { useState } from 'react';
import useDestinationPageInformation from 'D:/downloads/Software Engineering 2/Software-Engineering/frontend/src/GetDestinationPageInformation.js'; // Adjust the import path

const ProductCard = ({ 
  id, 
  image, 
  price, 
  name, 
  rating, 
  location, 
  description, 
  setDestinationName, 
  setInformation 
}) => {
  const [isHovered, setIsHovered] = useState(false);

  // Use the custom hook to get the function for fetching destination information
  const { getDestinationPageInformation } = useDestinationPageInformation();

  const cardStyle = {
    border: '1px solid #ccc',
    borderRadius: '8px',
    padding: '16px',
    margin: "30px",
    maxWidth: '300px',
    color: "black",
    textAlign: 'center',
    background: "white",
    boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)',
  };

  const imgStyle = {
    width: '100%',
    height: '220px',
    borderRadius: '8px',
  };

  const priceStyle = {
    fontWeight: 'bold',
    color: '#4CAF50',
  };

  const buttonStyle = {
    cursor: 'pointer',
    padding: '8px 16px',
    backgroundColor: isHovered ? '#4CAF50' : '#007BFF',  // Changes color on hover
    color: 'white',
    border: 'none',
    borderRadius: '4px',
    transition: 'background-color 0.3s ease',
  };

  const handleSeeMore = () => {
    getDestinationPageInformation(name, setInformation, setDestinationName, id);
  };

  return (
    <div style={cardStyle}>
      <h2>{name}</h2>
      <img src={image} alt={name} style={imgStyle} />
      <p style={priceStyle}>Price: ${price}</p>
      <p>Destination: {location}</p>
      <p>Rating: {rating}</p>
      <p>Description: {description}</p>
      <button
        style={buttonStyle}
        onMouseEnter={() => setIsHovered(true)} // Set hover state on mouse enter
        onMouseLeave={() => setIsHovered(false)} // Reset hover state on mouse leave
        onClick={handleSeeMore} // Call the function on click
      >
        See More
      </button>
    </div>
  );
};

export default ProductCard;
