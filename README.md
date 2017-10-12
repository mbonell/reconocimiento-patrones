# Reconocimiento de Patrones

Contiene la implementación de 5 algoritmos de reconocimiento de patrones y redes neuronales. A continuación se listan los algoritmos implementados y el problema que resuelve cada uno de ellos.


### Clasificador de distancia mínima.
------
Categoriza pelotas de béisbol y de fútbol dados sus respectivos valores de peso(g) y diámetro (cm).

#### Patrones ideales
Los valores son aproximados a los reglamentados por la Major League Baseball (MLB) y por la Fédération Internationale de Football Association (FIFA). Se escogieron estos patrones (peso y diámetro) por ser los usados por las asociaciones anteriormente señaladas para definir medidas oficiales de las pelotas para estos deportes.

###### Béisbol

| Peso (g)  | Diámetro (cm)|
| ----------|------------- |
| 142  		| 		7  	   |

###### Fútbol

| Peso (g)  | Diámetro (cm)|
| ----------|------------- |
| 	420     |  	21.5       |

#### Normas
Las normas que fueron aplicadas en el algoritmo son:

1. Distancia Euclidiana
2. Distancia Manhattan

### Perceptrón
------
El objetivo del programa es clasificar las flores de Iris tipo Setosa de entre otras flores de Iris (Versicolor y Virginica) dados sus respectivos valores respecto a la longitud y ancho del sépalo y pétalo con un perceptrón simple.

#### Set de aprendizaje
En la siguiente tabla se muestran las características usadas como entradas para describir a las flores, el set total de aprendizaje se encuentra dentro de la carpeta del proyecto **dataset/iris_aprendizaje.csv**

| Longitud del Sépalo(cm) | Ancho del Sépalo(cm) | Longitud del Pétalo (cm) | Ancho del Pétalo(cm) |
| ------------------------|--------------------- |--------------------------|--------------------- |

Para la inicialización de los pesos se usaron cantidades aleatorias que van desde 0.1 hasta 0.9, el valor del umbral es tomado del valor del primer peso. La razón de aprendizaje es inicializada en 0.5 y se estableció un límite para el número máximo de épocas permitidas para el aprendizaje (1000 épocas).

Todos estos valores a pesar de que son inicializados al arrancar el programa, pueden ser cambiados (son parametrizables) desde la interfaz del usuario para facilitar las pruebas en el perceptrón.

#### Set de pruebas
El set de pruebas utilizado en la práctica se encuentra en la carpeta del proyecto **dataset/iris_prueba.csv**
 
