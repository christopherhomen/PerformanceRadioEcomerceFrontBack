
package com.co.pojo;
import lombok.Data;
import java.io.Serializable;
import javax.persistence.*;//connection a la base de d

//modelo:
//serialiacion me combierte el objeto en un flujo de datos, el obeto edta dentro de la clase con sus atributos con serialize puedo usar sus atributos al llamar a este objeto, para ller, guardar y usar sus datos 

@Data //Notacion sobre cualquier clase: toma los atributos de la clase y crea los get set, to string y demás 
@Entity //va a trabajar sobr euna entidad :  para decir que es una entidad que va a hacer el mape objeto trelacional a una entidad, es decir a una tabla 
@Table(name="oyente") //para indicar la tabla relacionada
//Para conectarnos a la base de datos usamos SErializable
public class Oyente implements Serializable{
    //usamos wrapper, poara tener acceso a los metodos asociados a ellos, ejemplo int: Integer
    @Id
    private String username;
    private String contrasena;
    private String nombre_oyente;
    private String apellido_oyente;
    private String email_oyente; 
    private String pais_oyente; 
}
