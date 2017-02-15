using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using ProyectoAndroid.Dominio.Entidad.Articulo;

namespace ProyectoAndroid.Controllers
{
    public class ArticuloApiController : ApiController
    {
        private Articulo articulo;
        public ArticuloApiController()
        {
            articulo = new ArticuloEN();
        }
        [HttpGet]
        public List<ArticuloEN> BuscarArticulo()
        {
            List<ArticuloEN> resultado = new List<ArticuloEN>();
            resultado = articulo.ListarArticulo();
            return resultado;
        }
    }
}
