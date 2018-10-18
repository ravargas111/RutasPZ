# RutasPZ
ESPESIFICACION:
•	Se debe colocar en la pantalla el mapa de Pérez Zeledón.  El grafo está delimitado por lo que se encierra en las líneas rojas, las vías de las líneas rojas también se incluyen en el grafo.
•	El programa consiste en simular que un pequeño vehículo se desplaza de un punto A a un punto B, la mejor ruta del punto A al B es determinada por un algoritmo y presentar un posible costo del trasporte.
Para esto se requiere:
•	Debe existir la posibilidad de seleccionar el tipo de algoritmo a aplicar.
•	El algoritmo debe de calcular la mejor ruta en cada nodo visitado, de acuerdo a esto puede seguir la ruta original o bien puede tomar otra ruta más eficiente.
•	Luego de seleccionar los dos nodos se debe resaltar en pantalla la ruta, y al presionar alguna tecla o el click del mouse el carro debe moverse por el mapa hasta el nodo final.  Se deben respetar los sentidos de las calles únicamente en el caso de los grafos dirigidos.
•	Las aristas del grafo tendrán un peso proporcional asignado de acuerdo a la longitud de las vías en la realidad.
•	Los pesos pueden cambiar en tiempo ejecución, ocasionando que la ruta cambie cuando el vehículo está en movimiento. Las razones pueden ser:
•	Calle cerrada por mantenimiento del cosevi. La calle queda cerrada
•	Accidentes, si las calles son de un solo sentido queda cerrada, si es de doble sentido, debe definir en qué carril fue el accidente y este quedara cerrado.
•	Trafico, el trafico lo definen por un producto, si la ruta pesa 5, se puede definir trafico normal 1, trafico moderado 2, tráfico lento 3. La ruta aumenta su peso con el producto peso 5x3 = 15(nuevo peso).
•	Se debe de mostrar las dos rutas.
•	Ruta definida originalmente
•	Ruta que se trazó cuando el carro estaba en movimiento.
•	Al finalizar el recorrido se presentará el costo asociado al trasporte y se podrá escoger una nueva ruta.
•	El cálculo del costo incluirá las variables tiempo y peso (longitud de las aristas), dándole un valor al recorrido con el peso y otro valor al tiempo que está el vehículo detenido, al final se suma las cantidades para obtener un total.

Los tipos de grafos
•	Grafos dirigidos:
Dijkstra: Al aplicar este algoritmo se deben seleccionar dos vértices y el algoritmo generará todos los caminos más cortos entre el primer vértice a todos los demás, pero únicamente se utiliza en pantalla el camino más corto entre los dos vértices seleccionados.
Floyd: Este algoritmo construye una matriz de costos mínimos y de caminos.  Utilice únicamente el camino más corto para llevar el vehículo del nodo A al nodo B.
