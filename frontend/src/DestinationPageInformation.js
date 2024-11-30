import { useParams } from 'react-router-dom';

const DestinationPageInformation = () => {
  const { id } = useParams();
  const numericId = Number(id);

  let destination_pages_information = [
    {
      id: 1,
      name: "Great Wall of China",
      photo_url: "https://images4.alphacoders.com/143/thumb-1920-143584.jpg",
      location: "China",
      description:
        "The Great Wall of China is a series of ancient fortifications made of various materials, such as stone, brick, tamped earth, and wood, that stretch across northern China. Originally built to protect Chinese states and empires from nomadic invasions, the Great Wall is one of the most iconic landmarks in the world, known for its historical significance and architectural marvel.",
      prices_of_traveling: "45-100 CNY",
      rate_as_stars: 4.7,
      number_of_reviews: 120000,
    },
    {
      id: 2,
      name: "Eiffel Tower",
      photo_url: "https://burst.shopifycdn.com/photos/the-eiffel-tower-reaches-high-against-the-city-of-paris.jpg?exif=0&iptc=0",
      location: "Paris, France",
      description:
        "The Eiffel Tower, an iron lattice tower on the Champ de Mars in Paris, is one of the most recognizable structures in the world. Designed by engineer Gustave Eiffel, it was initially a temporary exhibit for the 1889 World's Fair and has become one of the most visited paid monuments in the world.",
      prices_of_traveling: "16-25 EUR",
      rate_as_stars: 4.8,
      number_of_reviews: 350000,
    },
  ];

  const information = destination_pages_information.find(
    (destination_page_information) => destination_page_information.id === numericId
  );

  return (
    <div>
      
      {information && (
        <div className="destination-page-information">
          <img 
            src={information.photo_url} 
            alt={information.name} 
            className="destination-page-image" 
          />
          <div className="destination-page-content">
            <h2 className="destination-page-title">{information.name}</h2>
            <p><strong>Location:</strong> {information.location}</p>
            <p><strong>Description:</strong> {information.description}</p>
            <p><strong>Prices of Traveling:</strong> {information.prices_of_traveling}</p>
            <p><strong>Rating:</strong> {information.rate_as_stars}   <img width="17" height="17" src="https://img.icons8.com/external-flaticons-flat-flat-icons/64/external-star-web-flaticons-flat-flat-icons-3.png" alt="external-star-web-flaticons-flat-flat-icons-3"/></p>
            <p><strong>Number of Reviews:</strong> {information.number_of_reviews}</p>
          </div>
        </div>
      )}
    </div>
  );
}  

export default DestinationPageInformation;