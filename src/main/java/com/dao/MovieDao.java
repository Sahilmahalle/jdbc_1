package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.Movie;

public class MovieDao {
	public int insertMovie(Movie m) {
		int check=0;
		Connection con=null;
		PreparedStatement pst=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jap69","root","root");
			String sql="insert into movies(title,genre,yearOfRelease) values(?,?,?)";
			pst=con.prepareStatement(sql);
			
			pst.setString(1,m.getTitle());
			pst.setString(2, m.getGenre());
			pst.setInt(3,m.getYearOfRelease());
			
			
			check=pst.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			
			
			e.printStackTrace();
		}finally {
			try {
				pst.close();
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		return check;
	}
	
	public int updateMovieById(Movie m) {
	
		int check=0;
		Connection con=null;
		PreparedStatement pst=null;
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jap69","root","root");
			String sql="update movies set genre=?, title=?, yearOfRelease=? where id=?";
			
			pst=con.prepareStatement(sql);
			
			pst.setString(1, m.getGenre());
			pst.setString(2, m.getTitle());
			pst.setInt(3, m.getYearOfRelease());
			pst.setInt(4,m.getId());
			
			check=pst.executeUpdate();
			
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				pst.close();
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		
		return check;
	}
	
	public int deleteMovieById(int id) {
		int check=0;
		
		Connection con=null;
		PreparedStatement pst=null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jap69","root","root");
			String sql="delete from  movies where id=?";
			pst=con.prepareStatement(sql);
			pst.setInt(1,id);
			
			check=pst.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				pst.close();
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			
		}
		
		return check;
	}
	
	public List<Movie> displayAllMovies() {
		List<Movie> result=new ArrayList();
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jap69","root", "root");
			String sql="select* from movies";
			
			pst=con.prepareStatement(sql);
			
			rs=pst.executeQuery();
			while(rs.next()) {
				Movie m1=new Movie();
				m1.setId(rs.getInt("id"));
				m1.setGenre(rs.getString("genre"));
				m1.setTitle(rs.getString("title"));
				m1.setYearOfRelease(rs.getInt("yearOfRelease"));
				result.add(m1);
			}
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pst.close();
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		return result;
	}
	public Movie searchMovieByName(int id) {
		
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		Movie m=null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jap69","root","root");
			String sql="select * from movies where id=?";
			pst=con.prepareStatement(sql);
			
			pst.setInt(1, id);
			rs=pst.executeQuery();
			
			while(rs.next()) {
				m=new Movie();
				m.setGenre(rs.getString("genre"));
				m.setTitle(rs.getString("title"));
				m.setYearOfRelease(rs.getInt("yearOfRelease"));
				m.setId(rs.getInt("id"));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pst.close();
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		
		return m;
	}
}
