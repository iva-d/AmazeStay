package mk.ukim.finki.amaze_stay.mockito;

import mk.ukim.finki.amaze_stay.model.Hotel;
import mk.ukim.finki.amaze_stay.service.HotelService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MockitoTest {
    @Test
    public void testFindHotelById() {
        HotelService hotelService = mock(HotelService.class);

        ObjectId sampleObjectId = new ObjectId("6501ddee3724a66b1b377cd6");

        Hotel sampleHotel = new Hotel(41.99252F, 21.465178F, "Hotel Russia", "https://hotelrussia.mk/",
                "ASNOM Blvd 1, Skopje 1000", "+389 2 240 0030", "info@hotelrussia.mk",
                "Good hotel to stay at, close to River Vardar.",
                "https://cf.bstatic.com/xdata/images/hotel/max1024x768/35884211.jpg?k=6def26316f9a346a6502294f27cc5687c940779e8e6b1c75b8fb75d58bd29e30&o=&hp=1",
                "https://www.booking.com/hotel/mk/russia.en-gb.html?aid=356980&label=gog235jc-1DCAsokwFCBnJ1c3NpYUgzWANokwGIAQGYAQm4ARfIAQzYAQPoAQGIAgGoAgO4AoGWh6gGwAIB0gIkNjQxODcwOWYtMGY1MS00ZGY2-lWI5NjQtZmY4YWNjMTIyYTBh2AIE4AIB&sid=04425f807bde8c7e47274532cb85e166&dist=0&keep_landing=1&sb_price_type=total&type=total&");

        when(hotelService.findHotelById(sampleObjectId)).thenReturn(sampleHotel);

        Hotel result = hotelService.findHotelById(sampleObjectId);

        verify(hotelService).findHotelById(sampleObjectId);

        assertNotNull(result);
        assertEquals(41.99252F, result.getLatitude(), 0.001);
        assertEquals(21.465178F, result.getLongitude(), 0.001);
        assertEquals("Hotel Russia", result.getName());
    }

    @Test
    public void testDeleteHotel() {
        HotelService hotelService = Mockito.mock(HotelService.class);

        ObjectId hotelIdToDelete = new ObjectId("650c393df0b11e22f760f9c1");

        doNothing().when(hotelService).deleteHotelById(hotelIdToDelete);

        hotelService.deleteHotelById(hotelIdToDelete);

        verify(hotelService).deleteHotelById(hotelIdToDelete);
    }

    @Test
    public void testEditHotelName() {
        HotelService hotelService = Mockito.mock(HotelService.class);

        ObjectId hotelIdToEdit = new ObjectId("63a5b0f8ff3a2f5a6669d62f");

        String updatedName = "Vila Enhalon";

        // Redirect standard output to capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            Hotel updatedHotel = new Hotel(41.169857F, 20.710436F, updatedName, "https://www.facebook.com/Enhalon",
                    "Ezerski Lozja 86, Struga, Republic of Macedonia", "070243534", "enhalon@gmail.com",
                    "Relaxing traditional family hotel by the lakeshore. WELCOME at our cosy home!",
                    "https://lh3.googleusercontent.com/p/AF1QipMlg3oKwBEJOSjQGmyB2JRGGCrY447Y6Hy7pQYq=s1600-w400",
                    "https://www.booking.com/city/mk/struga.en.html?aid=303948;label=struga-nOtTCu5Lo2Z8CJ9_f0BX1gS540957183540:pl:ta:p1:p2:ac:ap:neg:fi:tikwd-4961486709:lp2807:li:dec:dm;ws=&gclid=EAIaIQobChMI8LaGuqbC-wIVi-5RCh1-CQIEEAAYASAAEgLljfD_BwE");

            when(hotelService.edit(eq(hotelIdToEdit), anyFloat(), anyFloat(), eq(updatedName), anyString(),
                    anyString(), anyString(), anyString(), anyString(), anyString(), anyString()))
                    .thenReturn(updatedHotel);

            Hotel result = hotelService.edit(hotelIdToEdit, 0.0F, 0.0F, updatedName, "", "", "", "", "", "", "");

            verify(hotelService).edit(eq(hotelIdToEdit), anyFloat(), anyFloat(), eq(updatedName), anyString(),
                    anyString(), anyString(), anyString(), anyString(), anyString(), anyString());

            assertNotNull(result);
            assertEquals(updatedName, result.getName());

            System.out.println("Hotel Name Before Editing: " + "Vila ENHALON");
            System.out.println("Hotel Name After Editing: " + result.getName());

        } finally {
            System.setOut(originalOut);
        }

        String consoleOutput = outputStream.toString();
        System.out.println(consoleOutput);
    }
}
