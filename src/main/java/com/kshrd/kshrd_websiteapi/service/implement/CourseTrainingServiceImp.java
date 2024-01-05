package com.kshrd.kshrd_websiteapi.service.implement;

import com.kshrd.kshrd_websiteapi.model.course_training.CourseTrainingDto;
import com.kshrd.kshrd_websiteapi.model.course_training.CourseTrainingFilter;
import com.kshrd.kshrd_websiteapi.model.course_training.CourseTrainingRequest;
import com.kshrd.kshrd_websiteapi.model.course_training.CourseTrainingResponse;
import com.kshrd.kshrd_websiteapi.repository.CourseTrainingRepository;
import com.kshrd.kshrd_websiteapi.service.CourseTrainingService;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseTrainingServiceImp implements CourseTrainingService {

    private CourseTrainingRepository courseTrainingRepository;

    @Autowired
    public void setCourseTrainingRepository(CourseTrainingRepository courseTrainingRepository) {

        this.courseTrainingRepository = courseTrainingRepository;
    }

    //TODO: Select all course trainings =========================================================
    @Override
    public List<CourseTrainingDto> selectWithPagination(Integer id,CourseTrainingFilter name,Pagination pagination) {

        pagination.setTotalCount(courseTrainingRepository.getCourseTrainingTotalCount(name));
        return courseTrainingRepository.selectWithPagination(id,name,pagination);
    }

    //TODO: Check if type exists =========================================================
    @Override
    public boolean checkIfTypeExisted(int id) {

        boolean existed=false;
        if(courseTrainingRepository.checkIfTypeExisted(id)!=null)
            existed=true;
        return existed;
    }

    //TODO: Select all course trainings without pagination =========================================================
    @Override
    public List<CourseTrainingDto> selectAll() {
        return courseTrainingRepository.selectAll();
    }

    //TODO: Select course training by id =========================================================
    @Override
    public CourseTrainingDto selectById(int id) {
        return courseTrainingRepository.selectById(id);
    }

    //TODO: Select course training after delete =========================================================
    @Override
    public CourseTrainingResponse selectAfterDelete(int id) {
        return courseTrainingRepository.selectAfterDelete(id);
    }

    //TODO: Insert course training =========================================================
    @Override
    public CourseTrainingDto insert(CourseTrainingDto courseTrainingDto) {

        courseTrainingDto.setStatus(true);
        boolean isInserted = courseTrainingRepository.insert(courseTrainingDto);
        if (isInserted) {
            int courseTrainingTypeName = courseTrainingDto.getCourseTrainingTypeId().getId();
            courseTrainingDto.getCourseTrainingTypeId().setName(courseTrainingRepository.getCourseTrainingTypeName(courseTrainingTypeName));
            return courseTrainingDto;
        }
        else
            return null;
    }

    //TODO: Update course training =========================================================
    @Override
    public boolean update(int id,CourseTrainingRequest courseTrainingRequest) {

        return courseTrainingRepository.update(id,courseTrainingRequest);
    }

    //TODO: Delete course training =========================================================
    @Override
    public boolean delete(int id) {
        return courseTrainingRepository.delete(id);
    }

}
