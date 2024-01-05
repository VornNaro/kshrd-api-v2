package com.kshrd.kshrd_websiteapi.service;

import com.kshrd.kshrd_websiteapi.model.course_training_type.CourseTrainingTypeDto;
import com.kshrd.kshrd_websiteapi.model.course_training_type.CourseTrainingTypeFilter;
import com.kshrd.kshrd_websiteapi.model.course_training_type.CourseTrainingTypeRequest;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;

import java.util.List;

public interface CourseTrainingTypeService {

    //TODO: Select all course training types =========================================================
    List<CourseTrainingTypeDto> selectWithPagination(CourseTrainingTypeFilter name,Pagination pagination);

    //TODO: Select course training type by id =========================================================
    CourseTrainingTypeDto selectById(int id);

    //TODO: Select course training type after delete =========================================================
    CourseTrainingTypeDto selectAfterDelete(int id);

    //TODO: Insert course training type =========================================================
    CourseTrainingTypeDto insert(CourseTrainingTypeDto dto);

    //TODO: Update course training type =========================================================
    boolean update(int id, CourseTrainingTypeRequest courseTrainingTypeRequest);

    //TODO: Delete course training type =========================================================
    boolean delete(int id);
}

