using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ProyectoAndroid.Dominio.Entidad.Seguimiento
{
    public interface Seguimiento
    {
        int RegistrarSeguimiento(SeguimientoEN seguimientoEN);
        int ActualizarSeguimiento(SeguimientoEN seguimientoEN);
        List<SeguimientoEN> BuscarSeguimiento();
        SeguimientoEN ObtenerSeguimiento(SeguimientoEN seguimientoEN);
    }
}
