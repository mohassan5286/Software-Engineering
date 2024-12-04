import './App.css';
import DestinationPageInformation from './DestinationPageInformation.js';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Navbar from './Navbar.js';
import { useState } from 'react';
import Header from './components/Header.jsx';
import Home from './pages/Home.jsx';
import Login from './pages/Login.jsx';
import Nopage from './pages/Nopage.jsx';
import Cart from './pages/Cart.jsx';
import Signup from "./pages/Signup.jsx";
import Productpage from './pages/Productpage';

function App() {
  // State to manage the destination name
  const [destination_name, setDestinationName] = useState("");
  
  // State to manage destination information
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
      <Header />
      <Routes>
        <Route 
          path="/" 
          element={
            <Home 
              setDestinationName={setDestinationName} 
              setInformation={setInformation} 
            />
          } 
        />
        <Route 
          path="/home" 
          element={
            <Home 
              setDestinationName={setDestinationName} 
              setInformation={setInformation} 
            />
          } 
        />
        <Route path="/login" element={<Login />} />
        <Route path="/sold" element={<Login />} />
        <Route path="/orders" element={<Login />} />
        <Route path="/sell" element={<Login />} />
        <Route path="/cart" element={<Cart />} />
        <Route path="/signup" element={<Signup />} />
        <Route 
          path="/destination-page/:destination_name" 
          element={<DestinationPageInformation information={information} />} 
        />
        <Route path="/*" element={<Nopage />} />
      </Routes>
    </Router>
  );
}

export default App;
