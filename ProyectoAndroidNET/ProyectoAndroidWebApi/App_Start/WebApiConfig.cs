using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.Http;
using System.Net.Http.Headers;
using System.Net.Http.Formatting;
using Newtonsoft.Json;
using System.Configuration;
using Newtonsoft.Json.Converters;
using Newtonsoft.Json.Serialization;

namespace ProyectoAndroid
{
    public static class WebApiConfig
    {
        public static void Register(HttpConfiguration config)
        {

            

            config.Routes.MapHttpRoute(
                name: "SeguimientoBuscarApi",
                routeTemplate: "api/seguimiento/buscarseguimientoarticulo/{codigoArticulo}",
                defaults: new { controller  = "SeguimientoApi", action = "BuscarSeguimientoArticulo" }
            );
            config.Routes.MapHttpRoute(
                name: "ArticuloBuscarApi",
                routeTemplate: "api/articulo/buscar",
                defaults: new { controller = "ArticuloApi", action = "BuscarArticulo" }
            );

            var json = config.Formatters.JsonFormatter;
            config.Formatters.Remove(config.Formatters.XmlFormatter);
            config.Formatters.JsonFormatter.SupportedMediaTypes.Add(new MediaTypeHeaderValue("text/html"));

        }

    }
}
