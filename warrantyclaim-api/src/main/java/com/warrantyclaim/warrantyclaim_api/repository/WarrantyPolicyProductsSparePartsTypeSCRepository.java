package com.warrantyclaim.warrantyclaim_api.repository;

import com.warrantyclaim.warrantyclaim_api.entity.WarrantyPolicyProductsSparePartsTypeSC;
import com.warrantyclaim.warrantyclaim_api.entity.WarrantyPolicyProductsSparePartsTypeSCId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarrantyPolicyProductsSparePartsTypeSCRepository
        extends JpaRepository<WarrantyPolicyProductsSparePartsTypeSC, WarrantyPolicyProductsSparePartsTypeSCId> {
}
