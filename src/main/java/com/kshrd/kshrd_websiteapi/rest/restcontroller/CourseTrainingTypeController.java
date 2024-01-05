package com.kshrd.kshrd_websiteapi.rest.restcontroller;

import com.kshrd.kshrd_websiteapi.configuration.BaseAPI;
import com.kshrd.kshrd_websiteapi.rest.message.BaseApiResponse;
import com.kshrd.kshrd_websiteapi.model.course_training_type.CourseTrainingTypeDto;
import com.kshrd.kshrd_websiteapi.model.course_training_type.CourseTrainingTypeFilter;
import com.kshrd.kshrd_websiteapi.model.course_training_type.CourseTrainingTypeRequest;
import com.kshrd.kshrd_websiteapi.model.course_training_type.CourseTrainingTypeResponse;
import com.kshrd.kshrd_websiteapi.rest.message.BaseApiResponseWithPagination;
import com.kshrd.kshrd_websiteapi.rest.message.ErrorResponse;
import com.kshrd.kshrd_websiteapi.rest.message.MessageProperties;
import com.kshrd.kshrd_websiteapi.service.implement.CourseTrainingTypeServiceImp;
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
public class CourseTrainingTypeController {

    private CourseTrainingTypeServiceImp courseTrainingTypeServiceImp;

    @Autowired
    public void setCourseTrainingTypeServiceImp(CourseTrainingTypeServiceImp courseTrainingTypeServiceImp) {
        this.courseTrainingTypeServiceImp = courseTrainingTypeServiceImp;
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

        response.setMessage(messageProperties.insertError("Course training type"));
        response.setError(errors);
        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setTime(new Timestamp(System.currentTimeMillis()));

        return ResponseEntity.ok(response);
    }

    //TODO: Get course training type =========================================================
    @GetMapping("/course-training-types")
    public ResponseEntity<BaseApiResponseWithPagination<List<CourseTrainingTypeResponse>>> selectCourseTrainingType(CourseTrainingTypeFilter name, Pagination pagination){

        ModelMapper modelMapper = new ModelMapper();

        List<CourseTrainingTypeDto> courseTrainingTypeDtos = courseTrainingTypeServiceImp.selectWithPagination(name,pagination);

        List<CourseTrainingTypeResponse> courseTrainingTypeResponses = new ArrayList<>();

        for ( CourseTrainingTypeDto courseTrainingTypeDto: courseTrainingTypeDtos){
            courseTrainingTypeResponses.add(modelMapper.map(courseTrainingTypeDto,CourseTrainingTypeResponse.class));
        }
        BaseApiResponseWithPagination<List<CourseTrainingTypeResponse>> response = new BaseApiResponseWithPagination<>();

        if(courseTrainingTypeResponses.isEmpty()){

            response.setMessage(messageProperties.hasNoRecords("Course training type"));
            response.setData(new ArrayList<>());
            response.setPagination(pagination);
            response.setStatus(HttpStatus.NO_CONTENT);
        }
        else {
            response.setMessage(messageProperties.selected("Course training type"));
            response.setData(courseTrainingTypeResponses);
            response.setPagination(pagination);
            response.setStatus(HttpStatus.OK);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


    //TODO: Get course training type by id =========================================================
    @GetMapping("/course-training-types/{id}")
    public ResponseEntity<BaseApiResponse<CourseTrainingTypeResponse>> selectCourseTrainingTypeById(@PathVariable int id) {

        BaseApiResponse<CourseTrainingTypeResponse> courseTrainingTypeResponse = new BaseApiResponse<>();
        ModelMapper modelMapper = new ModelMapper();

        CourseTrainingTypeDto courseTrainingTypeDto = courseTrainingTypeServiceImp.selectById(id);

        if(courseTrainingTypeDto==null){
            courseTrainingTypeResponse.setMessage(messageProperties.hasNoRecord("Course training type"));
            courseTrainingTypeResponse.setStatus(HttpStatus.NO_CONTENT);
        }
        else {
            CourseTrainingTypeResponse response =  modelMapper.map(courseTrainingTypeDto,CourseTrainingTypeResponse.class);
            courseTrainingTypeResponse.setMessage(messageProperties.selectedOne("Course training type"));
            courseTrainingTypeResponse.setData(response);
            courseTrainingTypeResponse.setStatus(HttpStatus.OK);
        }

        courseTrainingTypeResponse.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(courseTrainingTypeResponse);
    }

    //TODO: Post course training type =========================================================
    @PostMapping("/course-training-types")
    public ResponseEntity<BaseApiResponse<CourseTrainingTypeResponse>> insertCourseTrainingType(@Valid  @RequestBody CourseTrainingTypeRequest courseTrainingTypeRequest){

        BaseApiResponse<CourseTrainingTypeResponse> response = new BaseApiResponse<>();
        ModelMapper mapper = new ModelMapper();

        CourseTrainingTypeDto courseTrainingTypeDto = mapper.map(courseTrainingTypeRequest, CourseTrainingTypeDto.class);

        if(courseTrainingTypeRequest.getName().isEmpty()){
            response.setMessage(messageProperties.insertError("Course training type"));
            response.setStatus(HttpStatus.BAD_REQUEST);
        }
        else{

            CourseTrainingTypeDto result = courseTrainingTypeServiceImp.insert(courseTrainingTypeDto);
            CourseTrainingTypeResponse courseTrainingTypeResponse = mapper.map(result, CourseTrainingTypeResponse.class);

            response.setMessage(messageProperties.inserted("Course training type"));
            courseTrainingTypeResponse.setId(result.getId());
            response.setData(courseTrainingTypeResponse);
            response.setStatus(HttpStatus.CREATED);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


    //TODO: Update course training type =========================================================
    @PutMapping("/course-training-types/{id}")
    private ResponseEntity<BaseApiResponse<CourseTrainingTypeDto>> updateCourseTrainingType(
            @PathVariable int id,
            @Valid @RequestBody CourseTrainingTypeRequest courseTrainingTypeRequest) {


        BaseApiResponse<CourseTrainingTypeDto> response = new BaseApiResponse<>();

        if(courseTrainingTypeServiceImp.update(id,courseTrainingTypeRequest)){

            CourseTrainingTypeDto courseTrainingTypeDto= courseTrainingTypeServiceImp.selectById(id);

            response.setMessage(messageProperties.updated("Course training type"));
            response.setData(courseTrainingTypeDto);
            response.setStatus(HttpStatus.OK);
        }
        else{
            response.setMessage(messageProperties.updatedError("Course training type"));
            response.setStatus(HttpStatus.NO_CONTENT);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    //TODO: Delete course training type =========================================================
    @DeleteMapping("/course-training-types/{id}")
    public ResponseEntity<BaseApiResponse<CourseTrainingTypeDto>> deleteCourseTrainingType(@PathVariable int id){

        BaseApiResponse<CourseTrainingTypeDto> response = new BaseApiResponse<>();

        if(courseTrainingTypeServiceImp.delete(id)){

            CourseTrainingTypeDto courseTrainingTypeDto = courseTrainingTypeServiceImp.selectAfterDelete(id);

            response.setMessage(messageProperties.deleted("Course training type"));
            response.setData(courseTrainingTypeDto);
            response.setStatus(HttpStatus.OK);
        }
        else{
            response.setMessage(messageProperties.deletedError("Course training type","course training type"));
            response.setStatus(HttpStatus.NO_CONTENT);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }
}
