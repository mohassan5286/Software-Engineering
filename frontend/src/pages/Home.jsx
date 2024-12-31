import React, { useState, useEffect } from "react";
import ProductCard from "../components/ProductCard";
import Slider from "react-slick";
import "./Home.css";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import "@fortawesome/fontawesome-free/css/all.min.css";

export default function Home({ setPid, setInformation, isAdmin }) {
  const [destinations, setDestinations] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [isFormVisible, setIsFormVisible] = useState(false);
  const [formData, setFormData] = useState({
    title: "",
    location: "",
    event: "",
    description: "",
    photoUrl: "",
    price: "",
    tourismType: "", 
  });
  const [formErrors, setFormErrors] = useState({});
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [selectedTourismType, setSelectedTourismType] = useState(""); 

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
    { title: "Religious Tourism 🕌", type: "Religious", data: filterDestinations("Religious") },
    { title: "Medical Tourism 🩺", type: "Medical", data: filterDestinations("Medical") },
    { title: "Cultural Tourism 🗽", type: "Cultural", data: filterDestinations("Cultural") },
    { title: "Nature Tourism 🌋", type: "Nature", data: filterDestinations("Nature") },
    { title: "Coastal Tourism 🌊", type: "Coastal", data: filterDestinations("Coastal") },
    { title: "Sports Tourism ⚽", type: "Sports", data: filterDestinations("Sports") },
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

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleCategorySelection = (type) => {
    setSelectedTourismType(type); 
    setFormData({ ...formData, tourismType: type }); 
    setIsFormVisible(true);
  };

  const validateForm = () => {
    const errors = {};

    if (!formData.title || typeof formData.title !== "string") {
      errors.title = "Title must be a string.";
    }
    if (!formData.location || typeof formData.location !== "string") {
      errors.location = "Location must be a string.";
    }
    if (!formData.event || typeof formData.event !== "string") {
      errors.event = "Event must be a string.";
    }
    if (!formData.description || typeof formData.description !== "string") {
      errors.description = "Description must be a string.";
    }

    const photoUrlRegex = /(https?:\/\/.*\.(?:png|jpg|jpeg|gif|webp|bmp))/i;
    if (!photoUrlRegex.test(formData.photoUrl)) {
      errors.photoUrl = "Photo URL must be a valid image URL.";
    }

    if (isNaN(formData.price) || parseFloat(formData.price) <= 0) {
      errors.price = "Price must be a positive number.";
    }

    setFormErrors(errors);
    return Object.keys(errors).length === 0;
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (validateForm()) {
      setIsSubmitting(true);
      setIsLoading(true);
      const destinationData = {
        title: formData.title,
        location: formData.location,
        event: formData.event,
        description: formData.description,
        photo_Url: formData.photoUrl,
        price: formData.price,
        rating: 0,
        no_of_reviews: 0,
        tourism_type: formData.tourismType,
      };

      console.log("Destination data:", destinationData);

      fetch("http://localhost:8081/destination/add", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(destinationData),
      })
        .then((response) => response.json())
        .then((data) => {
          console.log("Success:", data);
          setIsSubmitting(false);
          setIsLoading(false);
          setIsFormVisible(false);
          alert("Destination added successfully!");
        })
        .catch((error) => {
          console.error("Error:", error);
          alert("Failed to add destination.");
          setIsSubmitting(false);
          setIsLoading(false);
        });
    } else {
      console.log("Form has errors.");
    }
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
      {isFormVisible && (
        <div className="form-overlay">
          <form className="form-container" onSubmit={handleSubmit}>
            <button
              type="button"
              className="close-button"
              onClick={() => setIsFormVisible(false)}
            >
              &times;
            </button>
            <h2>Add New Destination</h2>
            <label>
              Title:
              <input
                type="text"
                name="title"
                value={formData.title}
                onChange={handleInputChange}
                required
              />
              {formErrors.title && <p className="error">{formErrors.title}</p>}
            </label>
            <label>
              Location:
              <input
                type="text"
                name="location"
                value={formData.location}
                onChange={handleInputChange}
                required
              />
              {formErrors.location && <p className="error">{formErrors.location}</p>}
            </label>
            <label>
              Event:
              <input
                type="text"
                name="event"
                value={formData.event}
                onChange={handleInputChange}
                required
              />
              {formErrors.event && <p className="error">{formErrors.event}</p>}
            </label>
            <label>
              Description:
              <textarea
                name="description"
                value={formData.description}
                onChange={handleInputChange}
                required
              ></textarea>
              {formErrors.description && <p className="error">{formErrors.description}</p>}
            </label>
            <label>
              Photo URL:
              <input
                type="url"
                name="photoUrl"
                value={formData.photoUrl}
                onChange={handleInputChange}
                required
              />
              {formErrors.photoUrl && <p className="error">{formErrors.photoUrl}</p>}
            </label>
            <label>
              Price:
              <input
                type="number"
                name="price"
                value={formData.price}
                onChange={handleInputChange}
                min="0"
                step="0.01"
                required
              />
              {formErrors.price && <p className="error">{formErrors.price}</p>}
            </label>
            <button type="submit" disabled={isSubmitting}>
              {isSubmitting ? "Submitting..." : "Add"}
            </button>
          </form>
        </div>
      )}

      {tourismCategories.map((category, index) => (
        <div className="mb-8" key={index} style={{ marginTop: "20px" }}>
          <div className="category-header">
            <h2 className="text-2xl font-bold mb-4" style={{ marginLeft: '10px' }}>{category.title}</h2>
            {isAdmin && (
              <button
                className="add-button"
                onClick={() => handleCategorySelection(category.type)}
              >
                +
              </button>
            )}
          </div>
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
        ...positionStyle,
        position: "absolute",
        top: "50%",
        transform: "translateY(-50%)",
        zIndex: "1",
        cursor: "pointer",
        fontSize: "30px",
        color: "black",
      }}
    >
      <i className={`fas fa-chevron-${direction}`}></i>
    </div>
  );
}