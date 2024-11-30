import './App.css';
import DestinationPageInformation from './DestinationPageInformation.js';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import Navbar from './Navbar.js';

function App() {
  return (
    <Router>
      <Navbar />
      <Switch>
        <Route exact path = '/destination-page/:id'>
          <DestinationPageInformation />
        </Route>
      </Switch>
    </Router>
  );
}

export default App;
