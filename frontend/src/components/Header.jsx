import React, { useState } from 'react';
import "./header.css";
import { useNavigate } from 'react-router-dom';
import axios from 'axios'; // Import Axios for HTTP requests

function Header() {
  const navigate = useNavigate();
  const [searchTerm, setSearchTerm] = useState('');
  const [filters, setFilters] = useState({
    title: '', // Title filter is now the main search term
    category: '',
    location: '',
    maxPrice: ''
  });
  const [isFilterVisible, setIsFilterVisible] = useState(true);

  const NavigateToHome = () => {
    navigate('/'); 
  };

  const NavigateToLogin = () => {
    navigate('/login'); 
  };
  const handleSearch = async (e) => {
    e.preventDefault();
  
    if (filters.title.trim()) {
      const searchParams = {
        title: filters.title.trim(),
        category: filters.category.trim() || undefined,
        location: filters.location.trim() || undefined,
        maxPrice: filters.maxPrice.trim() || undefined
      };
  
      // Clean up undefined values from searchParams
      const cleanedSearchParams = Object.fromEntries(
        Object.entries(searchParams).filter(([key, value]) => value !== undefined)
      );
  
      try {
        // Fetch search results from backend
        const response = await axios.get('http://localhost:8081/destination/search', {
          params: cleanedSearchParams
        });
  
        if (response.data.length > 0) {
          navigate('/search', {
            state: { searchResults: response.data } // Passing searchResults here
          });
        } else {
          alert('No results found');
        }
      } catch (error) {
        console.error('Search API request failed:', error);
        alert('An error occurred while fetching search results');
      }
    } else {
      alert('Please enter a title to search');
    }
  };
  
  const handleFilterChange = (e) => {
    const { name, value } = e.target;
    setFilters(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const applyFilters = () => {
    const activeFilters = Object.entries(filters)
      .filter(([key, value]) => value.trim() !== '')
      .reduce((acc, [key, value]) => ({ ...acc, [key]: value }), {});

    if (Object.keys(activeFilters).length > 0) {
      navigate('/search', { 
        state: { searchParams: activeFilters } // Pass the active filters to the search page
      });
    } else {
      alert('Please select at least one filter');
    }
  };

  const toggleFilterVisibility = () => {
    setIsFilterVisible(!isFilterVisible);
  };

  const sitename = "Pathport";

  return (
    <>
      <header className="site-header">
        <div className="site-identity">
          <a onClick={NavigateToHome} style={{ cursor: 'pointer' }}>
            <img 
              src="https://static.vecteezy.com/system/resources/thumbnails/007/642/395/small/laptop-electronic-portable-computer-notebook-line-pop-art-potrait-logo-colorful-design-illustration-vector.jpg" 
              alt={sitename} 
            />
          </a>
          <h1><a onClick={NavigateToHome} style={{ cursor: 'pointer' }}>{sitename}</a></h1>
        
          {/* Inline Search Component */}
          <div className="inline-search-container">
            <form className="inline-search-form" onSubmit={handleSearch}>
              <input 
                type="text" 
                placeholder="Search by title..." 
                value={filters.title}  // Use filters.title here instead of searchTerm
                onChange={(e) => setFilters({...filters, title: e.target.value})}  // Update title filter
              />
              <button type="submit">
                <i className="fa-solid fa-search"></i>
              </button>
            </form>
          </div>
        </div>
        
        <nav className="site-navigation">
          <ul className="header-links">
            <li><a onClick={NavigateToHome} style={{ cursor: 'pointer' }}><i className="fa-solid fa-house"></i><span className="link-text">Home</span></a></li>
            <li><a href="/wishlist"><i className="fa-solid fa-star"></i><span className="link-text">WishList</span></a></li>
            <li><a href="#"><i className="fa-solid fa-plane"></i><span className="link-text">Bookings</span></a></li>
            <li><a href="#"><i className="fa-solid fa-handshake"></i><span className="link-text">Sell</span></a></li>
            <li><a href="#"><i className="fa-solid fa-cart-shopping"></i><span className="link-text">Cart</span></a></li>
            <li><a onClick={NavigateToLogin}><i className="fa-solid fa-user"></i><span className="link-text">Log in</span></a></li>
          </ul>
        </nav>
      </header>

      <div className="search-filter-toggle">
        <button className="toggle-filters-btn" onClick={toggleFilterVisibility}>
          <i className={`fas fa-chevron-${isFilterVisible ? 'up' : 'down'}`}></i>
          Add Filters
        </button>
      </div>

      <div className={`search-filter-container ${isFilterVisible ? '' : 'hidden'}`}>
        <div className="search-filter-wrapper">
          <div className="filter-container">
            <div className="filter-item">
              <label>Title</label>
              <input 
                type="text" 
                name="title"
                placeholder="Enter title"
                value={filters.title}
                onChange={handleFilterChange}
              />
            </div>

            <div className="filter-item">
              <label>Category</label>
              <select 
                name="category"
                value={filters.category}
                onChange={handleFilterChange}
              >
                <option value="">All Categories</option>
                <option value="hotel">Hotel</option>
                <option value="flight">Flight</option>
                <option value="tour">Tour</option>
              </select>
            </div>

            <div className="filter-item">
              <label>Location</label>
              <input 
                type="text" 
                name="location"
                placeholder="Enter location"
                value={filters.location}
                onChange={handleFilterChange}
              />
            </div>

            <div className="filter-item">
              <label>Max Price</label>
              <input 
                type="number" 
                name="maxPrice"
                placeholder="Maximum Price"
                value={filters.maxPrice}
                onChange={handleFilterChange}
              />
            </div>

            <button 
              className="apply-filters"
              onClick={applyFilters}
            >
              Apply Filters
            </button>
          </div>
        </div>
      </div>
    </>
  );
}

export default Header;
