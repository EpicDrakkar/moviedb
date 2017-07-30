package com.example.fabian.mm.model;

import java.util.List;

/**
 * Created by fabian on 7/26/17.
 */

public class MovieResponse
{
    private List<Movie> results;

    private String page;

    private int total_pages;

    private String total_results;

    public List<Movie> getResults ()
    {
        return results;
    }

    public void setResults (List<Movie> results)
    {
        this.results = results;
    }

    public String getPage ()
    {
        return page;
    }

    public void setPage (String page)
    {
        this.page = page;
    }

    public int getTotal_pages ()
    {
        return total_pages;
    }

    public void setTotal_pages (int total_pages)
    {
        this.total_pages = total_pages;
    }

    public String getTotal_results ()
    {
        return total_results;
    }

    public void setTotal_results (String total_results)
    {
        this.total_results = total_results;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [results = "+results+", page = "+page+", total_pages = "+total_pages+", total_results = "+total_results+"]";
    }
}
