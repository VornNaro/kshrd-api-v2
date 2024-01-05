package com.kshrd.kshrd_websiteapi.service.implement;

import com.kshrd.kshrd_websiteapi.model.partner.PartnerFilter;
import com.kshrd.kshrd_websiteapi.model.partner.PartnerRequest;
import com.kshrd.kshrd_websiteapi.model.partner.PartnerResponse;
import com.kshrd.kshrd_websiteapi.repository.PartnerRepository;
import com.kshrd.kshrd_websiteapi.model.partner.PartnerDto;
import com.kshrd.kshrd_websiteapi.service.PartnerService;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartnerServiceImp implements PartnerService {

    private PartnerRepository partnerRepository;

    @Autowired
    public void setPartnerRepository(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    //TODO: Select all partners =========================================================
    @Override
    public List<PartnerDto> selectWithPagination(Integer partnerTypeId,PartnerFilter name,Pagination pagination) {
        pagination.setTotalCount(partnerRepository.getPartnerTotalCount(partnerTypeId));
        return partnerRepository.selectWithPagination(partnerTypeId,name,pagination);
    }

    @Override
    public List<PartnerDto> selectAll(PartnerFilter name, Pagination pagination) {
        pagination.setTotalCount(partnerRepository.getAllPartnerTotalCount(name));
        return partnerRepository.selectAll(name,pagination);
    }

    //TODO: Check if type exists =========================================================
    @Override
    public boolean checkIfTypeExisted(int id) {

        boolean existed=false;
        if(partnerRepository.checkIfTypeExisted(id)!=null)
            existed=true;
        return existed;
    }

    //TODO: Select partner by id =========================================================
    @Override
    public PartnerDto selectById(int id) {
        return partnerRepository.selectById(id);
    }

    //TODO: Select partner after delete =========================================================
    @Override
    public PartnerResponse selectAfterDelete(int id) {
        return partnerRepository.selectAfterDelete(id);
    }

    //TODO: Insert partner =========================================================
    @Override
    public PartnerDto insert(PartnerDto partnerDto) {

        partnerDto.setStatus(true);
        boolean isInserted = partnerRepository.insert(partnerDto);
        if (isInserted) {
            int id = partnerDto.getPartnerType().getId();
            partnerDto.getPartnerType().setName(partnerRepository.getPartnerTypeName(id));
            return partnerDto;
        }
        else
            return null;
    }

    //TODO: Delete partner =========================================================
    @Override
    public boolean delete(int id) {
        return partnerRepository.delete(id);
    }

    //TODO: Update partner =========================================================
    @Override
    public boolean update(int id, PartnerRequest partnerRequest) {
        return partnerRepository.update(id,partnerRequest);
    }

}
