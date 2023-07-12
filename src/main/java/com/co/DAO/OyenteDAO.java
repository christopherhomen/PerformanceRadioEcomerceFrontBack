
package com.co.DAO;

//aqui vamos a hacer el manejo del crud 

import com.co.pojo.Oyente;
import org.springframework.data.repository.CrudRepository;

//crud repository: para hacer un crud: crear, leer, update, delete 
//usamos una interface: es el medio de comunicación que va a ver entre mi modelo y mi vista
//ejm: vista de listar clientes - modelo: guardar, listar, update cliente 
//vista - interfaz - modelo
//tome todos los atributos de crudrepository<uso de genericos> es decir <nombreDeClase, tipo de datos de clave primaria>
public interface OyenteDAO extends CrudRepository<Oyente, String> { // Esta interfaz extiende CrudRepository para interactuar con la tabla de oyentes en la base de datos
    // El primer parámetro genérico "Oyente" indica el tipo de entidad con el que se va a interactuar
    // El segundo parámetro genérico "String" indica el tipo de dato del identificador único de la entidad
    /*Nota:Al utilizar esta interfaz en un controlador, se pueden realizar operaciones como oyenteDao.save(oyente) para guardar un oyente en la base de datos, oyenteDao.findById(username) para buscar un oyente por su nombre de usuario, y oyenteDao.deleteById(username) para eliminar un oyente por su nombre de usuario.
    */
}
