package com.inventarios.controlador;

import com.inventarios.modelo.Producto;
import com.inventarios.servicio.ProductoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//http://localhost:8080/inventario-app
@RequestMapping("/inventario-app")
@CrossOrigin(value = "http://localhost:4200")
public class ProductoControlador {

    private static final Logger logger= LoggerFactory.getLogger(ProductoControlador.class);

    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping("/productos")
    public ResponseEntity<List<Producto>> obtenerProductos(){
        return ResponseEntity.ok(this.productoServicio.listarProductos());
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> obtieneProductoPorId(@PathVariable int id){
        return new ResponseEntity<Producto>(this.productoServicio.buscarProductoPorID(id), HttpStatus.OK);
    }

    @PostMapping("/productos")
    public ResponseEntity<Producto> guardarProducto(@RequestBody Producto producto){
        return new ResponseEntity<Producto>(this.productoServicio.guardarProducto(producto), HttpStatus.CREATED);
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) {
        try {
            this.productoServicio.eliminarProductoPorId(id);
            return new ResponseEntity<String>("Producto eliminado con exito", HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<String>("Algo salio mal", HttpStatus.BAD_REQUEST);
        }
    }

}
