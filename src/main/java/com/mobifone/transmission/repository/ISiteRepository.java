package com.mobifone.transmission.repository;

import com.mobifone.transmission.dto.inf.SiteViewDTO;
import com.mobifone.transmission.model.Site;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ISiteRepository extends JpaRepository<Site,Long> {
    <T> List<T> findBy(Class<T> classType);
    <T> T findById(Long id, Class<T> classType);
    Site findSitesBySiteId(String siteId);
    <T> Page<T> findSiteBySiteIdContainingIgnoreCaseAndTransmissionOwner_NameContainingIgnoreCaseAndSiteTransmissionType_NameContainingIgnoreCaseAndProvince_NameContainingIgnoreCase(String siteId,String transOwner,String transType,String province, Pageable pageable, Class<T> classType );
//    Page<Site> findSitesBySiteIdContainingIgnoreCaseAndProvince_NameContainingIgnoreCase(String siteId, String provinceName, Pageable pageable);

    Site findSitesBySiteId2(String siteId2);
//    @Query(name = "select s.id, s.site_id as siteId, s.site_id2 as siteId2, s.latitude, s.longitude, s.address, so.name as siteOwner, smt.name as siteTransmissionType, p.name as province\n" +
//            "from site s left join site_owner so on s.site_owner_id = so.id \n" +
//            "left join site_transmission_type smt on s.site_transmission_type_id = smt.id\n" +
//            "left join transmission_owner toi on s.transmission_owner_id = toi.id\n" +
//            "left join province p on s.province_id = p.id;")
//    List<SiteViewDTO> findSiteViewDTOs() ;

}
