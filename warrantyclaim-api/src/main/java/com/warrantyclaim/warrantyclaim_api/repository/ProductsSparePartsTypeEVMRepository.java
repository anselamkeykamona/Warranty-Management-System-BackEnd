package com.warrantyclaim.warrantyclaim_api.repository;

import com.warrantyclaim.warrantyclaim_api.entity.ProductsSparePartsTypeEVM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsSparePartsTypeEVMRepository extends JpaRepository<ProductsSparePartsTypeEVM, String> {
}
