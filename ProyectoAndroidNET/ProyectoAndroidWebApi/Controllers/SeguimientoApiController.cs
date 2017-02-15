using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using ProyectoAndroid.Dominio.Entidad.Seguimiento;

namespace ProyectoAndroid.Controllers
{
    public class SeguimientoApiController : ApiController
    {
        private  Seguimiento seguimiento;
        public SeguimientoApiController()
        {
            seguimiento = new SeguimientoEN();
        }
        [HttpGet]
        public List<SeguimientoEN> BuscarSeguimiento()
        {
            List<SeguimientoEN> resultado = new List<SeguimientoEN>();
            resultado = seguimiento.BuscarSeguimiento();
            return resultado;
        }
    }
}
