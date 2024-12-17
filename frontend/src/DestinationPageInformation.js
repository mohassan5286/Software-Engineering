const DestinationPageInformation = ({ information }) => {
  if (!information || !information.photo_Url) {
    return null;
  }

  return (
    <div className="destination-page-information">
      <img 
        src={information.photo_Url} 
        alt={information.title} 
        className="destination-page-image" 
      />
      <div className="destination-page-content">
        <h2 className="destination-page-title">{information.title}</h2>
        <p><strong>Location:</strong> {information.location}</p>
        <p><strong>Tourism Type:</strong> {information.tourism_type}</p>
        <p><strong>Description:</strong> {information.description}</p>
        <p><strong>Events:</strong> {information.event}</p>
        <p><strong>Prices of Traveling:</strong> {information.price}</p>
        <p><strong>Rating:</strong> {information.rating}   
          <img width="17" height="17" src="https://img.icons8.com/external-flaticons-flat-flat-icons/64/external-star-web-flaticons-flat-flat-icons-3.png" alt="star icon" />
        </p>
        <p><strong>Number of Reviews:</strong> {information.no_of_reviews}</p>
      </div>
    </div>
  );
};

export default DestinationPageInformation;
