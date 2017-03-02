using System;
using System.Collections;
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
        public List<ArticuloEN> ListarArticulo()
        {
            var resultado= articulo.ListarArticulo();
            return resultado;
        }

        [HttpPost]
        public Object RegistrarArticulo(ArticuloEN articuloEN)
        {
            articulo.RegistrarArticulo(articuloEN);
            return articuloEN;
        }
    }
}
