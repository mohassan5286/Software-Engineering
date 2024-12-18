import { useNavigate } from 'react-router-dom';

function useDestinationPageInformation() {
  const navigate = useNavigate();

  const getDestinationPageInformation = async (setInformation, setPid, pid) => {
    const url = `http://localhost:8081/destination/get/${pid}`;
    
    try {
      const res = await fetch(url);
      if (!res.ok) {
        throw new Error(`HTTP error! Status: ${res.status}`);
      }
      
      const data = await res.json();
      setInformation(data);
      setPid(pid);

      navigate(`/destination-page/${pid}`);
    } catch (error) {
      console.error("Error fetching destination page information:", error);
    }
  };

  return { getDestinationPageInformation };
}

export default useDestinationPageInformation;
