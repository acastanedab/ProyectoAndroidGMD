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
        public IDictionary RegistrarPedido(PedidoEN pedidoEN)
        {
            IDictionary data = new Dictionary<string, Object>();
            data.Add("Pedido", pedidoEN);
            pedido.RegistrarPedido(pedidoEN);
            return data;
        }

        [HttpPost]
        public IDictionary RegistrarPedidoSeguimiento(PedidoSeguimientoEN pedidoSeguimientoEN)
        {
            IDictionary data = new Dictionary<string, Object>();
            data.Add("PedidoSeguimiento", pedidoSeguimientoEN);
            pedidoSeguimiento.RegistrarPedidoSeguimiento(pedidoSeguimientoEN);
            return data;
        }


        [HttpPost]
        public IDictionary RegistrarPedidoDetalle(PedidoDetalleEN pedidoDetalleEN)
        {
            IDictionary data = new Dictionary<string, Object>();
            pedidoDetalle.RegistrarPedidoDetalle(pedidoDetalleEN);
            data.Add("PedidoDetalle", pedidoDetalleEN);
            return data;
        }

        [HttpGet]
        public IDictionary ListarPedidoDetalle(long codigoPedido)
        {
            IDictionary data = new Dictionary<string, Object>();
            var resultado = pedidoDetalle.ListarPedidoDetalle(codigoPedido);
            data.Add("PedidoDetalle", resultado);
            return data;
        }

        [HttpGet]
        public IDictionary ListarPedidoSeguimiento(long codigoPedido)
        {
            IDictionary data = new Dictionary<string, Object>();
            var resultado = pedidoSeguimiento.ListarPedidoSeguimiento(codigoPedido);
            data.Add("PedidoSeguimiento", resultado);
            return data;
        }

    }
}
