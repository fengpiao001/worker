package com.apising.worker.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public class PageUtil {

    public static IPage pageToMybatisPage(com.apising.common.lang.domain.Page page){
        if(page == null){
            return null;
        }
        IPage mybatisPage = new Page(page.getCurPage(),page.getPageSize());
        return mybatisPage;
    }


    public static <T> com.apising.common.lang.domain.Page<List<T>> mybatisPageToPage(IPage<T> mybatisPage){
        if(mybatisPage == null){
            return null;
        }
        long total = mybatisPage.getTotal();
        com.apising.common.lang.domain.Page page = new com.apising.common.lang.domain.Page();
        page.setTotalNum((int)total);
        page.setCurPage((int)mybatisPage.getCurrent());
        page.setPageSize((int) mybatisPage.getSize());
        page.setList(mybatisPage.getRecords());
        return page;
    }
}
