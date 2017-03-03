using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using ProyectoAndroid.Dominio.Entidad.Usuario;

namespace ProyectoAndroid.Controllers
{
    public class UsuarioApiController : ApiController
    {
        private Usuario usuario;
        public UsuarioApiController()
        {
            usuario = new UsuarioEN();
            
        }
        [HttpPost]
        public UsuarioEN ObtenerUsuario(UsuarioEN usuarioEN)
        {
            string correo = usuarioEN.CorreoUsuario;
            string contrasenia = usuarioEN.ContraseniaUsuario;
            var resultado =  usuario.ObtenerUsuario(correo,contrasenia);
            return resultado;
        }
        [HttpPost]
        public Object RegistrarUsuario(UsuarioEN usuarioEN)
        {
            usuario.RegistrarUsuario(usuarioEN);
            return usuarioEN;
        }
    }
}
