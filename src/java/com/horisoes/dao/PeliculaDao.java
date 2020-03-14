package com.horisoes.dao;

import com.horisoes.modelo.Pelicula;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pol taborda
 */
public class PeliculaDao extends DAO 
{


    public void agregar(Pelicula pel) throws SQLException {

        try {
            this.ConectarBd();

            PreparedStatement st = (PreparedStatement) this.getCn().prepareStatement("INSERT INTO Pelicula (titulo, genero,sinopsis,tipo,calificacion,imagen,estreno) values (?,?,?,?,?,?,?)");

            st.setString(1, pel.getNombre());
            st.setString(2, pel.getTipo());
            st.setString(3, pel.getImagen());
            st.setInt(4, pel.getCalificacion());
            st.setInt(5, pel.getDuracion());

            st.executeUpdate();

        } catch (ClassNotFoundException ex) {
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
     st = (PreparedStatement) this.getCn().prepareStatement("SELECT idpeli_serie,nombre,tipo,imagen,calificacion,duracion FROM peli_serie");
              
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
                pel.setIdpeli_serie(rs.getInt("getIdpeli_serie"));
                pel.setNombre(rs.getString("nombre"));
                pel.setTipo(rs.getString("tipo"));
                pel.setImagen(rs.getString("imagen"));
                pel.setCalificacion(rs.getInt("calificacion"));
                pel.setDuracion(rs.getInt("duracion"));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            desConectarBD();
        }
        return pel;
    }
    

    public void eliminarDao(Pelicula pel) throws SQLException {

        try {
            this.ConectarBd();

            PreparedStatement st = (PreparedStatement) this.getCn().prepareStatement("Delete from peli_serie where idpeli_serie = "+pel.getIdpeli_serie());
            st.executeUpdate();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PeliculaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.desConectarBD();
        }

    }
    
    public void editarDao(Pelicula pel) throws SQLException {

        try {
            this.ConectarBd();

            PreparedStatement st = (PreparedStatement) this.getCn().prepareStatement("UPDATE  Pelicula SET nombre = ?, tipo  = ?,imagen  = ?,calificacion  = ?,duracion= ? WHERE idpeli_serie=?  ");
            st.setString(1, pel.getNombre());
            st.setString(2, pel.getTipo());
            st.setString(3, pel.getImagen());
            st.setInt(4, pel.getCalificacion());
            st.setInt(5, pel.getDuracion());
            st.setInt(6, pel.getIdpeli_serie());

            st.executeUpdate();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PeliculaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.desConectarBD();
        }

    }


}
