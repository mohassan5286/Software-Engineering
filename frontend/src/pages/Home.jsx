import React, { useState, useEffect } from "react";
import ProductCard from "../components/ProductCard";
import Slider from "react-slick";
import "./Home.css";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import "@fortawesome/fontawesome-free/css/all.min.css";
import axios from "axios";

export default function Home({ setPid, setInformation, isAdmin }) {
  const [destinations, setDestinations] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);
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
        const response = await axios.get(
          "http://localhost:8081/destination/get/all"
        );
        console.log("Fetched destinations:", response.data);
        setDestinations(response.data);
        setError(null);
      } catch (error) {
        console.error("Error fetching destinations:", error);
        setError("Failed to load destinations");
      } finally {
        setIsLoading(false);
      }
    };

    fetchDestinations();
  }, []);

  const filterDestinations = (type) => {
    console.log("Filtering for type:", type);
    console.log("All destinations:", destinations);
    const filtered =
      destinations && Array.isArray(destinations)
        ? destinations.filter((destination) => {
            console.log("Comparing:", destination.tourism_type, "with", type);
            return destination.tourism_type === type;
          })
        : [];
    console.log("Filtered results:", filtered);
    return filtered;
  };

  const tourismCategories = [
    {
      title: "Historical Tourism 🏛️",
      type: "Historical",
      data: filterDestinations("Historical"),
    },
    {
      title: "Religious Tourism 🕌",
      type: "Religious",
      data: filterDestinations("Religious"),
    },
    {
      title: "Medical Tourism 🩺",
      type: "Medical",
      data: filterDestinations("Medical"),
    },
    {
      title: "Cultural Tourism 🗽",
      type: "Cultural",
      data: filterDestinations("Cultural"),
    },
    {
      title: "Nature Tourism 🌋",
      type: "Nature",
      data: filterDestinations("Nature"),
    },
    {
      title: "Coastal Tourism 🌊",
      type: "Coastal",
      data: filterDestinations("Coastal"),
    },
    {
      title: "Sports Tourism ⚽",
      type: "Sports",
      data: filterDestinations("Sports"),
    },
    {
      title: "Adventure Tourism 🏔️",
      type: "Adventure",
      data: filterDestinations("Adventure"),
    },
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
    // Clear error for this field when user starts typing
    if (formErrors[name]) {
      setFormErrors({ ...formErrors, [name]: null });
    }
  };

  const handleCategorySelection = (type) => {
    setSelectedTourismType(type);
    setFormData({ ...formData, tourismType: type });
    setIsFormVisible(true);
  };

  const validateForm = () => {
    const errors = {};

    if (!formData.title?.trim()) {
      errors.title = "Title is required.";
    }
    if (!formData.location?.trim()) {
      errors.location = "Location is required.";
    }
    if (!formData.event?.trim()) {
      errors.event = "Event is required.";
    }
    if (!formData.description?.trim()) {
      errors.description = "Description is required.";
    }

    const photoUrlRegex = /(https?:\/\/.*\.(?:png|jpg|jpeg|gif|webp|bmp))/i;
    if (!photoUrlRegex.test(formData.photoUrl)) {
      errors.photoUrl = "Please enter a valid image URL.";
    }

    if (isNaN(formData.price) || parseFloat(formData.price) <= 0) {
      errors.price = "Price must be a positive number.";
    }

    setFormErrors(errors);
    return Object.keys(errors).length === 0;
  };

  const handleSubmit = async (e) => {
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
        price: parseFloat(formData.price),
        rating: 0,
        no_of_reviews: 0,
        tourism_type: formData.tourismType,
      };

      try {
        const response = await axios.post(
          "http://localhost:8081/destination/add",
          destinationData
        );
        console.log("Success:", response.data);

        // Refresh destinations after adding new one
        const updatedDestinations = await axios.get(
          "http://localhost:8081/destination/get/all"
        );
        setDestinations(updatedDestinations.data);

        // Reset form
        setFormData({
          title: "",
          location: "",
          event: "",
          description: "",
          photoUrl: "",
          price: "",
          tourismType: "",
        });
        setIsFormVisible(false);
        alert("Destination added successfully!");
      } catch (error) {
        console.error("Error:", error);
        alert("Failed to add destination. Please try again.");
      } finally {
        setIsSubmitting(false);
        setIsLoading(false);
      }
    }
  };

  if (isLoading) {
    return (
      <div className="loading-overlay">
        <div className="loading-spinner"></div>
        <p>Loading destinations...</p>
      </div>
    );
  }

  if (error) {
    return <div className="error-message">Error: {error}</div>;
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
            <h2>Add New {selectedTourismType} Destination</h2>

            <label>
              Title:
              <input
                type="text"
                name="title"
                value={formData.title}
                onChange={handleInputChange}
                required
                className={formErrors.title ? "error-input" : ""}
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
                className={formErrors.location ? "error-input" : ""}
              />
              {formErrors.location && (
                <p className="error">{formErrors.location}</p>
              )}
            </label>

            <label>
              Event:
              <input
                type="text"
                name="event"
                value={formData.event}
                onChange={handleInputChange}
                required
                className={formErrors.event ? "error-input" : ""}
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
                className={formErrors.description ? "error-input" : ""}
              ></textarea>
              {formErrors.description && (
                <p className="error">{formErrors.description}</p>
              )}
            </label>

            <label>
              Photo URL:
              <input
                type="url"
                name="photoUrl"
                value={formData.photoUrl}
                onChange={handleInputChange}
                required
                className={formErrors.photoUrl ? "error-input" : ""}
              />
              {formErrors.photoUrl && (
                <p className="error">{formErrors.photoUrl}</p>
              )}
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
                className={formErrors.price ? "error-input" : ""}
              />
              {formErrors.price && <p className="error">{formErrors.price}</p>}
            </label>

            <button
              type="submit"
              disabled={isSubmitting}
              className="submit-button"
            >
              {isSubmitting ? "Adding destination..." : "Add Destination"}
            </button>
          </form>
        </div>
      )}

      {tourismCategories.map((category, index) => (
        <div className="mb-8" key={index} style={{ marginTop: "20px" }}>
          <div className="category-header">
            <h2
              className="text-2xl font-bold mb-4"
              style={{ marginLeft: "10px" }}
            >
              {category.title}
            </h2>
            {isAdmin && (
              <button
                className="add-button"
                onClick={() => handleCategorySelection(category.type)}
                title={`Add new ${category.type} destination`}
              >
                +
              </button>
            )}
          </div>
          {category.data.length > 0 ? (
            <Slider {...sliderSettings}>
              {category.data.map((destination) => (
                <ProductCard
                  key={destination.pid}
                  pid={destination.pid}
                  location={destination.location}
                  photo_Url={destination.photo_Url}
                  price={destination.price}
                  rating={destination.rating}
                  title={destination.title}
                  setPid={setPid}
                  setInformation={setInformation}
                />
              ))}
            </Slider>
          ) : (
            <p className="text-gray-500 text-center py-4">
              No {category.type.toLowerCase()} destinations available yet
            </p>
          )}
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
