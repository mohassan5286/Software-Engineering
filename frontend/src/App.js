import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { useState, useEffect } from 'react';

import Header from './components/Header.jsx';
import Home from './pages/Home.jsx';
import Login from './pages/Login.jsx';
import Nopage from './pages/Nopage.jsx';
import Cart from './pages/Cart.jsx';
import Signup from './pages/Signup.jsx';
import DestinationPageInformation from './DestinationPageInformation.js';

function App() {
  const [pid, setPid] = useState(localStorage.getItem('pid') || '');
  const [information, setInformation] = useState(
    JSON.parse(localStorage.getItem('information')) || getDefaultInformation()
  );

  useEffect(() => {
    localStorage.setItem('pid', pid);
    localStorage.setItem('information', JSON.stringify(information));
  }, [pid, information]);

  return (
    <Router>
      <Header />
      <Routes>
        <Route path="/" element={<Home setPid={setPid} setInformation={setInformation} />} />
        <Route path="/login" element={<Login />} />
        <Route path="/sold" element={<Login />} />
        <Route path="/orders" element={<Login />} />
        <Route path="/sell" element={<Login />} />
        <Route path="/cart" element={<Cart />} />
        <Route path="/signup" element={<Signup />} />
        <Route path={`/destination-page/:${pid}`} element={<DestinationPageInformation information={information} />} />
        <Route path="/*" element={<Nopage />} />
      </Routes>
    </Router>
  );
}

function getDefaultInformation() {
  return {
    "photo_Url": "",
    "title": "",
    "location": "",
    "tourism_type": "",
    "description": "",
    "event": "",
    "price": "",
    "rating": "",
    "no_of_reviews": ""
  };
}

export default App;
