Reconocimiento de Patrones
=======================

Contiene la implementación de 5 algoritmos de reconocimiento de patrones y redes neuronales. A continuación se listan los algoritmos implementados y el problema que resuelve cada uno de ellos.


###Clasificador de distancia mínima.
------
Categoriza pelotas de béisbol y de fútbol dados sus respectivos valores de peso(g) y diámetro (cm).

####Patrones idealesLos valores son aproximados a los reglamentados por la Major League Baseball (MLB) y por la Fédération Internationale de Football Association (FIFA). Se escogieron estos patrones (peso y diámetro) por ser los usados por las asociaciones anteriormente señaladas para definir medidas oficiales de las pelotas para estos deportes.

######Béisbol

| Peso (g)  | Diámetro (cm)|
| ----------|------------- |
| 142  		| 		7  	   |

######Fútbol
| Peso (g)  | Diámetro (cm)|
| ----------|------------- |
| 	420     |  	21.5       |

####NormasLas normas que fueron aplicadas en el algoritmo son:

1. Distancia Euclidiana
2. Distancia Manhattan

###Perceptrón
------
El objetivo del programa es clasificar las flores de Iris tipo Setosa de entre otras flores de Iris (Versicolor y Virginica) dados sus respectivos valores respecto a la longitud y ancho del sépalo y pétalo con un perceptrón simple.

####Set de aprendizaje
En la siguiente tabla se muestran las características usadas como entradas para describir a las flores, el set total de aprendizaje se encuentra dentro de la carpeta del proyecto **dataset/iris_aprendizaje.csv**

| Peso (g)  | Diámetro (cm)| Peso (g)  | Diámetro (cm)|
| ----------|------------- |-----------|------------- |