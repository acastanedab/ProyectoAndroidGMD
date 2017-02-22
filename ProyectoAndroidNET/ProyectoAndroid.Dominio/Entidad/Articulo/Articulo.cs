using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ProyectoAndroid.Dominio.Entidad.Articulo
{
    public interface Articulo
    {
        int RegistrarArticulo(ArticuloEN articuloEN);
        List<ArticuloEN> ListarArticulo();
    }
}
