package com.mobifone.transmission.repository;

import com.mobifone.transmission.model.HiredFoLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHiredFoLineRepository extends JpaRepository<HiredFoLine,Integer> {
}
