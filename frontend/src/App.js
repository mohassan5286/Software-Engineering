import './App.css';
import { BrowserRouter as Router, Route, Routes, useLocation, Navigate } from 'react-router-dom';
import { useState, useEffect } from 'react';
import SearchResults from './pages/SearchResults.jsx';
import Header from './components/Header.jsx';
import Home from './pages/Home.jsx';
import Login from './pages/Login.jsx';
import Nopage from './pages/Nopage.jsx';
import Wishlist from './pages/Wishlist.jsx';
import DestinationPageInformation from './DestinationPageInformation.js';

function App() {
  const [user_id, setUserId] = useState(sessionStorage.getItem('user_id') || '');
  const [isAdmin, setIsAdmin] = useState(
    sessionStorage.getItem('isAdmin') === 'true'
      ? true
      : sessionStorage.getItem('isAdmin') === 'false'
      ? false
      : null
  );
  const [pid, setPid] = useState(sessionStorage.getItem('pid') || '');
  const [information, setInformation] = useState(() =>
    JSON.parse(sessionStorage.getItem('information')) || getDefaultInformation()
  );

  const location = useLocation();
  // const shouldRenderHeader = location.pathname !== '/' && location.pathname !== '/login';
  const noHeaderRoutes = ['/', '/login'];
  const shouldRenderHeader = !noHeaderRoutes.includes(location.pathname);

  const handleLogin = (userId, role) => {
    const isAdminRole = role === 'admin';
    setUserId(userId);
    setIsAdmin(isAdminRole);
    sessionStorage.setItem('user_id', userId);
    sessionStorage.setItem('isAdmin', isAdminRole);
  };

  const handleLogout = () => {
    setUserId('');
    setIsAdmin(null);
    setPid('');
    setInformation(getDefaultInformation());
    sessionStorage.clear();
  };

  // Redirect logic for unauthorized access
  useEffect(() => {
    if (!user_id && location.pathname !== '/login') {
      window.location.href = '/login';
    }
  }, [user_id, location.pathname]);

  useEffect(() => {
    sessionStorage.setItem('pid', pid);
  }, [pid]);

  useEffect(() => {
    sessionStorage.setItem('information', JSON.stringify(information));
  }, [information]);

  return (
    <>
      {shouldRenderHeader && <Header />}
      <Routes>
        {/* <Route path="/search" element={<SearchResults />} />
        <Route path="/home" element={<Home setPid={setPid} setInformation={setInformation} />} />
        <Route path="/login" element={<Login setUserId={setUserId} />} />
        <Route path="/" element={<Login setUserId={setUserId} />} />
        <Route path="/wishlist" element={<Wishlist setUserId={setUserId}/>} />
        <Route path={`/destination-page/:${pid}`} element={<DestinationPageInformation information={information} />} /> */}
        <Route
          path="/search"
          element={
            user_id ? <SearchResults /> : <Navigate to="/login" replace />
          }
        />
        <Route
          path="/home"
          element={
            user_id ? (
              <Home setPid={setPid} setInformation={setInformation} isAdmin={isAdmin} />
            ) : (
              <Navigate to="/login" replace />
            )
          }
        />
        <Route
          path="/login"
          element={<Login setUserId={handleLogin} />}
        />
        <Route
          path="/"
          element={<Login setUserId={handleLogin} />}
        />
        <Route path="/wishlist" element={<Wishlist setUserId={setUserId}/>} />
        <Route
          path="/destination-page/:pid"
          element={
            user_id ? (
              <DestinationPageInformation information={information} />
            ) : (
              <Navigate to="/login" replace />
            )
          }
        />
        <Route path="/*" element={<Nopage />} />
      </Routes>
      {user_id && (
        <button 
        onClick={handleLogout} 
        style={{
          position: 'fixed', 
          bottom: '20px', 
          right: '20px', 
          backgroundColor: '#007BFF', 
          color: 'white', 
          border: 'none', 
          borderRadius: '8px', 
          padding: '10px 15px', 
          cursor: 'pointer', 
          boxShadow: '0px 4px 6px rgba(0, 0, 0, 0.1)',
          fontSize: '16px'
        }}
        onMouseOver={(e) => e.target.style.backgroundColor = '#0056b3'}
        onMouseOut={(e) => e.target.style.backgroundColor = '#007BFF'}
      >
        Logout
      </button>
      )}
    </>
  );
}

function getDefaultInformation() {
  return {
    photo_Url: '',
    title: '',
    location: '',
    tourism_type: '',
    description: '',
    event: '',
    price: '',
    rating: '',
    no_of_reviews: ''
  };
}

export default function RootApp() {
  return (
    <Router>
      <App />
    </Router>
  );
}
