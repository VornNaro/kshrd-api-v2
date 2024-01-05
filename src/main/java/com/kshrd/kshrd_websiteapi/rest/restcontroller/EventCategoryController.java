package com.kshrd.kshrd_websiteapi.rest.restcontroller;

import com.kshrd.kshrd_websiteapi.configuration.BaseAPI;
import com.kshrd.kshrd_websiteapi.rest.message.BaseApiResponse;
import com.kshrd.kshrd_websiteapi.model.event_category.EventCategoryDto;
import com.kshrd.kshrd_websiteapi.model.event_category.EventCategoryFilter;
import com.kshrd.kshrd_websiteapi.model.event_category.EventCategoryRequest;
import com.kshrd.kshrd_websiteapi.model.event_category.EventCategoryResponse;
import com.kshrd.kshrd_websiteapi.rest.message.BaseApiResponseWithPagination;
import com.kshrd.kshrd_websiteapi.rest.message.ErrorResponse;
import com.kshrd.kshrd_websiteapi.rest.message.MessageProperties;
import com.kshrd.kshrd_websiteapi.service.implement.EventCategoryServiceImp;
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
public class EventCategoryController {

    private EventCategoryServiceImp eventCategoryServiceImp;

    @Autowired
    public void setEventCategoryServiceImp(EventCategoryServiceImp eventCategoryServiceImp) {
        this.eventCategoryServiceImp = eventCategoryServiceImp;
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

        response.setMessage(messageProperties.insertError("Event category"));
        response.setError(errors);
        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setTime(new Timestamp(System.currentTimeMillis()));

        return ResponseEntity.ok(response);
    }

    //TODO: Get event category =========================================================
    @GetMapping("/event-categories")
    public ResponseEntity<BaseApiResponseWithPagination<List<EventCategoryResponse>>> selectEventCategory(EventCategoryFilter name, Pagination pagination){

        BaseApiResponseWithPagination<List<EventCategoryResponse>> response = new BaseApiResponseWithPagination<>();
        ModelMapper mapper = new ModelMapper();

        List<EventCategoryDto> eventCategoryDtoList = eventCategoryServiceImp.selectWithPagination(name,pagination);

        List<EventCategoryResponse> eventCategoryResponses = new ArrayList<>();

        for (EventCategoryDto eventCategoryDto : eventCategoryDtoList) {
            eventCategoryResponses.add(mapper.map(eventCategoryDto, EventCategoryResponse.class));
        }
        if(eventCategoryResponses.isEmpty()){
            response.setMessage(messageProperties.hasNoRecords("Event category"));
            response.setData(new ArrayList<>());
            response.setPagination(pagination);
            response.setStatus(HttpStatus.NO_CONTENT);
        }
        else{
            response.setMessage(messageProperties.selected("Event category"));
            response.setData(eventCategoryResponses);
            response.setPagination(pagination);
            response.setStatus(HttpStatus.OK);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    //TODO: Get event category by id =========================================================
    @GetMapping("/event-categories/{id}")
    public ResponseEntity<BaseApiResponse<EventCategoryResponse>> selectEventCategoryById(@PathVariable int id) {

        BaseApiResponse<EventCategoryResponse> eventCategoryResponse = new BaseApiResponse<>();
        ModelMapper modelMapper = new ModelMapper();

        EventCategoryDto eventCategoryDto = eventCategoryServiceImp.selectById(id);

        if(eventCategoryDto==null){
            eventCategoryResponse.setMessage(messageProperties.hasNoRecord("Event category"));
            eventCategoryResponse.setStatus(HttpStatus.NO_CONTENT);
        }
        else {

            EventCategoryResponse response =  modelMapper.map(eventCategoryDto,EventCategoryResponse.class);

            eventCategoryResponse.setMessage(messageProperties.selectedOne("Event category"));
            eventCategoryResponse.setData(response);
            eventCategoryResponse.setStatus(HttpStatus.OK);
        }

        eventCategoryResponse.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(eventCategoryResponse);
    }

    //TODO: Post event category =========================================================
    @PostMapping("/event-categories")
    public ResponseEntity<BaseApiResponse<EventCategoryResponse>> insertEventCategory(
            @Valid @RequestBody EventCategoryRequest eventCategory) {

        BaseApiResponse<EventCategoryResponse> response = new BaseApiResponse<>();
        ModelMapper mapper = new ModelMapper();

        if(eventCategory.getCourseTrainingTypeId().getId()==0){
            response.setMessage(messageProperties.insertError("Event category"));
            response.setStatus(HttpStatus.BAD_REQUEST);
        }
        else if(eventCategory.getName().isEmpty()){
            response.setMessage(messageProperties.insertError("Event category"));
            response.setStatus(HttpStatus.BAD_REQUEST);
        }
        else{

            EventCategoryDto eventCategoryRequest = mapper.map(eventCategory, EventCategoryDto.class);
            EventCategoryDto result = eventCategoryServiceImp.insert(eventCategoryRequest);
            EventCategoryResponse eventCategoryResponse = mapper.map(result, EventCategoryResponse.class);

            response.setMessage(messageProperties.inserted("Event category"));
            eventCategoryResponse.setId(result.getId());
            response.setData(eventCategoryResponse);
            response.setStatus(HttpStatus.CREATED);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);

    }

    //TODO: Delete event category =========================================================
    @DeleteMapping("/event-categories/{id}")
    public ResponseEntity<BaseApiResponse<EventCategoryDto>> deleteEventCategory(@PathVariable int id){

        BaseApiResponse<EventCategoryDto> response = new BaseApiResponse<>();

        if(eventCategoryServiceImp.delete(id)){

            EventCategoryDto eventCategoryResponse = eventCategoryServiceImp.selectAfterDelete(id);

            response.setMessage(messageProperties.deleted("Event category"));
            response.setData(eventCategoryResponse);
            response.setStatus(HttpStatus.OK);
        }
        else{

            response.setMessage(messageProperties.deletedError("Event category","event category"));
            response.setStatus(HttpStatus.NO_CONTENT);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    //TODO: Update event category =========================================================
    @PutMapping("/event-categories/{id}")
    public ResponseEntity<BaseApiResponse<EventCategoryDto>> updateEventCategory(
            @PathVariable Integer id,
            @Valid @RequestBody EventCategoryRequest eventCategoryRequest){

        BaseApiResponse<EventCategoryDto> response = new BaseApiResponse<>();

        if(eventCategoryServiceImp.update(id,eventCategoryRequest)){

            EventCategoryDto eventCategoryDto = eventCategoryServiceImp.selectById(id);

            response.setMessage(messageProperties.updated("Event category"));
            response.setData(eventCategoryDto);
            response.setStatus(HttpStatus.OK);
        }
        else{
            response.setMessage(messageProperties.updatedError("Event category"));
            response.setStatus(HttpStatus.NO_CONTENT);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

}
