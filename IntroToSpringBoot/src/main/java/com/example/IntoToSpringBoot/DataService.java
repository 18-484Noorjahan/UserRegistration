package com.example.IntoToSpringBoot;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class DataService {

    // Replace this with your actual data storage mechanism (e.g., database, cache, etc.)
    // For the sake of example, I'm using a simple map to store data temporarily.
    private Map<String, Data> dataStore = new ConcurrentHashMap<>();

    public void storeData(String key, String value, User user) {
        Data data = new Data(key, value, user);
        dataStore.put(key, data);
    }

    public Data retrieveDataByKeyAndUser(String key, User user) {
        Data data = dataStore.get(key);
        if (data != null && data.getUser().equals(user)) {
            return data;
        }
        return null;
    }
}

