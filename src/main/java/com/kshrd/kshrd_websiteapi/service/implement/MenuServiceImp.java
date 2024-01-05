package com.kshrd.kshrd_websiteapi.service.implement;

import com.kshrd.kshrd_websiteapi.model.menu.*;
import com.kshrd.kshrd_websiteapi.repository.MenuRepository;
import com.kshrd.kshrd_websiteapi.service.MenuService;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImp implements MenuService {

    private MenuRepository menuRepository;

    @Autowired
    public void setMenuRepository(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    //TODO: Select all menus =========================================================
    @Override
    public List<MenuResponse> selectAll(MenuFilter name) {

        ModelMapper mapper = new ModelMapper();
        List<MenuDto> menuDtoNoSubMenu = new ArrayList<MenuDto>();

        menuDtoNoSubMenu = menuRepository.selectAll(name);
        List<MenuResponse> menuDtoWithSubMenu = new ArrayList<>();

        for (MenuDto menuDto : menuDtoNoSubMenu ){

            MenuResponse response = mapper.map(menuDto,MenuResponse.class);
            List<SubMenu> subMenus = menuRepository.selectByParentId(response.getId());

            response.setParent_id(subMenus);
            menuDtoWithSubMenu.add(response);
        }

        return menuDtoWithSubMenu;
    }

    //TODO: Select menu by id =========================================================
    @Override
    public MenuDto selectById(int id) {
        return menuRepository.selectById(id);
    }

    @Override
    public List<SubMenu> selectByParentId(int parentId) {
        return null;
    }

    //TODO: Select menu after delete =========================================================
    @Override
    public MenuDto selectAfterDelete(int id) {
        return menuRepository.selectAfterDelete(id);
    }

    //TODO: Insert menu =========================================================
    @Override
    public MenuDto insert(MenuDto menuDto) {

        menuDto.setStatus(true);
        int maxOrder = 0;
        boolean duplicated= false;
        if(menuRepository.selectByOrder(menuDto.getMenu_order())!=null){
            maxOrder = menuRepository.selectMaxOrder()+1;
            duplicated = menuRepository.changeOrderWhenDuplicated(menuDto.getMenu_order(),maxOrder);
        }
        boolean isInserted = menuRepository.insert(menuDto);
        if (isInserted) {
            return menuDto;
        }
        else
            return null;
    }

    //TODO: Delete menu =========================================================
    @Override
    public boolean delete(int id) {
        return menuRepository.delete(id);
    }

    //TODO: Update menu =========================================================
    @Override
    public boolean update(int id,MenuDto menuDto) {

        int maxOrder = 0;
        boolean duplicated= false;
        if(menuRepository.selectByOrder(menuDto.getMenu_order())!=null){
            maxOrder = menuRepository.selectMaxOrder()+1;
            duplicated = menuRepository.changeOrderWhenDuplicated(menuDto.getMenu_order(),maxOrder);
        }
        boolean isUpdated = menuRepository.update(id,menuDto);
        return isUpdated;
    }

    @Override
    public List<MenuResponseWithParent> selectAllMenuWithPagination(MenuFilter name, Pagination pagination) {

        ModelMapper mapper = new ModelMapper();
        List<MenuDto> menuDtoNoSubMenu = new ArrayList<MenuDto>();

        pagination.setTotalCount(menuRepository.getMenuTotalCount());

        menuDtoNoSubMenu = menuRepository.selectAllMenuWithPagination(name,pagination);

        List<MenuResponseWithParent> menuDtoWithParentMenu = new ArrayList<>();

        System.out.println(menuDtoNoSubMenu.toString());

        for (MenuDto menuDto : menuDtoNoSubMenu ){

            MenuResponseWithParent response = mapper.map(menuDto, MenuResponseWithParent.class);

            int parentID = menuRepository.selectParentIDByID(response.getId());
            MenuParent menuParent = menuRepository.selectParentByParentID(parentID);
//            System.out.println(menuDto);
//            System.out.println(response);
            if(menuParent!=null)
            response.setMenuParent(menuParent);
            else
                response.setMenuParent(new MenuParent());
            menuDtoWithParentMenu.add(response);
        }

        return menuDtoWithParentMenu;
    }

}
