package com.ferryboat.app.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ferryboat.app.data.dto.CarTypeResponseDTO;
import com.ferryboat.app.data.form.CarTypeRequestForm;
import com.ferryboat.app.entity.CarType;
import com.ferryboat.app.repository.CarTypeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarTypeService {

    private final CarTypeRepository carTypeRepository;

    @Transactional
    public CarTypeResponseDTO create(CarTypeRequestForm dto) {
        CarType carType = new CarType();
        carType.setCarType(dto.getCarType());
        carType.setSpace(dto.getSpace());
        carType.setCost(dto.getCost());

        CarType saved = carTypeRepository.save(carType);
        return toResponse(saved);
    }

    @Transactional
    public CarTypeResponseDTO update(UUID id, CarTypeRequestForm dto) {
        CarType existing = carTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CarType not found"));

        existing.setCarType(dto.getCarType());
        existing.setSpace(dto.getSpace());

        CarType updated = carTypeRepository.save(existing);
        return toResponse(updated);
    }

    @Transactional(readOnly = true)
    public List<CarTypeResponseDTO> getAll() {
        return carTypeRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CarTypeResponseDTO getById(UUID id) {
        CarType carType = carTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CarType not found"));
        return toResponse(carType);
    }

    @Transactional
    public void delete(UUID id) {
        if (!carTypeRepository.existsById(id)) {
            throw new RuntimeException("CarType not found");
        }
        carTypeRepository.deleteById(id);
    }

    private CarTypeResponseDTO toResponse(CarType entity) {
        CarTypeResponseDTO dto = new CarTypeResponseDTO();
        dto.setId(entity.getId());
        dto.setCarType(entity.getCarType());
        dto.setSpace(entity.getSpace());
        dto.setCost(entity.getCost());
        return dto;
    }
}