package com.example.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.dao.EquiposDao;
import com.example.dto.JugadorDTO;
import com.example.exceptions.NombreNoEncontradoException;
import com.example.model.Equipo;

@Service
public class EquiposServiceImpl implements EquiposService{

    @Autowired
    EquiposDao dao;

    @Autowired
    RestTemplate template;
    String url = "http://localhost:8888/";

    /**
     * Metodo que devuelve un listado de equipos ordenado por puntos
     */
    @Override
    public List<Equipo> getEquipos() {   
        List<Equipo> equipos = dao.findAll();
        equipos.sort((equipo1, equipo2) -> Integer.compare(equipo2.getPuntos(), equipo1.getPuntos()));
        return equipos;
    }

    /**
     * Metodo que devuelve un listado de equipos ordenado por victorias
     */
    @Override
    public List<Equipo> getEquiposVictorias() {
        List<Equipo> equipos = dao.findAll();
        equipos.sort((equipo1, equipo2) -> Integer.compare(equipo2.getVictorias(), equipo1.getVictorias()));
        return equipos;
    }

    /**
     * Metodo que devuelve un listado de equipos ordenado por empates
     */
    @Override
    public List<Equipo> getEquiposEmpates() {
        List<Equipo> equipos = dao.findAll();
        equipos.sort((equipo1, equipo2) -> Integer.compare(equipo2.getEmpates(), equipo1.getEmpates()));
        return equipos;
    }


    /**
     * Metodo que devuelve un listado de equipos ordenado por derrotas
     */
    @Override
    public List<Equipo> getEquiposDerrotas() {
        List<Equipo> equipos = dao.findAll();
        equipos.sort((equipo1, equipo2) -> Integer.compare(equipo2.getDerrotas(), equipo1.getDerrotas()));
        return equipos;
    }

    @Override
    public List<Equipo> getEquiposGoleadores() {
        List<Equipo> equipos = dao.findAll();
        equipos.sort((equipo1, equipo2) -> Integer.compare(equipo2.getGoles(), equipo1.getGoles()));
        return equipos;
    }


    /**
     * Metodo que devuelve los equipos que tengan mas puntos que los que le pasamos desde la url
     */
    @Override
    public List<Equipo> getEquiposConPuntos(int puntos) {
        return dao.getJugadoresConPuntos(puntos);
    }


    /**
     * Metodo que devuelve los equipos que han ganado mas de la mitad de los partidos
     */
    @Override
    public List<Equipo> getEquiposGanadores() {

        List<Equipo> equipos = dao.findAll();
        List<Equipo> equiposGanadores = new ArrayList<>();

        for (Equipo equipo : equipos) {
            if (equipo.getVictorias()>(equipo.getDerrotas()+equipo.getEmpates())) {
                equiposGanadores.add(equipo);
            }
        }
        return equiposGanadores;
    }

    /**
     * Metodo que elige 2 equipos aleatoriamente y que genera 2 numero aleatorios como resultado, y si encuentra los equipos llama 
     * a un metodo que asigna los goles a sus jugadores aleatoriamente y actualiza los puntos
     */
    @Override
    public String jugarPartidoAleatorio() {
        List<Equipo> equipos = dao.findAll();

        Random random = new Random();
        int tamaño = equipos.size();

        Equipo equipo1 = new Equipo();
        Equipo equipo2 = new Equipo();
        if (tamaño >= 2) {
            int indice1 = random.nextInt(tamaño);
            int indice2;

            do {
                indice2 = random.nextInt(tamaño);
            } while (indice2 == indice1);

            equipo1 = equipos.get(indice1);
            equipo2 = equipos.get(indice2);
        }

        int golesEquipo1 = random.nextInt(5);
        int golesEquipo2 = random.nextInt(5);
        
        if(golesEquipo1>0){
            List<JugadorDTO> jugadoresEquipo1 = Arrays.asList(template.getForObject(url+"jugador/equipo/"+equipo1.getNombre(),JugadorDTO[].class));
            asignarGoles(jugadoresEquipo1, golesEquipo1);
        }

        if(golesEquipo2>0){
            List<JugadorDTO> jugadoresEquipo2 = Arrays.asList(template.getForObject(url+"jugador/equipo/"+equipo2.getNombre(),JugadorDTO[].class));
            asignarGoles(jugadoresEquipo2, golesEquipo2);
        }
    
        actualizarEquipos(equipo1, equipo2, golesEquipo1, golesEquipo2);

        return "Resultado: "+ equipo1.getNombre()+" "+golesEquipo1+"-"+golesEquipo2+" "+ equipo2.getNombre();   
    }

