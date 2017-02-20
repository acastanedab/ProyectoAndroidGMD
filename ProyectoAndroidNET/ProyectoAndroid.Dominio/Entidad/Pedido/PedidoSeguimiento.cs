using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ProyectoAndroid.Dominio.Entidad.Pedido
{
    public interface PedidoSeguimiento
    {
        int RegistrarPedidoSeguimiento(PedidoSeguimientoEN pedidoSeguimiento);
        List<PedidoSeguimientoEN> ListarPedidoSeguimiento(long codigoPedido);
    }
}
