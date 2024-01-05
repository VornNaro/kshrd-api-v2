package com.kshrd.kshrd_websiteapi.rest.restcontroller;

import com.kshrd.kshrd_websiteapi.configuration.BaseAPI;
import com.kshrd.kshrd_websiteapi.rest.message.BaseApiResponse;
import com.kshrd.kshrd_websiteapi.model.alumni.AlumniFilter;
import com.kshrd.kshrd_websiteapi.model.alumni.AlumniRequest;
import com.kshrd.kshrd_websiteapi.model.alumni.AlumniDto;
import com.kshrd.kshrd_websiteapi.model.alumni.AlumniResponse;
import com.kshrd.kshrd_websiteapi.rest.message.BaseApiResponseWithPagination;
import com.kshrd.kshrd_websiteapi.rest.message.ErrorResponse;
import com.kshrd.kshrd_websiteapi.rest.message.MessageProperties;
import com.kshrd.kshrd_websiteapi.service.implement.AlumniServiceImp;
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
import java.util.*;

@CrossOrigin(origins = "*",allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping(value = BaseAPI.BASE_API_URL)
public class AlumniController {

    private AlumniServiceImp alumniServiceImp;

    @Autowired
    public void setAlumniServiceImp(AlumniServiceImp alumniServiceImp) {
        this.alumniServiceImp = alumniServiceImp;
    }

    private MessageProperties messageProperties;

    @Autowired
    public void setMessageProperties(MessageProperties messageProperties) {
        this.messageProperties = messageProperties;
    }

    //TODO: Exception =========================================================
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex){

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

        response.setMessage(messageProperties.insertError("Alumni"));
        response.setError(errors);
        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setTime(new Timestamp(System.currentTimeMillis()));

        return ResponseEntity.ok(response);
    }

    //TODO: Get alumni =========================================================
    @GetMapping("/alumni")
    public ResponseEntity<BaseApiResponseWithPagination<List<AlumniResponse>>> selectAlumni(AlumniFilter name, Pagination pagination) {

        ModelMapper mapper = new ModelMapper();
        BaseApiResponseWithPagination<List<AlumniResponse>> response = new BaseApiResponseWithPagination<>();

        List<AlumniDto> alumniDtoList = alumniServiceImp.selectWithPagination(name,pagination);
        List<AlumniResponse> alumniResponses = new ArrayList<>();

        for (AlumniDto alumniDto : alumniDtoList) {
            alumniResponses.add(mapper.map(alumniDto, AlumniResponse.class));
        }

        if (alumniDtoList.isEmpty()) {

            response.setMessage(messageProperties.hasNoRecords("Alumni"));
            response.setData(new ArrayList<>());
            response.setPagination(pagination);
            response.setStatus(HttpStatus.NO_CONTENT);
        } else {

            response.setMessage(messageProperties.selected("Alumni"));
            response.setData(alumniResponses);
            response.setPagination(pagination);
            response.setStatus(HttpStatus.OK);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


    //TODO: Get alumni by id =========================================================
    @GetMapping("/alumni/{id}")
    public ResponseEntity<BaseApiResponse<AlumniResponse>> selectAlumniById(@PathVariable int id) {

        BaseApiResponse<AlumniResponse> alumniResponse = new BaseApiResponse<>();
        ModelMapper modelMapper = new ModelMapper();
        AlumniDto alumniDto = alumniServiceImp.selectById(id);

        if(alumniDto==null){
            alumniResponse.setMessage(messageProperties.hasNoRecord("Alumni"));
            alumniResponse.setStatus(HttpStatus.NO_CONTENT);
        }
        else {
            AlumniResponse response =  modelMapper.map(alumniDto,AlumniResponse.class);
            alumniResponse.setMessage(messageProperties.selectedOne("Alumni"));
            alumniResponse.setData(response);
            alumniResponse.setStatus(HttpStatus.OK);
        }

        alumniResponse.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(alumniResponse);
    }

    //TODO: Post alumni =========================================================
    @PostMapping("/alumni")
    public ResponseEntity<BaseApiResponse<AlumniResponse>> insertAlumni(
            @Valid @RequestBody AlumniRequest alumni) {

        BaseApiResponse<AlumniResponse> response = new BaseApiResponse<>();
        ModelMapper mapper = new ModelMapper();
        AlumniDto alumniDto = mapper.map(alumni, AlumniDto.class);

        AlumniDto result = alumniServiceImp.insert(alumniDto);
        AlumniResponse alumniResponse = mapper.map(result, AlumniResponse.class);
        response.setMessage(messageProperties.inserted("Alumni"));
        alumniResponse.setId(result.getId());
        response.setData(alumniResponse);
        response.setStatus(HttpStatus.CREATED);

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);

    }

    //TODO: Delete alumni =========================================================
    @DeleteMapping("/alumni/{id}")
    private ResponseEntity<BaseApiResponse<AlumniDto>> deleteAlumni(@PathVariable("id") int id) {
        BaseApiResponse<AlumniDto> response = new BaseApiResponse<>();

        if(alumniServiceImp.delete(id)){

            AlumniDto alumniDto = alumniServiceImp.selectAfterDelete(id);
            response.setMessage(messageProperties.deleted("Alumni"));
            response.setData(alumniDto);
            response.setStatus(HttpStatus.OK);
        }
        else{
            response.setMessage(messageProperties.deletedError("Alumni","alumni"));
            response.setStatus(HttpStatus.NO_CONTENT);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    //TODO: Update alumni =========================================================
    @PutMapping("/alumni/{id}")
    private ResponseEntity<BaseApiResponse<AlumniDto>> updateAlumni(
            @PathVariable int id,
            @Valid @RequestBody  AlumniRequest alumniRequest) {

        BaseApiResponse<AlumniDto> response = new BaseApiResponse<>();

        if(alumniServiceImp.update(id,alumniRequest)){

            AlumniDto alumniDto = alumniServiceImp.selectById(id);
            response.setMessage(messageProperties.updated("Alumni"));
            response.setData(alumniDto);
            response.setStatus(HttpStatus.OK);
        }
        else{

            response.setMessage(messageProperties.updatedError("Alumni"));
            response.setStatus(HttpStatus.NO_CONTENT);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }
}