### Perceptrón (Diabetes)
------
El objetivo del programa es predecir si una mujer tiene diabetes o no respecto a ciertas características que revisaremos más adelante con un perceptrón simple. El set de datos utilizado para esta práctica se obtuvo del repositorio de maquinas de aprendizaje de la UC Irvine. [http://archive.ics.uci.edu/ml/datasets/Pima+Indians+Diabetes]

#### Normalización de datos
La normalización de datos de aprendizaje y clasificación se dio a través de dos métodos: Máximos y Min-Max.

#### Set de aprendizaje
En la siguiente lista se muestran las características usadas como entradas para describir los datos físicos/médicos de las mujeres. El set total de aprendizaje se encuentra dentro de la carpeta del proyecto **dataset/diabetes_aprendizaje_perceptron.csv** y se compone de 15 patrones de entrenamiento.

1. Embarazos
2. Conc. de glucosa en plasma a 2 horas en una prueba de tolerancia oral a la glucosa
3. Presión arterial diastólica (mm Hg)
4. Grosor del pliegue cutáneo del tríceps (mm)
5. Insulina en suero 2 horas (mu U / ml)
6. Índice de masa corporal (peso (kg)/(altura (m)) ^2)
7. Función Diabetes pedigree
8. Edad

Para la inicialización de los pesos se usaron cantidades aleatorias que van desde 0.1 hasta 0.9, el valor del umbral es tomado del valor del primer peso. La razón de aprendizaje es inicializada en 0.5 y se estableció un límite para el número máximo de épocas permitidas para el aprendizaje (1000 épocas).

Todos estos valores a pesar de que son inicializados al arrancar el programa, pueden ser cambiados (son parametrizables) desde la interfaz del usuario para facilitar las pruebas en el perceptrón.

#### Set de pruebas
El set de pruebas utilizado en la práctica se encuentra en la carpeta del proyecto **dataset/diabetes_prueba.csv** donde se usaron un total de 268 patrones.

### Adaline
------
El objetivo del programa es predecir si una mujer tiene diabetes o no respecto a ciertas características que revisaremos más adelante con un Adaline. El set de datos utilizado para esta práctica se obtuvo del repositorio de maquinas de aprendizaje de la UC Irvine. [http://archive.ics.uci.edu/ml/datasets/Pima+Indians+Diabetes]

#### Normalización de datos
La normalización de datos de aprendizaje y clasificación se dio a través del método: Min-Max.

#### Set de aprendizaje
En la siguiente lista se muestran las características usadas como entradas para describir los datos físicos/médicos de las mujeres. El set total de aprendizaje se encuentra dentro de la carpeta del proyecto **dataset/diabetes_aprendizaje_backpropagation.csv** y se compone de 10 patrones de entrenamiento.

1. Embarazos
2. Conc. de glucosa en plasma a 2 horas en una prueba de tolerancia oral a la glucosa
3. Presión arterial diastólica (mm Hg)
4. Grosor del pliegue cutáneo del tríceps (mm)
5. Insulina en suero 2 horas (mu U / ml)
6. Índice de masa corporal (peso (kg)/(altura (m)) ^2)
7. Función Diabetes pedigree
8. Edad

Para la inicialización de los pesos se usaron cantidades aleatorias que van desde 0.1 hasta 0.9. La razón de aprendizaje es inicializada en 0.5, se estableció un límite para el número máximo de épocas permitidas para el aprendizaje (80,000 épocas) y se definió un error deseado de 0.0001.

Todos estos valores a pesar de que son inicializados al arrancar el programa, pueden ser cambiados (son parametrizables) desde la interfaz del usuario para facilitar las pruebas en la Adaline.

#### Set de pruebas
El set de pruebas utilizado en la práctica se encuentra en la carpeta del proyecto **dataset/diabetes_prueba.csv** donde se usaron un total de 268 patrones.

### Multi-Layer Network (Backpropagation)
------
El objetivo del programa es predecir si una mujer tiene diabetes o no respecto a ciertas características que revisaremos más adelante con una red neuronal tipo Backpropagation. El set de datos utilizado para esta práctica se obtuvo del repositorio de maquinas de aprendizaje de la UC Irvine. [http://archive.ics.uci.edu/ml/datasets/Pima+Indians+Diabetes]

#### Normalización de datos
La normalización de datos de aprendizaje y clasificación se dio a través del método: Min-Max.

#### Set de aprendizaje
En la siguiente lista se muestran las características usadas como entradas para describir los datos físicos/médicos de las mujeres. El set total de aprendizaje se encuentra dentro de la carpeta del proyecto **dataset/diabetes_aprendizaje_backpropagation.csv** y se compone de 10 patrones de entrenamiento.

1. Embarazos
2. Conc. de glucosa en plasma a 2 horas en una prueba de tolerancia oral a la glucosa
3. Presión arterial diastólica (mm Hg)
4. Grosor del pliegue cutáneo del tríceps (mm)
5. Insulina en suero 2 horas (mu U / ml)
6. Índice de masa corporal (peso (kg)/(altura (m)) ^2)
7. Función Diabetes pedigree
8. Edad

Para la inicialización de los pesos por capa/neurona se usaron cantidades aleatorias que van desde 0.1 hasta 0.9. La razón de aprendizaje es inicializada en 0.5, se estableció un límite para el número máximo de épocas permitidas para el aprendizaje (50,000 épocas) y se definió un error deseado de 0.00001.

Todos estos valores a pesar de que son inicializados al arrancar el programa, pueden ser cambiados (son parametrizables) desde la interfaz del usuario para facilitar las pruebas con la red neuronal.

#### Set de pruebas
El set de pruebas utilizado en la práctica se encuentra en la carpeta del proyecto **dataset/diabetes_prueba.csv** donde se usaron un total de 268 patrones.

La arquitectura que se utilizó fue una red de 2 capas ocultas con 8 neuronas por capa, como salida se tenia una sola neurona y la función que se utilizó fue la de la sigmoide.

### Multi-Layer Network (Quickpropagation)
------
El objetivo del programa es predecir si una mujer tiene diabetes o no respecto a ciertas características que revisaremos más adelante con una red neuronal tipo Quickpropagation. El set de datos utilizado para esta práctica se obtuvo del repositorio de maquinas de aprendizaje de la UC Irvine. [http://archive.ics.uci.edu/ml/datasets/Pima+Indians+Diabetes]

#### Normalización de datos
La normalización de datos de aprendizaje y clasificación se dio a través del método: Maximos.

#### Set de aprendizaje
En la siguiente lista se muestran las características usadas como entradas para describir los datos físicos/médicos de las mujeres. El set total de aprendizaje se encuentra dentro de la carpeta del proyecto **dataset/diabetes_aprendizaje_quickpropagation.csv** y se compone de 10 patrones de entrenamiento.

1. Embarazos
2. Conc. de glucosa en plasma a 2 horas en una prueba de tolerancia oral a la glucosa
3. Presión arterial diastólica (mm Hg)
4. Grosor del pliegue cutáneo del tríceps (mm)
5. Insulina en suero 2 horas (mu U / ml)
6. Índice de masa corporal (peso (kg)/(altura (m)) ^2)
7. Función Diabetes pedigree
8. Edad

Para la inicialización de los pesos por capa/neurona se usaron cantidades aleatorias que van desde 0.1 hasta 0.9. La razón de aprendizaje es inicializada en 0.5, se estableció un límite para el número máximo de épocas permitidas para el aprendizaje (50,000 épocas) y se definió un error deseado de 0.01.

Todos estos valores a pesar de que son inicializados al arrancar el programa, pueden ser cambiados (son parametrizables) desde la interfaz del usuario para facilitar las pruebas con la red neuronal.

#### Set de pruebas
El set de pruebas utilizado en la práctica se encuentra en la carpeta del proyecto **dataset/diabetes_prueba.csv** donde se usaron un total de 268 patrones.

La arquitectura que se utilizó fue una red de 2 capas ocultas con 2 neuronas por capa, como salida se tenia una sola neurona y la función que se utilizó fue la de la sigmoide.

Dada a la naturaleza del problema y las entradas de los patrones de entrenamiento, el algoritmo no llegó a converger pues el gradiente se fue desvaneciendo tanto que el ajuste de los pesos era mínimo y no se lograba ninguna mejora.
