package com.kshrd.kshrd_websiteapi.rest.restcontroller;

import com.kshrd.kshrd_websiteapi.configuration.BaseAPI;
import com.kshrd.kshrd_websiteapi.rest.message.BaseApiResponse;
import com.kshrd.kshrd_websiteapi.model.announcement.AnnouncementDto;
import com.kshrd.kshrd_websiteapi.model.announcement.AnnouncementFilter;
import com.kshrd.kshrd_websiteapi.model.announcement.AnnouncementRequest;
import com.kshrd.kshrd_websiteapi.model.announcement.AnnouncementResponse;
import com.kshrd.kshrd_websiteapi.rest.message.BaseApiResponseWithPagination;
import com.kshrd.kshrd_websiteapi.rest.message.ErrorResponse;
import com.kshrd.kshrd_websiteapi.rest.message.MessageProperties;
import com.kshrd.kshrd_websiteapi.service.implement.AnnouncementServiceImp;
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

//@CrossOrigin(origins = "*",allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping(value = BaseAPI.BASE_API_URL)
public class AnnouncementController {

    private AnnouncementServiceImp announcementServiceImp;

    @Autowired
    public void setAnnouncementServiceImp(AnnouncementServiceImp announcementServiceImp) {
        this.announcementServiceImp = announcementServiceImp;
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

        response.setMessage(messageProperties.insertError("Announcement"));
        response.setError(errors);
        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    //TODO: Get alumni =========================================================
    @GetMapping("/announcements")
    public ResponseEntity<BaseApiResponseWithPagination<List<AnnouncementResponse>>> selectAnnouncement(AnnouncementFilter content, Pagination pagination) {

        ModelMapper mapper = new ModelMapper();
        BaseApiResponseWithPagination<List<AnnouncementResponse>> response = new BaseApiResponseWithPagination<>();

        List<AnnouncementDto> announcementDtoList = announcementServiceImp.selectWithPagination(content,pagination);
        List<AnnouncementResponse> announcementResponses = new ArrayList<>();

        for (AnnouncementDto announcementDto : announcementDtoList) {
            announcementResponses.add(mapper.map(announcementDto, AnnouncementResponse.class));
        }

        if (announcementDtoList.isEmpty()) {
            response.setMessage(messageProperties.hasNoRecords("Announcement"));
            response.setData(new ArrayList<>());
            response.setPagination(pagination);
            response.setStatus(HttpStatus.NO_CONTENT);
        } else {
            response.setMessage(messageProperties.selected("Announcement"));
            response.setData(announcementResponses);
            response.setPagination(pagination);
            response.setStatus(HttpStatus.OK);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


    //TODO: Get alumni by id =========================================================
    @GetMapping("/announcements/{id}")
    public ResponseEntity<BaseApiResponse<AnnouncementResponse>> selectAnnouncementById(@PathVariable int id) {

        BaseApiResponse<AnnouncementResponse> announcementResponse = new BaseApiResponse<>();
        ModelMapper modelMapper = new ModelMapper();
        AnnouncementDto announcementDto = announcementServiceImp.selectById(id);

        if(announcementDto==null){
            announcementResponse .setMessage(messageProperties.hasNoRecord("Announcement"));
            announcementResponse .setStatus(HttpStatus.NO_CONTENT);
        }
        else {
            AnnouncementResponse response =  modelMapper.map(announcementDto,AnnouncementResponse.class);
            announcementResponse.setMessage(messageProperties.selectedOne("Announcement"));
            announcementResponse.setData(response);
            announcementResponse .setStatus(HttpStatus.OK);
        }

        announcementResponse.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(announcementResponse);
    }

    //TODO: Post alumni =========================================================
    @PostMapping("/announcements")
    public ResponseEntity<BaseApiResponse<AnnouncementResponse>> insertAnnouncement(
            @Valid  @RequestBody AnnouncementRequest announcement) {

        BaseApiResponse<AnnouncementResponse> response = new BaseApiResponse<>();
        ModelMapper mapper = new ModelMapper();
        AnnouncementDto announcementDto = mapper.map(announcement, AnnouncementDto.class);

        if(announcement.getContent().isEmpty()){
            response.setMessage(messageProperties.insertError("Announcement"));
        }
        else{
            AnnouncementDto result = announcementServiceImp.insert(announcementDto);
            AnnouncementResponse announcementResponse = mapper.map(result, AnnouncementResponse.class);
            response.setMessage(messageProperties.inserted("Announcement"));
            announcementResponse.setId(result.getId());
            response.setData(announcementResponse);
            response.setStatus(HttpStatus.CREATED);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    //TODO: Delete alumni =========================================================
    @DeleteMapping("/announcements/{id}")
    private ResponseEntity<BaseApiResponse<AnnouncementDto>> deleteAnnouncement(@PathVariable("id") int id) {

        BaseApiResponse<AnnouncementDto> response = new BaseApiResponse<>();

        if(announcementServiceImp.delete(id)){
            AnnouncementDto announcementDto = announcementServiceImp.selectAfterDelete(id);
            response.setMessage(messageProperties.deleted("Announcement"));
            response.setData(announcementDto);
            response.setStatus(HttpStatus.OK);
        }
        else{
            response.setMessage(messageProperties.deletedError("Announcement","announcement"));
            response.setStatus(HttpStatus.NO_CONTENT);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    //TODO: Update alumni =========================================================
    @PutMapping("/announcements/{id}")
    private ResponseEntity<BaseApiResponse<AnnouncementDto>> updateAnnouncement(
            @PathVariable int id,
            @Valid @RequestBody  AnnouncementRequest announcementRequest) {

        BaseApiResponse<AnnouncementDto> response = new BaseApiResponse<>();

        if(announcementServiceImp.update(id,announcementRequest)){

            AnnouncementDto announcementDto= announcementServiceImp.selectById(id);

            response.setMessage(messageProperties.updated("Announcement"));
            response.setData(announcementDto);
            response.setStatus(HttpStatus.OK);
        }
        else{
            response.setMessage(messageProperties.updatedError("Announcement"));
            response.setStatus(HttpStatus.NO_CONTENT);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }
}
