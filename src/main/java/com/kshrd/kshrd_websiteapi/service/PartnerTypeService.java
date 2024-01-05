package com.kshrd.kshrd_websiteapi.service;

import com.kshrd.kshrd_websiteapi.model.partner_type.PartnerTypeDto;
import com.kshrd.kshrd_websiteapi.model.partner_type.PartnerTypeFilter;
import com.kshrd.kshrd_websiteapi.model.partner_type.PartnerTypeRequest;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;

import java.util.List;

public interface PartnerTypeService {

    //TODO: Select all partner types =========================================================
    List<PartnerTypeDto> selectWithPagination(PartnerTypeFilter name,Pagination pagination);

    //TODO: Select partner type by id =========================================================
    PartnerTypeDto selectById(int id);

    //TODO: Select partner type after delete =========================================================
    PartnerTypeDto selectAfterDelete(int id);

    //TODO: Insert partner type =========================================================
    PartnerTypeDto insert(PartnerTypeDto partnerTypeDto);

    //TODO: Delete partner type =========================================================
    boolean delete(int id);

    //TODO: Update partner type =========================================================
    boolean update(int id,PartnerTypeRequest partnerTypeRequest);

}
