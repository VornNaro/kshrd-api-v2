package com.kshrd.kshrd_websiteapi.repository;

import com.kshrd.kshrd_websiteapi.model.course_training.CourseTrainingDto;
import com.kshrd.kshrd_websiteapi.model.course_training.CourseTrainingFilter;
import com.kshrd.kshrd_websiteapi.model.course_training.CourseTrainingRequest;
import com.kshrd.kshrd_websiteapi.model.course_training.CourseTrainingResponse;
import com.kshrd.kshrd_websiteapi.model.course_training_type.CourseTrainingTypeDto;
import com.kshrd.kshrd_websiteapi.repository.provider.CourseTrainingProvider;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CourseTrainingRepository {

    //TODO: Select all course trainings  =========================================================
    @SelectProvider(value = CourseTrainingProvider.class,method = "selectWithPagination")
    @Results({
            @Result(column = "course_training_type_id", property = "courseTrainingTypeId", many = @Many(select = "getCourseTrainingTypeId"))
    })
    List<CourseTrainingDto> selectWithPagination(@Param ("courseTypeId") Integer courseTypeId,@Param("name") CourseTrainingFilter name,@Param("page") Pagination pagination);

    //TODO: Check if type exists  =========================================================
    @SelectProvider(value = CourseTrainingProvider.class,method = "checkIfTypeExisted")
    Object checkIfTypeExisted(int id);


    //TODO: Select course training totalCount =========================================================
    @SelectProvider(value = CourseTrainingProvider.class,method = "getCourseTrainingTotalCount")
    Integer getCourseTrainingTotalCount(@Param("name")CourseTrainingFilter name);

    //TODO: Select course training by id =========================================================
    @SelectProvider(value = CourseTrainingProvider.class, method = "selectById")
    @Results({
            @Result(column = "course_training_type_id", property = "courseTrainingTypeId", many = @Many(select = "getCourseTrainingTypeId"))
    })
    CourseTrainingDto selectById(int id);

    //TODO: Select course training type by id =========================================================
    @SelectProvider(value = CourseTrainingProvider.class, method = "getCourseTrainingTypeId")
    CourseTrainingTypeDto getCourseTrainingTypeId(int CourseTrainingTypeId);


    //TODO: Select course training type by id =========================================================
    @SelectProvider(value = CourseTrainingProvider.class, method = "getCourseTrainingTypeIdName")
    int getCourseTrainingTypeIdName(int CourseTrainingTypeId);

    //TODO: Select course training type by name =========================================================
    @SelectProvider(value = CourseTrainingProvider.class, method = "getCourseTrainingTypeName")
    String getCourseTrainingTypeName(int CourseTrainingTypeId);

    //TODO: Insert course training =========================================================
    @Insert("INSERT INTO hrd_course_training (name,description,logo,file,course_training_type_id,status)"
            + "VALUES (#{name},#{description},#{logo},#{file},#{courseTrainingTypeId.id},#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    boolean insert(CourseTrainingDto courseTrainingDto);

    //TODO: Update course training =========================================================
    @UpdateProvider(value = CourseTrainingProvider.class,method = "update")
    boolean update(int id,CourseTrainingRequest courseTrainingRequest);

    //TODO: Delete course training =========================================================
    @DeleteProvider(value = CourseTrainingProvider.class, method = "delete")
    boolean delete(int id);

    //TODO: Select course training after delete =========================================================
    @SelectProvider(value = CourseTrainingProvider.class, method = "selectAfterDelete")
    @Results({
            @Result(column = "course_training_type_id", property = "courseTrainingTypeId", many = @Many(select = "getCourseTrainingTypeId"))
    })
    CourseTrainingResponse selectAfterDelete(int id);


    //TODO: Select all course trainings without pagination  =========================================================
    @SelectProvider(value = CourseTrainingProvider.class,method = "selectAll")
    @Results({
            @Result(column = "course_training_type_id", property = "courseTrainingTypeId", many = @Many(select = "getCourseTrainingTypeId"))
    })
    List<CourseTrainingDto> selectAll();
}
