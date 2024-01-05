package com.kshrd.kshrd_websiteapi.rest.restcontroller;

import com.kshrd.kshrd_websiteapi.configuration.BaseAPI;
import com.kshrd.kshrd_websiteapi.rest.message.BaseApiResponse;
import com.kshrd.kshrd_websiteapi.model.event.EventDto;
import com.kshrd.kshrd_websiteapi.model.event.EventRequest;
import com.kshrd.kshrd_websiteapi.model.event.EventResponse;
import com.kshrd.kshrd_websiteapi.rest.message.BaseApiResponseWithPagination;
import com.kshrd.kshrd_websiteapi.rest.message.ErrorResponse;
import com.kshrd.kshrd_websiteapi.rest.message.MessageProperties;
import com.kshrd.kshrd_websiteapi.service.implement.EventServiceImp;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*",allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping(value = BaseAPI.BASE_API_URL)
public class EventController {
    private EventServiceImp eventServiceImp;
    @Autowired
    public void setEventServiceImp(EventServiceImp eventServiceImp) {
        this.eventServiceImp = eventServiceImp;
    }

    private MessageProperties messageProperties;

    @Autowired
    public void setMessageProperties(MessageProperties messageProperties) {
        this.messageProperties = messageProperties;
    }

    //TODO: Exception =========================================================
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {

        ErrorResponse response = new ErrorResponse();
        List<Object> errors = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            Map<String, String> objectError = new HashMap<>();
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            objectError.put("field", fieldName);
            objectError.put("message", errorMessage);
            errors.add(objectError);
        });

        response.setMessage(messageProperties.insertError("Event"));
        response.setError(errors);
        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setTime(new Timestamp(System.currentTimeMillis()));

        return ResponseEntity.ok(response);
    }

    //TODO: Select all events by id =========================================================
    @GetMapping("/events")
    public ResponseEntity<BaseApiResponseWithPagination<List<EventResponse>>> selectAllEvents(Pagination pagination){
        BaseApiResponseWithPagination<List<EventResponse>> response = new BaseApiResponseWithPagination<>();

        List<EventResponse> eventResponsesList = eventServiceImp.selectAllEvents(pagination);

        if (eventResponsesList.isEmpty()) {

            response.setMessage(messageProperties.selectedError("Event"));
            response.setData(new ArrayList<>());
            response.setStatus(HttpStatus.NO_CONTENT);
        } else {

            response.setMessage(messageProperties.selected("Event"));
            response.setData(eventResponsesList);
            response.setPagination(pagination);
            response.setStatus(HttpStatus.OK);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    //TODO: Select all basic events by id =========================================================
    @GetMapping("/course-training-types/{id}/events")
    public ResponseEntity<BaseApiResponse<List<EventResponse>>> selectAllEventsByCourseTrainingTypeId(@PathVariable int id){

        BaseApiResponse<List<EventResponse>> response = new BaseApiResponse<>();

        List<EventResponse> eventResponsesList = eventServiceImp.selectAllCourseEvents(id);

        if (eventResponsesList.isEmpty()) {

            response.setMessage(messageProperties.selectedError("Event"));
            response.setData(new ArrayList<>());
            response.setStatus(HttpStatus.NO_CONTENT);

        } else {

            response.setMessage(messageProperties.selected("Event"));
            response.setData(eventResponsesList);
            response.setStatus(HttpStatus.OK);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    //TODO: Select event by id =========================================================
    @GetMapping("/events/{id}")
    public ResponseEntity<BaseApiResponse<List<EventResponse>>> selectEventById(@PathVariable int id) {

        BaseApiResponse<List<EventResponse>> response = new BaseApiResponse<>();

        List<EventResponse> eventResponsesList = eventServiceImp.selectById(id);

        if (eventResponsesList.isEmpty()) {

            response.setMessage(messageProperties.selectedError("Event"));
            response.setData(new ArrayList<>());

        } else {

            response.setMessage(messageProperties.selectedOne("Event"));
            response.setData(eventResponsesList);
        }

        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    //TODO: Post event =========================================================
    @PostMapping("/events")
    public ResponseEntity<BaseApiResponse<EventResponse>> insertEvent(
            @Valid @RequestBody  EventRequest eventRequest) {

        BaseApiResponse<EventResponse> response = new BaseApiResponse<>();

        try {

            ModelMapper mapper = new ModelMapper();
            EventDto eventDto = mapper.map(eventRequest, EventDto.class);

            int checkType = eventRequest.getCategory().getId();
            int checkGeneration = eventRequest.getGeneration().getId();

            if (eventServiceImp.checkIfTypeExisted(checkType)) {
                if(eventServiceImp.checkGeneration(checkGeneration)){

                    EventDto result = eventServiceImp.insert(eventDto);
                    EventResponse eventResponse = mapper.map(result, EventResponse.class);

                    response.setMessage(messageProperties.inserted("Event"));
                    eventResponse.setId(result.getId());

                    response.setData(eventResponse);
                    response.setStatus(HttpStatus.OK);
                }

                else{

                    response.setMessage(messageProperties.insertedTypeNotFound("Event","generation"));
                    response.setStatus(HttpStatus.NO_CONTENT);
                }

            } else {

                response.setMessage(messageProperties.insertedTypeNotFound("Event","event category"));
                response.setStatus(HttpStatus.NO_CONTENT);
            }

        }catch (IllegalArgumentException e){

            response.setMessage(messageProperties.insertError("Event"));
            response.setStatus(HttpStatus.NO_CONTENT);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    //TODO: Delete event =========================================================
    @DeleteMapping("/events/{id}")
    public ResponseEntity<BaseApiResponse<List<EventResponse>>> deleteEvent(@PathVariable Integer id){

        BaseApiResponse<List<EventResponse>> response = new BaseApiResponse<>();

        if(eventServiceImp.delete(id)){

            List<EventResponse> generationDto = eventServiceImp.selectAfterDelete(id);
            response.setMessage(messageProperties.deleted("Event"));
            response.setData(generationDto);
            response.setStatus(HttpStatus.OK);
        }
        else{

            response.setMessage(messageProperties.deletedError("Event","event"));
            response.setData(new ArrayList<>());
            response.setStatus(HttpStatus.NO_CONTENT);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    //TODO: Update event =========================================================
    @PutMapping("/events/{id}")
    public ResponseEntity<BaseApiResponse<List<EventResponse>>> updateEvent(
            @PathVariable Integer id,
            @Valid @RequestBody EventRequest eventRequest){

        BaseApiResponse<List<EventResponse>> response = new BaseApiResponse<>();

        try {

            int checkType = eventRequest.getCategory().getId();
            int checkGeneration = eventRequest.getGeneration().getId();

            if (eventServiceImp.checkIfTypeExisted(checkType)) {
                if (eventServiceImp.checkGeneration(checkGeneration)) {

                    eventServiceImp.update(id, eventRequest);
                    List<EventResponse> eventDto = eventServiceImp.selectById(id);
                    response.setMessage(messageProperties.updated("Event"));
                    response.setData(eventDto);
                    response.setStatus(HttpStatus.OK);

                } else {

                    response.setMessage(messageProperties.updatedTypeNotFound("Event","generation"));
                    response.setData(new ArrayList<>());
                    response.setStatus(HttpStatus.NO_CONTENT);
                }
            } else {

                response.setMessage(messageProperties.updatedTypeNotFound("Event","event category"));
                response.setData(new ArrayList<>());
                response.setStatus(HttpStatus.NO_CONTENT);
            }
        }catch (IllegalArgumentException e){

            response.setMessage(messageProperties.updatedError("Event"));
            response.setData(new ArrayList<>());
            response.setStatus(HttpStatus.BAD_REQUEST);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    //TODO: Select all basic events by id =========================================================
    @GetMapping("/generation/{generationId}/event-category/{id}")
    public ResponseEntity<BaseApiResponse<List<EventResponse>>> selectEventsWithGeneration(
            @PathVariable int id, @PathVariable int generationId){

        BaseApiResponse<List<EventResponse>> response = new BaseApiResponse<>();

        List<EventResponse> eventResponsesList = eventServiceImp.selectEventsWithGeneration(id,generationId);

        if (eventResponsesList.isEmpty()) {

            response.setMessage(messageProperties.selectedError("Event"));
            response.setData(new ArrayList<>());
            response.setStatus(HttpStatus.NO_CONTENT);

        } else {

            response.setMessage(messageProperties.selected("Event"));
            response.setData(eventResponsesList);
            response.setStatus(HttpStatus.OK);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


}
