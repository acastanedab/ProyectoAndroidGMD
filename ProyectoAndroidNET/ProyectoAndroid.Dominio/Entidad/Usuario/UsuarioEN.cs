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

        public long CodigoUsuario { get; set; }
        public string NombreUsuario { get; set; }
        public string ContraseniaUsuario { get; set; }
        public string CorreoUsuario { get; set; }
        public string CelularUsuario { get; set; }

        public UsuarioEN()
        {

        }

        public int RegistrarUsuario(UsuarioEN usuario)
        {
            try
            {
                IDictionary map = new Dictionary<string, Object>();
                map.Add("USU_NOM", usuario.NombreUsuario);
                map.Add("USU_PASS", usuario.ContraseniaUsuario);
                map.Add("USU_EMAIL", usuario.CorreoUsuario);
                map.Add("USU_CEL", usuario.CelularUsuario);
                usuario.CodigoUsuario =(long) Mapper.Mapper.Instance().Insert("uspUsuarioINS", map);
                usuario.Estado = 1;
                usuario.Mensaje = "OK";
            }
            catch (Exception ex)
            {
                usuario.Mensaje = ex.Message;
                usuario.Estado = -1;
            }
            return (int)usuario.Estado;
        }

        public UsuarioEN ObtenerUsuario(string correo, string contrasenia)
        {
            try
            {
                IDictionary map = new Dictionary<string, Object>();
                map.Add("USU_EMAIL", correo);
                map.Add("USU_PASS", contrasenia);
                Object usuarioEN = Mapper.Mapper.Instance().QueryForObject("uspUsuarioSEL", map);
                if (usuarioEN != null)
                {
                    var usuario = new UsuarioEN
                    {
                        CodigoUsuario = ((UsuarioEN)usuarioEN).CodigoUsuario,
                        NombreUsuario = ((UsuarioEN)usuarioEN).NombreUsuario,
                        CorreoUsuario = ((UsuarioEN)usuarioEN).CorreoUsuario,
                        CelularUsuario = ((UsuarioEN)usuarioEN).CelularUsuario,
                        ContraseniaUsuario = ((UsuarioEN)usuarioEN).ContraseniaUsuario,
                        Mensaje = "OK",
                        Estado = 1
                    };
                    return usuario;
                }
                else
                {
                    return new UsuarioEN()
                    {
                        Mensaje = "Correo y/o Contraseña incorrecta",
                        Estado = -1
                    };
                }
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
