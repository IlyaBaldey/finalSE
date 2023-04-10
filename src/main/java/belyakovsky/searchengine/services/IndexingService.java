package belyakovsky.searchengine.services;

import org.springframework.http.ResponseEntity;
import belyakovsky.searchengine.dto.ApiResponse;

public interface IndexingService {

    ResponseEntity<ApiResponse> startIndexing();

    ResponseEntity<ApiResponse> stopIndexing();

    ResponseEntity<ApiResponse> indexPage(String pagePath);
}
