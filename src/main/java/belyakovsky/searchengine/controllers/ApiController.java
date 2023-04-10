package belyakovsky.searchengine.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import belyakovsky.searchengine.dto.ApiResponse;
import belyakovsky.searchengine.dto.search.SearchResultResponse;
import belyakovsky.searchengine.dto.statistics.StatisticsResponse;
import belyakovsky.searchengine.services.IndexingService;
import belyakovsky.searchengine.services.SearchService;
import belyakovsky.searchengine.services.StatisticsService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final StatisticsService statisticsService;
    private final IndexingService indexingService;
    private final SearchService searchService;

    @GetMapping("/statistics")
    public ResponseEntity<StatisticsResponse> statistics() {
        return ResponseEntity.ok(statisticsService.getStatistics());
    }

    @GetMapping("/startIndexing")
    public ResponseEntity<ApiResponse> startIndexing() {
        return indexingService.startIndexing();
    }

    @GetMapping("/stopIndexing")
    public ResponseEntity<ApiResponse> stopIndexing() {
        return indexingService.stopIndexing();
    }

    @PostMapping("/indexPage")
    public ResponseEntity<ApiResponse> indexPage(@RequestParam(name="url", required = false) String path) {
        return indexingService.indexPage(path);
    }

    @GetMapping("/search")
    public ResponseEntity<SearchResultResponse> search(
            @RequestParam(name="query", required = false) String query,
            @RequestParam(name="site", required = false) String site,
            @RequestParam(name="offset", required = false) Integer offset,
            @RequestParam(name="limit", required = false) Integer limit
    ) {
        return searchService.search(query, site, offset, limit);
    }
}
