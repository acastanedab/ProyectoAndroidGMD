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
        public IDictionary ListarArticulo()
        {
            IDictionary data = new Dictionary<string, Object>();
            var resultado= articulo.ListarArticulo();
            data.Add("Articulo", resultado);
            return data;
        }

        [HttpPost]
        public IDictionary RegistrarArticulo(ArticuloEN articuloEN)
        {
            IDictionary data = new Dictionary<string, Object>();
            articulo.RegistrarArticulo(articuloEN);
            data.Add("Articulo", articuloEN);
            return data;
        }
    }
}
