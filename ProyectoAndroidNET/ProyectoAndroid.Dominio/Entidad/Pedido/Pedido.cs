using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ProyectoAndroid.Dominio.Entidad.Pedido
{
    public interface Pedido
    {
        int RegistrarPedido(PedidoEN pedido);
        int RegistrarPedidoDetalle(PedidoEN pedido);
        List<PedidoEN> ListarPedidoDetalle(long codigoPedido);
        int RegistrarPedidoSeguimiento(PedidoEN pedido);
        List<PedidoEN> ListarPedidoSeguimiento(long codigoPedido);
    }
}
