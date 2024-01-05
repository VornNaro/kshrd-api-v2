package com.kshrd.kshrd_websiteapi.repository;

import com.kshrd.kshrd_websiteapi.model.course_training_type.CourseTrainingTypeDto;
import com.kshrd.kshrd_websiteapi.model.course_training_type.CourseTrainingTypeFilter;
import com.kshrd.kshrd_websiteapi.model.course_training_type.CourseTrainingTypeRequest;
import com.kshrd.kshrd_websiteapi.repository.provider.CourseTrainingTypeProvider;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CourseTrainingTypeRepository {

    //TODO: Select all course training type =========================================================
    @SelectProvider(value = CourseTrainingTypeProvider.class, method = "selectWithPagination")
    List<CourseTrainingTypeDto> selectWithPagination(@Param("name") CourseTrainingTypeFilter name,@Param("page") Pagination pagination);

    //TODO: Select course training type totalCount =========================================================
    @SelectProvider(value = CourseTrainingTypeProvider.class, method = "getCourseTrainingTypeTotalCount")
    Integer getCourseTrainingTypeTotalCount(@Param("name") CourseTrainingTypeFilter name);

    //TODO: Select course training type by id =========================================================
    @SelectProvider(value = CourseTrainingTypeProvider.class, method = "selectById")
    CourseTrainingTypeDto selectById(int id);

    //TODO: Insert course training type =========================================================
    @Insert("INSERT INTO hrd_course_training_type (name,status) VALUES(#{name},true)")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    boolean insert(CourseTrainingTypeDto courseTrainingTypeDto);

    //TODO: Update course training type =========================================================
    @UpdateProvider(value = CourseTrainingTypeProvider.class, method = "update")
    boolean update(int id,CourseTrainingTypeRequest courseTrainingTypeRequest);

    //TODO: Delete course training type =========================================================
    @DeleteProvider(value = CourseTrainingTypeProvider.class, method = "delete")
    boolean delete(int id);

    //TODO: Select course training type after delete =========================================================
    @SelectProvider(value = CourseTrainingTypeProvider.class, method = "selectAfterDelete")
    CourseTrainingTypeDto selectAfterDelete(int id);
}
