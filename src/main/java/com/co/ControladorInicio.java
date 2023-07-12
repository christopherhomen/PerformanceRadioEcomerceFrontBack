/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.co;

import com.co.DAO.OyenteDAO;
import com.co.pojo.Oyente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

//controlador de pagina web
@Controller
public class ControladorInicio {
    
    @Autowired // Esta anotación indica que Spring debe inyectar una instancia de la clase OyenteDAO en esta variable
    private OyenteDAO oyenteDao; // Instancia de la clase OyenteDAO para interactuar con la base de datos
    
    @GetMapping("/") // Esta anotación indica que este método maneja solicitudes GET a la ruta "/"
    public String inicio(){ // Este método devuelve el nombre de la vista que se debe mostrar
        return "index"; // Se devuelve el nombre de la vista "index"
    }
    
    @GetMapping("/login") // Esta anotación indica que este método maneja solicitudes GET a la ruta "/login"
    public String iniciosesion(){ // Este método devuelve el nombre de la vista que se debe mostrar
        return "iniciosesion"; // Se devuelve el nombre de la vista "iniciosesion"
    }
      
    @GetMapping("/register") // Esta anotación indica que este método maneja solicitudes GET a la ruta "/register"
    public String registro(){ // Este método devuelve el nombre de la vista que se debe mostrar
        return "register"; // Se devuelve el nombre de la vista "register"
    }
      
    @GetMapping("/loginadmin") // Esta anotación indica que este método maneja solicitudes GET a la ruta "/loginadmin"
    public String loginadmin(Model model){ // Este método toma como parámetro un objeto "model" para pasar datos a la vista
        var oyentes = oyenteDao.findAll(); // Se buscan todos los oyentes en la base de datos utilizando el objeto oyenteDao
        model.addAttribute("oyentes", oyentes); // Se agrega una lista con todos los oyentes encontrados al objeto "model" para pasarlo a la vista
        return "loginadmin"; // Se devuelve el nombre de la vista "loginadmin"
    }
     
    @GetMapping("/crear") // Esta anotación indica que este método maneja solicitudes GET a la ruta "/crear"
    public String crear(){ // Este método devuelve el nombre de la vista que se debe mostrar
        return "crear"; // Se devuelve el nombre de la vista "crear"
    }
      
    @PostMapping("/guardar") // Esta anotación indica que este método maneja solicitudes POST a la ruta "/guardar"
    public String guardar(@ModelAttribute Oyente oyentes){ // Este método toma como parámetro un objeto Oyente que se construye a partir de los datos enviados en la solicitud
        oyenteDao.save(oyentes); // Se guarda el objeto Oyente en la base de datos utilizando el objeto oyenteDao
        return "redirect:loginadmin"; // Se redirige al usuario a la ruta "/loginadmin"
    }
      
      
    @GetMapping("/editar/{username}") // Esta anotación indica que este método maneja solicitudes GET a la ruta "/editar/{username}"
    public String editar(@PathVariable("username") String username, Model model){ // Este método toma como parámetros una variable "username" extraída de la ruta y un objeto "model" para pasar datos a la vista
        var oyentes= oyenteDao.findById(username); // Se busca un oyente por su nombre de usuario utilizando el objeto oyenteDao
        model.addAttribute("oyentes",oyentes); // Se agrega el oyente encontrado al objeto "model" para pasarlo a la vista
        return "modificar1"; // Se devuelve el nombre de la vista "modificar1"
    }
        
    @GetMapping("/eliminar/{username}") // Esta anotación indica que este método maneja solicitudes GET a la ruta "/eliminar/{username}"
    public String eliminar(@PathVariable("username") String username){ // Este método toma como parámetro una variable "username" extraída de la ruta
        oyenteDao.deleteById(username); // Se elimina un oyente por su nombre de usuario utilizando el objeto oyenteDao
        return "redirect:/loginadmin"; // Se redirige al usuario a la ruta "/loginadmin"
    }
}


/*NOTA
El uso de `@GetMapping` en lugar de `@PutMapping` en el método `editar` se debe a la forma en que se realiza la edición de usuarios en este caso.

En el formulario de edición de usuario, se utiliza un formulario HTML con el método `POST` y el atributo `th:action="@{/guardar}"`. Esto significa que cuando se envía el formulario, los datos se envían al método `guardar` del controlador.

El método `editar` se encarga de mostrar la página de edición del usuario, y no realiza directamente ninguna actualización en la base de datos. Al utilizar `@GetMapping`, se mapea la URL `/editar/{username}` a este método, lo que significa que cuando se accede a esa URL, se mostrará la página de edición del usuario correspondiente al `username` especificado.

Cuando se envía el formulario de edición, los datos se envían al método `guardar` utilizando `@PostMapping("/guardar")`. Aquí es donde realmente se realiza la actualización en la base de datos.

Entonces, en resumen:
- `@GetMapping("/editar/{username}")` se utiliza para mostrar la página de edición del usuario.
- `@PostMapping("/guardar")` se utiliza para guardar los cambios realizados en la página de edición del usuario.

Ambos métodos cumplen con funciones diferentes en el proceso de edición de usuarios y, por lo tanto, utilizan anotaciones diferentes.
    }*/
