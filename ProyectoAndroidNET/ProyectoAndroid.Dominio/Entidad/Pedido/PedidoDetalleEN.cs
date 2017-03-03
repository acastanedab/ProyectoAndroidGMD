using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ProyectoAndroid.Dominio.Entidad.Articulo;
using ProyectoAndroid.Dominio.Util;

namespace ProyectoAndroid.Dominio.Entidad.Pedido
{
    public class PedidoDetalleEN
    {

        public long CodigoPedidoDetalle { get; set; }
        public decimal PrecioPedidoDetalle { get; set; }
        public int CantidadPedidoDetalle { get; set; }
        public decimal SubTotalPedidoDetalle { get; set; }
        public ArticuloEN Articulo { get; set; }

        public PedidoDetalleEN()
        {
            Articulo = new ArticuloEN();
        }
    
    }

}
