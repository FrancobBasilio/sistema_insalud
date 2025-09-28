# 📋 Sistema de Gestión de Atenciones Médicas

📖 **Descripción**  
Sistema backend para la gestión de atenciones médicas desarrollado con **Spring Boot**, implementando autenticación **JWT** y roles de usuario:  
- **PACIENTE**  
- **MÉDICO**  
- **ADMIN**  

---

## 🛠️ Tecnologías Utilizadas

- Java 17  
- Spring Boot 3.x  
- Spring Security + JWT  
- JPA / Hibernate  
- PostgreSQL  
- Maven  

---

## 📦 Estructura del Proyecto

src/
└── main/
└── java/
└── com/insalud/
├── config/ # Configuración (Seguridad y JWT)
├── controller/ # Controladores REST
├── entity/ # Entidades JPA
├── repository/ # Repositorios (DAO)
├── service/ # Servicios
└── WebInsaludApplication.java
  └── resources/
       ├── application.properties
       └── import.sql           # Datos de prueba

## 🚀 Instrucciones de Ejecución

### 🔹 Prerrequisitos
- JDK 17  
- PostgreSQL instalado y corriendo  
- IDE (Spring Tools Suite, IntelliJ, Eclipse)

### 🔹 Pasos
1. Clonar el repositorio:
   ```bash
   git clone [URL_DEL_REPOSITORIO]
Configurar la base de datos en application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/NOMBRE_TU_BASE_DATOS
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña

Importar como proyecto Maven en tu IDE.

Ejecutar como Spring Boot App.

✅ Los datos de prueba se cargan automáticamente desde import.sql.

🔐 Autenticación y Usuarios de Prueba
Rol	Usuario	Contraseña
ADMIN	admin1	12345
MÉDICO	medico1	12345
PACIENTE	paciente1	12345

Ejemplo de Login (HTTP)
bash
Copiar código
http POST http://localhost:8080/api/auth/login \
Content-Type:application/json \
usuario=paciente1 \
contrasena=12345

📡 Endpoints Principales

Atenciones

GET /api/atenciones → (ADMIN / MÉDICO)

GET /api/atenciones/mias → (PACIENTE)

POST /api/atenciones → (ADMIN / MÉDICO)

PUT /api/atenciones/{id} → (ADMIN / MÉDICO)

DELETE /api/atenciones/{id} → (ADMIN)

GET /api/atenciones/buscar-por-fecha?fecha=YYYY-MM-DD → (ADMIN / MÉDICO)

🗃️ Modelo de Datos

El sistema incluye las siguientes entidades:

Persona

Usuario

Paciente

Empleado

Especialidad

MedicoEspecialidad (generada automáticamente por Hibernate)

Atención
