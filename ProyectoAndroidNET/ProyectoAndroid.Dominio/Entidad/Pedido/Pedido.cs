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
        int RegistrarPedidoSeguimiento(PedidoEN pedido);
        List<PedidoEN> ListarPedido(long codigoUsuario);
        List<PedidoEN> ListarPedidoDetalle(long codigoPedido);
        List<PedidoEN> ListarPedidoSeguimiento(long codigoPedido);
    }
}
