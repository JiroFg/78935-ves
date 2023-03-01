using System;
using WSDL.mensajes;

namespace WSDL.operaciones
{
    public class Operaciones : Mensajes
    {
        List<string> mensajesList = new List<string>();

        public string Saludar(string nombre)
        {
            string msj = "Hola "+nombre;
            mensajesList.Add(msj);
            return msj;
        }
        public string Mostrar(int id)
        {
            return mensajesList[id];
        }
    }
}