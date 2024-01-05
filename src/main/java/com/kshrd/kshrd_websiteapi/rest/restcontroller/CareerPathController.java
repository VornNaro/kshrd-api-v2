package com.kshrd.kshrd_websiteapi.rest.restcontroller;

import com.kshrd.kshrd_websiteapi.configuration.BaseAPI;
import com.kshrd.kshrd_websiteapi.rest.message.BaseApiResponse;
import com.kshrd.kshrd_websiteapi.model.careerpath.CareerPathDto;
import com.kshrd.kshrd_websiteapi.model.careerpath.CareerPathFilter;
import com.kshrd.kshrd_websiteapi.model.careerpath.CareerPathRequest;
import com.kshrd.kshrd_websiteapi.model.careerpath.CareerPathResponse;
import com.kshrd.kshrd_websiteapi.rest.message.BaseApiResponseWithPagination;
import com.kshrd.kshrd_websiteapi.rest.message.ErrorResponse;
import com.kshrd.kshrd_websiteapi.rest.message.MessageProperties;
import com.kshrd.kshrd_websiteapi.service.implement.CareerPathServiceImp;
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
public class CareerPathController {

    private CareerPathServiceImp careerPathServiceImp;

    @Autowired
    public void setCareerPathServiceImp(CareerPathServiceImp careerPathServiceImp) {
        this.careerPathServiceImp = careerPathServiceImp;
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

        response.setMessage(messageProperties.insertError("Career path"));
        response.setError(errors);
        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    //TODO: Get careerpath =========================================================
    @GetMapping("/career-paths")
    public ResponseEntity<BaseApiResponseWithPagination<List<CareerPathResponse>>> selectCareerPathWithPagination(CareerPathFilter title, Pagination pagination) {

        ModelMapper mapper = new ModelMapper();
        BaseApiResponseWithPagination<List<CareerPathResponse>> response = new BaseApiResponseWithPagination<>();

        List<CareerPathDto> careerPathDtoList = careerPathServiceImp.selectWithPagination(title,pagination);
        List<CareerPathResponse> careerPathResponses = new ArrayList<>();

        for (CareerPathDto careerPathDto : careerPathDtoList) {
            careerPathResponses.add(mapper.map(careerPathDto, CareerPathResponse.class));
        }

        if(careerPathDtoList.isEmpty()){

            response.setMessage(messageProperties.hasNoRecords("Career path"));
            response.setData(new ArrayList<>());
            response.setPagination(pagination);
            response.setStatus(HttpStatus.NO_CONTENT);
        }
        else {

            response.setMessage(messageProperties.selected("Career path"));
            response.setData(careerPathResponses);
            response.setPagination(pagination);
            response.setStatus(HttpStatus.OK);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }
    //TODO: Get careerpath by id =========================================================
    @GetMapping("/career-paths/{id}")
    public ResponseEntity<BaseApiResponse<CareerPathResponse>> selectCareerPathById(@PathVariable int id) {

        BaseApiResponse<CareerPathResponse> careerPathResponse = new BaseApiResponse<>();
        ModelMapper modelMapper = new ModelMapper();

        CareerPathDto careerPathDto = careerPathServiceImp.selectById(id);

        if(careerPathDto==null){

            careerPathResponse.setMessage(messageProperties.hasNoRecord("Career path"));
            careerPathResponse.setStatus(HttpStatus.NO_CONTENT);
        }
        else {

            CareerPathResponse response =  modelMapper.map(careerPathDto,CareerPathResponse.class);
            careerPathResponse.setMessage(messageProperties.selectedOne("Career path"));
            careerPathResponse.setData(response);
            careerPathResponse.setStatus(HttpStatus.OK);
        }

        careerPathResponse.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(careerPathResponse);
    }
    //TODO: Post careerpath =========================================================
    @PostMapping("/career-paths")
    public ResponseEntity<BaseApiResponse<CareerPathResponse>> insertCareerPath(
            @Valid @RequestBody CareerPathRequest careerPath) {

        BaseApiResponse<CareerPathResponse> response = new BaseApiResponse<>();

        ModelMapper mapper = new ModelMapper();

        CareerPathDto careerPathDto = mapper.map(careerPath, CareerPathDto.class);

        int checkParentId = careerPath.getParent_id();

        if(!careerPathServiceImp.checkIfParentIDNotExisted(checkParentId)&&checkParentId!=0){

            response.setMessage(messageProperties.insertedTypeNotFound("Career path","parent id"));
            response.setStatus(HttpStatus.BAD_REQUEST);
        }
        else if(checkParentId==0){

            CareerPathDto result = careerPathServiceImp.insert(careerPathDto);
            CareerPathResponse careerPathResponse = mapper.map(result, CareerPathResponse.class);

            response.setMessage(messageProperties.inserted("Career path"));
            careerPathResponse.setId(result.getId());
            response.setData(careerPathResponse);
            response.setStatus(HttpStatus.CREATED);
        }

        else {

            CareerPathDto result = careerPathServiceImp.insert(careerPathDto);
            CareerPathResponse careerPathResponse = mapper.map(result, CareerPathResponse.class);
            response.setMessage(messageProperties.inserted("Career path"));
            careerPathResponse.setId(result.getId());
            response.setData(careerPathResponse);
            response.setStatus(HttpStatus.CREATED);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);

    }

    //TODO: Delete careerpath =========================================================
    @DeleteMapping("/career-paths/{id}")
    public ResponseEntity<BaseApiResponse<CareerPathDto>> deleteCareerPath(
           @PathVariable int id){

        BaseApiResponse<CareerPathDto> response = new BaseApiResponse<>();

        if(careerPathServiceImp.delete(id)){

            CareerPathDto careerPathDto = careerPathServiceImp.selectAfterDelete(id);
            response.setMessage(messageProperties.deleted("Career path"));
            response.setData(careerPathDto);
            response.setStatus(HttpStatus.OK);
        }
        else{
            response.setMessage(messageProperties.deletedError("Career path","career path"));
            response.setStatus(HttpStatus.NO_CONTENT);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    //TODO: Update careerpath =========================================================
    @PutMapping("/career-paths/{id}")
    private ResponseEntity<BaseApiResponse<CareerPathDto>> updateCareerPath(
            @PathVariable int id,
            @Valid @RequestBody CareerPathRequest careerPathRequest) {

        BaseApiResponse<CareerPathDto> response = new BaseApiResponse<>();

        int checkParentId = careerPathRequest.getParent_id();

        if(!careerPathServiceImp.checkIfParentIDNotExisted(checkParentId)&&checkParentId!=0){

            response.setMessage(messageProperties.updatedTypeNotFound("Career path","parent id"));
            response.setStatus(HttpStatus.NO_CONTENT);

        }
        else if(checkParentId==0){

            careerPathServiceImp.update(id,careerPathRequest);
            CareerPathDto careerPathDto = careerPathServiceImp.selectById(id);

            response.setMessage(messageProperties.updated("Career path"));
            response.setData(careerPathDto);
            response.setStatus(HttpStatus.OK);

        }
        else{

            careerPathServiceImp.update(id,careerPathRequest);
            CareerPathDto careerPathDto = careerPathServiceImp.selectById(id);

            response.setMessage(messageProperties.updated("Career path"));
            response.setData(careerPathDto);
            response.setStatus(HttpStatus.OK);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    //TODO: Get careerpath =========================================================
    @GetMapping("/all-career-paths")
    public ResponseEntity<BaseApiResponse<List<CareerPathResponse>>> selectCareerPath(CareerPathFilter title) {

        ModelMapper mapper = new ModelMapper();
        BaseApiResponse<List<CareerPathResponse>> response = new BaseApiResponse<>();

        List<CareerPathDto> careerPathDtoList = careerPathServiceImp.selectAll(title);
        List<CareerPathResponse> careerPathResponses = new ArrayList<>();

        for (CareerPathDto careerPathDto : careerPathDtoList) {
            careerPathResponses.add(mapper.map(careerPathDto, CareerPathResponse.class));
        }

        if(careerPathDtoList.isEmpty()){

            response.setMessage(messageProperties.hasNoRecords("Career path"));
            response.setData(new ArrayList<>());
            response.setStatus(HttpStatus.NO_CONTENT);
        }
        else {

            response.setMessage(messageProperties.selected("Career path"));
            response.setData(careerPathResponses);
            response.setStatus(HttpStatus.OK);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }
}
