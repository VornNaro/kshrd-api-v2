package com.kshrd.kshrd_websiteapi.repository;

import com.kshrd.kshrd_websiteapi.model.careerpath.CareerPathDto;
import com.kshrd.kshrd_websiteapi.model.careerpath.CareerPathFilter;
import com.kshrd.kshrd_websiteapi.model.careerpath.CareerPathRequest;
import com.kshrd.kshrd_websiteapi.repository.provider.CareerPathProvider;
import com.kshrd.kshrd_websiteapi.repository.provider.PartnerProvider;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CareerPathRepository {

    //TODO: Select career path =========================================================
    @SelectProvider(value = CareerPathProvider.class, method = "selectWithPagination")
    List<CareerPathDto> selectWithPagination(@Param("title") CareerPathFilter title,@Param("page") Pagination pagination);

    //TODO: Select career path total count =========================================================
    @SelectProvider(value = CareerPathProvider.class, method = "getCareerPathTotalCount")
    Integer getCareerPathTotalCount(@Param("title") CareerPathFilter title);


    //TODO: Select career path by id =========================================================
    @SelectProvider(value = CareerPathProvider.class, method = "selectById")
    CareerPathDto selectById(int id);

    //TODO: Insert career path =========================================================
    @Insert("INSERT INTO hrd_career_path (parent_id, title, description, detail, photo, status) " +
            "VALUES (#{parent_id}, #{title}, #{description}, #{detail},#{photo}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    boolean insert(CareerPathDto careerPathDto);

    //TODO: Delete caree rpath =========================================================
    @DeleteProvider(value = CareerPathProvider.class, method = "delete")
    boolean delete(int id);

    //TODO: Update career path =========================================================
    @UpdateProvider(value = CareerPathProvider.class, method = "update")
    boolean update(int id, CareerPathRequest careerPathRequest);

    //TODO: Select career path after delete
    @SelectProvider(value = CareerPathProvider.class, method = "selectAfterDelete")
    CareerPathDto selectAfterDelete(int id);


    @SelectProvider(value = CareerPathProvider.class,method = "checkIfParentIDNotExisted")
    Object checkIfParentIDNotExisted(int id);

    //TODO: Select all career path without pagination =========================================================
    @SelectProvider(value = CareerPathProvider.class, method = "selectAll")
    List<CareerPathDto> selectAll(@Param("title") CareerPathFilter title);
}

