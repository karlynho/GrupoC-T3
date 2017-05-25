/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeanPrincipal;

import ControlVistaHome.ControlHome;
import com.uma.diariosur.entidades.Evento;
import com.uma.diariosur.entidades.Formulario;
import com.uma.diariosur.entidades.Imagen;
import com.uma.diariosur.entidades.Megusta;
import com.uma.diariosur.entidades.Periodista;
import com.uma.diariosur.entidades.Usuario;
import com.uma.diariosur.entidades.Valoracion;


import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.enterprise.context.ApplicationScoped;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Carlos
 */
@ManagedBean(eager=true)
@Named(value = "beanPrincipal")
@ApplicationScoped


public class BeanPrincipal implements Serializable{
    
    @Inject
    private ControlHome ch;
      private List<Evento>eventos;
      private List<Formulario> formularios;
      private List<Imagen> imagenes;

    private List<Evento>eventosFiltrados;
    private List<Usuario> usuarios;
    private List<Periodista> periodistas;
    private List<Megusta> megusta;
    private Usuario usuario;

    private Evento eventoV;
     private List<Evento> validos = new ArrayList<Evento>();
      private List<Valoracion> valoracion = new ArrayList<Valoracion>();
  
      public List<Valoracion> getValoracion() {
        return valoracion;
    }

    public void setValoracion(List<Valoracion> valoracion) {
        this.valoracion = valoracion;
    }
  public Evento getEventoV() {
        return eventoV;
    }

    public void setEventoV(Evento eventoV) {
        this.eventoV = eventoV;
    }

    public List<Evento> getValidos() {
        return validos;
    }

    public void setValidos(List<Evento> validos) {
        this.validos = validos;
    }
     
  
  

    public List<Megusta> getMegusta() {
        return megusta;
    }

    public void setMegusta(List<Megusta> megusta) {
        this.megusta = megusta;
    }



    public List<Periodista> getPeriodistas() {
        return periodistas;
    }

    public void setPeriodistas(List<Periodista> periodistas) {
        this.periodistas = periodistas;
    }

    @Inject 
    ControlHome ctrlHome;
    
    public List<Evento> getEventosFiltrados() {
        return eventosFiltrados;
    }

    public void setEventosFiltrados(List<Evento> eventosFiltrados) {
        this.eventosFiltrados = eventosFiltrados;
    }

    public ControlHome getCtrlHome() {
        return ctrlHome;
    }

    public void setCtrlHome(ControlHome ctrlHome) {
        this.ctrlHome = ctrlHome;
    }
    
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Formulario> getFormularios() {
        return formularios;
    }

