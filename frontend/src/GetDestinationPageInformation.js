function getDestinationPageInformation(destination_name, setInformation, setDestinationName) {
    const url = `http://localhost:8080?name=${encodeURIComponent(destination_name)}`;

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
        })
        .catch(error => {
            console.error("Error fetching destination page information:", error);
        });
}
