import { useEffect, useState } from "react";
import axios from "axios";
import "./BookingPage.css";

const BookingPage = () => {
  const [bookingHistory, setBookingHistory] = useState([]);
  const [destinations, setDestinations] = useState([]);

  useEffect(() => {
    const fetchBookingHistory = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8081/booking/all/${localStorage.getItem("user_id")}`
        );
        setBookingHistory(response.data);
      } catch (error) {
        console.error("Booking API request failed:", error);
      }
    };

    const fetchDestinations = async () => {
      try {
        const response = await fetch(
          "http://localhost:8081/destination/get/all"
        );
        const data = await response.json();

        let temp = {};
        data.forEach((destination) => {
          temp[destination.pid] = destination.title;
        });

        setDestinations(temp);
      } catch (error) {
        console.error("Error fetching destinations:", error);
      }
    };

    fetchBookingHistory();
    fetchDestinations();
  }, []);

  // Delete a booking
  const deleteBooking = async (bookingId) => {
    try {
      await axios.delete(
        `http://localhost:8081/booking/remove/${bookingId.pid}/${bookingId.uid}`
      );

      setBookingHistory((prevBookings) =>
        prevBookings.filter((booking) => booking.id.pid !== bookingId.pid)
      );
      alert("Booking deleted successfully!");
    } catch (error) {
      console.error("Error deleting booking:", error);
      alert("An error occurred while deleting the booking.");
    }
  };

  return (
    <div className="booking-page">
      <ul className="booking-history">
        {bookingHistory.map((booking, index) => (
          <li key={index} className="booking-item">
            <h3>Booking {index + 1}</h3>
            <button
              className="delete-button"
              onClick={() => deleteBooking(booking.id)}
            >
              Delete
            </button>
            <div className="detail">
              <span>Destination Title:</span>
              <span className="value">{destinations[booking.id.pid]}</span>
            </div>
            <div className="detail">
              <span>No. of Persons:</span>
              <span className="value">{booking.no_of_persons}</span>
            </div>
            <div className="detail">
              <span>Date:</span>
              {new Date(booking.bookingDate).toLocaleDateString()}
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default BookingPage;
