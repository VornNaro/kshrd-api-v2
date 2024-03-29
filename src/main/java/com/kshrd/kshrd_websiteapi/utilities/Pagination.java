package com.kshrd.kshrd_websiteapi.utilities;

import io.swagger.annotations.ApiModelProperty;

public class Pagination {

    private int page;
    private int limit;

    @ApiModelProperty(hidden=true)
    private int nextPage;

    @ApiModelProperty(hidden=true)
    private int previousPage;

    @ApiModelProperty(hidden=true)
    private int totalCount;

    @ApiModelProperty(hidden=true)
    private int totalPages;

    @ApiModelProperty(hidden=true)
    private int pagesToShow;

    @ApiModelProperty(hidden=true)
    private int startPage;

    @ApiModelProperty(hidden=true)
    private int endPage;

    @ApiModelProperty(hidden=true)
    private int offset;

    public Pagination() {
        this(1,5,0,0,5);
    }

    public Pagination(int page, int limit, int totalCount, int totalPages, int pagesToShow){
        this.page= page;
        this.limit= limit;
        this.totalCount=totalCount;
        this.totalPages=totalPages;
        this.pagesToShow=pagesToShow;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int currentPage) {
        this.page = (currentPage<=1)? 1:currentPage;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {

        this.totalCount = totalCount;
        this.setStartPageEndPage(getTotalPages());
    }
    private  void setStartPageEndPage(int totalPages){

        int halfPagesToShow= pagesToShow/2;
        if(totalPages<=pagesToShow){
            startPage=1;
            endPage=totalPages;
        }else if(page - halfPagesToShow <=0){
            startPage=1;
            endPage=pagesToShow;
        }else if(page +halfPagesToShow== totalPages){
            startPage=page-halfPagesToShow;
            endPage=totalPages;
        }else if(page +halfPagesToShow > totalPages){
            startPage=totalPages-pagesToShow +1;
            endPage=totalPages;
        }else{
            startPage=page-halfPagesToShow;
            endPage=page+halfPagesToShow;
        }
    }
    public int getTotalPages() {
        return (int )Math.ceil((double)this.totalCount/limit);
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPagesToShow() {
        return pagesToShow;
    }

    public void setPagesToShow(int pagesToShow) {
        this.pagesToShow = pagesToShow;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public int getOffset() {
        return (this.page-1)*this.limit;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getNextPage() {
        return (int)(page>= getTotalPages()?getTotalCount():page+1);
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getPreviousPage() {
        return (page<=1)?1:page-1;
    }

    public void setPreviousPage(int previousPage) {
        this.previousPage = previousPage;
    }

    @Override
    public String toString() {
        return "Pagination{" +
                "page=" + page +
                ", limit=" + limit +
                ", nextPage=" + nextPage +
                ", previousPage=" + previousPage +
                ", totalCount=" + totalCount +
                ", totalPages=" + totalPages +
                ", pagesToShow=" + pagesToShow +
                ", startPage=" + startPage +
                ", endPage=" + endPage +
                ", offset=" + offset +
                '}';
    }
}
