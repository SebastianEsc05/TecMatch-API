package com.example.PotroNet;

import com.example.PotroNet.config.JpaUtil;
import com.example.PotroNet.controller.*;
import com.example.PotroNet.domain.enums.Tipo;
import com.example.PotroNet.dto.*;
import com.example.PotroNet.service.impl.UsuarioService;
import com.example.PotroNet.service.interfaces.IUsuarioService;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Set;

public class MainPruebasConsola {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("TecMatchPU");
    // IDs Fijos de Usuarios
    static Long idAna = 1L;
    static Long idLuis = 2L;
    static Long idCarlos = 3L;

    // IDs Dinámicos (para limpieza)
    static Long idHobbieProg = null;
    static Long idInteresIA = null;
    static Long idLikeAnaLuis = null;
    static Long idLikeLuisAna = null;
    static Long idLikeAnaCarlos = null;

    // IDs de Match/Chat generados
    static Long idMatchMutuo = null;
    static Long idChatPrivadoMutuo = null;
    static Long idChatGrupo = 2L;

    public static void main(String [] args) {
        try {

            System.out.println("====== FASE 1: INSTANCIANDO CONTROLADORES ======");
            IUsuarioService usuarioServicio = new UsuarioService(emf);
            MatchController matchController = new MatchController(emf);
            ChatController chatController = new ChatController(emf);
            HobbieController hobbieController = new HobbieController(emf);
            InteresController interesController = new InteresController(emf);
            LikeController likeController = new LikeController(emf);

            System.out.println("\n====== FASE 2: CREACIÓN DE USUARIOS BASE (Nuevo Constructor) ======");

//            UsuarioDTO dto1 = new UsuarioDTO(
//                    null, "Ana (ChatTest)", "ISC", "ana.chat@example.com",
//                    "Estudiante de ISC, buscando amigos.",
//                    "Femenino", "pass123", LocalDate.of(2000, 5, 15),"asd",
//                    Set.of("IA"), Set.of("Leer")
//            );
//
//            UsuarioDTO dto2 = new UsuarioDTO(
//                    null, "Luis (ChatTest)", "LNI", "luis.chat@example.com",
//                    "Estudiante de LNI, buscando negocios.",
//                    "Masculino", "pass456", LocalDate.of(1999, 10, 20),"asd",
//                    Set.of("Negocios"), Set.of("Fútbol")
//            );

//            UsuarioDTO dto3 = new UsuarioDTO(
//                    null, "Carlos (ChatTest)", "ARQ", "carlos.chat@example.com",
//                    "Estudiante de ARQ, buscando diseño.",
//                    "Masculino", "pass789", LocalDate.of(2001, 3, 10),"asd",
//                    Set.of("Diseño"), Set.of("Dibujar")
//            );

//            usuarioServicio.crearUsuario(dto1);
//            usuarioServicio.crearUsuario(dto2);
//            usuarioServicio.crearUsuario(dto3);

            System.out.println("\n====== FASE 3: PRUEBAS DE CATÁLOGO (HOBBIE/INTERÉS) ======");

            System.out.println("\n--- HOBBIES: CRUD COMPLETO ---");
            hobbieController.crearHobbie(new HobbieDTO(null, "Programación"));
            hobbieController.crearHobbie(new HobbieDTO(null, "Cocinar"));
            idHobbieProg = 1L;
            hobbieController.crearHobbie(new HobbieDTO(null, "Programación"));
            hobbieController.listarHobbies(10);
            hobbieController.buscarPorId(idHobbieProg);
            hobbieController.actualizarHobbie(idHobbieProg, new HobbieDTO(idHobbieProg, "Programación Web"));
            hobbieController.eliminarHobbie(2L);
            hobbieController.listarHobbies(10);

            System.out.println("\n--- INTERESES: CRUD COMPLETO ---");
            interesController.crearInteres(new InteresDTO(null, "Inteligencia Artificial"));
            interesController.crearInteres(new InteresDTO(null, "Negocios"));
            idInteresIA = 1L;
            interesController.crearInteres(new InteresDTO(null, "Inteligencia Artificial"));
            interesController.actualizarInteres(idInteresIA, new InteresDTO(idInteresIA, "Machine Learning"));
            interesController.eliminarInteres(2L);
            interesController.listarIntereses(10);

            System.out.println("\n====== FASE 4: PRUEBAS DE LIKES Y REGLA DE MATCH ======");

            System.out.println("\n--- A. LIKE SIMPLE: Ana (ID 1) da like a Carlos (ID 3) ---");
            LikeDTO likeAnaCarlos = new LikeDTO();
            likeAnaCarlos.setLikerId(idAna);
            likeAnaCarlos.setLikedId(idCarlos);
            likeController.crearLike(likeAnaCarlos);
            idLikeAnaCarlos = 1L;

            likeController.getLikesDados(idAna);
            likeController.getLikesRecibidos(idCarlos);
            likeController.getLikesRecibidos(idLuis);

            System.out.println("\n--- B. LIKE MUTUO (PASO 1/2): Luis (ID 2) da like a Ana (ID 1) ---");
            LikeDTO likeLuisAna = new LikeDTO();
            likeLuisAna.setLikerId(idLuis);
            likeLuisAna.setLikedId(idAna);
            likeController.crearLike(likeLuisAna);
            idLikeLuisAna = 2L;

            System.out.println("\n--- C. LIKE MUTUO (PASO 2/2): Ana (ID 1) da like a Luis (ID 2) ---");
            LikeDTO likeAnaLuis = new LikeDTO();
            likeAnaLuis.setLikerId(idAna);
            likeAnaLuis.setLikedId(idLuis);
            likeController.crearLike(likeAnaLuis);
            idLikeAnaLuis = 3L;

            idMatchMutuo = 1L;
            idChatPrivadoMutuo = 1L;
            matchController.listarTodosLosMatches();
            chatController.listarChats(10);

            System.out.println("\n--- D. FALLO: Intentar like duplicado (Ana -> Luis) ---");
            likeController.crearLike(likeAnaLuis);

            System.out.println("\n====== FASE 5: PRUEBAS DE CHAT (USANDO CHAT DE MATCH) ======");
            System.out.println("--- Probando fallo: Crear Chat PRIVADO manualmente ---");
            ChatDTO dtoChatPrivadoManual = new ChatDTO(null, null, Tipo.PRIVADO, Set.of(idLuis, idCarlos));
            chatController.crearChat(dtoChatPrivadoManual);

            System.out.println("\n--- Probando éxito: Crear Chat GRUPO ---");
            ChatDTO dtoChatGrupoCrear = new ChatDTO(null, null, Tipo.PUBLICO, Set.of(idAna, idCarlos));
            chatController.crearChat(dtoChatGrupoCrear);
            idChatGrupo = 2L;

            chatController.listarChats(10);

            System.out.println("\n--- Probando búsqueda por Usuario (Consulta Avanzada) ---");
            chatController.buscarChatsPorUsuarioId(idAna);

            System.out.println("\n--- Probando éxito: Agregar usuario (Luis) a Grupo ---");
            chatController.agregarUsuarioAChat(idChatGrupo, idLuis);

            chatController.buscarPorId(idChatGrupo);

            System.out.println("\n--- Probando éxito: Eliminar usuario (Ana) de Grupo ---");
            chatController.eliminarUsuarioDeChat(idChatGrupo, idAna);

            chatController.buscarPorId(idChatGrupo);

            chatController.eliminarChat(idChatGrupo);

            chatController.listarChats(10);


        } catch (Exception e) {
            System.err.println("\nERROR EN LA PRUEBA");
            e.printStackTrace();
        } finally {
            System.out.println("\n====== FASE 6: LIMPIEZA TOTAL ======");
            try {
                ChatController chatController = new ChatController(emf);
                MatchController matchController = new MatchController(emf);
                UsuarioController usuarioController = new UsuarioController(emf);
                HobbieController hobbieController = new HobbieController(emf);
                InteresController interesController = new InteresController(emf);
                LikeController likeController = new LikeController(emf);

                System.out.println("--- 1. Eliminando Likes (para liberar al Match) ---");
                likeController.eliminarLike(idLikeAnaCarlos);
                likeController.eliminarLike(idLikeLuisAna);
                likeController.eliminarLike(idLikeAnaLuis);

                System.out.println("--- 2. Eliminando Chats y Matches ---");
                chatController.eliminarChat(idChatPrivadoMutuo);
                matchController.eliminarMatch(idMatchMutuo);

                System.out.println("--- 3. Eliminando Usuarios ---");
                usuarioController.eliminarUsuario(idAna);
                usuarioController.eliminarUsuario(idLuis);
                usuarioController.eliminarUsuario(idCarlos);

                System.out.println("--- 4. Eliminando Catálogos ---");
                hobbieController.eliminarHobbie(idHobbieProg);
                interesController.eliminarInteres(idInteresIA);

                System.out.println("--- Verificación final de limpieza ---");
                usuarioController.listarUsuarios(10);

            } catch (Exception e) {
                System.err.println("¡ERROR DURANTE LA LIMPIEZA!");
                e.printStackTrace();
            }

            System.out.println("\n--- Cerrando Conexión ---");
            JpaUtil.close();
        }

        System.out.println("\n====== PRUEBAS FINALIZADAS ======");
    }
}