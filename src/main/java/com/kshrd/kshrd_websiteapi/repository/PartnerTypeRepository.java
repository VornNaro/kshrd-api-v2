package com.kshrd.kshrd_websiteapi.repository;

import com.kshrd.kshrd_websiteapi.model.partner_type.PartnerTypeDto;
import com.kshrd.kshrd_websiteapi.model.partner_type.PartnerTypeFilter;
import com.kshrd.kshrd_websiteapi.model.partner_type.PartnerTypeRequest;
import com.kshrd.kshrd_websiteapi.repository.provider.PartnerTypeProvider;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartnerTypeRepository {

    //TODO: Select all partner types with pagination =========================================================
    @SelectProvider(value = PartnerTypeProvider.class,method = "selectWithPagination")
    List<PartnerTypeDto> selectWithPagination(@Param("name") PartnerTypeFilter name,@Param("page") Pagination pagination);

    //TODO: Select partner type by id =========================================================
    @SelectProvider(value = PartnerTypeProvider.class, method = "selectById")
    PartnerTypeDto selectById(int id);

    //TODO: Select partner type total count =========================================================
    @SelectProvider(value = PartnerTypeProvider.class,method = "getPartnerTypeTotalCount")
    int getPartnerTypeTotalCount();

    //TODO: Insert partner type =====================================================================
    @Insert("INSERT INTO hrd_partner_type (name, status) " +
            "VALUES (#{name}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    boolean insert(PartnerTypeDto partnerTypeDto);

    //TODO: Delete partner type =====================================================================
    @DeleteProvider(value = PartnerTypeProvider.class,method = "delete")
    boolean delete(int id);

    //TODO: Update partner type =====================================================================
    @UpdateProvider(value = PartnerTypeProvider.class,method = "update")
    boolean update(int id,PartnerTypeRequest partnerTypeRequest);

    //TODO: Select partner type after delete =========================================================
    @SelectProvider(value = PartnerTypeProvider.class, method = "selectAfterDelete")
    PartnerTypeDto selectAfterDelete(int id);
}
