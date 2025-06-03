package io.wulfcodes.hotel.resource.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@SpringBootTest
public class HotelResourceTests {

    @Autowired
    private HotelResource hotelResource;

    /**
     * Method to perform smoke tests
     *
     * @throws Exception
     */
    @Test
    public void contextLoads() throws Exception {
        Assertions.assertThat(hotelResource).isNotNull();
    }

}
