package com.kshrd.kshrd_websiteapi.rest.restcontroller;

import com.kshrd.kshrd_websiteapi.configuration.BaseAPI;
import com.kshrd.kshrd_websiteapi.rest.message.BaseApiResponse;
import com.kshrd.kshrd_websiteapi.model.partner.PartnerFilter;
import com.kshrd.kshrd_websiteapi.model.partner.PartnerRequest;
import com.kshrd.kshrd_websiteapi.model.partner.PartnerDto;
import com.kshrd.kshrd_websiteapi.model.partner.PartnerResponse;
import com.kshrd.kshrd_websiteapi.repository.PartnerRepository;
import com.kshrd.kshrd_websiteapi.rest.message.BaseApiResponseWithPagination;
import com.kshrd.kshrd_websiteapi.rest.message.ErrorResponse;
import com.kshrd.kshrd_websiteapi.rest.message.MessageProperties;
import com.kshrd.kshrd_websiteapi.service.implement.PartnerServiceImp;
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
public class PartnerController {

    private final PartnerRepository partnerRepository;

    public PartnerController(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    private PartnerServiceImp partnerServiceImp;

    @Autowired
    public void setPartnerServiceImp(PartnerServiceImp partnerServiceImp) {
        this.partnerServiceImp = partnerServiceImp;
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

        response.setMessage(messageProperties.insertError("Partner"));
        response.setError(errors);

        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    //TODO: Select all partners =========================================================
    @GetMapping("/partners")
    public ResponseEntity<BaseApiResponseWithPagination<List<PartnerResponse>>> selectPartner(@RequestParam(value = "partnerTypeId",required = false) Integer partnerTypeId, PartnerFilter name, Pagination pagination) {

        ModelMapper modelMapper = new ModelMapper();

        BaseApiResponseWithPagination<List<PartnerResponse>> response = new BaseApiResponseWithPagination<>();

        List<PartnerResponse> partnerResponses = new ArrayList<>();

        if(partnerTypeId==null){
            response.setMessage("Partner Type Id cannot be empty");
            response.setData(new ArrayList<>());
            response.setPagination(pagination);
            response.setStatus(HttpStatus.BAD_REQUEST);
        }
        else {

            List<PartnerDto> partnerDtos = partnerServiceImp.selectWithPagination(partnerTypeId, name, pagination);

            for (PartnerDto partnerDto : partnerDtos) {
                partnerResponses.add(modelMapper.map(partnerDto, PartnerResponse.class));
            }
            if (partnerResponses.isEmpty()) {

                response.setMessage(messageProperties.hasNoRecords("Partner"));
                response.setData(new ArrayList<>());
                response.setPagination(pagination);
                response.setStatus(HttpStatus.NO_CONTENT);
            } else {

                response.setMessage(messageProperties.selected("Partner"));
                response.setData(partnerResponses);
                response.setPagination(pagination);
                response.setStatus(HttpStatus.OK);
            }
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);

    }

    //TODO: Select partner by id =========================================================
    @GetMapping("/partners/{id}")
    public ResponseEntity<BaseApiResponse<PartnerResponse>> selectPartnerById(@PathVariable int id) {

        BaseApiResponse<PartnerResponse> partnerResponse = new BaseApiResponse<>();
        ModelMapper modelMapper = new ModelMapper();

        PartnerDto partnerDto = partnerServiceImp.selectById(id);

        if(partnerDto==null){

            partnerResponse.setMessage(messageProperties.hasNoRecord("partner"));
            partnerResponse.setStatus(HttpStatus.NO_CONTENT);
        }
        else {

            PartnerResponse response =  modelMapper.map(partnerDto,PartnerResponse.class);
            partnerResponse.setMessage(messageProperties.selectedOne("Partner"));
            partnerResponse.setData(response);
            partnerResponse.setStatus(HttpStatus.OK);
        }

        partnerResponse.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(partnerResponse);
    }


    //TODO: Post partner =========================================================
    @PostMapping("/partners")
    public ResponseEntity<BaseApiResponse<PartnerResponse>> insertPartner(
         @Valid @RequestBody PartnerRequest partner) {

        BaseApiResponse<PartnerResponse> response = new BaseApiResponse<>();

        try {

            ModelMapper mapper = new ModelMapper();

            PartnerDto partnerDto = mapper.map(partner, PartnerDto.class);

            int checkType = partner.getPartnerType().getId();

            if(!partnerServiceImp.checkIfTypeExisted(checkType)) {
                response.setMessage(messageProperties.insertedTypeNotFound("Partner","partner type"));
                response.setStatus(HttpStatus.BAD_REQUEST);
            }
            else {

                PartnerDto result = partnerServiceImp.insert(partnerDto);
                PartnerResponse partnerResponse = mapper.map(result, PartnerResponse.class);

                response.setMessage(messageProperties.inserted("Partner"));
                partner.setId(result.getId());
                response.setData(partnerResponse);
                response.setStatus(HttpStatus.CREATED);
            }
        }catch (IllegalArgumentException e){
            response.setMessage(messageProperties.insertError("Partner"));
            response.setStatus(HttpStatus.BAD_REQUEST);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);

    }
    //TODO: Delete partner =========================================================
    @DeleteMapping("/partners/{id}")
    private ResponseEntity<BaseApiResponse<PartnerResponse>> deletePartner(
            @PathVariable("id") int id) {

        BaseApiResponse<PartnerResponse> response = new BaseApiResponse<>();

        if(partnerServiceImp.delete(id)){

            PartnerResponse partnerResponse = partnerServiceImp.selectAfterDelete(id);

            response.setMessage(messageProperties.deleted("Partner"));
            response.setData(partnerResponse);
            response.setStatus(HttpStatus.OK);
        }
        else{
            response.setMessage(messageProperties.deletedError("Partner","partner"));
            response.setStatus(HttpStatus.NO_CONTENT);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }
    //TODO: Update partner =========================================================
    @PutMapping("/partners/{id}")
    private ResponseEntity<BaseApiResponse<PartnerDto>> updatePartner(
            @PathVariable int id,
            @Valid @RequestBody  PartnerRequest partnerRequest) {

        BaseApiResponse<PartnerDto> response = new BaseApiResponse<>();

        int checkType = partnerRequest.getPartnerType().getId();

        if(partnerServiceImp.checkIfTypeExisted(checkType)) {

            if (partnerServiceImp.update(id, partnerRequest)) {

                PartnerDto partnerDto = partnerServiceImp.selectById(id);

                response.setMessage(messageProperties.updated("Partner"));
                response.setData(partnerDto);
                response.setStatus(HttpStatus.OK);
            }
            else{

                response.setMessage(messageProperties.updatedError("Partner"));
                response.setStatus(HttpStatus.NO_CONTENT);
            }
        }
        else{
            response.setMessage(messageProperties.updatedTypeNotFound("Partner","partner type"));
            response.setStatus(HttpStatus.NO_CONTENT);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    //TODO: Select all partners =========================================================
    @GetMapping("/all-partners")
    public ResponseEntity<BaseApiResponseWithPagination<List<PartnerResponse>>> selectAllPartners(PartnerFilter name, Pagination pagination) {

        ModelMapper modelMapper = new ModelMapper();

        BaseApiResponseWithPagination<List<PartnerResponse>> response = new BaseApiResponseWithPagination<>();

        List<PartnerResponse> partnerResponses = new ArrayList<>();

        List<PartnerDto> partnerDtos = partnerServiceImp.selectAll(name, pagination);

        for (PartnerDto partnerDto : partnerDtos) {
            partnerResponses.add(modelMapper.map(partnerDto, PartnerResponse.class));
        }
        if (partnerResponses.isEmpty()) {

            response.setMessage(messageProperties.hasNoRecords("Partner"));
            response.setData(new ArrayList<>());
            response.setPagination(pagination);
            response.setStatus(HttpStatus.NO_CONTENT);
        }
        else {

            response.setMessage(messageProperties.selected("Partner"));
            response.setData(partnerResponses);
            response.setPagination(pagination);
            response.setStatus(HttpStatus.OK);

        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);

    }

}
