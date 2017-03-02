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
        public PedidoEN()
        {
            Usuario = new UsuarioEN();
        }

        public long CodigoPedido { get; set; }
        
        public DateTime FechaPedido { get; set; }
        public string DireccionPedido { get; set; }
        public decimal PrecioPedido { get; set; }
        public UsuarioEN Usuario { get; set; }
           

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
    }
}
