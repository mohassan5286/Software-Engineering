import React, { useState, useEffect } from "react";
import ProductCard from "../components/ProductCard";
import Slider from "react-slick";
import "./Home.css";
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import '@fortawesome/fontawesome-free/css/all.min.css';

export default function Home() {
  // State to hold destinations data
  const [destinations, setDestinations] = useState([]);
  
  // Fetch destinations from backend when the component mounts
  useEffect(() => {
    // Replace this with your actual backend URL
    fetch("https://your-backend-url.com/api/destinations")
      .then((response) => response.json())
      .then((data) => setDestinations(data))
      .catch((error) => console.error("Error fetching destinations:", error));
  }, []);

  // Filter destinations by tourism type
  const religiousDestinations = destinations.filter(
    (destination) => destination.tourismType === "Religious"
  );
  const medicalDestinations = destinations.filter(
    (destination) => destination.tourismType === "Medical"
  );
  const culturalDestinations = destinations.filter(
    (destination) => destination.tourismType === "Cultural"
  );
  const natureDestinations = destinations.filter(
    (destination) => destination.tourismType === "Nature"
  );
  const coastalDestinations = destinations.filter(
    (destination) => destination.tourismType === "Coastal"
  );
  const sportsDestinations = destinations.filter(
    (destination) => destination.tourismType === "Sports"
  );

  // Slider settings
  const settings = {
    dots: true,
    infinite: false,
    speed: 500,
    slidesToShow: 4,
    slidesToScroll: 1,
    nextArrow: <NextArrow />,
    prevArrow: <PrevArrow />,
    responsive: [
      {
        breakpoint: 1024,
        settings: {
          slidesToShow: 3,
          slidesToScroll: 1,
        },
      },
      {
        breakpoint: 600,
        settings: {
          slidesToShow: 2,
          slidesToScroll: 1,
        },
      },
      {
        breakpoint: 480,
        settings: {
          slidesToShow: 1,
          slidesToScroll: 1,
        },
      },
    ],
  };

  return (
    <div className="p-4">
      {/* Render all tourism categories with Slider */}
      <div className="mb-8">
        <h1 className="text-2xl font-bold mb-4">Religious Tourism 🕌</h1>
        <Slider {...settings}>
          {religiousDestinations.map((product) => (
            <ProductCard key={product.id} {...product} />
          ))}
        </Slider>
      </div>
      
      {/* Medical Tourism */}
      <div className="mb-8">
        <h1 className="text-2xl font-bold mb-4">Medical Tourism 🩺</h1>
        <Slider {...settings}>
          {medicalDestinations.map((product) => (
            <ProductCard key={product.id} {...product} />
          ))}
        </Slider>
      </div>

      {/* Cultural Tourism */}
      <div className="mb-8">
        <h1 className="text-2xl font-bold mb-4">Cultural Tourism 🗽</h1>
        <Slider {...settings}>
          {culturalDestinations.map((product) => (
            <ProductCard key={product.id} {...product} />
          ))}
        </Slider>
      </div>

      {/* Nature Tourism */}
      <div className="mb-8">
        <h1 className="text-2xl font-bold mb-4">Nature Tourism 🌋</h1>
        <Slider {...settings}>
          {natureDestinations.map((product) => (
            <ProductCard key={product.id} {...product} />
          ))}
        </Slider>
      </div>

      {/* Coastal Tourism */}
      <div className="mb-8">
        <h1 className="text-2xl font-bold mb-4">Coastal Tourism 🌊</h1>
        <Slider {...settings}>
          {coastalDestinations.map((product) => (
            <ProductCard key={product.id} {...product} />
          ))}
        </Slider>
      </div>

      {/* Sports Tourism */}
      <div className="mb-8">
        <h1 className="text-2xl font-bold mb-4">Sports Tourism ⚽</h1>
        <Slider {...settings}>
          {sportsDestinations.map((product) => (
            <ProductCard key={product.id} {...product} />
          ))}
        </Slider>
      </div>
    </div>
  );
}

// Custom Arrow Components
function NextArrow(props) {
  const { className, style, onClick } = props;
  return (
    <div
      className={className}
      style={{
        ...style,
        display: "block",
        right: "10px",
        color: "blue",
        zIndex: 1,
      }}
      onClick={onClick}
    >
      <i className="fas fa-chevron-right"></i>
    </div>
  );
}

function PrevArrow(props) {
  const { className, style, onClick } = props;
  return (
    <div
      className={className}
      style={{
        ...style,
        display: "block",
        left: "10px",
        color: "blue",
        zIndex: 1,
      }}
      onClick={onClick}
    >
      <i className="fas fa-chevron-left"></i>
    </div>
  );
}
