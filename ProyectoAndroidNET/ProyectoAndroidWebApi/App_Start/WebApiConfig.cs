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

            var json = config.Formatters.JsonFormatter;
            config.Formatters.Remove(config.Formatters.XmlFormatter);
            config.Formatters.JsonFormatter.SupportedMediaTypes.Add(new MediaTypeHeaderValue("application/json"));


            config.Routes.MapHttpRoute(
                name: "ObtenerUsuarioApi",
                routeTemplate: "api/usuario/obtener/{nombreUsuario}",
                defaults: new { controller = "UsuarioApi", action = "ObtenerUsuario" }
            );
            config.Routes.MapHttpRoute(
                name: "RegistrarUsuarioApi",
                routeTemplate: "api/usuario/registrar",
                defaults: new { controller = "UsuarioApi", action = "BuscarArticulo" }
            );

            config.Routes.MapHttpRoute(
                name: "ListarArticuloApi",
                routeTemplate: "api/articulo/listar",
                defaults: new { controller = "ArticuloApi", action = "ListarArticulo" }
            );
            config.Routes.MapHttpRoute(
                name: "RegistrarArticuloApi",
                routeTemplate: "api/articulo/registrar",
                defaults: new { controller = "ArticuloApi", action = "RegistrarArticulo" }
            );


            config.Routes.MapHttpRoute(
                name: "RegistrarPedidoApi",
                routeTemplate: "api/pedido/registrar",
                defaults: new { controller = "PedidoApi", action = "RegistrarPedido" }
            );


            config.Routes.MapHttpRoute(
                name: "RegistrarPedidoSeguimientoApi",
                routeTemplate: "api/pedidoseguimiento/registrar",
                defaults: new { controller = "PedidoApi", action = "RegistrarPedidoSeguimiento" }
            );
            config.Routes.MapHttpRoute(
                name: "RegistrarPedidoDetalleApi",
                routeTemplate: "api/pedidodetalle/registrar",
                defaults: new { controller = "PedidoApi", action = "RegistrarPedidoDetalle" }
            );

            config.Routes.MapHttpRoute(
                name: "ListarPedidoDetalleApi",
                routeTemplate: "api/pedidodetalle/listar/{codigoPedido}",
                defaults: new { controller = "PedidoApi", action = "ListarPedidoDetalle" }
            );

            config.Routes.MapHttpRoute(
                name: "ListarPedidoSeguimientoApi",
                routeTemplate: "api/pedidoseguimiento/listar/{codigoPedido}",
                defaults: new { controller = "PedidoApi", action = "ListarPedidoSeguimiento" }
            );


        }
    }
}
