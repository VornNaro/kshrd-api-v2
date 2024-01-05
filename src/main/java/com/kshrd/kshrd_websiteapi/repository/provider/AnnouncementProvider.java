package com.kshrd.kshrd_websiteapi.repository.provider;

import com.kshrd.kshrd_websiteapi.model.announcement.AnnouncementFilter;
import com.kshrd.kshrd_websiteapi.model.announcement.AnnouncementRequest;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class AnnouncementProvider {

    //TODO: Select all announcements with pagination =========================================================
    public String selectWithPagination(@Param("title") AnnouncementFilter name, @Param("page") Pagination pagination){

        return new SQL(){{
            SELECT("* FROM hrd_announcement");
            WHERE("status = true");
            ORDER_BY("hrd_announcement.id DESC LIMIT #{page.limit} OFFSET #{page.offset}");
            if(name.getTitle()!=null){
                WHERE("hrd_announcement.title ILIKE '%'||#{title.title}||'%'");
            }
        }}.toString();
    }

    //TODO: Select alumni by id =========================================================
    public String selectById(int id){

        return new SQL(){{
            SELECT("* FROM hrd_announcement");
            WHERE("id=#{id}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Select alumni by id =========================================================
    public String selectAfterDelete(int id){

        return new SQL(){{
            SELECT("* FROM hrd_announcement");
            WHERE("id=#{id}");
        }}.toString();
    }

    //TODO: Count total alumni =========================================================
    public String getAnnouncementTotalCount(){

        return new SQL(){{
            SELECT("COUNT(id) FROM hrd_announcement");
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Delete announcement =========================================================
    public String delete(){

        return new SQL(){{
            UPDATE("hrd_announcement");
            SET("status = false");
            WHERE("id = #{id}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    //TODO: Update alumni =========================================================
    public String update(int id, AnnouncementRequest announcementRequest){

        return new SQL(){{
            UPDATE("hrd_announcement");
            SET("title = #{announcementRequest.title}");
            SET("description = #{announcementRequest.description}");
            SET("thumbnail = #{announcementRequest.thumbnail}");
            SET("content = #{announcementRequest.content}");
            WHERE("id = #{id}");
            AND();
            WHERE("status = true");
        }}.toString();
    }
}
