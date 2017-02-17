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
                IDictionary map = new Dictionary<string, Object>();
                map.Add("ART_COD", null);
                IList Lista = Mapper.Mapper.Instance().QueryForList("uspSeguimientoSEL", map);
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


        public SeguimientoEN BuscarSeguimientoArticulo(long codigoArticulo)
        {
            try
            {
                IDictionary map = new Dictionary<string, Object>();
                map.Add("ART_COD", codigoArticulo);
                Object Seguimiento = Mapper.Mapper.Instance().QueryForObject("uspSeguimientoSEL", map);
                return (SeguimientoEN)Seguimiento;
            }
            catch (Exception ex)
            {
                return new SeguimientoEN();
            }
        }
    }
}
