package belyakovsky.searchengine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import belyakovsky.searchengine.model.PageEntity;
import belyakovsky.searchengine.model.SiteEntity;

public interface PageRepository extends JpaRepository<PageEntity, Integer> {

    PageEntity findPageEntityByPathAndSiteEntity(String path, SiteEntity siteEntity);

    int countPageEntitiesBySiteEntity(SiteEntity siteEntity);

}
