package com.kshrd.kshrd_websiteapi.rest.restcontroller;

import com.kshrd.kshrd_websiteapi.configuration.BaseAPI;
import com.kshrd.kshrd_websiteapi.rest.message.BaseApiResponse;
import com.kshrd.kshrd_websiteapi.model.course_training.CourseTrainingDto;
import com.kshrd.kshrd_websiteapi.model.course_training.CourseTrainingFilter;
import com.kshrd.kshrd_websiteapi.model.course_training.CourseTrainingRequest;
import com.kshrd.kshrd_websiteapi.model.course_training.CourseTrainingResponse;
import com.kshrd.kshrd_websiteapi.rest.message.BaseApiResponseWithPagination;
import com.kshrd.kshrd_websiteapi.rest.message.ErrorResponse;
import com.kshrd.kshrd_websiteapi.rest.message.MessageProperties;
import com.kshrd.kshrd_websiteapi.service.implement.CourseTrainingServiceImp;
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
public class CourseTrainingController {

    private CourseTrainingServiceImp courseTrainingServiceImp;

    @Autowired
    public void setCourseTrainingServiceImp(CourseTrainingServiceImp courseTrainingServiceImp) {
        this.courseTrainingServiceImp = courseTrainingServiceImp;
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

        response.setMessage(messageProperties.insertError("Course training"));
        response.setError(errors);
        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setTime(new Timestamp(System.currentTimeMillis()));

        return ResponseEntity.ok(response);
    }

