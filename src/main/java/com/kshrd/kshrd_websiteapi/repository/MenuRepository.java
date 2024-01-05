package com.kshrd.kshrd_websiteapi.repository;

import com.kshrd.kshrd_websiteapi.model.menu.MenuFilter;
import com.kshrd.kshrd_websiteapi.model.menu.MenuParent;
import com.kshrd.kshrd_websiteapi.model.menu.SubMenu;
import com.kshrd.kshrd_websiteapi.repository.provider.MenuProvider;
import com.kshrd.kshrd_websiteapi.model.menu.MenuDto;
import com.kshrd.kshrd_websiteapi.utilities.Pagination;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository {

    //TODO: Select all menu  =========================================================
    @SelectProvider(value = MenuProvider.class,method = "selectAll")
    List<MenuDto> selectAll(@Param("name") MenuFilter name);

    //TODO: Select menu by id =========================================================
    @SelectProvider(value = MenuProvider.class, method = "selectById")
    MenuDto selectById(int id);

    //TODO: Select menu by parent id =========================================================
    @SelectProvider(value = MenuProvider.class, method = "selectByParentId")
    List<SubMenu> selectByParentId(int parentId);

    //TODO: Select menu total count =========================================================
    @SelectProvider(value = MenuProvider.class,method = "getMenuTotalCount")
    Integer getMenuTotalCount();

    //TODO: Insert menu =========================================================
    @Insert("INSERT INTO hrd_menu (parent_id, menu_order, name, link, status) " +
            "VALUES (#{parent_id}, #{menu_order}, #{name}, #{link}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    boolean insert(MenuDto menuDto);

    //TODO: Delete menu =========================================================
    @DeleteProvider(value = MenuProvider.class,method = "delete")
    boolean delete(int id);

    //TODO: Update menu =========================================================
    @UpdateProvider(value = MenuProvider.class,method = "update")
    boolean update(int id,MenuDto menuDto);

    //TODO: Select menu after delete =========================================================
    @SelectProvider(value = MenuProvider.class, method = "selectAfterDelete")
    MenuDto selectAfterDelete(int id);

    //TODO: Select menu by order =========================================================
    @SelectProvider(value = MenuProvider.class, method = "selectByOrder")
    MenuDto selectByOrder(int menu_order);

    //TODO: Select max menu order =========================================================
    @SelectProvider(value = MenuProvider.class, method = "selectMaxOrder")
    int selectMaxOrder();

    //TODO: Change menu order when duplicated =========================================================
    @UpdateProvider(value = MenuProvider.class, method = "changeOrderWhenDuplicated")
    boolean changeOrderWhenDuplicated(int menu_order, int maxOrder);

    //TODO: Select all menu with pagination =========================================================
    @SelectProvider(value = MenuProvider.class,method = "selectAllMenuWithPagination")
    List<MenuDto> selectAllMenuWithPagination(@Param("name") MenuFilter name, @Param("page") Pagination pagination);

    @SelectProvider(value = MenuProvider.class,method = "selectParentIDByID")
    int selectParentIDByID(int id);

    @SelectProvider(value = MenuProvider.class,method = "selectParentByParentID")
    MenuParent selectParentByParentID(int id);
}
