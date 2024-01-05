package com.kshrd.kshrd_websiteapi.rest.restcontroller;

import com.kshrd.kshrd_websiteapi.configuration.BaseAPI;
import com.kshrd.kshrd_websiteapi.rest.message.BaseApiResponse;
import com.kshrd.kshrd_websiteapi.model.generation.GenerationDto;
import com.kshrd.kshrd_websiteapi.model.generation.GenerationFilter;
import com.kshrd.kshrd_websiteapi.model.generation.GenerationRequest;
import com.kshrd.kshrd_websiteapi.model.generation.GenerationResponse;
import com.kshrd.kshrd_websiteapi.rest.message.BaseApiResponseWithPagination;
import com.kshrd.kshrd_websiteapi.rest.message.ErrorResponse;
import com.kshrd.kshrd_websiteapi.rest.message.MessageProperties;
import com.kshrd.kshrd_websiteapi.service.implement.GenerationServiceImp;
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
public class GenerationController {

    private GenerationServiceImp generationServiceImp;

    @Autowired
    public void setGenerationServiceImp(GenerationServiceImp generationServiceImp) {
        this.generationServiceImp = generationServiceImp;
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

        response.setMessage(messageProperties.insertError("Generation"));
        response.setError(errors);
        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setTime(new Timestamp(System.currentTimeMillis()));

        return ResponseEntity.ok(response);
    }

    //TODO: Select all generations =========================================================
    @GetMapping("/generations")
    public ResponseEntity<BaseApiResponseWithPagination<List<GenerationResponse>>> selectGenerationWithPagination(GenerationFilter name, Pagination pagination){
        BaseApiResponseWithPagination<List<GenerationResponse>> response = new BaseApiResponseWithPagination<>();

        ModelMapper mapper = new ModelMapper();

        List<GenerationDto> generationDtoList = generationServiceImp.selectWithPagination(name,pagination);

        List<GenerationResponse> generationResponses = new ArrayList<>();

        for (GenerationDto generationDto : generationDtoList) {
            generationResponses.add(mapper.map(generationDto, GenerationResponse.class));
        }

        if (generationDtoList.isEmpty()) {

            response.setMessage(messageProperties.hasNoRecords("Generation"));
            response.setData(new ArrayList<>());
            response.setPagination(pagination);
            response.setStatus(HttpStatus.NO_CONTENT);
        } else {

            response.setMessage(messageProperties.selected("Generation"));
            response.setData(generationResponses);
            response.setPagination(pagination);
            response.setStatus(HttpStatus.OK);
        }
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    //TODO: Select generation By ID =========================================================
    @GetMapping("/generations/{id}")
    public ResponseEntity<BaseApiResponse<GenerationResponse>> selectGenerationById(@PathVariable int id) {

        BaseApiResponse<GenerationResponse> generationResponse = new BaseApiResponse<>();
        ModelMapper modelMapper = new ModelMapper();

        GenerationDto generationDto = generationServiceImp.selectById(id);

        if(generationDto==null){

            generationResponse.setMessage(messageProperties.hasNoRecord("Generation"));
            generationResponse.setStatus(HttpStatus.NO_CONTENT);
        }
        else {

            GenerationResponse response =  modelMapper.map(generationDto,GenerationResponse.class);
            generationResponse.setMessage(messageProperties.selectedOne("Generation"));
            generationResponse.setData(response);
            generationResponse.setStatus(HttpStatus.OK);
        }

        generationResponse.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(generationResponse);
    }

    //TODO: Post generation =========================================================
    @PostMapping("/generations")
    public ResponseEntity<BaseApiResponse<GenerationResponse>> insertGeneration(
            @Valid @RequestBody GenerationRequest generationRequest) {

        BaseApiResponse<GenerationResponse> response = new BaseApiResponse<>();
        ModelMapper mapper = new ModelMapper();

        GenerationDto generationDto= mapper.map(generationRequest, GenerationDto.class);

        GenerationDto result = generationServiceImp.insert(generationDto);
        GenerationResponse generationResponse = mapper.map(result, GenerationResponse.class);

        response.setMessage(messageProperties.inserted("Generation"));
        generationResponse.setId(result.getId());

        response.setData(generationResponse);
        response.setStatus(HttpStatus.CREATED);

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);

    }

    //TODO: Delete generation =========================================================
    @DeleteMapping("/generations/{id}")
    public ResponseEntity<BaseApiResponse<GenerationDto>> deleteGeneration(@PathVariable Integer id){

        BaseApiResponse<GenerationDto> response = new BaseApiResponse<>();

        if(generationServiceImp.delete(id)){

            GenerationDto generationDto = generationServiceImp.selectAfterDelete(id);

            response.setMessage(messageProperties.deleted("Generation"));
            response.setData(generationDto);
            response.setStatus(HttpStatus.OK);
        }
        else{

            response.setMessage(messageProperties.deletedError("Generation","generation"));
            response.setStatus(HttpStatus.NO_CONTENT);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    //TODO: Update generation =========================================================
    @PutMapping("/generations/{id}")
    public ResponseEntity<BaseApiResponse<GenerationDto>> updateGeneration(
            @PathVariable Integer id,
            @Valid @RequestBody GenerationRequest generationRequest){

        BaseApiResponse<GenerationDto> response = new BaseApiResponse<>();

        if(generationServiceImp.update(id,generationRequest)){

            GenerationDto generationDto = generationServiceImp.selectById(id);

            response.setMessage(messageProperties.updated("Generation"));
            response.setData(generationDto);
            response.setStatus(HttpStatus.OK);
        }
        else{
            response.setMessage(messageProperties.updatedError("Generation"));
            response.setStatus(HttpStatus.NO_CONTENT);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);

    }

    //TODO: Select all generations =========================================================
    @GetMapping("/all-generations")
    public ResponseEntity<BaseApiResponse<List<GenerationResponse>>> selectGeneration(GenerationFilter name){
        BaseApiResponse<List<GenerationResponse>> response = new BaseApiResponse<>();

        ModelMapper mapper = new ModelMapper();

        List<GenerationDto> generationDtoList = generationServiceImp.selectAll(name);

        List<GenerationResponse> generationResponses = new ArrayList<>();

        for (GenerationDto generationDto : generationDtoList) {
            generationResponses.add(mapper.map(generationDto, GenerationResponse.class));
        }

        if (generationDtoList.isEmpty()) {

            response.setMessage(messageProperties.hasNoRecords("Generation"));
            response.setData(new ArrayList<>());
            response.setStatus(HttpStatus.NO_CONTENT);
        } else {

            response.setMessage(messageProperties.selected("Generation"));
            response.setData(generationResponses);
            response.setStatus(HttpStatus.OK);
        }
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }
}
