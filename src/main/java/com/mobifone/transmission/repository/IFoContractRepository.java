package com.mobifone.transmission.repository;

import com.mobifone.transmission.model.FoContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IFoContractRepository extends JpaRepository<FoContract,Integer> {
    public List<FoContract> findFirst10ByOrderByEndDateDesc();
    @Query(nativeQuery = true,value = "select * from fo_contract WHERE (end_date < DATE_ADD(CURDATE(),INTERVAL 2 MONTH)) AND (end_date > CURDATE()) ORDER BY end_date ASC ;")
    public List<FoContract> findContractEndIn5Month();


    FoContract findByContractNumber(String contractNumber);
}
