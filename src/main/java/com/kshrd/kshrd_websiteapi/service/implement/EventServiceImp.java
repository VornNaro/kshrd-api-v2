package com.kshrd.kshrd_websiteapi.service.implement;

import com.kshrd.kshrd_websiteapi.model.event.EventDto;
import com.kshrd.kshrd_websiteapi.model.event.EventRequest;
import com.kshrd.kshrd_websiteapi.model.event.EventResponse;
import com.kshrd.kshrd_websiteapi.repository.EventCategoryRepository;
import com.kshrd.kshrd_websiteapi.repository.EventRepository;
import com.kshrd.kshrd_websiteapi.repository.GenerationRepository;
import com.kshrd.kshrd_websiteapi.service.EventService;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImp implements EventService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    EventCategoryRepository eventCategoryRepository;

    @Autowired
    GenerationRepository generationRepository;

    //TODO: Select event by id =========================================================
    @Override
    public List<EventResponse> selectById(int id) {

        ModelMapper modelMapper = new ModelMapper();
        EventResponse eventResponse = new EventResponse();

        List<EventResponse> eventDtoList = eventRepository.selectById(id);

        for (EventResponse eventDto : eventDtoList) {
            eventDto.setEventDescription(eventRepository.selectAllDescriptionByEvent(eventDto.getId()));
        }

        List<EventResponse> eventDtoListWithCategoryAndGenerationName= new ArrayList<>();

        for (EventResponse eventDto : eventDtoList) {
            eventResponse = modelMapper.map(eventDto, EventResponse.class);
            eventDtoListWithCategoryAndGenerationName.add(eventResponse);
        }

        return eventDtoListWithCategoryAndGenerationName;
    }

    //TODO: Check if type exists =========================================================
    @Override
    public boolean checkIfTypeExisted(int id) {

        boolean existed=false;

        if(eventRepository.checkIfTypeExisted(id)!=null)
            existed=true;

        return existed;
    }

    //TODO: Check generation =========================================================
    @Override
    public boolean checkGeneration(int id) {

        boolean existed=false;

        if(eventRepository.checkGeneration(id)!=null)
            existed=true;

        return existed;
    }

    //TODO: Insert event =========================================================
    @Override
    public EventDto insert(EventDto eventDto) {

        boolean isInserted = eventRepository.insert(eventDto);

        if (isInserted) {

            int eventCategoryId = eventDto.getCategory().getId();
            int generationId = eventDto.getGeneration().getId();

            eventDto.getCategory().setName(eventRepository.getEventCategoryName(eventCategoryId));
            eventDto.getCategory().setCourseTrainingTypeId(eventRepository.getCourseTrainingType(eventCategoryId));
            eventDto.getGeneration().setName(eventRepository.getGenerationName(generationId));
            eventDto.getGeneration().setStartYear(eventRepository.getGenerationStartYear(generationId));
            eventDto.getGeneration().setEndYear(eventRepository.getGenerationEndYear(generationId));

            return eventDto;
        }
        else
            return null;
    }

    //TODO: Delete event =========================================================
    @Override
    public boolean delete(int id) {
        return eventRepository.delete(id);
    }

    //TODO: Update event =========================================================
    @Override
    public boolean update(int id, EventRequest eventRequest) {
        return eventRepository.update(id,eventRequest);
    }

    //TODO: Select all events =========================================================
    @Override
    public List<EventResponse> selectAllEvents(Pagination pagination) {

        ModelMapper modelMapper = new ModelMapper();
        EventResponse eventResponse = new EventResponse();
        pagination.setTotalCount(eventRepository.getEventTotalCount());
        List<EventDto> eventDtoList = eventRepository.selectAllEvents(pagination);

        for (EventDto eventDto : eventDtoList) {
            eventDto.setEventDescription(eventRepository.selectAllDescriptionByEvent(eventDto.getId()));
        }

        List<EventResponse> eventDtoListWithCategoryAndGenerationName= new ArrayList<>();

        for (EventDto eventDto : eventDtoList) {
            eventResponse = modelMapper.map(eventDto, EventResponse.class);
            eventDtoListWithCategoryAndGenerationName.add(eventResponse);
        }
        return eventDtoListWithCategoryAndGenerationName;
    }

    //TODO: Select all course events =========================================================
    @Override
    public List<EventResponse> selectAllCourseEvents(int id) {

        ModelMapper modelMapper = new ModelMapper();
        EventResponse eventResponse = new EventResponse();

        List<EventDto> eventDtoList = eventRepository.selectAllCourseEvents(id);

        for (EventDto eventDto : eventDtoList) {
            eventDto.setEventDescription(eventRepository.selectAllDescriptionByEvent(eventDto.getId()));
        }

        List<EventResponse> eventDtoListWithCategoryAndGenerationName= new ArrayList<>();

        for (EventDto eventDto : eventDtoList) {
            eventResponse = modelMapper.map(eventDto, EventResponse.class);
            eventDtoListWithCategoryAndGenerationName.add(eventResponse);
        }
        return eventDtoListWithCategoryAndGenerationName;
    }

    @Override
    public List<EventResponse> selectAfterDelete(int id) {
        return eventRepository.selectAfterDelete(id);
    }

    @Override
    public List<EventResponse> selectEventsWithGeneration(int id, int generationId) {
        ModelMapper modelMapper = new ModelMapper();
        EventResponse eventResponse = new EventResponse();

        List<EventDto> eventDtoList = eventRepository.selectEventsWithGeneration(id,generationId);

        for (EventDto eventDto : eventDtoList) {
            eventDto.setEventDescription(eventRepository.selectAllDescriptionByEvent(eventDto.getId()));
        }

        List<EventResponse> eventDtoListWithCategoryAndGenerationName= new ArrayList<>();

        for (EventDto eventDto : eventDtoList) {
            eventResponse = modelMapper.map(eventDto, EventResponse.class);
            eventDtoListWithCategoryAndGenerationName.add(eventResponse);
        }
        return eventDtoListWithCategoryAndGenerationName;
    }

}
