using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ProyectoAndroid.Dominio.Util;

namespace ProyectoAndroid.Dominio.Entidad.Articulo
{
    public class ArticuloEN : Resultado,Articulo
    {

        public ArticuloEN()
        {

        }
        #region Propiedades
        public long CodigoArticulo { get; set; }
        public string NombreArticulo { get; set; }
        public string DescripcionArticulo { get; set; }
        public decimal PrecioArticulo { get; set; }
        #endregion

        public int RegistrarArticulo(ArticuloEN articulo)
        {
            try
            {
                IDictionary map = new Dictionary<string, Object>();
                map.Add("ART_NOM", articulo.NombreArticulo);
                map.Add("ART_DES", articulo.DescripcionArticulo);
                map.Add("ART_PRE", articulo.PrecioArticulo);
                Mapper.Mapper.Instance().Insert("uspUsuarioINS", map);
                articulo.Estado = 1;
                articulo.Mensaje = "OK";
            }
            catch (Exception ex)
            {
                articulo.Estado = -1;
                articulo.Mensaje = ex.Message;
            }
            return articulo.Estado;
        }

        public List<ArticuloEN> ListarArticulo()
        {
            try
            {
                IList Lista = Mapper.Mapper.Instance().QueryForList("uspArticuloSEL", null);
                var lista = new List<ArticuloEN>(Lista.Cast<ArticuloEN>());
                return lista;
            }
            catch (Exception ex)
            {
                var lista = new List<ArticuloEN>();
                foreach (var item in lista)
                {
                    item.Mensaje = ex.Message;
                    item.Estado = -1;
                }
                return lista;
            }
        }


    }
}
