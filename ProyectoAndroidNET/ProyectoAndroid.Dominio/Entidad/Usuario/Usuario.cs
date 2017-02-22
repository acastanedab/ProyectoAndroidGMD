using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ProyectoAndroid.Dominio.Entidad.Usuario
{
    public interface Usuario
    {
        int RegistrarUsuario(UsuarioEN usuario);
        UsuarioEN ObtenerUsuario(string nombreUsuario);
    }
}
