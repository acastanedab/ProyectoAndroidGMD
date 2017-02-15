using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ProyectoAndroid.Dominio.Entidad.Articulo
{
    public class ArticuloEN : Articulo
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

        public int RegistrarArticulo(ArticuloEN articuloEN)
        {
            throw new NotImplementedException();
        }

        public int ActualizarArticulo(ArticuloEN articuloEN)
        {
            throw new NotImplementedException();
        }

        public List<ArticuloEN> ListarArticulo()
        {
            try
            {
                IList Lista = Mapper.Mapper.Instance().QueryForList("uspArticuloSEL", null);
                return new List<ArticuloEN>(Lista.Cast<ArticuloEN>());
            }
            catch (Exception ex)
            {
                return new List<ArticuloEN>();
            }
        }

        public ArticuloEN ObtenerArticulo(ArticuloEN articuloEN)
        {
            throw new NotImplementedException();
        }
    }
}
