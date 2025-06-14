## Writing/Calling REST APIs using Java

### Calling REST APIs from an external service
<img width="834" alt="Screenshot 2025-06-14 at 7 51 52 PM" src="https://github.com/user-attachments/assets/3fbeb6cd-3531-441b-841f-db0c11c4b046" />

```
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class PaginatedApiFetcher {

    private static final String BASE_URL = "https://api.example.com/items?page=";

    public static void main(String[] args) {
        int page = 1;
        boolean hasMore = true;

        while (hasMore) {
            try {
                String urlString = BASE_URL + page;
                URL url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder responseBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    responseBuilder.append(line);
                }
                reader.close();

                String response = responseBuilder.toString();

                // Parse JSON
                JSONObject json = new JSONObject(response);
                JSONArray items = json.getJSONArray("data"); // adjust this based on your API response

                for (int i = 0; i < items.length(); i++) {
                    JSONObject item = items.getJSONObject(i);
                    System.out.println("Item: " + item.toString());
                }

                // Check if more pages exist, some APIs expose this as a part of their response
                hasMore = json.optBoolean("hasMore", false); // or check items.length() > 0
                page++;

            } catch (Exception e) {
                e.printStackTrace();
                hasMore = false;
            }
        }
    }
}
```

#### If the API uses authorization, use:
```
conn.setRequestProperty("Authorization", "Bearer YOUR_API_KEY");
```

#### Deserializing the data and converting it to a class Object say Item
```
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        // Replace with your actual JSON source
        String json = new String(Files.readAllBytes(Paths.get("data.json")));

        Item response = mapper.readValue(json, Item.class);

        // Print first product brand
        System.out.println("First brand: " + response.getData().get(0).getBrand());
    }
}
```

<br>

---
---

## Writing REST end points in an application

Example:
```
package com.booking.recruitment.hotel.controller;

import com.booking.recruitment.hotel.exception.ElementNotFoundException;
import com.booking.recruitment.hotel.model.Hotel;
import com.booking.recruitment.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {
  private final HotelService hotelService;

  @Autowired
  public HotelController(HotelService hotelService) {
    this.hotelService = hotelService;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Hotel> getAllHotels() {
    return hotelService.getAllHotels();
  }

  @GetMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Hotel getHotelById(@PathVariable Long id) {
    Optional<Hotel> hotelOptional = hotelService.getHotelById(id);
    return hotelOptional.orElseThrow(() -> new ElementNotFoundException("Hotel not found"));
  }

  @DeleteMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Hotel deleteHotelById(@PathVariable Long id) {
    return hotelService.deleteHotelById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Hotel createHotel(@RequestBody Hotel hotel) {
    return hotelService.createNewHotel(hotel);
  }

    // Below is written in a diff appln
    @GetMapping("/book")
    public ResponseEntity<BookingResponseDto> bookHotel(@RequestParam("city")String city, @RequestParam("date")String date,
                                                        @RequestParam("guests")int guests, @RequestParam("hotelName")String hotelName) {
        BookingResponseDto bookingResponseDto = bookingService.bookHotelForCityDateAndGuests(city, LocalDate.parse(date), guests, hotelName);
        return new ResponseEntity<>(bookingResponseDto, HttpStatus.OK);
    }
}

```
Spring annotations to note:
1. @RestController -> defines the class as a REST controller
2. @RequestMapping("/hotel") -> provides the path in the application for the APIs
3. @GetMapping, @PostMapping @DeleteMapping
4. @ResponseStatus -> Specifies the reponse status if API executes properly
5. HttpStatus class: holds status values like 200, 404, etc.
6. @PathVariable -> used in case of a path variable in the API like id, so something like localhost:8080/hotel/id/123 -> here 123 is the value of id
7. @RequestParam -> localhost:8080/hotelInfo/searchWithBudget?city=Paris&budget=1000 -> here city and budget are request params
8. @RequestBody -> usually for POST requests where a body is passed, can be directly mapped to a Java class
9. @RequestHeader 
