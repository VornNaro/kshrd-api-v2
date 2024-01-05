package com.kshrd.kshrd_websiteapi.service.implement;

import com.kshrd.kshrd_websiteapi.model.alumni.AlumniFilter;
import com.kshrd.kshrd_websiteapi.model.alumni.AlumniRequest;
import com.kshrd.kshrd_websiteapi.repository.AlumniRepository;
import com.kshrd.kshrd_websiteapi.model.alumni.AlumniDto;
import com.kshrd.kshrd_websiteapi.service.AlumniService;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumniServiceImp implements AlumniService {

    private AlumniRepository alumniRepository;

    @Autowired
    public void setAlumniRepository(AlumniRepository alumniRepository) {
        this.alumniRepository = alumniRepository;
    }

    //TODO: Select all alumni =========================================================
    @Override
    public List<AlumniDto> selectWithPagination(AlumniFilter name, Pagination pagination) {

        pagination.setTotalCount(alumniRepository.getAlumniTotalCount(name));
        return alumniRepository.selectWithPagination(name,pagination);
    }

    //TODO: Select alumni by id =========================================================
    @Override
    public AlumniDto selectById(int id) {
        return alumniRepository.selectById(id);
    }

    //TODO: Select alumni after delete =========================================================
    @Override
    public AlumniDto selectAfterDelete(int id) {
        return alumniRepository.selectAfterDelete(id);
    }

    //TODO: Insert alumni =========================================================
    @Override
    public AlumniDto insert(AlumniDto alumniDto) {

        alumniDto.setStatus(true);
        boolean isInserted = alumniRepository.insert(alumniDto);
        if (isInserted) {
            return alumniDto;
        }
        else
            return null;
    }

    //TODO: Delete alumni =========================================================
    @Override
    public boolean delete(int id) {
        return alumniRepository.delete(id);
    }

    //TODO: Update alumni =========================================================
    @Override
    public boolean update(int id, AlumniRequest alumniRequest) {
        return alumniRepository.update(id,alumniRequest);
    }

}
