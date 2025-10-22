package com.warrantyclaim.warrantyclaim_api.repository;

import com.warrantyclaim.warrantyclaim_api.entity.ProductsSparePartsEVM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsSparePartsEVMRepository extends JpaRepository<ProductsSparePartsEVM, String> {

    /**
     * Find all spare parts by part type ID
     */
    List<ProductsSparePartsEVM> findByPartTypeId(String partTypeId);

    /**
     * Find spare parts by brand
     */
    List<ProductsSparePartsEVM> findByBrand(String brand);
}
