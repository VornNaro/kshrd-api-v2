package com.kshrd.kshrd_websiteapi.service;

import com.kshrd.kshrd_websiteapi.model.announcement.AnnouncementDto;
import com.kshrd.kshrd_websiteapi.model.announcement.AnnouncementFilter;
import com.kshrd.kshrd_websiteapi.model.announcement.AnnouncementRequest;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;

import java.util.List;

public interface AnnouncementService {

    //TODO: Select all announcements =========================================================
    List<AnnouncementDto> selectWithPagination(AnnouncementFilter filter, Pagination pagination);

    //TODO: Select announcement by id =========================================================
    AnnouncementDto selectById(int id);

    //TODO: Select announcement after delete =========================================================
    AnnouncementDto selectAfterDelete(int id);

    //TODO: Insert announcement =========================================================
    AnnouncementDto insert(AnnouncementDto announcementDto);

    //TODO: Delete announcement =========================================================
    boolean delete(int id);

    //TODO: Update announcement =========================================================
    boolean update(int id, AnnouncementRequest announcementRequest);
}
