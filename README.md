# Gestión de Notas Estudiantiles — JavaFX

**Autor:** Jordy Cajas  
**Institución:** Escuela Politécnica Nacional — ESFOT (Escuela de Formación de Tecnólogos)  
**Materia:** Programación Orientada a Objetos  

---

## Descripción

Aplicación de escritorio desarrollada en JavaFX que combina una encuesta estudiantil
y una calculadora de notas con historial de operaciones.

---

## Componentes utilizados

- **Label** — etiquetas de campos y mensajes de estado
- **TextField** — ingreso de nombre, edad y notas
- **ComboBox** — selección de carrera, semestre y operación matemática
- **ListView** — materias favoritas (selección múltiple) e historial de cálculos
- **Button** — registrar, calcular, limpiar y gestionar historial

---

## Funcionalidades

- Encuesta que registra nombre, edad, carrera, semestre y materias favoritas
- Calculadora con 5 operaciones: Promedio, Suma, Mayor nota, Menor nota, Diferencia
- Conversión de texto a número con Double.parseDouble() e Integer.parseInt()
- Validación de campos vacíos y manejo de errores con try-catch
- Botones limpiar que resetean todos los campos
- Historial de operaciones con opción de borrado

---

## Tecnologías

- Java con JavaFX
- IntelliJ IDEA
- Maven

---

## Cómo ejecutar

1. Abrir el proyecto en IntelliJ IDEA
2. Ejecutar la clase `GestionNotas` con Shift + F10
