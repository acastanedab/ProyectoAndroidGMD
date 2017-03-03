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

        
        public PedidoApiController()
        {
            pedido = new PedidoEN();
        }

        [HttpPost]
        public Object RegistrarPedido(PedidoEN pedidoEN)
        {
            pedido.RegistrarPedido(pedidoEN);
            return pedidoEN;
        }

        [HttpPost]
        public Object RegistrarPedidoSeguimiento(PedidoEN pedidoEN)
        {
            pedido.RegistrarPedidoSeguimiento(pedidoEN);
            return pedidoEN;
        }


        [HttpPost]
        public Object RegistrarPedidoDetalle(PedidoEN pedidoEN)
        {
            pedido.RegistrarPedidoDetalle(pedidoEN);
            return pedidoEN;
        }

        [HttpGet]
        public List<PedidoEN> ListarPedidoDetalle(long codigoPedido)
        {
            var resultado = pedido.ListarPedidoDetalle(codigoPedido);
            return resultado;
        }

        [HttpGet]
        public List<PedidoEN> ListarPedidoSeguimiento(long codigoPedido)
        {
            var resultado = pedido.ListarPedidoSeguimiento(codigoPedido);
            return resultado;
        }

    }
}
