package com.kshrd.kshrd_websiteapi.service.implement;

import com.kshrd.kshrd_websiteapi.model.careerpath.CareerPathDto;
import com.kshrd.kshrd_websiteapi.model.careerpath.CareerPathFilter;
import com.kshrd.kshrd_websiteapi.model.careerpath.CareerPathRequest;
import com.kshrd.kshrd_websiteapi.repository.CareerPathRepository;
import com.kshrd.kshrd_websiteapi.service.CareerPathService;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CareerPathServiceImp implements CareerPathService {

    private CareerPathRepository careerPathRepository;

    @Autowired
    public void setCareerPathRepository(CareerPathRepository careerPathRepository) {

        this.careerPathRepository = careerPathRepository;
    }

    //TODO: Select all careerpath  =========================================================
    @Override
    public List<CareerPathDto> selectWithPagination(CareerPathFilter title,Pagination pagination) {

        pagination.setTotalCount(careerPathRepository.getCareerPathTotalCount(title));
        return careerPathRepository.selectWithPagination(title,pagination);
    }

    //TODO: Select careerpath by id =========================================================
    @Override
    public CareerPathDto selectById(int id) {
        return careerPathRepository.selectById(id);
    }

    //TODO: Select careerpath after delete =========================================================
    @Override
    public CareerPathDto selectAfterDelete(int id) {
        return careerPathRepository.selectAfterDelete(id);
    }

    //TODO: Insert careerpath =========================================================
    @Override
    public CareerPathDto insert(CareerPathDto careerPathDto) {

        careerPathDto.setStatus(true);
        boolean isInserted = careerPathRepository.insert(careerPathDto);
        if (isInserted) {
            return careerPathDto;
        }
        else
            return null;
    }

    //TODO: Delete careerpath =========================================================
    @Override
    public boolean delete(int id) {
        return careerPathRepository.delete(id);
    }

    //TODO: Update careerpath =========================================================
    @Override
    public boolean update(int id, CareerPathRequest careerPathRequest) {
        return careerPathRepository.update(id,careerPathRequest);
    }

    //TODO: Check if parentid exists =========================================================
    @Override
    public boolean checkIfParentIDNotExisted(int id) {
        boolean existed=false;
        if(careerPathRepository.checkIfParentIDNotExisted(id)!=null)
            existed=true;
        return existed;
    }

    @Override
    public List<CareerPathDto> selectAll(CareerPathFilter title) {
        return careerPathRepository.selectAll(title);
    }

}
