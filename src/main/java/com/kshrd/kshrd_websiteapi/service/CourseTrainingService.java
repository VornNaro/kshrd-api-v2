package com.kshrd.kshrd_websiteapi.service;

import com.kshrd.kshrd_websiteapi.model.course_training.CourseTrainingDto;
import com.kshrd.kshrd_websiteapi.model.course_training.CourseTrainingFilter;
import com.kshrd.kshrd_websiteapi.model.course_training.CourseTrainingRequest;
import com.kshrd.kshrd_websiteapi.model.course_training.CourseTrainingResponse;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;

import java.util.List;

public interface CourseTrainingService {

    //TODO: Select all course trainings =========================================================
    List<CourseTrainingDto> selectWithPagination(Integer id,CourseTrainingFilter name,Pagination pagination);

    //TODO: Select course training by id =========================================================
    CourseTrainingDto selectById(int id);

    //TODO: Select course training after delete =========================================================
    CourseTrainingResponse selectAfterDelete(int id);

    //TODO: Insert course training =========================================================
    CourseTrainingDto insert(CourseTrainingDto courseTrainingDto);

    //TODO: Delete course training =========================================================
    boolean delete(int id);

    //TODO: Update course training =========================================================
    boolean update(int id,CourseTrainingRequest courseTrainingRequest);

    //TODO: Check if type exists =========================================================
    boolean checkIfTypeExisted(int id);

    //TODO: Select all course trainings without pagination =========================================================
    List<CourseTrainingDto> selectAll();
}
