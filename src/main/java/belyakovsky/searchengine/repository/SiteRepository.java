package belyakovsky.searchengine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import belyakovsky.searchengine.model.SiteEntity;
import belyakovsky.searchengine.model.Status;

public interface SiteRepository extends JpaRepository<SiteEntity, Long> {

    @Transactional
    void deleteSiteEntityByUrl(String url);

    SiteEntity findSiteEntityByUrl(String url);

    boolean existsByStatus(Status status);
}
