package com.warrantyclaim.warrantyclaim_api.service.Implement;

import com.warrantyclaim.warrantyclaim_api.entity.WarrantyPolicyProductsSparePartsTypeSC;
import com.warrantyclaim.warrantyclaim_api.entity.WarrantyPolicyProductsSparePartsTypeSCId;
import com.warrantyclaim.warrantyclaim_api.repository.WarrantyPolicyProductsSparePartsTypeSCRepository;
import com.warrantyclaim.warrantyclaim_api.service.WarrantyPolicyProductsSparePartsTypeSCService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarrantyPolicyProductsSparePartsTypeSCServiceImpl implements WarrantyPolicyProductsSparePartsTypeSCService {

    private final WarrantyPolicyProductsSparePartsTypeSCRepository repository;

    public WarrantyPolicyProductsSparePartsTypeSCServiceImpl(WarrantyPolicyProductsSparePartsTypeSCRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<WarrantyPolicyProductsSparePartsTypeSC> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<WarrantyPolicyProductsSparePartsTypeSC> getById(WarrantyPolicyProductsSparePartsTypeSCId id) {
        return repository.findById(id);
    }

    @Override
    public WarrantyPolicyProductsSparePartsTypeSC save(WarrantyPolicyProductsSparePartsTypeSC entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteById(WarrantyPolicyProductsSparePartsTypeSCId id) {
        repository.deleteById(id);
    }
}
