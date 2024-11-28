package ee.mihkel.film_rental;

import ee.mihkel.film_rental.controller.RentalController;
import ee.mihkel.film_rental.entity.Rental;
import ee.mihkel.film_rental.service.RentalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class RentalControllerTests {

    private MockMvc mvc;

    @Mock
    RentalService rentalService;

    @InjectMocks
    RentalController rentalController;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(rentalController).build();
    }

    @Test
    void getPoints() throws Exception {
       MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get("/get-points")).andReturn().getResponse();
       assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void getPoints404() throws Exception {
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get("/get-poi")).andReturn().getResponse();
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    void startRentalWithoutBody_ReturnsBadRequest() throws Exception {
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.post("/start-rental")).andReturn().getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    void startRentalWithoutBody_ReturnsMethodNotAllowed() throws Exception {
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get("/start-rental")).andReturn().getResponse();
        assertEquals(HttpStatus.METHOD_NOT_ALLOWED.value(), response.getStatus());
    }

    @Test
    void startRentalWithBody_ReturnsCreated() throws Exception {
        //Rental rental = new Rental();
        String requestBody = "{\"films\": []}";
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders
                .post("/start-rental")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andReturn().getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }
}
