package com.kshrd.kshrd_websiteapi.service.implement;

import com.kshrd.kshrd_websiteapi.model.generation.GenerationDto;
import com.kshrd.kshrd_websiteapi.model.generation.GenerationFilter;
import com.kshrd.kshrd_websiteapi.model.generation.GenerationRequest;
import com.kshrd.kshrd_websiteapi.repository.GenerationRepository;
import com.kshrd.kshrd_websiteapi.service.GenerationService;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenerationServiceImp implements GenerationService {

    private GenerationRepository generationRepository;

    @Autowired
    public void setGenerationRepository(GenerationRepository generationRepository) {
        this.generationRepository = generationRepository;
    }

    //TODO: Select all generations =========================================================
    @Override
    public List<GenerationDto> selectWithPagination(GenerationFilter name, Pagination pagination) {

        pagination.setTotalCount(generationRepository.getGenerationTotalCount(name));
        return generationRepository.selectWithPagination(name,pagination);
    }

    //TODO: Select generation by id =========================================================
    @Override
    public GenerationDto selectById(int id) {
        return generationRepository.selectById(id);
    }

    //TODO: Select generation after delete =========================================================
    @Override
    public GenerationDto selectAfterDelete(int id) {
        return generationRepository.selectAfterDelete(id);
    }

    //TODO: Insert generation =========================================================
    @Override
    public GenerationDto insert(GenerationDto generationDto) {

        generationDto.setStatus(true);
        boolean isInserted = generationRepository.insert(generationDto);
        if (isInserted) {
            return generationDto;
        }
        else
            return null;
    }

    //TODO: Delete generation =========================================================
    @Override
    public boolean delete(Integer id) {
        return  generationRepository.delete(id);
    }

    //TODO: Update generation =========================================================
    @Override
    public boolean update(Integer id, GenerationRequest generationRequest) {

        return generationRepository.update(id,generationRequest);
    }

    @Override
    public List<GenerationDto> selectAll(GenerationFilter name) {
        return generationRepository.selectAll(name);
    }
}
