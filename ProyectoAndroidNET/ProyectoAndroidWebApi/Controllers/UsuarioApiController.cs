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
        [HttpGet]
        public UsuarioEN ObtenerUsuario(string nombreUsuario)
        {
            var resultado =  usuario.ObtenerUsuario(nombreUsuario);
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
