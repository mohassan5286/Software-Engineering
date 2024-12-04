import './App.css';
import DestinationPageInformation from './DestinationPageInformation.js';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Navbar from './Navbar.js';
import { useState } from 'react';

function App() {
  // Path the setDestinationName function to the card component
  const [destination_name, setDestinationName] = useState("");
  // Path the setinformation function to the card component
  const [information, setInformation] = useState({
    "photo_Url": "",
    "title": "",
    "location": "",
    "tourism_type": "",
    "description": "",
    "event": "",
    "price": "",
    "rating": "",
    "no_of_reviews": ""
  });

  return (
    <Router>
      <Navbar />
      <Switch>
        <Route exact path={`/destination-page/${destination_name}`}>
          <DestinationPageInformation information = {information} />
        </Route>
      </Switch>
    </Router>
  );
}

export default App;
