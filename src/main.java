//Acuerdo: Un programa donde se puedan manejar todas las colmenas y sectores de una finca, permitiendo registrar eventos y ver una tabla con el contenido de todo
//Estrategia: Implementar vndntanas y controllers para recibir bien los datos

package src;

import java.io.IOException;
import src.Finca.Finca;
import src.GUI.*;

public class main {
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        Finca finca;
        JsonHandler jsonHandler = new JsonHandler();
        finca = jsonHandler.startFinca();
        System.out.println(finca);
        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(finca);

        ventanaPrincipal.setVisible(true);
    }
}


//TODO: Podría hacerse un enum para los tipos de evento
//*Listo */

//TODO: Crear ya sea un controller para todas las ventanas o bien un controller por ventana (MVC), lo importante que hayan clases que sean mediadoras entre el UI y la lógica
//*Listo para VentanaPrincipal*/
//*Listo para VentanaRegistroColmenas*/
//*Listo para VentanaRegistroEventos*/
//*Listo para VentanaReporte*/

//TODO: La búsqueda de colmena hay que optimizarla, pensa en usar hashtablas
//*Listo para registrar colmenas*/
//*Listo para registrar eventos */

//TODO: Arreglar modales de ventanas de registro
//*Listo */

//TODO: La fecha en registrar evento sacarla con un datetimepicker
//*Listo */

//TODO: Cuando se usan listas para seleccionar, se puede hacer bind al objeto como tal para no tener que hacer search, si no que el evento de action ya me trae un objeto asociado
//*Listo para registrar colmenas*/
//*Listo para registrar eventos */

//TODO: Registrar evento necesita el tipo de evento, fecha, descripción, necesita una cantidad en el caso de que sea el evento producción de miel
//*Listo */

//TODO: El reporte debería salir la fecha y que estén en orden descendente por fecha
//*Listo */

//TODO: Permanencia de objetos
//*Listo */