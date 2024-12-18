import React, { useState } from 'react';

const DestinationPageInformation = ({ information }) => {

  const [numberOfPersons, setNumberOfPersons] = useState(1);
  
  if (!information || !information.photo_Url) {
    return null;
  }

  const incrementCounter = () => {
    setNumberOfPersons(numberOfPersons+1);
  };
  
  const decrementCounter = () => {
    if (numberOfPersons > 1) {
      setNumberOfPersons(numberOfPersons-1);
    }
  };

  const bookTrip = () => {

    const booking = {
      "pid": information.pid,
      "uid": "U456",
      "bookingDate": (new Date(Date.UTC(2024, 11, 16, 12, 0, 0))).toISOString(), 
      "status": "Pending",
      "no_of_persons": numberOfPersons
    }

    fetch('http://localhost:8081/booking', {
      method: 'POST',
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(booking)
    }).then(() => {
      alert("Booking successful!");
    })
  }

  return (
    <div className="destination-page-information">
      <img 
        src={information.photo_Url} 
        alt={information.title} 
        className="destination-page-image" 
      />
      <div className="destination-page-content">
        <h2 className="destination-page-title">{information.title}
        <button className="destination-page-book-button" onClick={bookTrip}>Book Now</button>
        </h2>
        <p><strong>Location:</strong> {information.location}</p>
        <p><strong>Tourism Type:</strong> {information.tourism_type}</p>
        <p><strong>Description:</strong> {information.description}</p>
        <p><strong>Events:</strong> {information.event}</p>
        <p><strong>Prices of Traveling:</strong> {information.price}</p>
        <p><strong>Rating:</strong> {information.rating}   
          <img width="17" height="17" src="https://img.icons8.com/external-flaticons-flat-flat-icons/64/external-star-web-flaticons-flat-flat-icons-3.png" alt="star icon" />
        </p>
        <p><strong>Number of Reviews:</strong> {information.no_of_reviews}</p>
        <div className="person-counter">
            <p><strong>Number of Persons:</strong></p>
            <button className="decrement-btn" onClick={decrementCounter}>-</button>
            <span className="counter-value">{numberOfPersons}</span>
            <button className="increment-btn" onClick={incrementCounter}>+</button>
        </div>
      </div>
    </div>
  );
};

export default DestinationPageInformation;
