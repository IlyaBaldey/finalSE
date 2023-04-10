package belyakovsky.searchengine.services;

import belyakovsky.searchengine.config.SitesList;
import belyakovsky.searchengine.dto.statistics.TotalStatistics;
import belyakovsky.searchengine.model.SiteEntity;
import belyakovsky.searchengine.model.Status;
import belyakovsky.searchengine.repository.LemmaRepository;
import belyakovsky.searchengine.repository.PageRepository;
import belyakovsky.searchengine.repository.SiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import belyakovsky.searchengine.dto.statistics.DetailedStatisticsItem;
import belyakovsky.searchengine.dto.statistics.StatisticsData;
import belyakovsky.searchengine.dto.statistics.StatisticsResponse;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final SitesList sites;
    private final SiteRepository siteRepository;
    private final PageRepository pageRepository;
    private final LemmaRepository lemmaRepository;

    @Override
    public StatisticsResponse getStatistics() {
        TotalStatistics total = new TotalStatistics();
        total.setSites(sites.getSites().size());
        total.setIndexing(isIndexing());

        List<DetailedStatisticsItem> detailed = new ArrayList<>();
        List<SiteEntity> siteEntityList = siteRepository.findAll();
        for(SiteEntity siteEntity : siteEntityList) {
            DetailedStatisticsItem item = new DetailedStatisticsItem();
            item.setName(siteEntity.getName());
            String siteUrl = siteEntity.getUrl();
            item.setUrl(siteUrl.substring(0, siteUrl.length() - 1));
            int pages = countPagesBySiteEntity(siteEntity);
            int lemmas = countLemmasBySiteEntity(siteEntity);
            item.setPages(pages);
            item.setLemmas(lemmas);
            item.setStatus(siteEntity.getStatus().toString());
            item.setError(siteEntity.getLastError());
            item.setStatusTime(siteEntity.getStatusTime().getTime());
            total.setPages(total.getPages() + pages);
            total.setLemmas(total.getLemmas() + lemmas);
            detailed.add(item);
        }

        StatisticsResponse response = new StatisticsResponse();
        StatisticsData data = new StatisticsData();
        data.setTotal(total);
        data.setDetailed(detailed);
        response.setStatistics(data);
        response.setResult(true);
        return response;
    }

    private boolean isIndexing() {
        return siteRepository.existsByStatus(Status.INDEXING);
    }

    private int countPagesBySiteEntity(SiteEntity site) {
        return pageRepository.countPageEntitiesBySiteEntity(site);
    }

    private int countLemmasBySiteEntity(SiteEntity site) {
        return lemmaRepository.countLemmaEntitiesBySiteId(site);
    }
}
