package springboot.tienda.constants;

public class EstadosPedido {
	//El user ha iniciado un proceso de formar pedido
		public static final String EN_PROCESO = "en proceso";
		
		//El usuario ha finalizado el pedido y un administrador podrá gestionarlo
		public static final String TERMINADO = "terminado";
		
		//Un administrador ha preparado fisicamente el envio del para ser recogido por la empresa de mensajeria
		public static final String LISTO_PARA_ENVIAR = "listo para enviar";
		
		//La empresa de mensajeria ha confirmado la recepcion del paquete
		public static final String RECIBIDO_POR_EL_CLIENTE = "recibido por el cliente";

}
