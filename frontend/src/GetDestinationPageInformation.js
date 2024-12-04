import { useNavigate } from 'react-router-dom';

function useDestinationPageInformation() {
  const navigate = useNavigate();

  const getDestinationPageInformation = (destination_name, setInformation, setDestinationName, id) => {
    const url = `http://localhost:8081/destination/get/${id}`;
    
    fetch(url)
      .then(res => {
        if (!res.ok) {
          throw new Error(`HTTP error! Status: ${res.status}`);
        }
        return res.json();
      })
      .then(data => {
        setInformation(data);
        setDestinationName(destination_name);

        // Navigate to the destination page using navigate
        navigate(`/destination-page/${destination_name}`);
      })
      .catch(error => {
        console.error("Error fetching destination page information:", error);
      });
  };

  return { getDestinationPageInformation };
}

export default useDestinationPageInformation;
