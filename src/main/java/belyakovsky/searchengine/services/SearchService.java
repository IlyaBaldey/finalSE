package belyakovsky.searchengine.services;

import org.springframework.http.ResponseEntity;
import belyakovsky.searchengine.dto.search.SearchResultResponse;

public interface SearchService {

    ResponseEntity<SearchResultResponse> search(String query, String site, int offset, int limit);
}
