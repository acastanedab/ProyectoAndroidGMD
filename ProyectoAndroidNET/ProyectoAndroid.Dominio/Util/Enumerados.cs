using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ProyectoAndroid.Dominio.Util
{
    public class Enumerados
    {
        public struct ResultadoMensaje
        {
            public const string exito = "OK";
            public const string error = "ERROR";
        }
        public enum ResultadoEstado
        {
            Exito = 1,
            Error = -1
        }
    }
}
