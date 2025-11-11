package com.ferryboat.app.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ferryboat.app.data.dto.CarTypeResponseDTO;
import com.ferryboat.app.data.form.CarTypeRequestForm;
import com.ferryboat.app.service.CarTypeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/car-type")
@RequiredArgsConstructor
public class CarTypeController {

    private final CarTypeService carTypeService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CarTypeResponseDTO> create(@RequestBody CarTypeRequestForm dto) {
        return ResponseEntity.ok(carTypeService.create(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CarTypeResponseDTO> update(
            @PathVariable UUID id, @RequestBody CarTypeRequestForm dto) {
        return ResponseEntity.ok(carTypeService.update(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<CarTypeResponseDTO>> getAll() {
        return ResponseEntity.ok(carTypeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarTypeResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(carTypeService.getById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        carTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
