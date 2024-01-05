package com.kshrd.kshrd_websiteapi.repository;

import com.kshrd.kshrd_websiteapi.model.partner.PartnerFilter;
import com.kshrd.kshrd_websiteapi.model.partner.PartnerRequest;
import com.kshrd.kshrd_websiteapi.model.partner.PartnerResponse;
import com.kshrd.kshrd_websiteapi.model.partner_type.PartnerTypeDto;
import com.kshrd.kshrd_websiteapi.repository.provider.PartnerProvider;
import com.kshrd.kshrd_websiteapi.model.partner.PartnerDto;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartnerRepository {

    //TODO: Select all partners by partner type id =====================================================================
    @SelectProvider(value = PartnerProvider.class, method = "selectWithPagination")
    @Results({
            @Result(column = "partner_type",property = "partnerType",many = @Many(select = "getPartnerTypeById"))
    })
    List<PartnerDto> selectWithPagination(@Param ("partnerTypeId") Integer partnerTypeId, @Param("name") PartnerFilter name, @Param("page") Pagination pagination);

    //TODO: Check if type exists =========================================================
    @SelectProvider(value = PartnerProvider.class,method = "checkIfTypeExisted")
    Object checkIfTypeExisted(int id);

    //TODO: Select partner total count =========================================================
    @SelectProvider(value = PartnerProvider.class,method = "getPartnerTotalCount")
    Integer getPartnerTotalCount(int partner_type);

    //TODO: Select partner by id =========================================================
    @SelectProvider(value = PartnerProvider.class, method = "selectById")
    @Results({
            @Result(column = "partner_type", property = "partnerType", many = @Many(select = "getPartnerTypeById")),
    })
    PartnerDto selectById(int id);

    //TODO: Select partner type by id
    @SelectProvider(value = PartnerProvider.class, method = "getPartnerTypeById")
    PartnerTypeDto getPartnerTypeById(int partnerTypeId);

    //TODO: Insert partner =====================================================================
    @Insert("INSERT INTO hrd_partner (name, address, logo, partner_type, status) " +
            "VALUES (#{name}, #{address}, #{logo}, #{partnerType.id}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    boolean insert(PartnerDto partnerDto);

    //TODO: Delete partner =====================================================================
    @DeleteProvider(value = PartnerProvider.class,method = "delete")
    boolean delete(int id);

    //TODO: Update partner =====================================================================
    @UpdateProvider(value = PartnerProvider.class,method = "update")
    boolean update(int id,PartnerRequest partnerRequest);

    //TODO: Select partner type name by id
    @SelectProvider(value = PartnerProvider.class, method = "getPartnerTypeName")
    String getPartnerTypeName(int id);

    //TODO: Select partner after delete =========================================================
    @SelectProvider(value = PartnerProvider.class, method = "selectAfterDelete")
    @Results({
            @Result(column = "partner_type", property = "partnerType", many = @Many(select = "getPartnerTypeById")),
    })
    PartnerResponse selectAfterDelete(int id);

    //TODO: Select all partner total count =========================================================
    @SelectProvider(value = PartnerProvider.class,method = "getAllPartnerTotalCount")
    int getAllPartnerTotalCount(PartnerFilter name);

    //TODO: Select all partners =====================================================================
    @SelectProvider(value = PartnerProvider.class, method = "selectAll")
    @Results({
            @Result(column = "partner_type",property = "partnerType",many = @Many(select = "getPartnerTypeById"))
    })
    List<PartnerDto> selectAll(@Param("name") PartnerFilter name, @Param("page") Pagination pagination);
}
