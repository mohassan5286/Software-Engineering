import React, { useState, useEffect } from 'react';
import useDestinationPageInformation from '../GetDestinationPageInformation.js'; // Adjust the import path

// const ProductCard = ({ id, image, price, name, rating, location, description, setDestinationName, 
const ProductCard = ({   description,
  event,
  id,
  location,
  no_of_reviews,
  photo_Url, // Changed from image to photoUrl
  price,
  rating,
  title, setDestinationName, 
  setInformation  }) => {
  const [isHovered, setIsHovered] = useState(false);
  const { getDestinationPageInformation } = useDestinationPageInformation();
  useEffect(() => {
    // console.log(id, image, price, name, rating,   location, description)
  },[]);
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
    getDestinationPageInformation(title, setInformation, setDestinationName, id);
  };
  return (
    <div onClick={() => {
      console.log("you clicked it :)");
    }} style={cardStyle}>
      <h2>{title}</h2>
      <img src={photo_Url} alt={title} style={imgStyle} />
      <p style={priceStyle}>Price: ${price}</p>
      <p>destination: {location}</p>
      <p>{rating}</p>
      <p> {description}</p>
      <button
        style={buttonStyle}
        onMouseEnter={() => setIsHovered(true)} // Set hover state on mouse enter
        onMouseLeave={() => setIsHovered(false)} // Reset hover state on mouse leave
        onClick={handleSeeMore}     
        >
        See more..
      </button>
    </div>
  );
};

export default ProductCard;