package com.mobifone.transmission.repository;

import com.mobifone.transmission.model.LeaseLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILeaseLineRepository extends JpaRepository<LeaseLine,Integer> {
}
