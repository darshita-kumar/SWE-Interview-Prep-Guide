# HashMap Java 

### Useful Methods
  - map.getOrDefault(key, def_value)
    
  - map.computeIfAbsent(key, k -> new ArrayList<>()).add(objectToAdd);
      - Above is useful in case we have something like a map of <String, List<String>>
      - It adds a new List if absent and we can add elements to this list using the add method
    
  - map.putIfAbsent(key,value)
      - The putIfAbsent() method writes an entry into the map. If an entry with the same key already exists and its value is not null then the map is not changed.

<br>

---
---

### Complexity:
TODO
