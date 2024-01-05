package com.kshrd.kshrd_websiteapi.repository.provider;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kshrd.kshrd_websiteapi.model.event.EventDto;
import com.kshrd.kshrd_websiteapi.model.event.EventRequest;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class EventProvider {

    //TODO: Select events with pagination
    public String select(@Param("page") Pagination pagination){

        return new SQL(){{
            SELECT("b.id, b.name, b.video, b.event_category_id, b.generation_id," +
                    "bt.id,bt.name AS category_name,bt.course_training_type_id,ct.id, ct.name AS training_type_name," +
                    "g.id, g.name AS generation_name, g.start_year As start, g.end_year As end");
            FROM("hrd_event AS b");
            INNER_JOIN("hrd_event_category AS bt ON b.event_category_id = bt.id");
            INNER_JOIN("hrd_course_training_type AS ct ON bt.course_training_type_id = ct.id");
            INNER_JOIN("hrd_generation AS g ON b.generation_id = g.id");
            ORDER_BY("b.id DESC LIMIT #{page.limit} OFFSET #{page.offset}");
            WHERE("b.status = true AND bt.status = true");
        }}.toString();
    }

    //TODO: Select all events
    public String selectAllCourseEvents(int id){

        return new SQL(){{
            SELECT("b.id, b.name, b.video, b.event_category_id, b.generation_id," +
                    "bt.id,bt.name AS category_name,bt.course_training_type_id,ct.id, ct.name AS training_type_name," +
                    "g.id, g.name AS generation_name, g.start_year As start, g.end_year As end");
            FROM("hrd_event AS b");
            INNER_JOIN("hrd_event_category AS bt ON b.event_category_id = bt.id");
            INNER_JOIN("hrd_course_training_type AS ct ON bt.course_training_type_id = ct.id");
            INNER_JOIN("hrd_generation AS g ON b.generation_id = g.id");
            WHERE("b.event_category_id != 4");
            AND();
            WHERE("ct.id = #{id}");
            AND();
            WHERE("b.status = true AND bt.status = true");
            ORDER_BY("b.id DESC");
        }}.toString();
    }

    //TODO: Select all description event
    public String selectAllDescriptionByEvent() {

        return new SQL(){{
            SELECT(" jsonb_array_elements(event_description)->> 'description' AS description, " +
                    "        jsonb_array_elements(event_description)->> 'image' AS image");
            FROM("hrd_event");
            WHERE("id = #{id}");
        }}.toString();
    }

    //TODO: Insert event
    public String insert(EventDto eventDto) {

        return new SQL(){{
            INSERT_INTO("hrd_event");
            VALUES("name", "#{name}");
            VALUES("event_description", "'" +convertJson(eventDto.getEventDescription())+ "'");
            VALUES("video", "#{video}");
            VALUES("event_category_id", "#{category.id}");
            VALUES("generation_id", "#{generation.id}");
            VALUES("status","true");
        }}.toString();
    }

    //TODO: Convert JSON
    private String convertJson(Object object) {

        ObjectMapper objectMapper = new ObjectMapper();
        String stringJson = "";
        try {
            stringJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return stringJson;
    }

    //TODO: Delete event by id
    public String delete(){

        return new SQL(){{
            UPDATE("hrd_event");
            SET("status = false");
            WHERE("id = #{id}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Select event by id
    public String selectById(){

        return new SQL(){{
            SELECT("b.id, b.name, b.video, b.event_category_id, b.generation_id," +
                    "bt.id,bt.name AS category_name,bt.course_training_type_id,ct.id, ct.name AS training_type_name," +
                    "g.id, g.name AS generation_name, g.start_year As start, g.end_year As end");
            FROM("hrd_event AS b");
            INNER_JOIN("hrd_event_category AS bt ON b.event_category_id = bt.id");
            INNER_JOIN("hrd_course_training_type AS ct ON bt.course_training_type_id = ct.id");
            INNER_JOIN("hrd_generation AS g ON b.generation_id = g.id");
            WHERE("b.id = #{id}");
            AND();
            WHERE("b.status = true AND bt.status = true");
        }}.toString();
    }

    //TODO: Update event by id
    public String update(int id,EventRequest eventRequest){

        return new SQL(){{
            UPDATE("hrd_event");
            SET("name = #{eventRequest.name}");
            SET("event_description = '" +convertJson(eventRequest.getEventDescription())+ "'");
            SET("video = #{eventRequest.video}");
            SET("event_Category_id = #{eventRequest.category.id}");
            SET("generation_id = #{eventRequest.generation.id}");
            WHERE("id = #{id}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Check type
    public String checkIfTypeExisted(){

        return new SQL(){{
            SELECT("id from hrd_event_category WHERE id=#{id}");
        }}.toString();
    }

    //TODO: Check generation
    public String checkGeneration(){

        return new SQL(){{
            SELECT("id from hrd_generation");
            WHERE("id = #{id}");
            AND();
            WHERE("hrd_generation.status = true");
        }}.toString();
    }

    //TODO: Select event category by id
    public String getEventCategory(int id){

        return new SQL(){{
            SELECT("*");
            FROM("hrd_event_category");
            WHERE("id = #{id}");
        }}.toString();
    }

    //TODO: Select event name by id
    public String getEventCategoryName(){
        return new  SQL(){{
            SELECT("name");
            FROM("hrd_event_category");
            WHERE("id = #{id}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Select course training type by id
    public String getCourseTrainingType(int id){
        return new SQL(){{
            SELECT("* FROM hrd_course_training_type");
            WHERE("id=#{id}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Select generation name by id
    public String getGenerationName(){
        return new  SQL(){{
            SELECT("name");
            FROM("hrd_generation");
            WHERE("id = #{id}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Select generation start year by id
    public String getGenerationStartYear(){
        return new  SQL(){{
            SELECT("start_year");
            FROM("hrd_generation");
            WHERE("id = #{id}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Select generation end year by id
    public String getGenerationEndYear(){
        return new  SQL(){{
            SELECT("end_year");
            FROM("hrd_generation");
            WHERE("id = #{id}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Select event by id
    public String selectAfterDelete(){

        return new SQL(){{
            SELECT("b.id, b.name, b.video, b.event_category_id, b.generation_id," +
                    "bt.id,bt.name AS category_name,bt.course_training_type_id,ct.id, ct.name AS training_type_name," +
                    "g.id, g.name AS generation_name, g.start_year As start, g.end_year As end");
            FROM("hrd_event AS b");
            INNER_JOIN("hrd_event_category AS bt ON b.event_category_id = bt.id");
            INNER_JOIN("hrd_course_training_type AS ct ON bt.course_training_type_id = ct.id");
            INNER_JOIN("hrd_generation AS g ON b.generation_id = g.id");
            WHERE("b.id = #{id}");
//            AND();
//            WHERE("b.status = true AND bt.status = true");
        }}.toString();
    }

    //TODO: Select all basic events with generation
    public String selectEventsWithGeneration(int id,int generationId){
        return new SQL(){{
            SELECT("b.id, b.name, b.video, b.event_category_id, b.generation_id," +
                    "bt.id,bt.name AS category_name,bt.course_training_type_id,ct.id, ct.name AS training_type_name," +
                    "g.id, g.name AS generation_name, g.start_year As start, g.end_year As end");
            FROM("hrd_event AS b");
            INNER_JOIN("hrd_event_category AS bt ON b.event_category_id = bt.id");
            INNER_JOIN("hrd_course_training_type AS ct ON bt.course_training_type_id = ct.id");
            INNER_JOIN("hrd_generation AS g ON b.generation_id = g.id");
            WHERE("b.event_category_id = #{id}");
            AND();
            WHERE("g.id = #{generationId}");
            AND();
            WHERE("b.status = true AND bt.status = true");
            ORDER_BY("b.id DESC");
        }}.toString();
    }

    //TODO: Count total count event  =========================================================
    public String getEventTotalCount(){

        return new SQL(){{
            SELECT("COUNT(id) FROM hrd_event");
            WHERE("status = true");
        }}.toString();
    }

}
