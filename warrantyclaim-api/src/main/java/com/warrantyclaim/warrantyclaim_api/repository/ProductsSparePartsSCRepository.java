package com.warrantyclaim.warrantyclaim_api.repository;

import com.warrantyclaim.warrantyclaim_api.entity.ProductsSparePartsSC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsSparePartsSCRepository extends JpaRepository<ProductsSparePartsSC, String> {

    /**
     * Find all spare parts by part type ID
     */
    List<ProductsSparePartsSC> findByPartTypeId(String partTypeId);

    /**
     * Find all spare parts by claim ID
     */
    List<ProductsSparePartsSC> findByClaimClaimId(String claimId);

    /**
     * Find spare parts by brand
     */
    List<ProductsSparePartsSC> findByBrand(String brand);
}
