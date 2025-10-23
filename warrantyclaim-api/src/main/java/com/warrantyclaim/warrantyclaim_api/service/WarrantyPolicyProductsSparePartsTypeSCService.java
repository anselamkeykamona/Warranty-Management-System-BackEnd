package com.warrantyclaim.warrantyclaim_api.service;

import com.warrantyclaim.warrantyclaim_api.entity.WarrantyPolicyProductsSparePartsTypeSC;
import com.warrantyclaim.warrantyclaim_api.entity.WarrantyPolicyProductsSparePartsTypeSCId;

import java.util.List;
import java.util.Optional;

public interface WarrantyPolicyProductsSparePartsTypeSCService {
    List<WarrantyPolicyProductsSparePartsTypeSC> getAll();
    Optional<WarrantyPolicyProductsSparePartsTypeSC> getById(WarrantyPolicyProductsSparePartsTypeSCId id);
    WarrantyPolicyProductsSparePartsTypeSC save(WarrantyPolicyProductsSparePartsTypeSC entity);
    void deleteById(WarrantyPolicyProductsSparePartsTypeSCId id);
}
