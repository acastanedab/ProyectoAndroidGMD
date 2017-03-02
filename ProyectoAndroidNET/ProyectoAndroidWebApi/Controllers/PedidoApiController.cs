using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using ProyectoAndroid.Dominio.Entidad.Pedido;

namespace ProyectoAndroid.Controllers
{
    public class PedidoApiController : ApiController
    {
        private Pedido pedido;
        private PedidoSeguimiento pedidoSeguimiento;
        private PedidoDetalle pedidoDetalle;
        
        public PedidoApiController()
        {
            pedidoSeguimiento = new PedidoSeguimientoEN();
            pedidoDetalle = new PedidoDetalleEN();
            pedido = new PedidoEN();
        }

        [HttpPost]
        public Object RegistrarPedido(PedidoEN pedidoEN)
        {
            pedido.RegistrarPedido(pedidoEN);
            return pedidoEN;
        }

        [HttpPost]
        public Object RegistrarPedidoSeguimiento(PedidoSeguimientoEN pedidoSeguimientoEN)
        {
            pedidoSeguimiento.RegistrarPedidoSeguimiento(pedidoSeguimientoEN);
            return pedidoSeguimientoEN;
        }


        [HttpPost]
        public Object RegistrarPedidoDetalle(PedidoDetalleEN pedidoDetalleEN)
        {
            pedidoDetalle.RegistrarPedidoDetalle(pedidoDetalleEN);
            return pedidoDetalleEN;
        }

        [HttpGet]
        public List<PedidoDetalleEN> ListarPedidoDetalle(long codigoPedido)
        {
            var resultado = pedidoDetalle.ListarPedidoDetalle(codigoPedido);
            return resultado;
        }

        [HttpGet]
        public List<PedidoSeguimientoEN> ListarPedidoSeguimiento(long codigoPedido)
        {
            var resultado = pedidoSeguimiento.ListarPedidoSeguimiento(codigoPedido);
            return resultado;
        }

    }
}
