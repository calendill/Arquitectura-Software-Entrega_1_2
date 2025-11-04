Patrones de Diseño en el Proyecto de Veterinaria
Este documento describe tres patrones de diseño utilizados en el proyecto de gestión de clientes y mascotas en una veterinaria: Builder Pattern, Factory Method Pattern y Repository Pattern.
________________________________________
Builder Pattern
El Builder Pattern permite construir objetos complejos de manera gradual, sin necesidad de crear múltiples constructores con muchos parámetros. Este patrón ofrece varias ventajas: evita constructores largos y difíciles de leer, permite asignar únicamente los campos necesarios y facilita la creación de objetos inmutables o semi-inmutables. En el proyecto se utiliza Lombok con la anotación @Builder.
Ejemplo en ClienteDto:


ClienteDto dto = ClienteDto.builder()
        .cedula(cliente.getCedula())
        .nombre(cliente.getNombre())
        .apellido1(cliente.getApellido1())
        .apellido2(cliente.getApellido2())
        .telefono(cliente.getTelefono())
        .correo(cliente.getCorreo())
        .direccion(cliente.getDireccion())
        .build();

        
Ventajas prácticas: - Permite crear DTOs y entidades de manera clara en convertirADto y convertirAEntidad. - Facilita agregar nuevos campos sin romper constructores existentes.
________________________________________
Factory Method Pattern
El Factory Method Pattern permite crear objetos sin exponer la lógica de construcción al cliente. En el proyecto, MascotaFactory decide si crear un objeto Perro o Gato según los datos del DTO.
Ejemplo:
public static Mascota crearDesdeDto(MascotaDto dto, Cliente cliente) {
    if ("PERRO".equalsIgnoreCase(dto.getTipoAnimal())) {
        return new Perro(...);
    } else if ("GATO".equalsIgnoreCase(dto.getTipoAnimal())) {
        return new Gato(...);
    } else {
        throw new IllegalArgumentException("Tipo de mascota desconocido: " + dto.getTipoAnimal());
    }
}
Ventajas: - Encapsula la creación de objetos complejos. - Facilita la extensión a nuevos tipos de mascotas. - Centraliza cambios en la lógica de construcción.
Ejemplo de uso:
Mascota mascota = MascotaFactory.crearDesdeDto(dto, cliente);
mascotaDao.guardar(mascota);
________________________________________
Repository Pattern
El Repository Pattern actúa como capa de abstracción entre la lógica de negocio y la base de datos, proporcionando métodos CRUD y ocultando los detalles de persistencia.
Ejemplo en ClienteDaoJpaImpl:
•	GuardarCliente → Inserta un cliente.
•	obtenerTodos → Lista todos los clientes.
•	obtenerPorId → Busca por cédula.
•	actualizar → Modifica cliente.
•	eliminarCliente → Borra cliente.
Ventajas: - Separación de responsabilidades. - Facilita pruebas unitarias. - Permite cambiar la implementación de la persistencia sin afectar los servicios.
Uso en el servicio:
List<ClienteDto> clientes = clienteServicio.obtenerTodos();
