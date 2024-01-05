package com.kshrd.kshrd_websiteapi.service;

import com.kshrd.kshrd_websiteapi.model.generation.GenerationDto;
import com.kshrd.kshrd_websiteapi.model.generation.GenerationFilter;
import com.kshrd.kshrd_websiteapi.model.generation.GenerationRequest;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;

import java.util.List;

public interface GenerationService {

    //TODO: Select all generations with pagination =========================================================
    List<GenerationDto> selectWithPagination(GenerationFilter name, Pagination pagination);

    //TODO: Select generation by id =========================================================
    GenerationDto selectById(int id);

    //TODO: Select generation after delete =========================================================
    GenerationDto selectAfterDelete(int id);

    //TODO: Insert generation =========================================================
    GenerationDto insert(GenerationDto generationDto);

    //TODO: Delete generation =========================================================
    boolean delete(Integer id);

    //TODO: Update generation =========================================================
    boolean update(Integer id,GenerationRequest generationRequest);

    //TODO: Select all generations =========================================================
    List<GenerationDto> selectAll(GenerationFilter name);
}
