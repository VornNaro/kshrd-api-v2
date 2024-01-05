package com.kshrd.kshrd_websiteapi.service.implement;

import com.kshrd.kshrd_websiteapi.model.course_training_type.CourseTrainingTypeDto;
import com.kshrd.kshrd_websiteapi.model.course_training_type.CourseTrainingTypeFilter;
import com.kshrd.kshrd_websiteapi.model.course_training_type.CourseTrainingTypeRequest;
import com.kshrd.kshrd_websiteapi.repository.CourseTrainingTypeRepository;
import com.kshrd.kshrd_websiteapi.service.CourseTrainingTypeService;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseTrainingTypeServiceImp implements CourseTrainingTypeService {

    private CourseTrainingTypeRepository courseTrainingTypeRepository;
    @Autowired
    public void setCourseTrainingTypeRepository(CourseTrainingTypeRepository courseTrainingTypeRepository) {

        this.courseTrainingTypeRepository = courseTrainingTypeRepository;
    }

    //TODO: Select all course training types =========================================================
    @Override
    public List<CourseTrainingTypeDto> selectWithPagination(CourseTrainingTypeFilter name,Pagination pagination) {

        pagination.setTotalCount(courseTrainingTypeRepository.getCourseTrainingTypeTotalCount(name));
        return courseTrainingTypeRepository.selectWithPagination(name,pagination);
    }

    //TODO: Select course training type by id =========================================================
    @Override
    public CourseTrainingTypeDto selectById(int id) {
        return courseTrainingTypeRepository.selectById(id);
    }

    //TODO: Select course training type after delete =========================================================
    @Override
    public CourseTrainingTypeDto selectAfterDelete(int id) {
        return courseTrainingTypeRepository.selectAfterDelete(id);
    }

    //TODO: Insert course training type =========================================================
    @Override
    public CourseTrainingTypeDto insert(CourseTrainingTypeDto courseTrainingTypeDto) {

        courseTrainingTypeDto.setStatus(true);
        boolean isInserted = courseTrainingTypeRepository.insert(courseTrainingTypeDto);
        if (isInserted) {
            return courseTrainingTypeDto;
        }
        else
            return null;
    }

    //TODO: Update course training type =========================================================
    @Override
    public boolean update(int id, CourseTrainingTypeRequest courseTrainingTypeRequest) {

        return courseTrainingTypeRepository.update(id,courseTrainingTypeRequest);
    }

    //TODO: Delete course training type =========================================================
    @Override
    public boolean delete(int id) {
        return courseTrainingTypeRepository.delete(id);
    }

}

