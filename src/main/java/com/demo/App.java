package com.demo;

import com.dao.MovieDao;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        MovieDao md=new MovieDao();
        
        System.out.println(md.displayAllMovies());
    }
}
