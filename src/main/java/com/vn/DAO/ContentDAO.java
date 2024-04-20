package com.vn.DAO;

import com.vn.entities.Content;

import java.util.List;


public interface ContentDAO extends GenericDAO<Content,Integer> {

    public Content getContentTitle(String title);

    public List<Content> getContentByAuthorIdAndPagingAndSearch(Integer pageNum, Integer pageSize, String search);

    public int getCountContentByAuthorIdAndSearch(String search);
}
