import React, { useState, useEffect } from "react";
import ProductCard from "../components/ProductCard";
import Slider from "react-slick";
import "./Home.css";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import "@fortawesome/fontawesome-free/css/all.min.css";

export default function Home({ setPid, setInformation }) {
  const [destinations, setDestinations] = useState([]);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const fetchDestinations = async () => {
      try {
        const response = await fetch("http://localhost:8081/destination/get/all");
        const data = await response.json();
        setDestinations(data);
      } catch (error) {
        console.error("Error fetching destinations:", error);
      }
    };

    const timeout = setTimeout(() => {
      setIsLoading(false);
    }, 2000);

    fetchDestinations();

    return () => clearTimeout(timeout);
  }, []);

  const filterDestinations = (type) =>
    destinations.filter((destination) => destination.tourism_type === type);

  const tourismCategories = [
    { title: "Religious Tourism 🕌", data: filterDestinations("Religious") },
    { title: "Medical Tourism 🩺", data: filterDestinations("Medical") },
    { title: "Cultural Tourism 🗽", data: filterDestinations("Cultural") },
    { title: "Nature Tourism 🌋", data: filterDestinations("Nature") },
    { title: "Coastal Tourism 🌊", data: filterDestinations("Coastal") },
    { title: "Sports Tourism ⚽", data: filterDestinations("Sports") },
  ];

  const sliderSettings = {
    dots: true,
    infinite: false,
    speed: 500,
    slidesToShow: 4,
    slidesToScroll: 1,
    nextArrow: <Arrow direction="right" />,
    prevArrow: <Arrow direction="left" />,
    responsive: [
      { breakpoint: 1024, settings: { slidesToShow: 3, slidesToScroll: 1 } },
      { breakpoint: 600, settings: { slidesToShow: 2, slidesToScroll: 1 } },
      { breakpoint: 480, settings: { slidesToShow: 1, slidesToScroll: 1 } },
    ],
  };

  if (isLoading) {
    return (
      <div className="loading-overlay">
        <div className="loading-spinner"></div>
        <p>Loading...</p>
      </div>
    );
  }

  return (
    <div className="p-4">
      {tourismCategories.map((category, index) => (
        <div className="mb-8" key={index}>
          <h2 className="text-2xl font-bold mb-4" style={{ marginLeft: '10px' }}>{category.title}</h2>
          <Slider {...sliderSettings}>
            {category.data.map((product) => (
              <ProductCard
                key={product.pid}
                pid={product.pid}
                location={product.location}
                photo_Url={product.photo_Url}
                price={product.price}
                rating={product.rating}
                title={product.title}
                setPid={setPid}
                setInformation={setInformation}
              />
            ))}
          </Slider>
        </div>
      ))}
    </div>
  );
}


function Arrow({ direction, ...props }) {
  const positionStyle =
    direction === "right" ? { right: "10px" } : { left: "10px" };

  return (
    <div
      {...props}
      style={{
        ...props.style,
        ...positionStyle,
        display: "block",
        color: "blue",
        zIndex: 1,
        cursor: "pointer",
        fontSize: "20px",
      }}
    >
      <i className={`fas fa-chevron-${direction}`} style={{ fontSize: "20px" }}></i>
    </div>
  );
}