    public List<Imagen> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<Imagen> imagenes) {
        this.imagenes = imagenes;
    }

    public void setFormularios(List<Formulario> formularios) {
        this.formularios = formularios;
    }

     public Usuario user(){
        return this.usuario;
    }
      
    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
    
  
    public BeanPrincipal() throws ParseException{
        
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy",new Locale("es","ES"));
        Date date_ini = (Date)formatter.parse("05/05/2017");
        date_ini.setHours(22);
        
        Date date_fin = (Date)formatter.parse("05/06/2017");
        date_fin.setHours(02);
        
        Date date_ini2 = (Date)formatter.parse("05/09/2017");
        date_ini2.setHours(20);
        
        Date date_fin2 = (Date)formatter.parse("05/09/2017");
        date_fin2.setHours(23);
        
        
        Date date_ini3 = (Date)formatter.parse("05/03/2017");
        date_ini3.setHours(23);
        
        Date date_fin3 = (Date)formatter.parse("05/04/2017");
        date_fin3.setHours(02);
        
        Date date_ini4 = (Date)formatter.parse("06/10/2017");
        date_ini4.setHours(21);
        
        Date date_fin4 = (Date)formatter.parse("06/11/2017");
        date_fin4.setHours(01);
        
        Date date_ini5 = (Date)formatter.parse("05/17/2017");
        date_ini5.setHours(22);
        
        Date date_fin5 = (Date)formatter.parse("05/18/2017");
        date_fin5.setHours(02);
        
        
        Date date_ini6 = (Date)formatter.parse("05/21/2017");
        date_ini6.setHours(20);
        
        Date date_fin6 = (Date)formatter.parse("05/21/2017");
        date_fin6.setHours(23);
        
        Date date_ini7 = (Date)formatter.parse("05/02/2017");
        date_ini7.setHours(10);
        
        Date date_fin7 = (Date)formatter.parse("05/02/2017");
        date_fin7.setHours(12);
        
        Date date_ini8 = (Date)formatter.parse("05/19/2017");
        date_ini8.setHours(8);
        
        Date date_fin8 = (Date)formatter.parse("05/19/2017");
        date_fin8.setHours(11);
        
        Date date_ini9 = (Date)formatter.parse("05/01/2017");
        date_ini9.setHours(22);
        
        Date date_fin9 = (Date)formatter.parse("05/06/2017");
        date_fin9.setHours(02);
       
        
        Date fecha= (Date)formatter.parse("12/05/1993");
        
        
       usuarios = new ArrayList<>();
       Usuario u = new Usuario();
       u.setNombre("Carlos");
       u.setApellidos("Velazquez");
       u.setPassword("buenooo");
       u.setNick("karlynho");
       u.setEmail("carlospuli10@gmail.com");
       u.setFecha_nacimiento(fecha);
       usuarios.add(u);
       
       Usuario u1 = new Usuario();
       u1.setNombre("Carmen");
       u1.setApellidos("Moreno");
       u1.setPassword("hola");
       u1.setNick("CarmenB");
       u1.setEmail("carmen_06_95@hotmail.com");
       u1.setFecha_nacimiento(fecha);
       usuarios.add(u1);
       
       
       
       
       periodistas = new ArrayList<>();
       
       Periodista p = new Periodista();
       p.setNombre("Steven");
       p.setApellidos("Montoya");
       p.setPassword("contrasenia");
       p.setEmail("smv@gmail.com");
       p.setDni("78974380Q");
       periodistas.add(p);
        
        
        
        

       
       
       
        imagenes = new ArrayList<>();
        Imagen i1 = new Imagen();
        i1.setTipo(".jpg");
        i1.setEnlace("Malaga-RMD.jpg");
        
        Imagen i2 = new Imagen();
        i2.setTipo(".jpg");
        i2.setEnlace("Offspring.jpg");
        
        Imagen i3 = new Imagen();
        i3.setTipo(".jpg");
        i3.setEnlace("Red Hot Chili Peppers.jpg");
        
        Imagen i4 = new Imagen();
        i4.setTipo(".jpg");
        i4.setEnlace("Uni vs RMB.jpg");
        
        
        Imagen i5 = new Imagen();
        i5.setEnlace("JasonDerulo.jpg");
        
        
        Imagen i6 = new Imagen();
        i6.setEnlace("cuadros_exposicion.jpg");
        
        Imagen i7 = new Imagen();
        i7.setEnlace("lebarbe1.jpg");
       
        Imagen i8 = new Imagen();
        i8.setEnlace("Melendi.jpg");
        
        Imagen i9 = new Imagen();
        i9.setEnlace("Dani Martin.jpg");
        
        Imagen i10 = new Imagen();
        i10.setEnlace("Leiva.jpg");
        
        Imagen i11 = new Imagen();
        i11.setEnlace("chema.jpg");
        
        Imagen i12 = new Imagen();
        i12.setEnlace("feriaquesojpg.jpg");
        
        Imagen i13 = new Imagen();
        i13.setEnlace("feria.jpg");
        
        eventos = new ArrayList<>();
        Evento e1 = new Evento();
        e1.setNombre("Red Hot Chili Peppers");
        e1.setCategoria("Conciertos");
        e1.setDescripcion("Los Red Hot Chili Peppers visitan España por primera vez desde hace 5 años y vienen a Malaga"
                + "interpretar sus exitos y sus nuevos temas del nuevo disco");
        e1.setFecha_inicio(date_ini);
        e1.setFecha_final(date_fin);
        e1.setPeriodista(p);

        e1.setPrecio(20.00);
        e1.setUbicacion("Estadio de Futbol La Rosaleda (Málaga)");
        e1.setImagen(i3);
        
        
        Evento e2 = new Evento();
        e2.setNombre("Uni vs RMB");
        e2.setCategoria("Deportivo");
        e2.setDescripcion("Partido perteneciente a la jornada numero 12 de la Liga Endesa entre el líder de la clasificacion"
                + "y el siempre peligroso Unicaja");
        e2.setFecha_inicio(date_ini2);
        e2.setFecha_final(date_fin2);
        e2.setPeriodista(p);
        e2.setPrecio(34.00);
        e2.setUbicacion("Palacio de los Deportes Martin Carpena (Málaga)");
        e2.setImagen(i4);
        
        
        Evento e3 = new Evento();
        e3.setNombre("Offspring");
        e3.setCategoria("Conciertos");
        e3.setDescripcion("El mitico grupo de Rock comienza su gira europea en España en concreto en Malaga, ciudad que"
                + "nunca han visitado");
        e3.setFecha_inicio(date_ini3);
        e3.setFecha_final(date_fin3);
        e3.setPeriodista(p);
        e3.setPrecio(324.00);
        e3.setUbicacion("Paris 15 (Málaga)");
        e3.setImagen(i2);
        
      
        eventos.add(e1);
        eventos.add(e2);
        eventos.add(e3);
       
        
        
        Evento e4 = new Evento();
        e4.setNombre("Melendi");
        e4.setCategoria("Conciertos");
        e4.setDescripcion("El cantante y compositor Melendi estrena gira de su nuevo disco Botella de vino y sus exitos 'Aterriza como puedas' y"
                + "este vuelo no despega");
        e4.setFecha_inicio(date_ini4);
        e4.setFecha_final(date_fin4);
        e4.setPeriodista(p);
        e4.setPrecio(34.00);


        e4.setUbicacion("Starlite (Marbella)");

        e4.setImagen(i8);
        
        
     
        Evento e5 = new Evento();
        e5.setNombre("Dani Martin");
        e5.setCategoria("Conciertos");
        e5.setDescripcion("El cantante madrileño visita Marbella por segunda vez tras 5 años para rememorar sus viejos exitos y "
                + "la actuación de su nuevo disco 'Chanclas'");
        e5.setFecha_inicio(date_ini5);
        e5.setFecha_final(date_fin5);
        e5.setPeriodista(p);
        e5.setPrecio(34.00);
        e5.setUbicacion("Plaza de Toros (Marbella)");
        
        e5.setImagen(i9);
        
      Evento e6 = new Evento();
        e6.setNombre("Leiva");
        e6.setCategoria("Conciertos");
        e6.setDescripcion("Primera vez desde su separación el ex de Pereza, protagoniza una actuación en la provincia");
        e6.setFecha_inicio(date_ini6);
        e6.setFecha_final(date_fin6);
        e6.setPeriodista(p);
        e6.setPrecio(34.00);

        e6.setUbicacion("Plaza de Toros (Málaga)");

      
        
        e6.setImagen(i10);
       
        eventos.add(e4);
        eventos.add(e5);
        eventos.add(e6);
        
        Evento e7 = new Evento();
        e7.setNombre("Dialogando : Chema Alonso");
        e7.setCategoria("Conferencia");
        e7.setDescripcion("Seguimos celebrando nuestro 20 Aniversario con el proyecto \"Dialogando\". "
                + "Tras tratar asuntos tan importantes para toda la sociedad como la educación y la nutrición, el próximo debate girará en torno a un tema de máxima actualidad como es la ciberseguridad y para ello contaremos con el hacker y chief data officer (CDO) de Telefónica Chema Alonso (descargar cartel)."
                + " La entrevista la realizará la periodista de Canal Sur Radio Susana Escudero.");
        e7.setFecha_inicio(date_ini7);
        e7.setFecha_final(date_fin7);
        e7.setPeriodista(p);
        e7.setPrecio(0.00);
        e7.setUbicacion(" Salón de Actos de la E.T.S.I. Informática (Málaga)");
      
        
        e7.setImagen(i11);
        
        Evento e8 = new Evento();
        e8.setNombre("Feria del Queso Artesanal");
        e8.setCategoria("Gastronomia");
        e8.setDescripcion("El fin de semana del 1 y 2 de abril se va a celebrar la Feria del Queso Artesanal de Andalucía 2017,"
                + " es la novena edición de esta feria que tiene lugar en la cuna del queso de la Sierra de Cádiz, en Villaluenga del Rosario."
                + " Este año participan una treintena de quesería y además se realizarán distintas actividades y concursos");
        e8.setFecha_inicio(date_ini8);
        e8.setFecha_final(date_fin8);
        e8.setPeriodista(p);
        e8.setPrecio(5.00);
        e8.setUbicacion("Palacio de Ferias (Málaga)");
      
        
        e8.setImagen(i12);
        
        Evento e9 = new Evento();
        e9.setNombre("Feria de los Pueblos Fuengirola");
        e9.setCategoria("Gastronomia");
        e9.setDescripcion("La Feria de los Pueblos 2017 comenzará el jueves 27 de Abril y finalizará el 2 de Mayo, un día más de lo normal debido" + 
                "a los dos días de fuertes lluvias. Durante ese tiempo podremos disfrutar de un espacio multicultural donde poder" +
                "paladear la gastronomía y conocer el folclore de los lugares representado");
        e9.setFecha_inicio(date_ini9);
        e9.setFecha_final(date_fin9);
        e9.setPeriodista(p);
        e9.setPrecio(0.00);
        e9.setUbicacion("Parque de España (Fuengirola)");
      
        
        e9.setImagen(i13);
        
        eventos.add(e7);
        eventos.add(e8);
        eventos.add(e9);
        /*
        Valoracion v1= new Valoracion(1234,"Me ha parecido muy bueno", 3,u, e1);
        Valoracion v2= new Valoracion(1235,"Ha sido muy bueno", 5,u, e1);
        Valoracion v3= new Valoracion(1236,"Me ha encantado", 4,u, e1);
        Valoracion v4= new Valoracion(1237,"Podia haber esado algo mejor", 2,u, e1);
       
        List<Valoracion> v = new ArrayList();
        v.add(v1);
        v.add(v2);
        v.add(v3);
        v.add(v4);
        e1.setValoraciones(v);
      
         List<Valoracion> v_vacia = new ArrayList();
         e2.setValoraciones(v_vacia);
         e3.setValoraciones(v_vacia);
         e4.setValoraciones(v_vacia);
         e5.setValoraciones(v_vacia);
         e6.setValoraciones(v_vacia);
         e7.setValoraciones(v_vacia);
         e8.setValoraciones(v_vacia);
         e9.setValoraciones(v_vacia);
*/

      
        
        megusta = new ArrayList<Megusta>();
        Megusta m1 = new Megusta();
       
        m1.setUsuario(u);
        m1.setEvento(e1);
        Megusta m2 = new Megusta();
       
        m2.setUsuario(u);
        m2.setEvento(e3);
        Megusta m3 = new Megusta();
        m3.setUsuario(u);
        m3.setEvento(e2);
       
         megusta.add(m1);
         megusta.add(m3);
         megusta.add(m2);
        
        u.setMegusta(megusta);
        
        Megusta m4 = new Megusta();
       
        m4.setUsuario(u1);
        m4.setEvento(e1);
        megusta.add(m4);
        u1.setMegusta(megusta);
        
        
        formularios = new ArrayList<>();
        Formulario f = new Formulario();
            f.setNombre("Jason Derulo Starlite");
            f.setDescripcion("Jason Derulo, considerado como una de las mejores voces de la ú"
                    + "ltima década, se subirá al escenario de Starlite el jueves 24 de agosto y"
                    + " pondrá a bailar al público de la cantera con su ritmo inconfundible. "
                    + "Este concierto es el único que tiene confirmado por el momento en España. Cantante,"
                    + " compositor y bailarín Derulo presentará en Starlite su nuevo disco del que ya hemos "
                    + "podido escuchar el primer single “Swalla”"
                    );
            f.setCategoria("Concierto");
            f.setFecha_inicio(date_ini2);
            f.setFecha_fin(date_fin2);
            f.setUbicacion("Marbella (Malaga)");
            f.setPrecio(70.00);
            f.setFecha_subida(new Date());
            f.setEstado("pendiente");
            f.setUsuario(u);
            f.setIm_id(i5);
            
            formularios.add(f);
        
            
            
        Formulario f1 = new Formulario();
            f1.setNombre("Seurat´s Circus Sideshow");
            f1.setDescripcion("Una exhibición temática dedicada a la obra neo impresionista del pintor francés Georges Seurat."
                    + " Más de cien pinturas, dibujos, impresiones e ilustraciones relacionadas "
                    + "con esta obra exhibida por primera vez en París en 1888 serán puestas en exhibición. ");
            f1.setCategoria("Exposicion");
            f1.setFecha_inicio(date_ini5);
            f1.setFecha_fin(date_fin5);
            f1.setUbicacion("Palacio de Congresos (Marbella)");
            f1.setPrecio(10.00);
            f1.setFecha_subida(new Date());
            f1.setEstado("pendiente");
            f1.setUsuario(u);
            f1.setIm_id(i6);
            
            formularios.add(f1);
            
        Formulario f2 = new Formulario();
            f2.setNombre("Mohas´s Moet");
            f2.setDescripcion("Una quedada shishera dedicada a la shisha de la marca Moet"
                    + " Más de 123 tabacos, cachimbas, accesorios y articulos relacionados "
                    + "con este evento organizado por primera vez en Marbella en 2017. ");
            f2.setCategoria("Charlas");
            f2.setFecha_inicio(date_ini6);
            f2.setFecha_fin(date_fin6);
            f2.setUbicacion("Palacio de Congresos (Marbella)");
            f2.setPrecio(10.00);
            f2.setFecha_subida(new Date());
            f2.setEstado("pendiente");
            f2.setUsuario(u);
            f2.setIm_id(i7);
           
            formularios.add(f2);
        
        
        imagenes.add(i1);
        imagenes.add(i2);
        imagenes.add(i3);
        imagenes.add(i4);
        imagenes.add(i5);
        imagenes.add(i6);
        imagenes.add(i7);
        imagenes.add(i8);
        imagenes.add(i9);
        imagenes.add(i10);
        imagenes.add(i11);
        imagenes.add(i12);
        imagenes.add(i13);
    }

    public void eliminarForm(Formulario f) {
       
        int i = 0;
        int aux=0;
        boolean encontrado = false;
        while (i<formularios.size() && !encontrado){
            if(formularios.get(i).getNombre().equalsIgnoreCase(f.getNombre())){
                encontrado = true;
                aux = i;
                
            }
            i++;
        }
        
        formularios.remove(aux);
      
    }
    
    public void addEvent(Evento e){
        eventos.add(e);
    }
    
    public void addForm(Formulario f){
        formularios.add(f);
    }
    
    public void addImage(Imagen i){
        imagenes.add(i);
    }
    
    public void añadirUsuario(Usuario u){
        usuarios.add(u);
    }
    
    public void deleteImage(Imagen i){
        imagenes.remove(i);
    }
    
     public void eliminarMegusta(Megusta e){
        int i = 0;
        
        int aux=0;
        
        boolean encontrado = false;
        while (i<megusta.size() && !encontrado){
            if(megusta.get(i).getEvento().getNombre().equalsIgnoreCase(e.getEvento().getNombre())){
                encontrado = true;
                aux = i;
                
            }
            i++;
        }
        
        megusta.remove(aux);
        
      
    }
    
    public void addMegusta(Megusta e){
        megusta.add(e);
    }
  
    public List<Megusta> mismegusta(){
        List<Megusta> lista = new ArrayList<>();
        int i=0;
        while(i< megusta.size()){
           if(megusta.get(i).getUsuario().getNick().equals(ch.getUsuario().getNick())){
               lista.add(megusta.get(i));
           } 
           i++;
        }
        return lista;
    }
    
    public void intercambiar(String password){
        ch.getUsuario().setPassword(password);
    }
    
    public void cambio(String correo){
        ch.getUsuario().setEmail(correo);
    }
    
    public void cambiar_pass (int a, String pass){
        
       
       usuarios.get(a).setPassword(pass);
       
        
        
    }
    
}
