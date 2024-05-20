package com.santiagoprogrammer.Tutorial;

import com.santiagoprogrammer.Tutorial.models.Libro;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class Rutas {

    private final Logger logger = LoggerFactory.getLogger(Rutas.class);

    @GetMapping("/hola")
    String miPrimeraRuta () {
        return "Hola mundo desde Spring Controller :)";
    }

    @GetMapping("/libro/{id}/editorial/{editorial}")
    String leerLibro(@PathVariable int id, @PathVariable String editorial) {
        return "Leyendo el libro con id: " + id + ", editorial: " + editorial;
    }

    @GetMapping("/libro2/{id}")
    String leerLibro2(@PathVariable int id, @RequestParam String gato, @RequestParam String editorial) {
        return "Leyendo el libro con id: " + id + ", params: " + gato + ", editorial: " + editorial;
    }

    @PostMapping("/libro")
    String guardarLibro(@RequestBody Map<String, Object> libro) {

        libro.keySet().forEach(llave -> {
            logger.debug("llave {} valor {}", llave, libro.get(llave));
        });

        return "Libro guardado";
    }

    @PostMapping("/crear_libro")
    String guardarLibro(@RequestBody Libro libro) {
        logger.debug("nombre {} editorial {}", libro.nombre, libro.editorial);

        return "Libro guardado con éxito";
    }

    @GetMapping("/saludar")
   //@ResponseStatus(value = HttpStatus.OK)
    @ResponseStatus(value = HttpStatus.MOVED_PERMANENTLY, reason = "Fue movida a la version 2.0 de la API")
    String miSegundaRutaconStatus(){
        return "Aprendiendo Spring Boot Status:)";
    }

    @GetMapping("/animales/{lugar}")
    public ResponseEntity<String> getAnimales(@PathVariable String lugar) {
        if (lugar.equals("granja")) {
            return ResponseEntity.status(HttpStatus.OK).body("Caballo, vaca, oveja, gallina");
        } else if (lugar.equals("selva")){
            return ResponseEntity.status(HttpStatus.OK).body("Mono, gorila, puma");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lugar no valido");
        }
    }

    @GetMapping("/calcular/{numero}")
    public int getCalculo(@PathVariable int numero) {
        throw new NullPointerException("Esta excepción ocurrio porque habla de información no relevante para el cliente.");
    }

    @GetMapping("/userData")
    public ResponseEntity<String> getUserData() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("Content-Type","application/json")
                .body("{\"name\": \"mary\"}");
    }

    @GetMapping("/userData/v2")
    public Map<String, Map<String, Object>> getUserDataV2() {
        return Map.of("user", Map.of("name", "mary", "age", 25));
    }
}
