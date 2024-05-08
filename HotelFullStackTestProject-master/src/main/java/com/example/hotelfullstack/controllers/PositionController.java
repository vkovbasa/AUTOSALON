package com.example.hotelfullstack.controllers;

import com.example.hotelfullstack.dtos.PositionDTO;
import com.example.hotelfullstack.mapper.PositionMapper;
import com.example.hotelfullstack.models.Client;
import com.example.hotelfullstack.models.Position;
import com.example.hotelfullstack.services.PositionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/positions")
@RequiredArgsConstructor
public class PositionController {
    private final PositionService positionService;
    private final PositionMapper positionMapper;
    @GetMapping
    public List<Position> getAllPosition() {
        return positionService.getAllPosition();
    }

    @GetMapping("/with-employees")
    public ResponseEntity<List<Position>> getPositionsWithEmployees() {
        List<Position> positions = positionService.getPositionsWithEmployees();
        return ResponseEntity.ok(positions);
    }

    @GetMapping("/{id}")
    public Position getPositionById(@PathVariable Long id) {
        return positionService.getPositionById(id);
    }

    @PostMapping
    public Position createPosition(@RequestBody @Valid PositionDTO positionDTO) {
        return positionService.createPosition(positionMapper.toEntity(positionDTO));
    }

    @PutMapping("/{id}")
    public Position updatePosition(@PathVariable Long id, @RequestBody @Valid PositionDTO positionDTO) {
        return positionService.updatePosition(id, positionMapper.toEntity(positionDTO));
    }

    @DeleteMapping("/{id}")
    public void deletePosition(@PathVariable Long id) {
        positionService.deletePosition(id);
    }
}
