package com.kshrd.kshrd_websiteapi.repository;

import com.kshrd.kshrd_websiteapi.model.generation.GenerationDto;
import com.kshrd.kshrd_websiteapi.model.generation.GenerationFilter;
import com.kshrd.kshrd_websiteapi.model.generation.GenerationRequest;
import com.kshrd.kshrd_websiteapi.repository.provider.GenerationProvider;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenerationRepository {

    //TODO : Select all generations =========================================================
    @SelectProvider(type = GenerationProvider.class,method = "selectWithPagination")
    @Results({
            @Result(property = "startYear",column = "start_year"),
            @Result(property = "endYear",column = "end_year")
    })
    List<GenerationDto> selectWithPagination(@Param("name") GenerationFilter name,@Param("page") Pagination pagination);

    //TODO: Select generation total count =========================================================
    @SelectProvider(value = GenerationProvider.class, method = "getGenerationTotalCount")

    Integer getGenerationTotalCount(@Param("name") GenerationFilter name);

    //TODO: Select generation by id =========================================================
    @SelectProvider(value = GenerationProvider.class, method = "selectById")
    @Results({
            @Result(property = "startYear",column = "start_year"),
            @Result(property = "endYear",column = "end_year")
    })
    GenerationDto selectById(int id);

    //TODO : Insert generation =========================================================
    @Insert("INSERT INTO hrd_generation (name, start_year, end_year, status) " +
            "VALUES (#{name}, #{startYear}, #{endYear}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    boolean insert(GenerationDto generationDto);

    //TODO : Delete generation =========================================================
    @DeleteProvider(type = GenerationProvider.class,method = "delete")
    boolean delete(Integer id);

    //TODO : Update generation =========================================================
    @UpdateProvider(type = GenerationProvider.class,method = "update")
    boolean update(Integer id, GenerationRequest generationRequest);

    @SelectProvider(value = GenerationProvider.class, method = "selectAfterDelete")
    @Results({
            @Result(property = "startYear",column = "start_year"),
            @Result(property = "endYear",column = "end_year")
    })
    GenerationDto selectAfterDelete(int id);


    //TODO : Select all generations =========================================================
    @SelectProvider(type = GenerationProvider.class,method = "selectAll")
    @Results({
            @Result(property = "startYear",column = "start_year"),
            @Result(property = "endYear",column = "end_year")
    })
    List<GenerationDto> selectAll( @Param("name") GenerationFilter name);
}
