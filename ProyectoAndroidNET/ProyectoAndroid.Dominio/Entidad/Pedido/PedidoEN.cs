using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ProyectoAndroid.Dominio.Entidad.Usuario;
using ProyectoAndroid.Dominio.Util;

namespace ProyectoAndroid.Dominio.Entidad.Pedido
{
    public class PedidoEN:Resultado,Pedido
    {

        public long CodigoPedido { get; set; }
        
        public DateTime? FechaPedido { get; set; }
        public string DireccionPedido { get; set; }
        public decimal PrecioTotalPedido { get; set; }
        public UsuarioEN Usuario { get; set; }
        public PedidoDetalleEN PedidoDetalle { get; set; }
        public PedidoSeguimientoEN PedidoSeguimiento { get; set; }

        public PedidoEN()
        {
            PedidoSeguimiento = new PedidoSeguimientoEN();
            PedidoDetalle = new PedidoDetalleEN();
            Usuario = new UsuarioEN();
        }

        public int RegistrarPedido(PedidoEN pedido)
        {
            try
            {
                IDictionary map = new Dictionary<string, Object>();
                map.Add("PED_DIR", pedido.FechaPedido);
                map.Add("PED_USU", pedido.Usuario.CodigoUsuario);
                pedido.CodigoPedido = (long) Mapper.Mapper.Instance().Insert("uspPedidoINS", map);
                pedido.Estado = 1;
                pedido.Mensaje = "OK";
            }
            catch (Exception ex)
            {
                pedido.Estado = -1;
                pedido.Mensaje = ex.Message;
            }
            return (int)pedido.Estado;
        }
        public List<PedidoEN> ListarPedido(long codigoUsuario)
        {
            try
            {
                IDictionary map = new Dictionary<string, Object>();
                map.Add("USU_COD", codigoUsuario);
                IList Lista = Mapper.Mapper.Instance().QueryForList("uspPedidoSEL", map);
                var lista = new List<PedidoEN>(Lista.Cast<PedidoEN>());
                return lista;
            }
            catch (Exception ex)
            {
                var lista = new List<PedidoEN>();
                foreach (var item in lista)
                {
                    item.Mensaje = ex.Message;
                    item.Estado = -1;
                }
                return lista;
            }
        }
        #region PedidoDetalle
        public int RegistrarPedidoDetalle(PedidoEN pedido)
        {
            try
            {
                IDictionary map = new Dictionary<string, Object>();
                map.Add("PDE_PED_COD", pedido.CodigoPedido);
                map.Add("PDE_ART_COD", pedido.PedidoDetalle.Articulo.CodigoArticulo);
                map.Add("PDE_PRE", pedido.PedidoDetalle.PrecioPedidoDetalle);
                map.Add("PDE_CAN", pedido.PedidoDetalle.CantidadPedidoDetalle);
                map.Add("PDE_SUB", pedido.PedidoDetalle.SubTotalPedidoDetalle);
                Mapper.Mapper.Instance().Insert("uspPedidoDetalleINS", map);
                pedido.Estado = 1;
                pedido.Mensaje = "OK";
            }
            catch (Exception ex)
            {
                pedido.Estado = -1;
                pedido.Mensaje = ex.Message;
            }
            return (int)pedido.Estado;
        }

        public List<PedidoEN> ListarPedidoDetalle(long codigoPedido)
        {
            try
            {
                IDictionary map = new Dictionary<string, Object>();
                map.Add("PDE_PED_COD", codigoPedido);
                IList Lista = Mapper.Mapper.Instance().QueryForList("uspPedidoDetalleSEL", map);
                var lista = new List<PedidoEN>(Lista.Cast<PedidoEN>());
                return lista;
            }
            catch (Exception ex)
            {
                var lista = new List<PedidoEN>();
                foreach (var item in lista)
                {
                    item.Mensaje = ex.Message;
                    item.Estado = -1;
                }
                return lista;
            }
        }
        #endregion 

        #region PedidoSeguimiento
        public int RegistrarPedidoSeguimiento(PedidoEN pedido)
        {
            try
            {
                IDictionary map = new Dictionary<string, Object>();
                map.Add("PSE_PED_COD", pedido.CodigoPedido);
                map.Add("PSE_COR_LAT", pedido.PedidoSeguimiento.LatitudPedidoSeguimiento);
                map.Add("PSE_COR_LON", pedido.PedidoSeguimiento.LongitudPedidoSeguimiento);
                pedido.PedidoSeguimiento.CodigoPedidoSeguimiento = (long)Mapper.Mapper.Instance().Insert("uspPedidoSeguimientoINS", map);
                pedido.Estado = 1;
                pedido.Mensaje = "OK";
            }
            catch (Exception ex)
            {
                pedido.Estado = -1;
                pedido.Mensaje = ex.Message;
            }
            return (int)pedido.Estado;
        }

        public List<PedidoEN> ListarPedidoSeguimiento(long codigoPedido)
        {
            try
            {
                IDictionary map = new Dictionary<string, Object>();
                map.Add("PSE_PED_COD", codigoPedido);
                IList Lista = Mapper.Mapper.Instance().QueryForList("uspPedidoSeguimientoSEL", map);
                var lista = new List<PedidoEN>(Lista.Cast<PedidoEN>());
                return lista;
            }
            catch (Exception ex)
            {
                var lista = new List<PedidoEN>();
                foreach (var item in lista)
                {
                    item.Mensaje = ex.Message;
                    item.Estado = -1;
                }
                return lista;
            }
        }
        #endregion

    }
}
