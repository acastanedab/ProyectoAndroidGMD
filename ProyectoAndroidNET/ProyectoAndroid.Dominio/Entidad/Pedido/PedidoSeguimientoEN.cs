using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ProyectoAndroid.Dominio.Util;

namespace ProyectoAndroid.Dominio.Entidad.Pedido
{
    public class PedidoSeguimientoEN
    {

        public long CodigoPedidoSeguimiento { get; set; }
        public DateTime FechaPedidoSeguimiento { get; set; }
        public string LatitudPedidoSeguimiento { get; set; }
        public string LongitudPedidoSeguimiento { get; set; }
        public PedidoEN Pedido { get; set; }

      
    }
}
