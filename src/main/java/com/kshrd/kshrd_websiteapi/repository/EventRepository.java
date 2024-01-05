package com.kshrd.kshrd_websiteapi.repository;

import com.kshrd.kshrd_websiteapi.model.course_training_type.CourseTrainingTypeDto;
import com.kshrd.kshrd_websiteapi.model.event.EventDescription;
import com.kshrd.kshrd_websiteapi.model.event.EventDto;
import com.kshrd.kshrd_websiteapi.model.event.EventRequest;
import com.kshrd.kshrd_websiteapi.model.event.EventResponse;
import com.kshrd.kshrd_websiteapi.model.event_category.EventCategoryDto;
import com.kshrd.kshrd_websiteapi.model.event_category.EventCategoryFilter;
import com.kshrd.kshrd_websiteapi.repository.provider.*;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository {

    //TODO : Select all events with pagination =========================================================
    @SelectProvider(value = EventProvider.class,method = "select")
    @Results({
            @Result(column = "event_category_id", property = "category.id"),
            @Result(column = "category_name", property = "category.name"),
            @Result(column = "course_training_type_id", property = "category.courseTrainingTypeId.id"),
            @Result(column = "training_type_name", property = "category.courseTrainingTypeId.name"),
            @Result(column = "generation_id", property = "generation.id"),
            @Result(column = "generation_name", property = "generation.name"),
            @Result(column = "start", property = "generation.startYear"),
            @Result(column = "end", property = "generation.endYear")
    })
    List<EventDto> selectAllEvents(@Param("page") Pagination pagination);

    //TODO : Select all description by event =========================================================
    @SelectProvider(value = EventProvider.class, method = "selectAllDescriptionByEvent")
    List<EventDescription> selectAllDescriptionByEvent(int eventId);

    //TODO : Insert event =========================================================
    @InsertProvider(value = EventProvider.class, method = "insert")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    boolean insert(EventDto eventDto);

    //TODO :Delete event =========================================================
    @DeleteProvider(value = EventProvider.class, method = "delete")
    boolean delete(int id);

    //TODO : Select event by id =========================================================
    @SelectProvider(value = EventProvider.class, method = "selectById")
    @Results({
            @Result(column = "event_category_id", property = "category.id"),
            @Result(column = "category_name", property = "category.name"),
            @Result(column = "course_training_type_id", property = "category.courseTrainingTypeId.id"),
            @Result(column = "training_type_name", property = "category.courseTrainingTypeId.name"),
            @Result(column = "generation_id", property = "generation.id"),
            @Result(column = "generation_name", property = "generation.name"),
            @Result(column = "start", property = "generation.startYear"),
            @Result(column = "end", property = "generation.endYear")
    })
    List<EventResponse> selectById(int eventId);

    //TODO : Update event =========================================================
    @UpdateProvider(value = EventProvider.class, method = "update")
    boolean update(int id, EventRequest eventRequest);

    //TODO : Select all course events =========================================================
    @SelectProvider(value = EventProvider.class,method = "selectAllCourseEvents")
    @Results({
            @Result(column = "event_category_id", property = "category.id"),
            @Result(column = "category_name", property = "category.name"),
            @Result(column = "course_training_type_id", property = "category.courseTrainingTypeId.id"),
            @Result(column = "training_type_name", property = "category.courseTrainingTypeId.name"),
            @Result(column = "generation_id", property = "generation.id"),
            @Result(column = "generation_name", property = "generation.name"),
            @Result(column = "start", property = "generation.startYear"),
            @Result(column = "end", property = "generation.endYear")
    })
    List<EventDto> selectAllCourseEvents(int id);

    //TODO : Check if type exists =========================================================
    @SelectProvider(value = EventProvider.class,method = "checkIfTypeExisted")
    Object checkIfTypeExisted(int id);

    //TODO : Check generation =========================================================
    @SelectProvider(value = EventProvider.class,method = "checkGeneration")
    Object checkGeneration(int id);

    //TODO : Select event catetory =========================================================
    @SelectProvider(value = EventProvider.class, method = "getEventCategory")
    EventCategoryDto getEventCategory(int eventCategoryId);


    @SelectProvider(value = EventProvider.class, method = "getEventCategoryName")
    String getEventCategoryName(int id);

    @SelectProvider(value = EventProvider.class, method = "getCourseTrainingType")
    CourseTrainingTypeDto getCourseTrainingType(int id);

    @SelectProvider(value = EventProvider.class, method = "getGenerationName")
    String getGenerationName(int id);

    @SelectProvider(value = EventProvider.class, method = "getGenerationStartYear")
    String getGenerationStartYear(int id);

    @SelectProvider(value = EventProvider.class, method = "getGenerationEndYear")
    String getGenerationEndYear(int id);

    //TODO: Select after delete
    @SelectProvider(value = EventProvider.class, method = "selectAfterDelete")
    @Results({
            @Result(column = "event_category_id", property = "category.id"),
            @Result(column = "category_name", property = "category.name"),
            @Result(column = "course_training_type_id", property = "category.courseTrainingTypeId.id"),
            @Result(column = "training_type_name", property = "category.courseTrainingTypeId.name"),
            @Result(column = "generation_id", property = "generation.id"),
            @Result(column = "generation_name", property = "generation.name"),
            @Result(column = "start", property = "generation.startYear"),
            @Result(column = "end", property = "generation.endYear")
    })
    List<EventResponse> selectAfterDelete(int id);

    @SelectProvider(value = EventProvider.class, method = "selectEventsWithGeneration")
    @Results({
            @Result(column = "event_category_id", property = "category.id"),
            @Result(column = "category_name", property = "category.name"),
            @Result(column = "course_training_type_id", property = "category.courseTrainingTypeId.id"),
            @Result(column = "training_type_name", property = "category.courseTrainingTypeId.name"),
            @Result(column = "generation_id", property = "generation.id"),
            @Result(column = "generation_name", property = "generation.name"),
            @Result(column = "start", property = "generation.startYear"),
            @Result(column = "end", property = "generation.endYear")
    })
    List<EventDto> selectEventsWithGeneration(int id,int generationId);

    //TODO: Select event category  total count =========================================================
    @SelectProvider(value = EventProvider.class,method = "getEventTotalCount")
    Integer getEventTotalCount();

}
