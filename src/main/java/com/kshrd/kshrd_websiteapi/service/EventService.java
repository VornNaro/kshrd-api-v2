package com.kshrd.kshrd_websiteapi.service;

import com.kshrd.kshrd_websiteapi.model.alumni.AlumniDto;
import com.kshrd.kshrd_websiteapi.model.event.EventDto;
import com.kshrd.kshrd_websiteapi.model.event.EventRequest;
import com.kshrd.kshrd_websiteapi.model.event.EventResponse;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;

import java.util.List;

public interface EventService {

    //TODO: Select event by id =========================================================
    List<EventResponse>  selectById(int id);

    //TODO: Check if type exists =========================================================
    boolean checkIfTypeExisted(int id);

    //TODO: Check generation =========================================================
    boolean checkGeneration(int id);

    //TODO: Insert event =========================================================
    EventDto insert(EventDto eventDto);

    //TODO: Delete event =========================================================
    boolean delete(int id);

    //TODO: Update event =========================================================
    boolean update(int id, EventRequest eventRequest);

    //TODO: Select all events =========================================================
    List<EventResponse> selectAllEvents(Pagination pagination);

    //TODO: Select all course events =========================================================
    List<EventResponse> selectAllCourseEvents(int id);

    //TODO: Select event by id after delete data =========================================================
    List<EventResponse> selectAfterDelete(int id);

    //TODO: Select all course events with generation =========================================================
    List<EventResponse> selectEventsWithGeneration(int id,int generationId);

}
