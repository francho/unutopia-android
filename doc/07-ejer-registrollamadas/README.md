Ejercicio Hangout #7: Registro de llamadas
==========================================

Objetivo
--------

Hacer una aplicación que guarde en una BBDD todas las llamadas que se reciben en el teléfono.

Pasos
-----

1.- Crea un helper de BBDD que cree una base de datos con una tabla para guardar el número entrante y la fecha en la que se produjo la llamada
2.- Crea una clase que extienda de BroadcastReceiver que se encargará de hacer un insert en esta tabla cada vez que entre una llamada
3.- Registra tu Receiver en el manifiesto para que se llame automáticamente cada vez que se recibe una llamada
4.- Simula varias llamadas en el emulador
5.- Entra a través de "adb shell" en el emulador y comprueba que tu base de datos tiene los registros correctos

Para nota
---------

Crea un ContentProvider para gestionar el acceso a la BBDD
