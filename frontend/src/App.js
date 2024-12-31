import './App.css';
import { BrowserRouter as Router, Route, Routes, useLocation } from 'react-router-dom';
import { useState, useEffect } from 'react';
import SearchResults from './pages/SearchResults.jsx';

import Header from './components/Header.jsx';
import Home from './pages/Home.jsx';
import Login from './pages/Login.jsx';
import Nopage from './pages/Nopage.jsx';
import DestinationPageInformation from './DestinationPageInformation.js';
import BookingPage from './BookingPage.js';

function App() {
  const [user_id, setUserId] = useState(localStorage.getItem('user_id') || '');
  const [pid, setPid] = useState(localStorage.getItem('pid') || '');
  const [information, setInformation] = useState(
    JSON.parse(localStorage.getItem('information')) || getDefaultInformation()
  );

  const location = useLocation(); // Use useLocation to track the current path
  const shouldRenderHeader = location.pathname !== '/' && location.pathname !== '/login'; // Update based on location.pathname

  useEffect(() => {
    localStorage.setItem('user_id', user_id);
    localStorage.setItem('pid', pid);
    localStorage.setItem('information', JSON.stringify(information));
  }, [user_id, pid, information]);

  return (
    <>
      {shouldRenderHeader && <Header />}
      <Routes>
      <Route path="/search" element={<SearchResults />} />
        <Route path="/home" element={<Home setPid={setPid} setInformation={setInformation} />} />
        <Route path="/login" element={<Login setUserId={setUserId} />} />
        <Route path="/" element={<Login setUserId={setUserId} />} />
        <Route path='/booking-page' element={<BookingPage />} />
        <Route path={`/destination-page/:${pid}`} element={<DestinationPageInformation information={information} />} />
        <Route path="/*" element={<Nopage />} />
      </Routes>
    </>
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

export default function RootApp() {
  return (
    <Router>
      <App />
    </Router>
  );
}