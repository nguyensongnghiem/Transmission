package com.mobifone.transmission.repository;

import com.mobifone.transmission.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IVendorRepository extends JpaRepository<Vendor,Integer> {
}
