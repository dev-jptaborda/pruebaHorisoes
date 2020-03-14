
package com.horisoes.dao;


import java.sql.*;

/**
 *
 * @author Pol Taborda
 */
public class DAO {
    private Connection cn;

   

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }
  public void ConectarBd() throws ClassNotFoundException{
    try{
        final String Controlador="com.mysql.jdbc.Driver";
        Class.forName(Controlador);
        final String url_bd="jdbc:mysql://localhost:3306/peliculasql?characterEncoding=utf8";
        cn= (com.mysql.jdbc.Connection) DriverManager.getConnection(url_bd,"root","root");
    }catch(ClassNotFoundException | SQLException e){
        
    }
    }
    
  public void desConectarBD(){
    try{
        if(cn!=null){
            if(cn.isClosed()==false){
                cn.close();
            }
        }
    }catch(SQLException e){
    }
    }
    
}
