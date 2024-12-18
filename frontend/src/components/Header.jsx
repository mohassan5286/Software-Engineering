import React, { useState } from 'react';
import "./header.css";
import { useNavigate } from 'react-router-dom';

function Header() {
  const navigate = useNavigate();
  const [searchTerm, setSearchTerm] = useState('');

  const NavigateToHome = () => {
    navigate('/home'); 
  };

  const NavigateToLogin = () => {
    navigate('/login'); 
  };

  const handleSearch = (e) => {
    e.preventDefault();
    console.log('Searching for:', searchTerm);
  };

  const sitename = "Pathport";

  return (
    <header className="site-header">
      <div className="site-identity">
        <a onClick={NavigateToHome} style={{ cursor: 'pointer' }}>
          <img 
            src="https://static.vecteezy.com/system/resources/thumbnails/007/642/395/small/laptop-electronic-portable-computer-notebook-line-pop-art-potrait-logo-colorful-design-illustration-vector.jpg" 
            alt={sitename} 
          />
        </a>
        <h1><a onClick={NavigateToHome} style={{ cursor: 'pointer' }}>{sitename}</a></h1>
      </div>
      
      <nav className="site-navigation">
        <ul className="header-links">
          <li><a onClick={NavigateToHome} style={{ cursor: 'pointer' }}><i className="fa-solid fa-house"></i><span className="link-text">Home</span></a></li>
          <li><a href="#"><i className="fa-solid fa-star"></i><span className="link-text">WishList</span></a></li>
          <li><a href="#"><i className="fa-solid fa-plane"></i><span className="link-text">Bookings</span></a></li>
          <li><a href="#"><i className="fa-solid fa-handshake"></i><span className="link-text">Sell</span></a></li>
          <li><a href="#"><i className="fa-solid fa-cart-shopping"></i><span className="link-text">Cart</span></a></li>
          <li><a onClick={NavigateToLogin}><i className="fa-solid fa-user"></i><span className="link-text">Log in</span></a></li>
        </ul>
      </nav>

      <div className="search-container">
        <form onSubmit={handleSearch} style={{ display: 'flex', alignItems: 'center' }}>
          <input 
            placeholder="Search for your travel" 
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
          /> 
          <button type="submit">
            <i className="fa-solid fa-search"></i>
          </button>
        </form>
      </div>
      
    </header>
  );
}

export default Header;