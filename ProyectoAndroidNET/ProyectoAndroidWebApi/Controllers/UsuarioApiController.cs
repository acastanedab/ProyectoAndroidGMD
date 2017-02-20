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
        public IDictionary ObtenerUsuario(string nombreUsuario)
        {
            IDictionary data = new Dictionary<string, Object>();
            var resultado =  usuario.ObtenerUsuario(nombreUsuario);
            data.Add("Usuario", resultado);
            return data;
        }
        [HttpPost]
        public IDictionary RegistrarUsuario(UsuarioEN usuarioEN)
        {
            IDictionary data = new Dictionary<string, Object>();
            usuario.RegistrarUsuario(usuarioEN);
            data.Add("Usuario", usuarioEN);
            return data;
        }
    }
}
