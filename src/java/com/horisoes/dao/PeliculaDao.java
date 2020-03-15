package com.horisoes.dao;

import com.horisoes.modelo.Pelicula;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Pol taborda
 */
public class PeliculaDao extends DAO 
{


    public void agregar(Pelicula pel) throws SQLException {

        try 
        {
            this.ConectarBd();

            PreparedStatement st = (PreparedStatement) this.getCn().prepareStatement("INSERT INTO peli_serie (nombre, tipo,imagen,calificacion,duracion) values (?,?,?,?,?)");

            st.setString(1, pel.getNombre());
            st.setString(2, pel.getTipo());
            st.setInt(3, pel.getIdpeli_serie());
            st.setInt(4, pel.getCalificacion());
            st.setInt(5, pel.getDuracion());

            st.executeUpdate();
            
            FacesMessage mesanjes = new FacesMessage ("Registro Agredado");
            FacesContext.getCurrentInstance().addMessage(null, mesanjes);

        } catch (ClassNotFoundException ex) 
        {
            Logger.getLogger(PeliculaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.desConectarBD();
        }

    }

    public List<Pelicula> listar() throws Exception {

        List<Pelicula> listPelicula;
        ResultSet rs;
        try {
            PreparedStatement st ;
            this.ConectarBd();
     st = (PreparedStatement) this.getCn().prepareStatement("SELECT idpeli_serie,nombre,tipo,imagen,calificacion,duracion FROM peli_serie ");
              
            rs = st.executeQuery();
            listPelicula = new ArrayList<>();
            while (rs.next()) {
            Pelicula pel = new Pelicula();                
               
            pel.setIdpeli_serie(rs.getInt("idpeli_serie"));
            pel.setNombre(rs.getString("nombre"));
            pel.setTipo(rs.getString("tipo"));
            pel.setImagen(rs.getString("imagen"));
            pel.setCalificacion(rs.getInt("calificacion"));
            pel.setDuracion(rs.getInt("duracion"));
            listPelicula.add(pel);

     

                        
            }

        } catch (Exception e) {
            throw e;
        } finally {
            desConectarBD();
        }
        return listPelicula;
    }

    public Pelicula selecccionar(Pelicula peli) throws Exception {

        Pelicula pel = null ;
        ResultSet rs;
        try {
            this.ConectarBd();
            PreparedStatement st = (PreparedStatement) this.getCn().prepareStatement("SELECT idpeli_serie,nombre,tipo,imagen,calificacion,duracion FROM peli_serie where idpeli_serie=?");
            st.setInt(1, peli.getIdpeli_serie());
            rs = st.executeQuery();

            while (rs.next()) {
                pel = new Pelicula();
                pel.setIdpeli_serie(rs.getInt("idpeli_serie"));
                pel.setNombre(rs.getString("nombre"));
                pel.setTipo(rs.getString("tipo"));
                pel.setImagen(rs.getString("imagen"));
                pel.setCalificacion(rs.getInt("calificacion"));
                pel.setDuracion(rs.getInt("duracion"));
            }
            FacesMessage mesanjess = new FacesMessage ("Registro Eliminado");
            FacesContext.getCurrentInstance().addMessage(null, mesanjess);

        } catch (Exception e) {
            throw e;
        }    

     finally{this.desConectarBD();}
     return peli;
    }
    

    public void eliminarDao(Pelicula pel) throws SQLException {

        try {
            this.ConectarBd();

            PreparedStatement st = (PreparedStatement) this.getCn().prepareStatement("Delete from peli_serie where idpeli_serie = "+pel.getIdpeli_serie());
            st.executeUpdate();
            
            FacesMessage mesanjess = new FacesMessage ("Registro Eliminado");
            FacesContext.getCurrentInstance().addMessage(null, mesanjess);
            
          
   
            

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PeliculaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.desConectarBD();
        }

    }
    
    public void editarDao(Pelicula pel) throws SQLException {

        try {
            this.ConectarBd();

            PreparedStatement st = (PreparedStatement) this.getCn().prepareStatement("UPDATE peli_serie SET nombre = ?, tipo  = ?,imagen  = ?,calificacion  = ?,duracion= ? WHERE idpeli_serie=?  ");
            st.setString(1, pel.getNombre());
            st.setString(2, pel.getTipo());
            st.setInt(3, pel.getIdpeli_serie());
            st.setInt(4, pel.getCalificacion());
            st.setInt(5, pel.getDuracion());
            st.setInt(6, pel.getIdpeli_serie());

            st.executeUpdate();
            FacesMessage mesanjess = new FacesMessage ("Datos Actualizaos");
            FacesContext.getCurrentInstance().addMessage(null, mesanjess);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PeliculaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.desConectarBD();
        }

    }


    
        public List<Pelicula> listarPeliculasTop(boolean upComing) throws Exception {

        List<Pelicula> listPelicula;
        ResultSet rs;
        try {
            PreparedStatement st ;
            this.ConectarBd();
        st = (PreparedStatement) this.getCn().prepareStatement("SELECT idpeli_serie,nombre,tipo,imagen,calificacion,duracion FROM peli_serie order by calificacion desc");
              
            rs = st.executeQuery();
            listPelicula = new ArrayList<>();
            while (rs.next()) {
            Pelicula pel = new Pelicula();                
               
            pel.setIdpeli_serie(rs.getInt("idpeli_serie"));
            pel.setNombre(rs.getString("nombre"));
            pel.setTipo(rs.getString("tipo"));
            pel.setImagen(rs.getString("imagen"));
            pel.setCalificacion(rs.getInt("calificacion"));
            pel.setDuracion(rs.getInt("duracion"));
            listPelicula.add(pel);
            
            }
          FacesMessage mesanjess = new FacesMessage ("Upcoming...Peliculas por mejor Calificacion");
            FacesContext.getCurrentInstance().addMessage(null, mesanjess);
        } catch (Exception e) {
            throw e;
        } finally {
            desConectarBD();
        }
        return listPelicula;
    }
    
    
}
