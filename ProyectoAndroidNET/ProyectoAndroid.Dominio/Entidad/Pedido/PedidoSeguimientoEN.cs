using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ProyectoAndroid.Dominio.Util;

namespace ProyectoAndroid.Dominio.Entidad.Pedido
{
    public class PedidoSeguimientoEN:Resultado,PedidoSeguimiento
    {
        public PedidoSeguimientoEN()
        {
            Pedido = new PedidoEN();
        }
        public DateTime FechaPedidoSeguimiento { get; set; }
        public string LatitudPedidoSeguimiento { get; set; }
        public string LongitudPedidoSeguimiento { get; set; }
        public PedidoEN Pedido { get; set; }

        public int RegistrarPedidoSeguimiento(PedidoSeguimientoEN pedidoSeguimiento)
        {
            try
            {
                IDictionary map = new Dictionary<string, Object>();
                map.Add("PSE_PED_COD", pedidoSeguimiento.Pedido.CodigoPedido );
                map.Add("PSE_COR_LAT", pedidoSeguimiento.LatitudPedidoSeguimiento);
                map.Add("PSE_COR_LON", pedidoSeguimiento.LongitudPedidoSeguimiento);
                Mapper.Mapper.Instance().Insert("uspPedidoSeguimientoINS", map);
                pedidoSeguimiento.Estado = 1;
                pedidoSeguimiento.Mensaje = "OK";
            }
            catch (Exception ex)
            {
                pedidoSeguimiento.Estado = -1;
                pedidoSeguimiento.Mensaje = ex.Message;
            }
            return pedidoSeguimiento.Estado;
        }

        public List<PedidoSeguimientoEN> ListarPedidoSeguimiento(long codigoPedido)
        {
            try
            {
                IDictionary map = new Dictionary<string, Object>();
                map.Add("PSE_PED_COD", codigoPedido);
                IList Lista = Mapper.Mapper.Instance().QueryForList("uspPedidoSeguimientoSEL", map);
                var lista = new List<PedidoSeguimientoEN>(Lista.Cast<PedidoSeguimientoEN>());
                return lista;
            }
            catch (Exception ex)
            {
                var lista = new List<PedidoSeguimientoEN>();
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
