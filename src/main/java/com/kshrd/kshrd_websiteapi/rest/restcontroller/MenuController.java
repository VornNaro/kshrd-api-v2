package com.kshrd.kshrd_websiteapi.rest.restcontroller;

import com.kshrd.kshrd_websiteapi.configuration.BaseAPI;
import com.kshrd.kshrd_websiteapi.model.menu.*;
import com.kshrd.kshrd_websiteapi.rest.message.BaseApiResponse;
import com.kshrd.kshrd_websiteapi.rest.message.BaseApiResponseWithPagination;
import com.kshrd.kshrd_websiteapi.rest.message.ErrorResponse;
import com.kshrd.kshrd_websiteapi.rest.message.MessageProperties;
import com.kshrd.kshrd_websiteapi.service.implement.MenuServiceImp;
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
public class MenuController {

    private MenuServiceImp menuServiceImp;

    @Autowired
    public void setMenuServiceImp(MenuServiceImp menuServiceImp) {
        this.menuServiceImp = menuServiceImp;
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

        response.setMessage(messageProperties.insertError("Menu"));
        response.setError(errors);
        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setTime(new Timestamp(System.currentTimeMillis()));

        return ResponseEntity.ok(response);
    }

    //TODO: Select all menus =========================================================
    @GetMapping("/menus")
    public ResponseEntity<BaseApiResponse<List<MenuResponse>>> selectMenu(MenuFilter menu) {

        List<MenuResponse> menuResponses = menuServiceImp.selectAll(menu);
        BaseApiResponse<List<MenuResponse>> response = new BaseApiResponse<>();
        if(menuResponses.isEmpty()){

            response.setMessage(messageProperties.hasNoRecords("Menu"));
            response.setData(new ArrayList<>());
            response.setStatus(HttpStatus.NO_CONTENT);

        }
        else{

            response.setMessage(messageProperties.selected("Menu"));
            response.setData(menuResponses);
            response.setStatus(HttpStatus.OK);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));

        return ResponseEntity.ok(response);
    }

    //TODO: Select menu by id =========================================================
    @GetMapping("/menus/{id}")
    public ResponseEntity<BaseApiResponse<MenuResponse>> selectMenuById(@PathVariable int id) {

        BaseApiResponse<MenuResponse> menuResponse = new BaseApiResponse<>();
        ModelMapper modelMapper = new ModelMapper();

        MenuDto menuDto = menuServiceImp.selectById(id);

        if(menuDto==null){
            menuResponse.setMessage(messageProperties.hasNoRecord("Menu"));


            menuResponse.setStatus(HttpStatus.NO_CONTENT);
        }
        else {
            MenuResponse response =  modelMapper.map(menuDto,MenuResponse.class);
            menuResponse.setMessage(messageProperties.selectedOne("Menu"));
            menuResponse.setData(response);
            menuResponse.setStatus(HttpStatus.OK);
        }

        menuResponse.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(menuResponse);
    }


    //TODO: Post menu =========================================================
    @PostMapping("/menus")
    public ResponseEntity<BaseApiResponse<MenuResponse>> insertMenu(
            @Valid @RequestBody MenuRequest menu) {

        BaseApiResponse<MenuResponse> response = new BaseApiResponse<>();
        ModelMapper mapper = new ModelMapper();
        MenuDto menuDto = mapper.map(menu, MenuDto.class);

        if(menu.getName().isEmpty()){
            response.setMessage(messageProperties.insertError("Menu"));
            response.setStatus(HttpStatus.BAD_REQUEST);
        }
        else{
            MenuDto result = menuServiceImp.insert(menuDto);
            MenuResponse menuResponse = mapper.map(result, MenuResponse.class);
            response.setMessage(messageProperties.inserted("Menu"));
            menuResponse.setId(result.getId());
            response.setData(menuResponse);
            response.setStatus(HttpStatus.CREATED);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);

    }

    //TODO: Delete menu =========================================================
    @DeleteMapping("/menus/{id}")
    private ResponseEntity<BaseApiResponse<MenuDto>> deleteMenu(@PathVariable("id") int id) {

        BaseApiResponse<MenuDto> response = new BaseApiResponse<>();

        if(menuServiceImp.delete(id)){
            MenuDto menuDto = menuServiceImp.selectAfterDelete(id);
            response.setMessage(messageProperties.deleted("Menu"));
            response.setData(menuDto);
            response.setStatus(HttpStatus.OK);
        }
        else{
            response.setMessage(messageProperties.deletedError("Menu","menu"));
            response.setStatus(HttpStatus.NO_CONTENT);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    //TODO: Update menu =========================================================
    @PutMapping("/menus/{id}")
    private ResponseEntity<BaseApiResponse<MenuDto>> updateMenu(
            @PathVariable int id,
            @Valid @RequestBody  MenuRequest menuRequest) {

        BaseApiResponse<MenuDto> response = new BaseApiResponse<>();
        ModelMapper mapper = new ModelMapper();
        MenuDto menuDto = mapper.map(menuRequest, MenuDto.class);

        if(menuServiceImp.update(id,menuDto)){

            MenuDto menuResponse = menuServiceImp.selectById(id);

            response.setMessage(messageProperties.updated("Menu"));
            response.setData(menuResponse);
            response.setStatus(HttpStatus.OK);
        }
        else{
            response.setMessage(messageProperties.updatedError("Menu"));
            response.setStatus(HttpStatus.NO_CONTENT);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    //TODO: Select all menus with pagination=========================================================
    @GetMapping("/menus-with-pagination")
    public ResponseEntity<BaseApiResponseWithPagination<List<MenuResponseWithParent>>> selectMenuWithPagination(MenuFilter menu, Pagination pagination) {

        List<MenuResponseWithParent> menuResponses = menuServiceImp.selectAllMenuWithPagination(menu,pagination);

        BaseApiResponseWithPagination<List<MenuResponseWithParent>> response = new BaseApiResponseWithPagination<>();

        if(menuResponses.isEmpty()){

            response.setMessage(messageProperties.hasNoRecords("Menu"));
            response.setData(new ArrayList<>());
            response.setStatus(HttpStatus.NO_CONTENT);

        }
        else{

            response.setMessage(messageProperties.selected("Menu"));
            response.setData(menuResponses);
            response.setPagination(pagination);
            response.setStatus(HttpStatus.OK);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));

        return ResponseEntity.ok(response);
    }
}
