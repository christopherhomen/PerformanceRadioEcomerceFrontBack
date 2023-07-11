
package com.co.DAO;

//aqui vamos a hacer el manejo del crud 

import com.co.pojo.Oyente;
import org.springframework.data.repository.CrudRepository;

//crud repository: para hacer un crud: crear, leer, update, delete 
//usamos una interface: es el medio de comunicación que va a ver entre mi modelo y mi vista
//ejm: vista de listar clientes - modelo: guardar, listar, update cliente 
//vista - interfaz - modelo
//tome todos los atributos de crudrepository<uso de genericos> es decir <nombreDeClase, tipo de datos de clave primaria>
public interface OyenteDAO extends CrudRepository<Oyente, String> {
    
}
