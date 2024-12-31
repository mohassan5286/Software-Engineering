package com.backend.backend;

import com.backend.backend.Controller.DestinationController;
import com.backend.backend.Entity.Destination;
import com.backend.backend.Repository.DestinationRepository;
import com.backend.backend.Service.DestinationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.AllArgsConstructor;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class DestinationServiceTest {

    //    What we are going to mock
    @Mock
    private DestinationRepository destinationRepository;

    //  Destination Book is accepting DestinationRepository as a mock
    @InjectMocks
    private DestinationService destinationService;

    Destination pyramids = new Destination(
            "Pyramids of Giza",
            "Giza, Egypt",
            "Sphinx Light and Sound Show - A captivating evening experience where the history of ancient Egypt is narrated with lights and music, bringing the pyramids and Sphinx to life. The show takes place every evening year-round, starting after sunset.",
            "The Pyramids of Giza are ancient architectural marvels and one of the Seven Wonders of the Ancient World. Visitors can explore the tombs of pharaohs, enjoy camel rides in the desert, and learn about the fascinating history of Egypt.",
            "https://images5.alphacoders.com/458/458484.jpg",
            120,
            4.8,
            500,
            "Historical"
    );

    Destination greatWall = new Destination(
            "Great Wall of China",
            "Beijing, China",
            "Great Wall Marathon - An exhilarating annual marathon held on the third Saturday of May, where participants run along the wall, enjoying breathtaking views and the challenge of its steep climbs.",
            "The Great Wall of China is a UNESCO World Heritage site stretching over 13,000 miles. This iconic structure offers stunning views, historic watchtowers, and insights into the defense strategies of ancient China.",
            "https://images4.alphacoders.com/143/143584.jpg",
            180,
            4.9,
            400,
            "Historical/Adventure"
    );

    Destination eiffelTower = new Destination(
            "Eiffel Tower",
            "Paris, France",
            "Eiffel Tower Light Show - A nightly event where the tower sparkles with thousands of lights. The show happens every evening on the hour from sunset to 1:00 AM.",
            "The Eiffel Tower is an iconic symbol of Paris and one of the most visited monuments in the world. Tourists can climb to the top for panoramic views of the city or dine in its luxurious restaurants.",
            "https://images.alphacoders.com/960/96031.jpg",
            250,
            4.7,
            600,
            "Cultural"
    );

    Destination machuPicchu = new Destination(
            "Machu Picchu",
            "Cusco Region, Peru",
            "Inti Raymi Festival - A grand celebration of the Sun God, featuring traditional Inca rituals, music, and colorful parades in the nearby city of Cusco. This festival is held annually on June 24th.",
            "Machu Picchu is an ancient Inca city perched high in the Andes Mountains. Its stone terraces, temples, and breathtaking mountain views make it a must-visit for history and nature enthusiasts.",
            "https://picfiles.alphacoders.com/266/266632.jpg",
            300,
            4.9,
            400,
            "Historical/Adventure"
    );

    Destination sydneyOperaHouse = new Destination(
            "Sydney Opera House",
            "Sydney, Australia",
            "Sydney Music Festival - A two-week event held annually in January, featuring world-class musical performances and cultural showcases at the Opera House.",
            "The Sydney Opera House is a modern architectural marvel and a center for performing arts. It is located on the picturesque Sydney Harbor, perfect for sightseeing and photography.",
            "https://picfiles.alphacoders.com/275/275104.jpg",
            200,
            4.8,
            350,
            "Cultural"
    );

    Destination everestBaseCamp = new Destination(
            "Mount Everest Base Camp",
            "Khumbu, Nepal",
            "Himalayan Hiking Festival - Celebrating trekking culture with guided hikes, cultural performances, and environmental workshops, this event takes place annually in early October.",
            "The Mount Everest Base Camp trek offers adventure and stunning Himalayan landscapes. It’s a bucket-list destination for trekkers and mountaineers.",
            "https://images7.alphacoders.com/546/546508.jpg",
            500,
            4.9,
            200,
            "Adventure"
    );

    Destination tajMahal = new Destination(
            "Taj Mahal",
            "Agra, India",
            "Taj Mahotsav - A cultural extravaganza showcasing Indian art, craft, music, and dance, held annually from February 18th to 27th near the Taj Mahal.",
            "The Taj Mahal is a white marble mausoleum and a symbol of eternal love. Visitors are mesmerized by its intricate carvings, lush gardens, and the reflection of the monument in its central pool.",
            "https://picfiles.alphacoders.com/284/284792.jpg",
            150,
            4.8,
            550,
            "Cultural"
    );

    Destination christRedeemer = new Destination(
            "Christ the Redeemer",
            "Rio de Janeiro, Brazil",
            "Rio Carnival - A world-famous event with samba parades, vibrant costumes, and street parties celebrating Brazilian culture. The Carnival takes place annually, beginning on the Friday before Ash Wednesday and lasting for five days.",
            "Christ the Redeemer is an iconic statue atop Mount Corcovado, offering panoramic views of Rio. It’s a symbol of faith and a must-see landmark in Brazil.",
            "https://picfiles.alphacoders.com/218/218747.jpg",
            220,
            4.7,
            300,
            "Cultural"
    );

    Destination goldenGateBridge = new Destination(
            "Golden Gate Bridge",
            "San Francisco, USA",
            "Bridge to Bridge Run - A popular annual running event starting at the Golden Gate Bridge, held every October on the second Sunday.",
            "The Golden Gate Bridge is an iconic engineering marvel in San Francisco. Visitors can walk or bike across the bridge, enjoying views of the bay and nearby landmarks.",
            "https://images7.alphacoders.com/418/418009.jpg",
            50,
            4.7,
            600,
            "Cultural/Adventure"
    );

    Destination santoriniCaldera = new Destination(
            "Santorini Caldera",
            "Santorini, Greece",
            "Volcanic Wine Festival - An annual festival celebrating the unique volcanic wines of Santorini, held in late September. It features wine tastings, food, and music.",
            "Santorini Caldera offers breathtaking views of the Aegean Sea and the iconic white-washed architecture of Greece. It’s a favorite destination for honeymooners and photographers.",
            "https://images.alphacoders.com/541/541445.jpg",
            300,
            4.9,
            320,
            "Romantic"
    );

    List<Destination> destinations = new ArrayList<>(Arrays.asList(greatWall, pyramids, eiffelTower, machuPicchu, sydneyOperaHouse));

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllRecordsHappy1() {
        // Arrange
        Mockito.when(destinationRepository.findAll()).thenReturn(destinations);

        // Act
        List<Destination> result = destinationService.getDestinationAll();

        // Assert
        assertEquals(5, result.size());
    }

    @Test
    public void getRecordHappy1() {
        // Arrange
        Mockito.when(destinationRepository.findById("0")).thenReturn(Optional.ofNullable(destinations.get(0)));

        // Act
        Destination result = destinationService.getDestinationById("0");

        // Assert
        assertEquals("Great Wall of China", result.getTitle());
    }

    @Test
    public void getRecordSad1() {
        // Arrange
        Mockito.when(destinationRepository.findById(Mockito.any(String.class))).thenThrow(NoSuchElementException.class);

        // Act
        Destination result = destinationService.getDestinationById("-1");

        // Assert
        assertEquals(null, result.getTitle());
    }
    @Test
    public void searchByTitle_shouldReturnMatchingDestinations() {
        // Using actual mock data for "Eiffel Tower"
        Mockito.when(destinationRepository.findByTitleContainingIgnoreCase("Eiffel")).thenReturn(List.of(eiffelTower));
        List<Destination> result = destinationService.searchByTitle("Eiffel");
        assertEquals(1, result.size());
        assertEquals("Eiffel Tower", result.get(0).getTitle());
    }

    @Test
    public void filterDestinations_byLocationTourismTypeAndPrice() {
        // Filtering based on actual mock data (e.g., "Historical")
        Mockito.when(destinationRepository.findByLocationAndTourism_typeAndPriceLessThanEqual("Giza, Egypt", "Historical", 250.0))
                .thenReturn(List.of(pyramids));
        List<Destination> result = destinationService.filterDestinations("Giza, Egypt", "Historical", 250.0);
        assertEquals(1, result.size());
        assertEquals("Pyramids of Giza", result.get(0).getTitle());
    }

    @Test
    public void filterDestinations_byLocationAndPrice() {
        // Filtering based on actual mock data
        Mockito.when(destinationRepository.findByLocationAndPriceLessThanEqual("Cusco Region, Peru", 300.0))
                .thenReturn(List.of(machuPicchu));
        List<Destination> result = destinationService.filterDestinations("Cusco Region, Peru", null, 300.0);
        assertEquals(1, result.size());
        assertEquals("Machu Picchu", result.get(0).getTitle());
    }

    @Test
    public void filterDestinations_byTourismTypeAndPrice() {
        // Filtering by "Historical" tourism type and max price
        Mockito.when(destinationRepository.findByTourism_typeAndPriceLessThanEqual("Historical", 500.0))
                .thenReturn(List.of(pyramids, machuPicchu));
        List<Destination> result = destinationService.filterDestinations(null, "Historical", 500.0);
        assertEquals(2, result.size());
    }

    @Test
    public void filterDestinations_byLocationAndTourismType() {
        // Filtering based on "Paris, France" and "Cultural"
        Mockito.when(destinationRepository.findByLocationAndTourism_type("Paris, France", "Cultural"))
                .thenReturn(List.of(eiffelTower));
        List<Destination> result = destinationService.filterDestinations("Paris, France", "Cultural", null);
        assertEquals(1, result.size());
        assertEquals("Eiffel Tower", result.get(0).getTitle());
    }

    @Test
    public void filterDestinations_byLocation() {
        // Filtering by "San Francisco, USA"
        Mockito.when(destinationRepository.findByLocation("San Francisco, USA"))
                .thenReturn(List.of(goldenGateBridge));
        List<Destination> result = destinationService.filterDestinations("San Francisco, USA", null, null);
        assertEquals(1, result.size());
        assertEquals("Golden Gate Bridge", result.get(0).getTitle());
    }

    @Test
    public void filterDestinations_byTourismType() {
        // Filtering by "Adventure" tourism type
        Mockito.when(destinationRepository.findByTourism_type("Adventure"))
                .thenReturn(List.of(greatWall, everestBaseCamp));
        List<Destination> result = destinationService.filterDestinations(null, "Adventure", null);
        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(d -> d.getTitle().equals("Great Wall of China")));
        assertTrue(result.stream().anyMatch(d -> d.getTitle().equals("Mount Everest Base Camp")));
    }

    @Test
    public void filterDestinations_byMaxPrice() {
        // Filtering by max price 250
        Mockito.when(destinationRepository.findByPriceLessThanEqual(250.0))
                .thenReturn(List.of(pyramids, greatWall, eiffelTower));
        List<Destination> result = destinationService.filterDestinations(null, null, 250.0);
        assertEquals(3, result.size());
    }

    @Test
    public void filterDestinations_noFilters_shouldReturnAll() {
        // Testing with no filters
        Mockito.when(destinationRepository.findAll()).thenReturn(destinations);
        List<Destination> result = destinationService.filterDestinations(null, null, null);
        assertEquals(5, result.size());
    }

    @Test
    public void filterDestinations_noMatchingResults() {
        // Case where no matching destinations found
        Mockito.when(destinationRepository.findByLocationAndTourism_typeAndPriceLessThanEqual("Non-existent Location", "Fictional", 100.0))
                .thenReturn(Collections.emptyList());
        List<Destination> result = destinationService.filterDestinations("Non-existent Location", "Fictional", 100.0);
        assertEquals(0, result.size());
    }

    @Test
    public void testAddDestination() {
        Destination destination = new Destination("Santorini Caldera", "Santorini, Greece", "Volcanic Wine Festival",
                "Beautiful caldera destination with volcanic views", "photoUrl", 300.0, 4.9, 320, "Romantic");
        destination.setPid("12");

        Mockito.when(destinationRepository.save(Mockito.any(Destination.class))).thenReturn(destination);

        Destination result = destinationService.saveDestination(destination);

        assertEquals("12", result.getPid());
        assertEquals("Santorini Caldera", result.getTitle());
    }
}
