import { useNavigate } from 'react-router-dom';

function useDestinationPageInformation() {
  const navigate = useNavigate();

  const getDestinationPageInformation = (setInformation, setId, pid) => {
    const url = `http://localhost:8081/destination/get/${pid}`;
    
    fetch(url)
      .then(res => {
        if (!res.ok) {
          throw new Error(`HTTP error! Status: ${res.status}`);
        }
        return res.json();
      })
      .then(data => {
        setInformation(data);
        setId(pid);

        navigate(`/destination-page/${pid}`);
      })
      .catch(error => {
        console.error("Error fetching destination page information:", error);
      });
  };

  return { getDestinationPageInformation };
}

export default useDestinationPageInformation;
