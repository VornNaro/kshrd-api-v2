package com.kshrd.kshrd_websiteapi.service;

import com.kshrd.kshrd_websiteapi.model.menu.*;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MenuService {

    //TODO: Select all menus =========================================================
    List<MenuResponse> selectAll(MenuFilter name);

    //TODO: Select menu by id =========================================================
    MenuDto selectById(int id);

    //TODO: Select menu by parent id =========================================================
    List<SubMenu>  selectByParentId(int parentId);

    //TODO: Select menu after delete =========================================================
    MenuDto selectAfterDelete(int id);

    //TODO: Insert menu =========================================================
    MenuDto insert(MenuDto menuDto);

    //TODO: Delete menu =========================================================
    boolean delete(int id);

    //TODO: Update menu =========================================================
    boolean update(int id, MenuDto menuDto);

    //TODO: Select all menus with pagination =========================================================
    List<MenuResponseWithParent> selectAllMenuWithPagination(MenuFilter name,Pagination pagination);
}
