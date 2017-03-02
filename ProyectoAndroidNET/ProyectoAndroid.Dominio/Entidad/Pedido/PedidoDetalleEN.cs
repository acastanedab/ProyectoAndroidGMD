using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ProyectoAndroid.Dominio.Entidad.Articulo;
using ProyectoAndroid.Dominio.Util;

namespace ProyectoAndroid.Dominio.Entidad.Pedido
{
    public class PedidoDetalleEN: Resultado,PedidoDetalle
    {
        public PedidoDetalleEN()
        {
            Pedido = new PedidoEN();
            Articulo = new ArticuloEN();
        }
        public long CodigoPedidoDetalle { get; set; }
        public decimal PrecioPedidoDetalle { get; set; }
        public int CantidadPedidoDetalle { get; set; }
        public decimal SubTotalPedidoDetalle { get; set; }
        public PedidoEN Pedido { get; set; }
        public ArticuloEN Articulo { get; set; }


        public int RegistrarPedidoDetalle(PedidoDetalleEN pedidoDetalle)
        {
            try
            {
                IDictionary map = new Dictionary<string, Object>();
                map.Add("PDE_PED_COD", pedidoDetalle.Pedido.CodigoPedido);
                map.Add("PDE_ART_COD", pedidoDetalle.Pedido.CodigoPedido);
                map.Add("PDE_PRE", pedidoDetalle.PrecioPedidoDetalle);
                map.Add("PDE_CAN", pedidoDetalle.CantidadPedidoDetalle);
                map.Add("PDE_SUB", pedidoDetalle.SubTotalPedidoDetalle);
                Mapper.Mapper.Instance().Insert("uspPedidoDetalleINS", map);
                pedidoDetalle.Estado = 1;
                pedidoDetalle.Mensaje = "OK";
            }
            catch (Exception ex)
            {
                pedidoDetalle.Estado = -1;
                pedidoDetalle.Mensaje = ex.Message;
            }
            return (int)pedidoDetalle.Estado;
        }

        public List<PedidoDetalleEN> ListarPedidoDetalle(long codigoPedido)
        {
            try
            {
                IDictionary map = new Dictionary<string, Object>();
                map.Add("PDE_PED_COD", codigoPedido);
                IList Lista = Mapper.Mapper.Instance().QueryForList("uspPedidoDetalleSEL", map);
                var lista = new List<PedidoDetalleEN>(Lista.Cast<PedidoDetalleEN>());
                return lista;
            }
            catch (Exception ex)
            {
                var lista = new List<PedidoDetalleEN>();
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
