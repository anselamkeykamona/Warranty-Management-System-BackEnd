package com.warrantyclaim.warrantyclaim_api.service;

import com.warrantyclaim.warrantyclaim_api.entity.WarrantyPolicyElectricVehicleType;
import com.warrantyclaim.warrantyclaim_api.entity.WarrantyPolicyElectricVehicleTypeId;
import com.warrantyclaim.warrantyclaim_api.repository.WarrantyPolicyElectricVehicleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarrantyPolicyElectricVehicleTypeService {

    @Autowired
    private WarrantyPolicyElectricVehicleTypeRepository repository;

    public List<WarrantyPolicyElectricVehicleType> getAll() {
        return repository.findAll();
    }

    public Optional<WarrantyPolicyElectricVehicleType> getById(String warrantyPolicyId, String vehicleTypeId) {
        return repository.findById(new WarrantyPolicyElectricVehicleTypeId(warrantyPolicyId, vehicleTypeId));
    }

    public WarrantyPolicyElectricVehicleType create(WarrantyPolicyElectricVehicleType entity) {
        return repository.save(entity);
    }

    public WarrantyPolicyElectricVehicleType update(String warrantyPolicyId, String vehicleTypeId, WarrantyPolicyElectricVehicleType entity) {
        WarrantyPolicyElectricVehicleTypeId id = new WarrantyPolicyElectricVehicleTypeId(warrantyPolicyId, vehicleTypeId);
        if (repository.existsById(id)) {
            entity.setWarrantyPolicyId(warrantyPolicyId);
            entity.setVehicleTypeId(vehicleTypeId);
            return repository.save(entity);
        }
        return null;
    }

    public boolean delete(String warrantyPolicyId, String vehicleTypeId) {
        WarrantyPolicyElectricVehicleTypeId id = new WarrantyPolicyElectricVehicleTypeId(warrantyPolicyId, vehicleTypeId);
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
