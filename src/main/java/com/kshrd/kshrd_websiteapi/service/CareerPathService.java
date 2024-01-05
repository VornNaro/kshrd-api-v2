package com.kshrd.kshrd_websiteapi.service;

import com.kshrd.kshrd_websiteapi.model.careerpath.CareerPathDto;
import com.kshrd.kshrd_websiteapi.model.careerpath.CareerPathFilter;
import com.kshrd.kshrd_websiteapi.model.careerpath.CareerPathRequest;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;

import java.util.List;

public interface CareerPathService {

    //TODO: Select all career path  =========================================================
    List<CareerPathDto> selectWithPagination(CareerPathFilter title,Pagination pagination);

    //TODO: Select career path by id =========================================================
    CareerPathDto selectById(int id);

    //TODO: Select career path after delete =========================================================
    CareerPathDto selectAfterDelete(int id);

    //TODO: Insert career path =========================================================
    CareerPathDto insert(CareerPathDto careerPathDto);

    //TODO: Delete career path =========================================================
    boolean delete(int id);

    //TODO: Update career path =========================================================
    boolean update(int id, CareerPathRequest careerPathRequest);

    //TODO: Check if parentid exists =========================================================
    boolean checkIfParentIDNotExisted(int id);

    //TODO: Select all career path  =========================================================
    List<CareerPathDto> selectAll(CareerPathFilter title);
}
