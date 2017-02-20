using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ProyectoAndroid.Dominio.Entidad.Pedido
{
    public interface PedidoDetalle
    {
        int RegistrarPedidoDetalle(PedidoDetalleEN pedidoDetalle);
        List<PedidoDetalleEN> ListarPedidoDetalle(long codigoPedido);
    }
}
