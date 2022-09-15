package com.egs.hibernate.model;

public class UserRequest {

    private Integer pageNumber;
    private Integer pageSize;
    private String SortBy;


    public UserRequest(Integer pageNumber, Integer pageSize, String sortBy) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        SortBy = sortBy;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortBy() {
        return SortBy;
    }

    public void setSortBy(String sortBy) {
        SortBy = sortBy;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", SortBy='" + SortBy + '\'' +
                '}';
    }
}
