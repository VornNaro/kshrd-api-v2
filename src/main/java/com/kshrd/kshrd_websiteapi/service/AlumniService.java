package com.kshrd.kshrd_websiteapi.service;

import com.kshrd.kshrd_websiteapi.model.alumni.AlumniDto;
import com.kshrd.kshrd_websiteapi.model.alumni.AlumniFilter;
import com.kshrd.kshrd_websiteapi.model.alumni.AlumniRequest;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AlumniService {

    //TODO: Select all alumni =========================================================
    List<AlumniDto> selectWithPagination(AlumniFilter name, Pagination pagination);

    //TODO: Select alumni by id =========================================================
    AlumniDto selectById(int id);

    //TODO: Select alumni after delete =========================================================
    AlumniDto selectAfterDelete(int id);

    //TODO: Insert alumni =========================================================
    AlumniDto insert(AlumniDto alumniDto);

    //TODO: Delete alumni =========================================================
    boolean delete(int id);

    //TODO: Update alumni =========================================================
    boolean update(int id, AlumniRequest alumniRequest);
}
