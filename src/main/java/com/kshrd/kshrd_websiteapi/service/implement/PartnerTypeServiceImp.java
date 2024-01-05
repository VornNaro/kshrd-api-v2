package com.kshrd.kshrd_websiteapi.service.implement;

import com.kshrd.kshrd_websiteapi.model.partner_type.PartnerTypeDto;
import com.kshrd.kshrd_websiteapi.model.partner_type.PartnerTypeFilter;
import com.kshrd.kshrd_websiteapi.model.partner_type.PartnerTypeRequest;
import com.kshrd.kshrd_websiteapi.repository.PartnerTypeRepository;
import com.kshrd.kshrd_websiteapi.service.PartnerTypeService;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartnerTypeServiceImp implements PartnerTypeService {

    private PartnerTypeRepository partnerTypeRepository;

    @Autowired
    public void setPartnerTypeRepository(PartnerTypeRepository partnerTypeRepository) {

        this.partnerTypeRepository = partnerTypeRepository;
    }

    //TODO: Select all partner types =========================================================
    @Override
    public List<PartnerTypeDto> selectWithPagination(PartnerTypeFilter name,Pagination pagination) {

        pagination.setTotalCount(partnerTypeRepository.getPartnerTypeTotalCount());
        return partnerTypeRepository.selectWithPagination(name,pagination);
    }

    //TODO: Select partner type by id =========================================================
    @Override
    public PartnerTypeDto selectById(int id) {
        return partnerTypeRepository.selectById(id);
    }

    //TODO: Select partner type after delete =========================================================
    @Override
    public PartnerTypeDto selectAfterDelete(int id) {
        return partnerTypeRepository.selectAfterDelete(id);
    }

    //TODO: Insert partner type =========================================================
    @Override
    public PartnerTypeDto insert(PartnerTypeDto partnerTypeDto) {

        partnerTypeDto.setStatus(true);
        boolean isInserted = partnerTypeRepository.insert(partnerTypeDto);
        if (isInserted) {
            return partnerTypeDto;
        }
        else
            return null;
    }

    //TODO: Delete partner type =========================================================
    @Override
    public boolean delete(int id) {
        return partnerTypeRepository.delete(id);
    }

    //TODO: Update partner type =========================================================
    @Override
    public boolean update(int id,PartnerTypeRequest partnertypeRequest) {

        return partnerTypeRepository.update(id,partnertypeRequest);
    }


}