    /**
     * Metodo que genera 2 numero aleatorios como resultado, y si encuentra los equipos llama 
     * a un metodo que asigna los goles a sus jugadores aleatoriamente y actualiza los puntos
     */
    @Override
    public String jugarPartidoConcreto(String nombreEquipo1, String nombreEquipo2) {
        List<Equipo> equipos = dao.findAll();

        Random random = new Random();

        Equipo equipo1 = new Equipo();
        Equipo equipo2 = new Equipo();
        boolean equipo1Encontrado = false;
        boolean equipo2Encontrado = false;
        
        for (Equipo equipo : equipos) {
            if (equipo.getNombre().equalsIgnoreCase(nombreEquipo1)) {
                equipo1 = equipo;
                equipo1Encontrado = true;
            }
            if (equipo.getNombre().equalsIgnoreCase(nombreEquipo2)) {
                equipo2 = equipo;
                equipo2Encontrado = true;
            }
        }
        
        int golesEquipo1 = random.nextInt(5);
        int golesEquipo2 = random.nextInt(5);

        if (equipo1Encontrado && equipo2Encontrado) {
            
            if(golesEquipo1>0){
                List<JugadorDTO> jugadoresEquipo1 = Arrays.asList(template.getForObject(url+"jugador/equipo/"+equipo1.getNombre(),JugadorDTO[].class));
                asignarGoles(jugadoresEquipo1, golesEquipo1);
            }

            if(golesEquipo2>0){
                List<JugadorDTO> jugadoresEquipo2 = Arrays.asList(template.getForObject(url+"jugador/equipo/"+equipo2.getNombre(),JugadorDTO[].class));
                asignarGoles(jugadoresEquipo2, golesEquipo2);
            }
            
            actualizarEquipos(equipo1, equipo2, golesEquipo1, golesEquipo2);       
              
        } else {
            throw new NombreNoEncontradoException("No se han encontrado ambos equipos");
        }
        
        return "Resultado: "+ equipo1.getNombre()+" "+golesEquipo1+"-"+golesEquipo2+" "+ equipo2.getNombre();   
    }

    /**
     * Metodo que elimina un equipo y todos los jugadores de ese equipo
     */
    @Override
    public List<Equipo> deleteDescenderEquipo(String nombreEquipo) {
         List<Equipo> equipos = dao.findAll();
         for (Equipo equipo : equipos) {
            if (equipo.getNombre().equalsIgnoreCase(nombreEquipo)) {
                
                List<JugadorDTO> jugadoresEquipo= Arrays.asList(template.getForObject(url+"jugador/equipo/"+equipo.getNombre(),JugadorDTO[].class));
                if (jugadoresEquipo.size()>0) {
                    for (JugadorDTO jugadorEquipo : jugadoresEquipo) {
                        template.delete(url+"jugador/"+jugadorEquipo.getNombre());
                    }
                }
                dao.delete(equipo);
            }
        }

        return dao.findAll();
    }

    /**
     * Metodo que Guarda un equipo
     */
    @Override
    public List<Equipo> postAscenderEquipo(Equipo equipo) {
        dao.save(equipo);
        return dao.findAll();
    }

    /**
     * Metodo que recibe
     * @param equipo1
     * @param equipo2
     * @param golesEquipo1
     * @param golesEquipo2
     * 
     * y reparte los puntos para quien haya ganado, o si han empatado y los actualiza
     */
    public void actualizarEquipos(Equipo equipo1, Equipo equipo2, int golesEquipo1, int golesEquipo2){
        if (golesEquipo1>golesEquipo2) {
            equipo1.setVictorias(equipo1.getVictorias()+1);
            equipo1.setPuntos(equipo1.getPuntos()+3);
            equipo2.setDerrotas(equipo2.getDerrotas()+1);
        }else{
            if(golesEquipo2>golesEquipo1){
                equipo2.setVictorias(equipo2.getVictorias()+1);
                equipo2.setPuntos(equipo2.getPuntos()+3);
                equipo1.setDerrotas(equipo1.getDerrotas()+1);
            }else{
                equipo1.setEmpates(equipo1.getEmpates()+1);
                equipo1.setPuntos(equipo1.getPuntos()+1);
                equipo2.setEmpates(equipo2.getEmpates()+1);
                equipo2.setPuntos(equipo1.getPuntos()+1);
            }
        }
        
        dao.save(equipo1);
        dao.save(equipo2);
    }

    /**
     * Metodo que recibe una lista de jugadores y asigna aleatoriamente entre esos jugadores un gol hasta que no quedan goles por asignar
     */
    public void asignarGoles(List<JugadorDTO> jugadores, int goles){
        for (int i = 0; i < goles; i++) {
            JugadorDTO jugador = new JugadorDTO();
            jugador = jugadores.get(new Random().nextInt(jugadores.size()));
            jugador.setGoles(jugador.getGoles()+1);
            template.put(url+"jugador", jugador);
        }
    }
    
    
}
