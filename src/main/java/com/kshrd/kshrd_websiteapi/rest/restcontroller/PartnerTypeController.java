package com.kshrd.kshrd_websiteapi.rest.restcontroller;

import com.kshrd.kshrd_websiteapi.configuration.BaseAPI;
import com.kshrd.kshrd_websiteapi.rest.message.BaseApiResponse;
import com.kshrd.kshrd_websiteapi.model.partner_type.PartnerTypeDto;
import com.kshrd.kshrd_websiteapi.model.partner_type.PartnerTypeFilter;
import com.kshrd.kshrd_websiteapi.model.partner_type.PartnerTypeRequest;
import com.kshrd.kshrd_websiteapi.model.partner_type.PartnerTypeResponse;
import com.kshrd.kshrd_websiteapi.rest.message.BaseApiResponseWithPagination;
import com.kshrd.kshrd_websiteapi.rest.message.ErrorResponse;
import com.kshrd.kshrd_websiteapi.rest.message.MessageProperties;
import com.kshrd.kshrd_websiteapi.service.implement.PartnerTypeServiceImp;
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
public class PartnerTypeController {

    private PartnerTypeServiceImp partnerTypeServiceImp;

    @Autowired
    public void setPartnerTypeServiceImp(PartnerTypeServiceImp partnerTypeServiceImp) {
        this.partnerTypeServiceImp = partnerTypeServiceImp;
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

        response.setMessage(messageProperties.insertError("Partner type"));
        response.setError(errors);
        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setTime(new Timestamp(System.currentTimeMillis()));

        return ResponseEntity.ok(response);
    }

    //TODO: Select all partner types =========================================================
    @GetMapping("/partner-types")
    public ResponseEntity<BaseApiResponseWithPagination<List<PartnerTypeResponse>>> selectPartnerType(PartnerTypeFilter name, Pagination pagination) {

        ModelMapper mapper = new ModelMapper();
        BaseApiResponseWithPagination<List<PartnerTypeResponse>> response = new BaseApiResponseWithPagination<>();

        List<PartnerTypeDto> partnerTypeDtoList = partnerTypeServiceImp.selectWithPagination(name,pagination);
        List<PartnerTypeResponse> partnerTypeResponses= new ArrayList<>();

        for (PartnerTypeDto partnerTypeDto : partnerTypeDtoList) {
            partnerTypeResponses.add(mapper.map(partnerTypeDto, PartnerTypeResponse.class));
        }
        if(partnerTypeResponses.isEmpty()){

            response.setMessage(messageProperties.hasNoRecords("Partner type"));
            response.setData(new ArrayList<>());
            response.setPagination(pagination);
            response.setStatus(HttpStatus.NO_CONTENT);
        }
        else {

            response.setMessage(messageProperties.selected("Partner type"));
            response.setData(partnerTypeResponses);
            response.setPagination(pagination);
            response.setStatus(HttpStatus.OK);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    //TODO: Select partner type by id =========================================================
    @GetMapping("/partner-types/{id}")
    public ResponseEntity<BaseApiResponse<PartnerTypeResponse>> selectPartnerTypeById(@PathVariable int id) {

        BaseApiResponse<PartnerTypeResponse> partnerTypeResponse = new BaseApiResponse<>();
        ModelMapper modelMapper = new ModelMapper();

        PartnerTypeDto partnerTypeDto = partnerTypeServiceImp.selectById(id);
        if(partnerTypeDto==null){

            partnerTypeResponse.setMessage(messageProperties.hasNoRecord("Partner type"));
            partnerTypeResponse.setStatus(HttpStatus.NO_CONTENT);
        }
        else {

            PartnerTypeResponse response =  modelMapper.map(partnerTypeDto,PartnerTypeResponse.class);
            partnerTypeResponse.setMessage(messageProperties.selectedOne("Partner type"));
            partnerTypeResponse.setData(response);
            partnerTypeResponse.setStatus(HttpStatus.OK);
        }

        partnerTypeResponse.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(partnerTypeResponse);
    }

    //TODO: Post partner types =========================================================
    @PostMapping("/partner-types")
    public ResponseEntity<BaseApiResponse<PartnerTypeResponse>> insertPartnerType(
         @Valid @RequestBody PartnerTypeRequest partnerType) {

        BaseApiResponse<PartnerTypeResponse> response = new BaseApiResponse<>();
        ModelMapper mapper = new ModelMapper();

        PartnerTypeDto partnerTypeDto = mapper.map(partnerType, PartnerTypeDto.class);

        if(partnerType.getName().isEmpty()){

            response.setMessage(messageProperties.insertError("Partner type"));
            response.setStatus(HttpStatus.BAD_REQUEST);
        }
        else {

            PartnerTypeDto result = partnerTypeServiceImp.insert(partnerTypeDto);

            PartnerTypeResponse partnerTypeResponse = mapper.map(result, PartnerTypeResponse.class);
            response.setMessage(messageProperties.inserted("Partner type"));
            partnerTypeResponse.setId(result.getId());
            response.setData(partnerTypeResponse);
            response.setStatus(HttpStatus.CREATED);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }
    //TODO: Update partner type =========================================================
    @PutMapping("/partner-types/{id}")
    private ResponseEntity<BaseApiResponse<PartnerTypeDto>> updatePartnerType(
            @PathVariable int id,
            @Valid @RequestBody  PartnerTypeRequest partnerTypeRequest) {

        BaseApiResponse<PartnerTypeDto> response = new BaseApiResponse<>();

        if(partnerTypeServiceImp.update(id,partnerTypeRequest)){

            PartnerTypeDto partnerTypeDto = partnerTypeServiceImp.selectById(id);

            response.setMessage(messageProperties.updated("Partner type"));
            response.setData(partnerTypeDto);
            response.setStatus(HttpStatus.OK);
        }
        else{
            response.setMessage(messageProperties.updatedError("Partner type"));
            response.setStatus(HttpStatus.NO_CONTENT);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }
    //TODO: Delete partner type =========================================================
    @DeleteMapping("/partner-types/{id}")
    private ResponseEntity<BaseApiResponse<PartnerTypeDto>> deletePartnerType(
            @PathVariable("id") int id) {

        BaseApiResponse<PartnerTypeDto> response = new BaseApiResponse<>();

        if(partnerTypeServiceImp.delete(id)){

            PartnerTypeDto partnerTypeDto = partnerTypeServiceImp.selectAfterDelete(id);

            response.setMessage(messageProperties.deleted("Partner type"));
            response.setData(partnerTypeDto);
            response.setStatus(HttpStatus.OK);
        }
        else{
            response.setMessage(messageProperties.deletedError("Partner type","partner type"));
            response.setStatus(HttpStatus.NO_CONTENT);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }
}
