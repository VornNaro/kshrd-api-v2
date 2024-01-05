package com.kshrd.kshrd_websiteapi.repository;

import com.kshrd.kshrd_websiteapi.model.course_training_type.CourseTrainingTypeDto;
import com.kshrd.kshrd_websiteapi.model.event_category.EventCategoryDto;
import com.kshrd.kshrd_websiteapi.model.event_category.EventCategoryFilter;
import com.kshrd.kshrd_websiteapi.model.event_category.EventCategoryRequest;
import com.kshrd.kshrd_websiteapi.repository.provider.CourseTrainingProvider;
import com.kshrd.kshrd_websiteapi.repository.provider.EventCategoryProvider;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventCategoryRepository {

        //TODO : Select all event categories =========================================================
        @SelectProvider(type= EventCategoryProvider.class,method = "selectWithPagination")
        @Result(column = "course_training_type_id",property = "courseTrainingTypeId",many = @Many(select = "getCourseTrainingTypeId"))
        List<EventCategoryDto> selectWithPagination(@Param("name")EventCategoryFilter name,
                                                    @Param("page")Pagination pagination);

        //TODO : Select course training type by id =========================================================
        @SelectProvider(value = EventCategoryProvider.class, method = "getCourseTrainingTypeId")
        CourseTrainingTypeDto getCourseTrainingTypeId(int courseTrainingTypeId);

        //TODO: Select event category  total count =========================================================
        @SelectProvider(value = EventCategoryProvider.class,method = "getEventCategoryTotalCount")
        Integer getEventCategoryTotalCount(@Param("name") EventCategoryFilter name);

        //TODO: Select event category by id =========================================================
        @SelectProvider(value = EventCategoryProvider.class, method = "selectById")
        @Results({
                @Result(column = "course_training_type_id", property = "courseTrainingTypeId", many = @Many(select = "getCourseTrainingTypeId"))
        })
        EventCategoryDto selectById(int id);


        //TODO : Insert event category =========================================================
        @InsertProvider(type = EventCategoryProvider.class,method = "insert")
        @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
        boolean insert(EventCategoryDto eventCategoryDto);

        //TODO : Delete event category =========================================================
        @DeleteProvider(type = EventCategoryProvider.class,method = "delete")
        boolean delete(int id);

        //TODO : Update event category =========================================================
        @UpdateProvider(type = EventCategoryProvider.class,method = "update")
        boolean update(Integer id,EventCategoryRequest eventCategoryRequest);

        //TODO: Select course training type name by id
        @SelectProvider(value = CourseTrainingProvider.class, method = "getCourseTrainingTypeName")
        String getCourseTrainingTypeName(int CourseTrainingTypeId);

        //TODO : Select event category after delete =========================================================
        @SelectProvider(value = EventCategoryProvider.class, method = "selectAfterDelete")
        @Results({
                @Result(column = "course_training_type_id", property = "courseTrainingTypeId", many = @Many(select = "getCourseTrainingTypeId"))
        })
        EventCategoryDto selectAfterDelete(int id);
}
