package com.example.hotelfullstack.services;

import com.example.hotelfullstack.exceptions.ResourceNotFoundException;
import com.example.hotelfullstack.models.Position;
import com.example.hotelfullstack.repositories.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionService {
    private final PositionRepository positionRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public List<Position> getAllPosition() {
        return positionRepository.findAll();
    }

    public List<Position> getPositionsWithEmployees() {
        return positionRepository.findPositionsWithEmployees();
    }

    public Position createPosition(Position position) {
        return positionRepository.save(position);
    }

    public Position getPositionById(Long id) {
        return positionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Position not found id : " + id)
        );
    }

    public Position updatePosition(Long id, Position position) {
        Position updatedPosition = positionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Position not found id : " + id)
        );
        modelMapper.map(position, updatedPosition);

        return positionRepository.save(updatedPosition);
    }

    public void deletePosition(Long id) {
        Position position = positionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Position not found id : " + id)
        );
        positionRepository.delete(position);
    }

}
