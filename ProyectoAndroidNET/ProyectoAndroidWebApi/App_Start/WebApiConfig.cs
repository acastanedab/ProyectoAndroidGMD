using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.Http;
using System.Net.Http.Headers;
using System.Net.Http.Formatting;
using Newtonsoft.Json;
using System.Configuration;
using Newtonsoft.Json.Converters;

namespace ProyectoAndroid
{
    public static class WebApiConfig
    {
        public static void Register(HttpConfiguration config)
        {
            config.Routes.MapHttpRoute(
                name: "SeguimientoBuscarApi",
                routeTemplate: "api/seguimiento/buscar",
                defaults: new { controller  = "SeguimientoApi", action = "BuscarSeguimiento" }
            );

            config.Routes.MapHttpRoute(
                name: "ArticuloBuscarApi",
                routeTemplate: "api/articulo/buscar",
                defaults: new { controller = "ArticuloApi", action = "BuscarArticulo" }
            );


            //Configure formatters.
            var json = config.Formatters.JsonFormatter;
            json.SerializerSettings.PreserveReferencesHandling = Newtonsoft.Json.PreserveReferencesHandling.Objects;
            config.Formatters.Remove(config.Formatters.XmlFormatter);
            json.SerializerSettings.PreserveReferencesHandling = Newtonsoft.Json.PreserveReferencesHandling.None;

            JsonMediaTypeFormatter jsonFormatter = GlobalConfiguration.Configuration.Formatters.JsonFormatter;
            JsonSerializerSettings jSettings = new Newtonsoft.Json.JsonSerializerSettings()
            {
                Formatting = Formatting.Indented, 
                DateTimeZoneHandling = DateTimeZoneHandling.Utc,
                NullValueHandling = NullValueHandling.Ignore
            };
            jSettings.Converters.Add(new MyDateTimeConvertor());
            jsonFormatter.UseDataContractJsonSerializer = true;
            jsonFormatter.SerializerSettings = jSettings;


            config.Formatters.JsonFormatter.SupportedMediaTypes.Add(new MediaTypeHeaderValue("text/html"));
        }

        public class MyDateTimeConvertor : DateTimeConverterBase
        {
            public override object ReadJson(JsonReader reader, Type objectType, object existingValue, JsonSerializer serializer)
            {
                return DateTime.Parse(reader.Value.ToString());
            }
            public override void WriteJson(JsonWriter writer, object value, JsonSerializer serializer)
            {
                writer.WriteValue(((DateTime)value).ToString("yyyy/MM/dd hh:mm:ss"));
            }
        }
    }
}
