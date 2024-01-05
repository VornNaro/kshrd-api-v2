package com.kshrd.kshrd_websiteapi.service.implement;

import com.kshrd.kshrd_websiteapi.model.announcement.AnnouncementDto;
import com.kshrd.kshrd_websiteapi.model.announcement.AnnouncementFilter;
import com.kshrd.kshrd_websiteapi.model.announcement.AnnouncementRequest;
import com.kshrd.kshrd_websiteapi.repository.AnnouncementRepository;
import com.kshrd.kshrd_websiteapi.service.AnnouncementService;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementServiceImp implements AnnouncementService {

    private AnnouncementRepository announcementRepository;

    @Autowired
    public void setAnnouncementRepository(AnnouncementRepository announcementRepository) {

        this.announcementRepository = announcementRepository;
    }

    //TODO: Select all announcements =========================================================
    @Override
    public List<AnnouncementDto> selectWithPagination(AnnouncementFilter title, Pagination pagination) {

        pagination.setTotalCount(announcementRepository.getAnnouncementTotalCount(title));
        return announcementRepository.selectWithPagination(title,pagination);
    }

    //TODO: Select announcement by id =========================================================
    @Override
    public AnnouncementDto selectById(int id) {
        return announcementRepository.selectById(id);
    }

    //TODO: Select announcement after delete =========================================================
    @Override
    public AnnouncementDto selectAfterDelete(int id) {
        return announcementRepository.selectAfterDelete(id);
    }

    //TODO: Insert announcement =========================================================
    @Override
    public AnnouncementDto insert(AnnouncementDto announcementDto) {

        boolean isInserted = announcementRepository.insert(announcementDto);
        if (isInserted) {
            return announcementDto;
        }
        else
            return null;
    }

    //TODO: Delete announcement =========================================================
    @Override
    public boolean delete(int id) {
        return announcementRepository.delete(id);
    }

    //TODO: Update announcement =========================================================
    @Override
    public boolean update(int id, AnnouncementRequest announcementRequest) {

        return announcementRepository.update(id,announcementRequest);
    }
}
