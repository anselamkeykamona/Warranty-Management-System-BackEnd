package com.warrantyclaim.warrantyclaim_api.service;

import com.warrantyclaim.warrantyclaim_api.entity.ProductsSparePartsTypeEVMWarrantyPolicy;
import com.warrantyclaim.warrantyclaim_api.entity.ProductsSparePartsTypeEVMWarrantyPolicyId;
import com.warrantyclaim.warrantyclaim_api.repository.ProductsSparePartsTypeEVMWarrantyPolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsSparePartsTypeEVMWarrantyPolicyService {

    @Autowired
    private ProductsSparePartsTypeEVMWarrantyPolicyRepository repository;

    public List<ProductsSparePartsTypeEVMWarrantyPolicy> getAll() {
        return repository.findAll();
    }

    public Optional<ProductsSparePartsTypeEVMWarrantyPolicy> getById(String partTypeId, String warrantyPolicyId) {
        return repository.findById(new ProductsSparePartsTypeEVMWarrantyPolicyId(partTypeId, warrantyPolicyId));
    }

    public ProductsSparePartsTypeEVMWarrantyPolicy create(ProductsSparePartsTypeEVMWarrantyPolicy entity) {
        return repository.save(entity);
    }

    public ProductsSparePartsTypeEVMWarrantyPolicy update(String partTypeId, String warrantyPolicyId, ProductsSparePartsTypeEVMWarrantyPolicy entity) {
        ProductsSparePartsTypeEVMWarrantyPolicyId id = new ProductsSparePartsTypeEVMWarrantyPolicyId(partTypeId, warrantyPolicyId);
        if (repository.existsById(id)) {
            entity.setPartTypeId(partTypeId);
            entity.setWarrantyPolicyId(warrantyPolicyId);
            return repository.save(entity);
        }
        return null;
    }

    public boolean delete(String partTypeId, String warrantyPolicyId) {
        ProductsSparePartsTypeEVMWarrantyPolicyId id = new ProductsSparePartsTypeEVMWarrantyPolicyId(partTypeId, warrantyPolicyId);
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
