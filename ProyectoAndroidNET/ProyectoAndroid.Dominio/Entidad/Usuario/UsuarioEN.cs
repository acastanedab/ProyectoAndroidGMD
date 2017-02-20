using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ProyectoAndroid.Dominio.Util;

namespace ProyectoAndroid.Dominio.Entidad.Usuario
{
    public class UsuarioEN:Resultado,Usuario
    {
        public UsuarioEN()
        {

        }
        public long CodigoUsuario { get; set; }
        public string NombreUsuario { get; set; }
        public string ContraseniaUsuario { get; set; }
        public string CorreoUsuario { get; set; }
        public string CelularUsuario { get; set; }

        public int RegistrarUsuario(UsuarioEN usuario)
        {
            try
            {
                IDictionary map = new Dictionary<string, Object>();
                map.Add("USU_NOM", usuario.CodigoUsuario);
                map.Add("USU_PASS", usuario.ContraseniaUsuario);
                map.Add("USU_EMAIL", usuario.CorreoUsuario);
                map.Add("USU_CEL", usuario.CelularUsuario);
                Mapper.Mapper.Instance().Insert("uspUsuarioINS", map);
                usuario.Estado = 1;
                usuario.Mensaje = "OK";
            }
            catch (Exception ex)
            {
                usuario.Mensaje = ex.Message;
                usuario.Estado = -1;
            }
            return usuario.Estado;
        }

        public UsuarioEN ObtenerUsuario(string nombreUsuario)
        {
            try
            {
                IDictionary map = new Dictionary<string, Object>();
                map.Add("USU_NOM", nombreUsuario);
                Object usuarioEN = Mapper.Mapper.Instance().QueryForObject("uspUsuarioSEL", map);
                var usuario = new UsuarioEN
                {
                    CodigoUsuario = ((UsuarioEN)usuarioEN).CodigoUsuario,
                    NombreUsuario = ((UsuarioEN)usuarioEN).NombreUsuario,
                    CorreoUsuario = ((UsuarioEN)usuarioEN).CorreoUsuario,
                    CelularUsuario = ((UsuarioEN)usuarioEN).CelularUsuario,
                    Mensaje = "OK",
                    Estado = 1
                };

                return usuario;
            }
            catch (Exception ex)
            {
                return new UsuarioEN
                {
                    Mensaje = ex.Message,
                    Estado = -1
                };
            }

        }
    }
}