    //TODO: Get course training =========================================================
    @GetMapping("/course-trainings")
    public ResponseEntity<BaseApiResponseWithPagination<List<CourseTrainingResponse>>> selectCourseTraining(@RequestParam(value = "courseTypeId",required = false) Integer courseTypeId, CourseTrainingFilter name, Pagination pagination){

        ModelMapper modelMapper = new ModelMapper();
        BaseApiResponseWithPagination<List<CourseTrainingResponse>> response = new BaseApiResponseWithPagination<>();
        List<CourseTrainingResponse> courseTrainingResponses = new ArrayList<>();

        List<CourseTrainingDto> courseTrainingDtos= courseTrainingServiceImp.selectWithPagination(courseTypeId,name,pagination);

        for(CourseTrainingDto dto : courseTrainingDtos){
            courseTrainingResponses.add(modelMapper.map(dto,CourseTrainingResponse.class));
        }

        if(courseTrainingResponses.isEmpty()){

            response.setMessage(messageProperties.hasNoRecords("Course training"));
            response.setData(new ArrayList<>());
            response.setPagination(pagination);
            response.setStatus(HttpStatus.NO_CONTENT);
        }
        else {

            response.setMessage(messageProperties.selected("Course training"));
            response.setData(courseTrainingResponses);
            response.setPagination(pagination);
            response.setStatus(HttpStatus.OK);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


    //TODO: Get course training by id =========================================================
    @GetMapping("/course-trainings/{id}")
    public ResponseEntity<BaseApiResponse<CourseTrainingResponse>> selectCourseTrainingById(@PathVariable int id) {

        BaseApiResponse<CourseTrainingResponse> courseTrainingResponse = new BaseApiResponse<>();
        ModelMapper modelMapper = new ModelMapper();

        CourseTrainingDto courseTrainingDto = courseTrainingServiceImp.selectById(id);

        if(courseTrainingDto==null){

            courseTrainingResponse.setMessage(messageProperties.hasNoRecord("Course training"));
            courseTrainingResponse.setStatus(HttpStatus.NO_CONTENT);
        }
        else {

            CourseTrainingResponse response =  modelMapper.map(courseTrainingDto,CourseTrainingResponse.class);

            courseTrainingResponse.setMessage(messageProperties.selectedOne("Course training"));
            courseTrainingResponse.setData(response);
            courseTrainingResponse.setStatus(HttpStatus.OK);
        }

        courseTrainingResponse.setStatus(HttpStatus.OK);
        courseTrainingResponse.setTime(new Timestamp(System.currentTimeMillis()));

        return ResponseEntity.ok(courseTrainingResponse);
    }

    //TODO: Post course training =========================================================
    @PostMapping("/course-trainings")
    public ResponseEntity<BaseApiResponse<CourseTrainingResponse>> insertCourseTraining(@Valid  @RequestBody CourseTrainingRequest courseTrainingRequest){

        BaseApiResponse<CourseTrainingResponse> response = new BaseApiResponse<>();

        try {
            ModelMapper mapper = new ModelMapper();

            CourseTrainingDto courseTrainingDto = mapper.map(courseTrainingRequest, CourseTrainingDto.class);

            int checkType = courseTrainingRequest.getCourseTrainingTypeId().getId();

            if (!courseTrainingServiceImp.checkIfTypeExisted(checkType)) {

                response.setMessage(messageProperties.insertedTypeNotFound("Course training","course training type"));
                response.setStatus(HttpStatus.BAD_REQUEST);
            }
            else {

                CourseTrainingDto result = courseTrainingServiceImp.insert(courseTrainingDto);
                CourseTrainingResponse courseTrainingResponse = mapper.map(result, CourseTrainingResponse.class);

                response.setMessage(messageProperties.inserted("Course training"));
                courseTrainingResponse.setId(result.getId());
                response.setData(courseTrainingResponse);
                response.setStatus(HttpStatus.CREATED);
            }

        }catch (IllegalArgumentException e){

            response.setMessage(messageProperties.insertError("Course training"));
            response.setStatus(HttpStatus.BAD_REQUEST);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    //TODO: Update course training =========================================================
    @PutMapping("/course-trainings/{id}")
    private ResponseEntity<BaseApiResponse<CourseTrainingDto>> updateCourseTraining(
            @PathVariable int id,
            @Valid @RequestBody CourseTrainingRequest courseTrainingRequest) {

        BaseApiResponse<CourseTrainingDto> response = new BaseApiResponse<>();

        int checkType = courseTrainingRequest.getCourseTrainingTypeId().getId();
        System.out.println(checkType);

        if(courseTrainingServiceImp.checkIfTypeExisted(checkType)){

            courseTrainingServiceImp.update(id,courseTrainingRequest);
            CourseTrainingDto courseTrainingDto = courseTrainingServiceImp.selectById(id);

            response.setMessage(messageProperties.updated("Course training"));
            response.setData(courseTrainingDto);
            response.setStatus(HttpStatus.OK);
        }
        else{

            response.setMessage(messageProperties.updatedTypeNotFound("Course training","course training type"));
            response.setStatus(HttpStatus.NO_CONTENT);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    //TODO: Delete course training =========================================================
    @DeleteMapping("/course-trainings/{id}")
    public ResponseEntity<BaseApiResponse<CourseTrainingResponse>> deleteCourseTraining(@PathVariable int id){

        BaseApiResponse<CourseTrainingResponse> response = new BaseApiResponse<>();

        if(courseTrainingServiceImp.delete(id)){

            CourseTrainingResponse courseTrainingResponse = courseTrainingServiceImp.selectAfterDelete(id);

            response.setMessage(messageProperties.deleted("Course training"));
            response.setData(courseTrainingResponse);
            response.setStatus(HttpStatus.OK);
        }
        else{
            response.setMessage(messageProperties.deletedError("Course training","course training"));
            response.setStatus(HttpStatus.NO_CONTENT);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    //TODO: Get course training =========================================================
    @GetMapping("/all-course-trainings")
    public ResponseEntity<BaseApiResponse<List<CourseTrainingResponse>>> selectAllCourseTraining(){

        ModelMapper modelMapper = new ModelMapper();
        BaseApiResponse<List<CourseTrainingResponse>> response = new BaseApiResponse<>();
        List<CourseTrainingResponse> courseTrainingResponses = new ArrayList<>();

        List<CourseTrainingDto> courseTrainingDtos= courseTrainingServiceImp.selectAll();

        for(CourseTrainingDto dto : courseTrainingDtos){
            courseTrainingResponses.add(modelMapper.map(dto,CourseTrainingResponse.class));
        }

        if(courseTrainingResponses.isEmpty()){

            response.setMessage(messageProperties.hasNoRecords("Course training"));
            response.setData(new ArrayList<>());
            response.setStatus(HttpStatus.NO_CONTENT);
        }
        else {

            response.setMessage(messageProperties.selected("Course training"));
            response.setData(courseTrainingResponses);
            response.setStatus(HttpStatus.OK);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

}
