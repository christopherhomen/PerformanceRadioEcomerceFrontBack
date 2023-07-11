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
    
    @Autowired //para inyectar la clase para que se pueda acceder mediante cualquier lugar: Yo voy a usar mi propia clase, no la que tienes en otros lados
    //instancia de clase:
    private OyenteDAO oyenteDao;
    
       
    //Muestre desde la posicion o raiz absoluta de la página :
    @GetMapping("/")
    public String inicio(){
        return "index";
    }
    
     //Cuando pida mostrar inicio de sesion va a hacer: 
    //para abrir la pagina 
    @GetMapping("/login")
      public String iniciosesion(){
        return "iniciosesion";
    }
      
     @GetMapping("/register")
      public String registro(){
        return "register";
    }
      
      //cuiando entre al login amdin
    @GetMapping("/loginadmin")
    public String loginadmin(Model model){
           //creo una variable u objeto oyentes
          //findall: listar
          var oyentes = oyenteDao.findAll();
          //debo compartir a la vista:
            //va a compartir todos sus atributos: el oyente de var, y el objeto oyentes
          model.addAttribute("oyentes", oyentes);
          //esto lo comparto con el loginadmin
        return "loginadmin";
    }
     
            //Mapeo para mostrar la pagina de formulario de guardar 
      @GetMapping("/crear")
      //llamo desde login del admin 
      public String crear(){
          return "crear";
      }
      
   //para guardar y actualizar los datos del usuario
      @PostMapping("/guardar")
      //ModelAttribute: para recuperar el modelo de Cleinte
      public String guardar(@ModelAttribute Oyente oyentes){
      //cuando el guarde guarde en la base de datos y re direccione para decir que el usuario se duargo correctanemten
      //ClienteDAO: podemos utuilizar los metodos del crud repository, ahora vamos a usar guardar 
      oyenteDao.save(oyentes);
        return "redirect:loginadmin";
      }
      
      
        @GetMapping("/editar/{username}")
        //recibo una variable id cliente
        public String editar(@PathVariable("username") String username, Model model){
            var oyentes= oyenteDao.findById(username);
            model.addAttribute("oyentes",oyentes);
            return "modificar1";
        }
        
        @GetMapping("/eliminar/{username}")
        //recibo una variable id username
        public String eliminar(@PathVariable("username") String username){
            oyenteDao.deleteById(username);
            return "redirect:/loginadmin";
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
