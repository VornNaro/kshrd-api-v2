package com.kshrd.kshrd_websiteapi.service;

import com.kshrd.kshrd_websiteapi.model.event_category.EventCategoryDto;
import com.kshrd.kshrd_websiteapi.model.event_category.EventCategoryFilter;
import com.kshrd.kshrd_websiteapi.model.event_category.EventCategoryRequest;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;

import java.util.List;

public interface EventCategoryService {

    //TODO: Select all event categories =========================================================
    List<EventCategoryDto> selectWithPagination(EventCategoryFilter name, Pagination pagination);

    //TODO: Select event category by id =========================================================
    EventCategoryDto selectById(int id);

    //TODO: Select event category after delete =========================================================
    EventCategoryDto selectAfterDelete(int id);

    //TODO: Insert event category =========================================================
    EventCategoryDto insert(EventCategoryDto eventCategoryDto);

    //TODO: Delete event category =========================================================
    boolean delete(int id);

    //TODO: Update event category =========================================================
    boolean update(Integer id,EventCategoryRequest eventCategoryRequest);
}
