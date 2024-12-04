import React, { useState, useEffect } from "react";
import ProductCard from "../components/ProductCard";
import Slider from "react-slick";
import "./Home.css";
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import '@fortawesome/fontawesome-free/css/all.min.css';

export default function Home({ setDestinationName, setInformation }) {
  // State to hold destinations data
  const [destinations, setDestinations] = useState([]);
  
  // Fetch destinations from backend when the component mounts
  useEffect(() => {
    fetch("https://your-backend-url.com/api/destinations")
      .then((response) => response.json())
      .then((data) => setDestinations(data))
      .catch((error) => console.error("Error fetching destinations:", error));
  }, []);

  // Filter destinations by tourism type
  const filterDestinations = (type) =>
    destinations.filter((destination) => destination.tourismType === type);

  const religiousDestinations = filterDestinations("Religious");
  const medicalDestinations = filterDestinations("Medical");
  const culturalDestinations = filterDestinations("Cultural");
  const natureDestinations = filterDestinations("Nature");
  const coastalDestinations = filterDestinations("Coastal");
  const sportsDestinations = filterDestinations("Sports");

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
      { breakpoint: 1024, settings: { slidesToShow: 3, slidesToScroll: 1 } },
      { breakpoint: 600, settings: { slidesToShow: 2, slidesToScroll: 1 } },
      { breakpoint: 480, settings: { slidesToShow: 1, slidesToScroll: 1 } },
    ],
  };

  return (
    <div className="p-4">
      {/* Render tourism categories with Slider */}
      {[
        { title: "Religious Tourism 🕌", data: religiousDestinations },
        { title: "Medical Tourism 🩺", data: medicalDestinations },
        { title: "Cultural Tourism 🗽", data: culturalDestinations },
        { title: "Nature Tourism 🌋", data: natureDestinations },
        { title: "Coastal Tourism 🌊", data: coastalDestinations },
        { title: "Sports Tourism ⚽", data: sportsDestinations },
      ].map((category, index) => (
        <div className="mb-8" key={index}>
          <h1 className="text-2xl font-bold mb-4">{category.title}</h1>
          <Slider {...settings}>
            {category.data.map((product) => (
              <ProductCard
                key={product.id}
                {...product}
                setDestinationName={setDestinationName}
                setInformation={setInformation}
              />
            ))}
          </Slider>
        </div>
      ))}
    </div>
  );
}

// Custom Arrow Components
function NextArrow(props) {
  const { className, style, onClick } = props;
  return (
    <div
      className={className}
      style={{ ...style, display: "block", right: "10px", color: "blue", zIndex: 1 }}
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
      style={{ ...style, display: "block", left: "10px", color: "blue", zIndex: 1 }}
      onClick={onClick}
    >
      <i className="fas fa-chevron-left"></i>
    </div>
  );
}
