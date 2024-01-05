package com.kshrd.kshrd_websiteapi.service.implement;

import com.kshrd.kshrd_websiteapi.model.event_category.EventCategoryDto;
import com.kshrd.kshrd_websiteapi.model.event_category.EventCategoryFilter;
import com.kshrd.kshrd_websiteapi.model.event_category.EventCategoryRequest;
import com.kshrd.kshrd_websiteapi.repository.EventCategoryRepository;
import com.kshrd.kshrd_websiteapi.service.EventCategoryService;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventCategoryServiceImp implements EventCategoryService {

    private EventCategoryRepository eventCategoryRepository;

    @Autowired
    public void setEventCategoryRepository(EventCategoryRepository eventCategoryRepository) {

        this.eventCategoryRepository = eventCategoryRepository;
    }

    //TODO: Select all event categories =========================================================
    @Override
    public List<EventCategoryDto> selectWithPagination(EventCategoryFilter name, Pagination pagination) {

        pagination.setTotalCount(eventCategoryRepository.getEventCategoryTotalCount(name));
        return eventCategoryRepository.selectWithPagination(name,pagination);
    }

    //TODO: Select event category by id =========================================================
    @Override
    public EventCategoryDto selectById(int id) {
        return eventCategoryRepository.selectById(id);
    }

    //TODO: Select event category after delete =========================================================
    @Override
    public EventCategoryDto selectAfterDelete(int id) {
        return eventCategoryRepository.selectAfterDelete(id);
    }

    //TODO: Insert event category =========================================================
    @Override
    public EventCategoryDto insert(EventCategoryDto eventCategoryDto) {

        eventCategoryDto.setStatus(true);
        boolean isInserted = eventCategoryRepository.insert(eventCategoryDto);
        if (isInserted) {
            int id = eventCategoryDto.getCourseTrainingTypeId().getId();
            eventCategoryDto.getCourseTrainingTypeId().setName(eventCategoryRepository.getCourseTrainingTypeName(id));
            return eventCategoryDto;
        }
        else
            return null;
    }

    //TODO: Delete event category =========================================================
    @Override
    public boolean delete(int id) {
        return eventCategoryRepository.delete(id);
    }

    //TODO: Update event category =========================================================
    @Override
    public boolean update(Integer id, EventCategoryRequest eventCategoryRequest) {

        return eventCategoryRepository.update(id,eventCategoryRequest);
    }
}
