import React from "react";
import ProductCard from "../components/ProductCard";
import Slider from "react-slick";
import "./Home.css"
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import '@fortawesome/fontawesome-free/css/all.min.css';

export default function Home() {
  // Updated Tourism Products Data with one extra card in each category
  const religiousTourismProducts = [
    {
      id: 1,
      name: "Jerusalem Holy Land Tour",
      description: "Explore sacred sites in the Holy City",
      price: 1500,
      image: "https://images.unsplash.com/photo-1551742327-5900f98fe35f", // Jerusalem image from Unsplash
      rating: 4.8,
      location: "Israel"
    },
    {
      id: 2,
      name: "Mecca Pilgrimage Package",
      description: "Comprehensive Hajj and Umrah experience",
      price: 2000,
      image: "https://www.big5constructsaudi.com/wp-content/uploads/sites/7/2023/07/500x320-industry-news-25.png", // Mecca image
      rating: 4.9,
      location: "Saudi Arabia"
    },
    {
      id: 3,
      name: "Mount Sinai and Moses' Mountain Tour",
      description: "Visit the sacred Mount Sinai, where Prophet Moses received the Ten Commandments.",
      price: 1800,
      image: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNyqL7f4LLZ5ijn-hKnYR1tW2eQxTjnzHYpg&s", // Mount Sinai image from Unsplash
      rating: 4.7,
      location: "Egypt"
    },
    {
      id: 4,
      name: "Medina Pilgrimage Tour",
      description: "Visit the second holiest city in Islam, home to the Prophet's Mosque.",
      price: 1700,
      image: "https://media.licdn.com/dms/image/D4D12AQG2Xl_Tqv1Q2Q/article-cover_image-shrink_720_1280/0/1707685031199?e=2147483647&v=beta&t=XzxiAuPKeCZiwYLOAoPP3dw84RZ5Dvutl4DmNgJ-bqU ", // Medina image from Unsplash
      rating: 4.8,
      location: "Saudi Arabia"
    },
    {
      id: 5,
      name: "Al-Aqsa Mosque and Dome of the Rock Tour",
      description: "Explore the third holiest site in Islam and the Dome of the Rock in Jerusalem.",
      price: 1600,
      image: "https://almukarramah.com/cdn/shop/articles/masjid_al-aqsa.jpg?v=1639921762", // Al-Aqsa image from Unsplash
      rating: 4.9,
      location: "Israel"
    },
    {
      id: 6,
      name: "The Great Mosque of Al-Nuri Tour",
      description: "Visit the historic mosque in Mosul, Iraq, known for its iconic leaning minaret.",
      price: 1500,
      image: "https://images.unsplash.com/photo-1603907027884-f07c68e189c9", // Al-Nuri Mosque image from Unsplash
      rating: 4.6,
      location: "Iraq"
    },
    {
      id: 7,
      name: "Kabah and Tawaf Experience",
      description: "Perform the sacred Tawaf around the Kaaba during Hajj or Umrah.",
      price: 2200,
      image: "https://images.unsplash.com/photo-1607128124705-2c1fe51bc2c9", // Kaaba image from Unsplash
      rating: 4.9,
      location: "Saudi Arabia"
    },
    {
      id: 8,
      name: "Samarra Mosque and Al-Askari Shrine Tour",
      description: "Explore the spiritual significance of the Al-Askari Shrine in Samarra, Iraq.",
      price: 1800,
      image: "https://images.unsplash.com/photo-1570625449782-42f7f8e7e059", // Samarra Mosque image from Unsplash
      rating: 4.7,
      location: "Iraq"
    }
  ];
  

  const medicalTourismProducts = [
    {
      id: 1,
      name: "Bangkok Wellness Clinic",
      description: "Advanced medical treatments and recovery",
      price: 3000,
      image: "/api/placeholder/300/200",
      rating: 4.7,
      location: "Thailand"
    },
    {
      id: 2,
      name: "Costa Rica Dental Vacation",
      description: "High-quality dental care with tropical relaxation",
      price: 2500,
      image: "/api/placeholder/300/200",
      rating: 4.5,
      location: "Costa Rica"
    },
    {
      id: 3,
      name: "South Korea Cosmetic Surgery Tour",
      description: "World-class aesthetic procedures",
      price: 4000,
      image: "/api/placeholder/300/200",
      rating: 4.9,
      location: "South Korea"
    },
    {
      id: 4,
      name: "Germany Rehabilitation Retreat",
      description: "Comprehensive medical recovery programs",
      price: 3500,
      image: "/api/placeholder/300/200",
      rating: 4.6,
      location: "Germany"
    },
    {
      id: 5,
      name: "India Ayurvedic Treatment Package",
      description: "Traditional holistic healing therapies",
      price: 2200,
      image: "/api/placeholder/300/200",
      rating: 4.8,
      location: "India"
    }
  ];

  const culturalTourismProducts = [
    {
      id: 1,
      name: "Kyoto Cultural Immersion",
      description: "Traditional Japanese arts and customs",
      price: 2200,
      image: "/api/placeholder/300/200",
      rating: 4.8,
      location: "Japan"
    },
    {
      id: 2,
      name: "Machu Picchu Heritage Tour",
      description: "Explore Incan civilization and archaeology",
      price: 1900,
      image: "/api/placeholder/300/200",
      rating: 4.7,
      location: "Peru"
    },
    {
      id: 3,
      name: "Istanbul Cultural Expedition",
      description: "Byzantine and Ottoman cultural highlights",
      price: 1700,
      image: "/api/placeholder/300/200",
      rating: 4.6,
      location: "Turkey"
    },
    {
      id: 4,
      name: "Cairo Historical Journey",
      description: "Ancient Egyptian culture and monuments",
      price: 2000,
      image: "/api/placeholder/300/200",
      rating: 4.5,
      location: "Egypt"
    },
    {
      id: 5,
      name: "Athens Ancient Wonders Tour",
      description: "Explore Greek history and archaeology",
      price: 2300,
      image: "/api/placeholder/300/200",
      rating: 4.8,
      location: "Greece"
    }
  ];

  const natureTourismProducts = [
    {
      id: 1,
      name: "Amazon Rainforest Expedition",
      description: "Biodiversity and ecological exploration",
      price: 2500,
      image: "/api/placeholder/300/200",
      rating: 4.9,
      location: "Brazil"
    },
    {
      id: 2,
      name: "New Zealand Wildlife Adventure",
      description: "Unique flora and fauna experience",
      price: 2200,
      image: "/api/placeholder/300/200",
      rating: 4.7,
      location: "New Zealand"
    },
    {
      id: 3,
      name: "Iceland Northern Lights Tour",
      description: "Volcanic landscapes and aurora borealis",
      price: 1800,
      image: "/api/placeholder/300/200",
      rating: 4.8,
      location: "Iceland"
    },
    {
      id: 4,
      name: "Galapagos Islands Eco-Tour",
      description: "Unique wildlife and conservation experience",
      price: 3000,
      image: "/api/placeholder/300/200",
      rating: 4.9,
      location: "Ecuador"
    },
    {
      id: 5,
      name: "Patagonia Adventure Tour",
      description: "Explore glaciers and unique landscapes",
      price: 2800,
      image: "/api/placeholder/300/200",
      rating: 4.7,
      location: "Argentina"
    }
  ];

  const coastalTourismProducts = [
    {
      id: 1,
      name: "Maldives Luxury Retreat",
      description: "Overwater bungalows and pristine beaches",
      price: 4000,
      image: "/api/placeholder/300/200",
      rating: 4.9,
      location: "Maldives"
    },
    {
      id: 2,
      name: "Great Barrier Reef Dive Expedition",
      description: "Marine life and underwater exploration",
      price: 2800,
      image: "/api/placeholder/300/200",
      rating: 4.7,
      location: "Australia"
    },
    {
      id: 3,
      name: "Bali Beach and Culture Package",
      description: "Tropical paradise with cultural experiences",
      price: 2000,
      image: "/api/placeholder/300/200",
      rating: 4.6,
      location: "Indonesia"
    },
    {
      id: 4,
      name: "Caribbean Island Hopping",
      description: "Multiple tropical destinations in one trip",
      price: 3500,
      image: "/api/placeholder/300/200",
      rating: 4.8,
      location: "Caribbean"
    },
    {
      id: 5,
      name: "Phuket Island Escape",
      description: "Tropical islands and stunning beaches",
      price: 2700,
      image: "/api/placeholder/300/200",
      rating: 4.7,
      location: "Thailand"
    }
  ];

  const sportsTourismProducts = [
    {
      id: 1,
      name: "FIFA World Cup Experience",
      description: "Complete football tournament package",
      price: 5000,
      image: "/api/placeholder/300/200",
      rating: 4.9,
      location: "Qatar"
    },
    {
      id: 2,
      name: "Olympics VIP Tour",
      description: "Premium access to international sporting event",
      price: 6000,
      image: "/api/placeholder/300/200",
      rating: 4.8,
      location: "Paris"
    },
    {
      id: 3,
      name: "Golf Championship Getaway",
      description: "Prestigious golf tournament and resort stay",
      price: 3500,
      image: "/api/placeholder/300/200",
      rating: 4.7,
      location: "Scotland"
    },
    {
      id: 4,
      name: "Australian Open Tennis Vacation",
      description: "Grand Slam tournament and Melbourne experience",
      price: 4000,
      image: "/api/placeholder/300/200",
      rating: 4.6,
      location: "Australia"
    },
    {
      id: 5,
      name: "Formula 1 Grand Prix Tour",
      description: "Exclusive access to the Formula 1 races",
      price: 7500,
      image: "/api/placeholder/300/200",
      rating: 4.9,
      location: "Monaco"
    }
  ];

  // Slider settings
  let settings = {
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
        }
      },
      {
        breakpoint: 600,
        settings: {
          slidesToShow: 2,
          slidesToScroll: 1,
        }
      },
      {
        breakpoint: 480,
        settings: {
          slidesToShow: 1,
          slidesToScroll: 1,
        }
      }
    ]
  };

  return (
    <div className="p-4">
      {/* Render all tourism categories with Slider */}
      <div className="mb-8">
        <h1 className="text-2xl font-bold mb-4">Religious Tourism 🕌</h1>
        <Slider {...settings}>
          {religiousTourismProducts.map((product) => (
            <ProductCard key={product.id} {...product} />
          ))}
        </Slider>
      </div>
      {/* Repeat for other categories */}
      <div className="mb-8">
        <h1 className="text-2xl font-bold mb-4">Medical Tourism 🩺</h1>
        <Slider {...settings}>
          {medicalTourismProducts.map((product) => (
            <ProductCard key={product.id} {...product} />
          ))}
        </Slider>
      </div>
      <div className="mb-8">
        <h1 className="text-2xl font-bold mb-4">Cultural Tourism 🗽</h1>
        <Slider {...settings}>
          {culturalTourismProducts.map((product) => (
            <ProductCard key={product.id} {...product} />
          ))}
        </Slider>
      </div>
      <div className="mb-8">
        <h1 className="text-2xl font-bold mb-4">Nature Tourism 🌋</h1>
        <Slider {...settings}>
          {natureTourismProducts.map((product) => (
            <ProductCard key={product.id} {...product} />
          ))}
        </Slider>
      </div>
      <div className="mb-8">
        <h1 className="text-2xl font-bold mb-4">Coastal Tourism 🌊</h1>
        <Slider {...settings}>
          {coastalTourismProducts.map((product) => (
            <ProductCard key={product.id} {...product} />
          ))}
        </Slider>
      </div>
      <div className="mb-8">
        <h1 className="text-2xl font-bold mb-4">Sports Tourism ⚽</h1>
        <Slider {...settings}>
          {sportsTourismProducts.map((product) => (
            <ProductCard key={product.id} {...product} />
          ))}
        </Slider>
      </div>
    </div>
  );
}

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
