package com.kshrd.kshrd_websiteapi.service;

import com.kshrd.kshrd_websiteapi.model.partner.PartnerDto;
import com.kshrd.kshrd_websiteapi.model.partner.PartnerFilter;
import com.kshrd.kshrd_websiteapi.model.partner.PartnerRequest;
import com.kshrd.kshrd_websiteapi.model.partner.PartnerResponse;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface PartnerService {

    //TODO: Select all partners by partner type id =========================================================
    List<PartnerDto> selectWithPagination(Integer partnerTypeId,PartnerFilter name,Pagination pagination);

    //TODO: Select all partners =========================================================
    List<PartnerDto> selectAll(PartnerFilter name,Pagination pagination);

    //TODO: Select partner by id =========================================================
    PartnerDto selectById(int id);

    //TODO: Select partner after delete =========================================================
    PartnerResponse selectAfterDelete(int id);

    //TODO: Insert partner =========================================================
    PartnerDto insert(PartnerDto partnerDto);

    //TODO: Delete partner =========================================================
    boolean delete(int id);

    //TODO: Update partner =========================================================
    boolean update(int id, PartnerRequest partnerRequest);

    //TODO: Check if type exists =========================================================
    boolean checkIfTypeExisted(int id);
}
