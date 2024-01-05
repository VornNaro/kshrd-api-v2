package com.kshrd.kshrd_websiteapi.repository;

import com.kshrd.kshrd_websiteapi.model.announcement.AnnouncementDto;
import com.kshrd.kshrd_websiteapi.model.announcement.AnnouncementFilter;
import com.kshrd.kshrd_websiteapi.model.announcement.AnnouncementRequest;
import com.kshrd.kshrd_websiteapi.repository.provider.AnnouncementProvider;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnouncementRepository {

    //TODO: Select all announcement =========================================================
    @SelectProvider(value = AnnouncementProvider.class, method = "selectWithPagination")
    List<AnnouncementDto> selectWithPagination(@Param("title") AnnouncementFilter name, @Param("page") Pagination pagination);

    //TODO: Select all total count announcement =========================================================
    @SelectProvider(value = AnnouncementProvider.class, method = "getAnnouncementTotalCount")
    Integer getAnnouncementTotalCount(@Param("title") AnnouncementFilter title);

    //TODO: Select alumni by id =========================================================
    @SelectProvider(value = AnnouncementProvider.class, method = "selectById")
    AnnouncementDto selectById(int id);

    //TODO: Insert announcement =========================================================
    @Insert("INSERT INTO hrd_announcement (title,description,thumbnail,content,date,status) " +
            "VALUES (#{title},#{description},#{thumbnail},#{content}, #{date}, true)")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    boolean insert(AnnouncementDto announcementDto);

    //TODO: Delete alumni =========================================================
    @DeleteProvider(value = AnnouncementProvider.class,method = "delete")
    boolean delete(int id);

    //TODO: Update alumni =========================================================
    @UpdateProvider(value = AnnouncementProvider.class,method = "update")
    boolean update(int id, AnnouncementRequest announcementRequest);

    //TODO: Select after delete
    @SelectProvider(value = AnnouncementProvider.class, method = "selectAfterDelete")
    AnnouncementDto selectAfterDelete(int id);
}
