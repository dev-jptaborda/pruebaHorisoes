
package com.horisoes.beam;

import com.horisoes.dao.PeliculaDao;
import com.horisoes.modelo.Pelicula;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Pol Taborda
 */
@ManagedBean
@ViewScoped
  
public class PeliculaBean 
{    
    
    List<Pelicula> listarPeliculas;
    private Pelicula selPeliSerie;
    private Pelicula pelicula = new Pelicula();
    private boolean upcoming = false;

    public boolean isUpcoming() {
        return upcoming;
    }

    public void setUpcoming(boolean upcoming) {
        this.upcoming = upcoming;
    }
    
      private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    public List<Pelicula> getListarPeliculas() {
        return listarPeliculas;
    }

    public void setListarPeliculas(List<Pelicula> listarPeliculas) {
        this.listarPeliculas = listarPeliculas;
    }
    
   

    public Pelicula getSelPeliSerie() {
        return selPeliSerie;
    }

    public void setSelPeliSerie(Pelicula selPeliSerie) {
        this.selPeliSerie = selPeliSerie;
    }

        
    @PostConstruct
    public void init() {
        try {
            listar_Peliculas();
        } catch (Exception ex) {
            Logger.getLogger(PeliculaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    
    public Pelicula getPelicula() {
        return pelicula;
    }
    
    
    public void crearPelicula() throws Exception{
        PeliculaDao dao;       
        try {
            dao=new PeliculaDao();
           dao.agregar(pelicula);
              listarPeliculas= dao.listar();
        } catch (Exception e) {
            throw e;
        }
    
    }
 
        public void listar_Peliculas() throws Exception{
        PeliculaDao dao;       
        try {
            dao=new PeliculaDao();
           listarPeliculas= dao.listar();
        } catch (Exception e) {
            throw e;
        }
        }

     public void openCrearPelicula()
     {
         pelicula = new Pelicula();}
     
     public void eliminarPelicula(Pelicula pel)throws Exception
     {
        PeliculaDao dao;       
        try {
            dao = new PeliculaDao(); 
           dao.eliminarDao(pel);
          listarPeliculas= dao.listar();
        } catch (Exception e) {
            throw e;
        } 
     }
     
     public void leerEdirarPelicula(Pelicula pel)throws Exception
     {
        PeliculaDao dao;   
        Pelicula temPel;
        try {
            dao = new PeliculaDao(); 
           temPel=dao.selecccionar(pel);
           
           if(temPel!=null){
               this.pelicula=temPel;
           }
        } catch (Exception e) {
            throw e;
        } 
     }
     
     public void editarPelicula()throws Exception
     {
        PeliculaDao dao;       
        try {
            dao = new PeliculaDao(); 
           dao.editarDao(pelicula);
        listarPeliculas= dao.listar();
        } catch (Exception e) {
            throw e;
        } 
     }   
     
          
    /**
     *
     * @throws java.lang.Exception
     */
    public void upcoming() throws Exception
     {
        upcoming = true;
        PeliculaDao dao;       
        try {
            dao = new PeliculaDao(); 
           listarPeliculas= dao.listarPeliculasTop(upcoming);
        } catch (Exception e) {
            throw e;
        } 
     }
}