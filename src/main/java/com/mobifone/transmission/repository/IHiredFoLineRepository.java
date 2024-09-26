package com.mobifone.transmission.repository;

import com.mobifone.transmission.dto.inf.HiredFoLineViewDTO;
import com.mobifone.transmission.model.HiredFoLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IHiredFoLineRepository extends JpaRepository<HiredFoLine,Integer> {
    <T> T findById(Integer id,Class<T> classType);
    <T> List<T> findBy(Class<T> classType);
    <T> List<T> findByFoContract_ContractNumber(String contractNumber,Class<T> classType);

    <T> List<T> findByFoContract_Id(int id,Class<T> classType);
    @Query(value = "select * from hired_fo_line where deleted = b'1' ", nativeQuery = true)
    List <HiredFoLine> findAllDeletedHiredFo () ;

}
