package com.kshrd.kshrd_websiteapi.rest.restcontroller;

import com.kshrd.kshrd_websiteapi.configuration.BaseAPI;
import com.kshrd.kshrd_websiteapi.configuration.jwtconfiguration.JwtTokenUtil;
import com.kshrd.kshrd_websiteapi.model.token.TokenResponse;
import com.kshrd.kshrd_websiteapi.model.user.*;
import com.kshrd.kshrd_websiteapi.rest.message.*;
import com.kshrd.kshrd_websiteapi.service.implement.UserServiceImp;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;
import org.modelmapper.ModelMapper;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.*;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*", maxAge = 3600)
@RequestMapping(value = BaseAPI.BASE_API_URL)
public class JwtRestController {

    private AuthenticationManager authenticationManager;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    private UserServiceImp userServiceImp;

    @Autowired
    public void setUserServiceImp(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    JwtTokenUtil jwtTokenUtil;

    @Autowired
    public void setJwtTokenUtil(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    BCryptPasswordEncoder encoder;
    @Autowired
    public void setEncoder(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
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

        response.setMessage(messageProperties.insertError("User"));
        response.setError(errors);
        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setTime(new Timestamp(System.currentTimeMillis()));

        return ResponseEntity.ok(response);
    }

    //TODO: Generate token =========================================================
    @PostMapping("/authenticates")
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody TokenRequestUser user) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userServiceImp.loadUserByUsername(user.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        BaseApiResponse<TokenResponse> response = new BaseApiResponse<>();
        TokenResponse tokenResponse = new TokenResponse();
        System.out.println(userDetails);

        if(token==null){
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage(messageProperties.hasNoRecord("User"));
        }else{
            tokenResponse.setToken(new JwtResponse("Bearer " + token));
            tokenResponse.setUsername(user.getUsername());
            tokenResponse.setPassword(user.getPassword());

            response.setMessage(messageProperties.selected("Token"));
            response.setStatus(HttpStatus.OK);
            response.setData(tokenResponse);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    //TODO: Post user =========================================================
    @PostMapping("/users")
    public ResponseEntity<BaseApiResponse<UserResponse>> insertUser(@Valid @RequestBody UserRequest userRequest){

        BaseApiResponse<UserResponse> response = new BaseApiResponse<>();

        try {

            ModelMapper modelMapper = new ModelMapper();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            UserRequest userRequest1 = modelMapper.map(userRequest, UserRequest.class);

            userRequest.setPassword(encoder.encode(userRequest.getPassword()));
            userRequest.setUser_id(UUID.randomUUID().toString());

            int check = userRequest1.getRole_id();

            if(userServiceImp.checkIfTypeExisted(check)) {

                String checkUsername = userRequest.getUsername();

                if(!userServiceImp.checkIfUsernameExisted(checkUsername)) {

                    userServiceImp.insertUser(userRequest);

                    UserDto userDto = (UserDto) userServiceImp.loadUserByUsername(userRequest.getUsername());

                    userServiceImp.insertUserRole(userDto.getId(), userRequest.getRole_id());

                    userDto.setRoles(userServiceImp.selectRolesById(userDto.getId()));

                    UserResponse userResponse = modelMapper.map(userDto, UserResponse.class);

                    response.setMessage(messageProperties.inserted("User"));
                    response.setStatus(HttpStatus.CREATED);
                    response.setData(userResponse);
                }
                else{
                    response.setStatus(HttpStatus.BAD_REQUEST);
                    response.setMessage(messageProperties.duplicate("User","username"));
                }
            }
            else{
                response.setStatus(HttpStatus.BAD_REQUEST);
                response.setMessage(messageProperties.insertedTypeNotFound("User","role"));
            }

        }catch (IllegalArgumentException e){
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage(messageProperties.insertError("User"));
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    //TODO: Update user =========================================================
    @PutMapping("/users/{id}")
    public ResponseEntity<BaseApiResponse<UserDto>> updateUser(
            @PathVariable int id,
            @Valid @RequestBody UserRequest userRequest){

        BaseApiResponse<UserDto> response = new BaseApiResponse<>();

        try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            userRequest.setPassword(encoder.encode(userRequest.getPassword()));

            int check = userRequest.getRole_id();
            String checkUsername = userRequest.getUsername();
            System.out.println(checkUsername);

            UserDto oldUser = userServiceImp.selectById(id);
            System.out.println(oldUser.getUsername());

            if (userServiceImp.checkIfTypeExisted(check)) {
                if(!userServiceImp.checkIfUsernameExisted(checkUsername)) {
                    userServiceImp.updateUser(id, userRequest);
                    UserDto userDto = userServiceImp.selectById(id);
                    response.setMessage(messageProperties.updated("User"));
                    response.setData(userDto);
                    response.setStatus(HttpStatus.OK);
                }
                else if(checkUsername.equals(oldUser.getUsername())){
                    userServiceImp.updateUser(id, userRequest);
                    UserDto userDto = userServiceImp.selectById(id);
                    response.setMessage(messageProperties.updated("User"));
                    response.setData(userDto);
                    response.setStatus(HttpStatus.OK);
                    System.out.println("=======");
                }
                else{
                    response.setMessage(messageProperties.duplicate("User","username"));
                    response.setStatus(HttpStatus.BAD_REQUEST);
                }
            }
            else {
                response.setMessage(messageProperties.updatedTypeNotFound("User","user's role"));
                response.setStatus(HttpStatus.BAD_REQUEST);
            }
        }catch (NullPointerException e){
            response.setMessage(messageProperties.updatedTypeNotFound("User","user"));
            response.setStatus(HttpStatus.BAD_REQUEST);
        }
        catch (MyBatisSystemException duplicateUsername){
            response.setMessage(messageProperties.duplicate("User","username"));
            response.setStatus(HttpStatus.BAD_REQUEST);
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    //TODO: Select all users =========================================================
    @GetMapping("/users")
    public ResponseEntity<BaseApiResponseWithPagination<List<UserResponse>>> getAllUsers(UserFilter username, Pagination pagination){

        ModelMapper mapper = new ModelMapper();

        BaseApiResponseWithPagination<List<UserResponse>> response = new BaseApiResponseWithPagination<>();

        List<UserDto> userDtoList = userServiceImp.selectWithPagination(username,pagination);

        List<UserResponse> userResponse = new ArrayList<>();

        for (UserDto userDto : userDtoList) {
            userResponse.add(mapper.map(userDto, UserResponse.class));
        }

        if (userDtoList.isEmpty()) {

            response.setMessage(messageProperties.hasNoRecords("User"));
            response.setData(new ArrayList<>());
            response.setPagination(pagination);
            response.setStatus(HttpStatus.NO_CONTENT);

        } else {

            response.setMessage(messageProperties.selected("Users"));
            response.setData(userResponse);
            response.setPagination(pagination);
            response.setStatus(HttpStatus.OK);

        }
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    //TODO: Select user by id =========================================================
    @GetMapping("/users/{id}")
    public ResponseEntity<BaseApiResponse<UserResponse>> selectUserById(@PathVariable int id) {

        BaseApiResponse<UserResponse> userResponseBaseApiResponse = new BaseApiResponse<>();
        ModelMapper modelMapper = new ModelMapper();

        UserDto userDto = userServiceImp.selectById(id);

        if(userDto==null){
            userResponseBaseApiResponse.setMessage(messageProperties.hasNoRecord("User"));
            userResponseBaseApiResponse.setStatus(HttpStatus.NO_CONTENT);
        }
        else {
            UserResponse response =  modelMapper.map(userDto,UserResponse.class);
            userResponseBaseApiResponse.setMessage(messageProperties.selectedOne("User"));
            userResponseBaseApiResponse.setData(response);
            userResponseBaseApiResponse.setStatus(HttpStatus.OK);
        }
        userResponseBaseApiResponse.setStatus(HttpStatus.OK);
        userResponseBaseApiResponse.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(userResponseBaseApiResponse);
    }

    //TODO: Delete user =========================================================
    @DeleteMapping("/users/{id}")
    private ResponseEntity<BaseApiResponse<UserResponse>> deleteUser(@PathVariable("id") int id) {

        BaseApiResponse<UserResponse> userResponse = new BaseApiResponse<>();
        ModelMapper modelMapper = new ModelMapper();

        if(userServiceImp.deleteUser(id)){

            UserDto userDto = userServiceImp.selectAfterDelete(id);
            UserResponse response =  modelMapper.map(userDto,UserResponse.class);
            userResponse.setMessage(messageProperties.deleted("User"));
            userResponse.setData(response);
            userResponse.setStatus(HttpStatus.OK);
        }
        else{
            userResponse.setMessage(messageProperties.deletedError("User","user"));
            userResponse.setStatus(HttpStatus.NO_CONTENT);
        }
        userResponse.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(userResponse);
    }


}

