## Writing REST APIs using Java

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


