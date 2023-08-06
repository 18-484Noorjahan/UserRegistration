package com.example.IntoToSpringBoot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import com.example.IntoToSpringBoot.Data;
import com.example.IntoToSpringBoot.User; 





@RestController
@RequestMapping("/api/data")
public class DataController {
    @Autowired
    private DataService dataService;
    
    @PostMapping
    public ResponseEntity<?> storeData(@RequestBody Data dataDto, @AuthenticationPrincipal User user) {
        // Implement data storage logic
        dataService.storeData(dataDto.getKey(), dataDto.getValue(), user);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{key}")
    public ResponseEntity<?> retrieveData(@PathVariable String key, @AuthenticationPrincipal User user) {
        // Implement data retrieval logic
        Data data = dataService.retrieveDataByKeyAndUser(key, user);
        if (data != null) {
            return ResponseEntity.ok(data);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Other API methods for data-related operations
}
