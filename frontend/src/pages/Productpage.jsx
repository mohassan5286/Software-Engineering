import React from 'react';
import './ProductPage.css';

export default function ProductPage() {
  const product = {
    id: "xyz98765",
    imgLink: "https://m.media-amazon.com/images/I/513QlbbUAZL._AC_SX569_.jpg",
    price: 2500,
    name: "HP",
    processor: "Ryzen 5",
    storage: 1024,
    type: "SSD",
  };

  return (
    <div className='product-page'>
      

      <div className='product-image'>
        <img src={product.imgLink} alt={`${product.name} product`} />
      </div>
      <div>
      <div className='product-info'>
        <h2>Brand: {product.name}</h2>
        <p style={{ color: 'green' }}>Price: ${product.price}</p>
        <p>Processor: {product.processor}</p>
        <p>Storage: {product.storage}GB {product.type}</p>
      </div>
      <div className='product-viewed'>
        Viewed: <i className="fa-solid fa-eye"></i> 1500
      </div>
      </div>
    </div>
  );
}
