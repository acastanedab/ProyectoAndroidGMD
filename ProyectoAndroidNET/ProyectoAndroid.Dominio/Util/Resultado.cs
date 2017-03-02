using System;

namespace ProyectoAndroid.Dominio.Util
{
    [Serializable]
    public class Resultado
    {
        public string Mensaje { get; set; }
        public int? Estado { get; set; }
    }
}
