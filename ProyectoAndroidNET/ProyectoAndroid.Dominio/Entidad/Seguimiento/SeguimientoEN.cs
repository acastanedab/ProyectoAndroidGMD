using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ProyectoAndroid.Dominio;
using ProyectoAndroid.Dominio.Entidad.Articulo;
using ProyectoAndroid.Dominio.Mapper;
using ProyectoAndroid.Dominio.Util;

namespace ProyectoAndroid.Dominio.Entidad.Seguimiento
{
    public class SeguimientoEN: Seguimiento
    {
        #region Propiedades
        public long CodigoSeguimiento { get; set; }
        public DateTime FechaSeguimiento { get; set; }
        public decimal LatitudSeguimiento { get; set; }
        public decimal LongitudSeguimiento { get; set; }
        public ArticuloEN Articulo { get; set; }
        #endregion

        public SeguimientoEN()
        {
            Articulo = new ArticuloEN();
        }

        public int RegistrarSeguimiento(SeguimientoEN seguimientoEN)
        {
            throw new NotImplementedException();
        }

        public int ActualizarSeguimiento(SeguimientoEN seguimientoEN)
        {
            throw new NotImplementedException();
        }

        public List<SeguimientoEN> BuscarSeguimiento()
        {
            
            try
            {
                IList Lista = Mapper.Mapper.Instance().QueryForList("uspSeguimientoSEL", null);
                return new List<SeguimientoEN>(Lista.Cast<SeguimientoEN>());
            }
            catch(Exception ex)
            {
                return new List<SeguimientoEN>();
            }
        }

        public SeguimientoEN ObtenerSeguimiento(SeguimientoEN seguimientoEN)
        {
            throw new NotImplementedException();
        }
    }
}
