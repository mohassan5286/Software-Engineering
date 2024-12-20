import React, { useState, useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import axios from 'axios';

function SearchResults() {
  const location = useLocation();
  const [results, setResults] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const searchResults = location.state?.searchResults || [];

    // If the search results are passed via state, use them directly
    if (searchResults.length > 0) {
      setResults(searchResults);
      setLoading(false);
    } else {
      // If no results are passed, fetch again
      const fetchResults = async () => {
        const searchParams = location.state?.searchParams || {};
        
        try {
          setLoading(true);

          const queryParams = new URLSearchParams();

          // Prepare query parameters for the backend request
          if (searchParams.title) queryParams.append('title', searchParams.title);
          if (searchParams.name) queryParams.append('name', searchParams.name);
          if (searchParams.category) queryParams.append('category', searchParams.category);
          if (searchParams.location) queryParams.append('location', searchParams.location);
          if (searchParams.maxPrice) queryParams.append('maxPrice', searchParams.maxPrice);

          // Make the request to the backend API
          const response = await axios.get(`http://localhost:8081/destination/filter?${queryParams.toString()}`);
          setResults(response.data);
        } catch (error) {
          console.error('Search failed:', error);
        } finally {
          setLoading(false);
        }
      };

      fetchResults();
    }
  }, [location.state]);

  if (loading) {
    return <div>Loading search results...</div>;
  }

  return (
    <div style={{ padding: '20px' }}>
      <h1>Search Results</h1>
      {results.length === 0 ? (
        <p>No results found</p>
      ) : (
        <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fill, minmax(250px, 1fr))', gap: '20px' }}>
          {results.map(item => (
            <div 
              key={item.id} 
              style={{
                border: '1px solid #ddd',
                borderRadius: '8px',
                padding: '15px',
                boxShadow: '0 4px 6px rgba(0,0,0,0.1)'
              }}
            >
              <h2>{item.title}</h2>
              <p><strong>Category:</strong> {item.category}</p>
              <p><strong>Location:</strong> {item.location}</p>
              <p><strong>Price:</strong> ${item.price}</p>
              <p>{item.description}</p>
              <button 
                style={{
                  backgroundColor: '#1A5F7A',
                  color: 'white',
                  border: 'none',
                  padding: '10px 15px',
                  borderRadius: '5px',
                  cursor: 'pointer'
                }}
              >
                View Details
              </button>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default SearchResults;
