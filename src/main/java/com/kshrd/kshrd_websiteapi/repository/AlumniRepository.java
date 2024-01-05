package com.kshrd.kshrd_websiteapi.repository;

import com.kshrd.kshrd_websiteapi.model.alumni.AlumniFilter;
import com.kshrd.kshrd_websiteapi.model.alumni.AlumniRequest;
import com.kshrd.kshrd_websiteapi.model.announcement.AnnouncementDto;
import com.kshrd.kshrd_websiteapi.repository.provider.AlumniProvider;
import com.kshrd.kshrd_websiteapi.model.alumni.AlumniDto;
import com.kshrd.kshrd_websiteapi.repository.provider.AnnouncementProvider;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlumniRepository {

    //TODO: Select all alumni =========================================================
    @SelectProvider(value = AlumniProvider.class, method = "selectWithPagination")
    List<AlumniDto> selectWithPagination(@Param("name")AlumniFilter name, @Param("page") Pagination pagination);

    //TODO: Select all alumni =========================================================
    @SelectProvider(value = AlumniProvider.class, method = "getAlumniTotalCount")
    Integer getAlumniTotalCount(@Param("name") AlumniFilter name);

    //TODO: Select alumni by id =========================================================
    @SelectProvider(value = AlumniProvider.class, method = "selectById")
    AlumniDto selectById(int id);

    //TODO: Insert alumni =========================================================
    @Insert("INSERT INTO hrd_alumni (name, major, workplace, photo, comment, link, status) " +
            "VALUES (#{name}, #{major}, #{workplace}, #{photo}, #{comment}, #{link}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    boolean insert(AlumniDto alumniDto);

    //TODO: Delete alumni =========================================================
    @DeleteProvider(value = AlumniProvider.class,method = "delete")
    boolean delete(int id);

    //TODO: Update alumni =========================================================
    @UpdateProvider(value = AlumniProvider.class,method = "update")
    boolean update(int id, AlumniRequest alumniRequest);

    //TODO: Select after delete
    @SelectProvider(value = AlumniProvider.class, method = "selectAfterDelete")
    AlumniDto selectAfterDelete(int id);
}
