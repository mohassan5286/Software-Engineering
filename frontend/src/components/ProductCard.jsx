import React, { useState } from 'react';

const ProductCard = ({ id, image, price, name, rating, location, description }) => {
  const [isHovered, setIsHovered] = useState(false);

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

  return (
    <div onClick={() => {
      console.log("you clicked it :)");
    }} style={cardStyle}>
      <h2>{name}</h2>
      <img src={image} alt={name} style={imgStyle} />
      <p style={priceStyle}>Price: ${price}</p>
      <p>destination: {location}</p>
      <p>{rating}</p>
      <p> {description}</p>
      <button
        style={buttonStyle}
        onMouseEnter={() => setIsHovered(true)} // Set hover state on mouse enter
        onMouseLeave={() => setIsHovered(false)} // Reset hover state on mouse leave
      >
        See more..
      </button>
    </div>
  );
};

export default ProductCard;
