/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.github.anastaciocintra.escpos.EscPos;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;
import modelo.*;
import vista.IMenu;

/**
 *
 * @author CESAR DIAZ MARADIAGA
 */
public class CtrlPagos extends CtrlImprimir implements ActionListener, CaretListener, MouseListener {

	IMenu menu;
	PagosCreditos pagos;
	CtrlReportes ctrlR;
	Creditos creditos;
	Reportes reportes;
	PrintReportes print;
	InfoFactura info;
	DefaultTableModel modelo;
	String id;
	Date fecha;
	DecimalFormat formato;

	public CtrlPagos(IMenu menu, PagosCreditos pagos) {
		this.menu = menu;
		this.pagos = pagos;
		this.formato = new DecimalFormat("############0.00");
		this.reportes = new Reportes();
		this.creditos = new Creditos();
		this.ctrlR = new CtrlReportes(menu, reportes);
		this.modelo = new DefaultTableModel();
		this.print = new PrintReportes();
		this.info = new InfoFactura();
		this.fecha = new Date();
		this.menu.cmbFormaPagoCredito.setModel(pagos.FormasPago());
		this.menu.btnGuardarPago.addActionListener(this);
		this.menu.btnActualizarPago.addActionListener(this);
		this.menu.btnNuevoPago.addActionListener(this);
		this.menu.EditarPago.addActionListener(this);
		this.menu.BorrarPago.addActionListener(this);
		this.menu.txtBuscarPago.addCaretListener(this);
		this.menu.btnMostrarPagosRegistrados.addActionListener(this);
		this.menu.tblPagos.addMouseListener(this);
		MostrarPagos("");
		DeshabilitarPagos();
		UltimoPago();
		this.menu.jcFechaPago.setDate(fecha);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == menu.btnGuardarPago) {
			guardarPago();
		}
		if (e.getSource() == menu.btnActualizarPago) {
			int c;
			float montoPago;
			String credito = menu.txtCreditoPago.getText(),
				monto = menu.txtMontoPago.getText(),
				formaPago = menu.cmbFormaPagoCredito.getSelectedItem().toString(),
				anotacion = menu.txtAreaAnotacionPago.getText();
			Date f = menu.jcFechaPago.getDate();
			int idFormaPago = Integer.parseInt(pagos.ObtenerFormaPago(formaPago));
			long fecha = f.getTime();
			java.sql.Date fechaPago = new java.sql.Date(fecha);
			if (!credito.equals("") && !monto.equals("")) {
				if (isNumeric(credito) && isNumeric(monto)) {
					c = Integer.parseInt(credito);
					montoPago = Float.parseFloat(monto);
					pagos.Actualizar(this.id, c, montoPago, fechaPago, idFormaPago, anotacion);
					MostrarPagos("");
					CtrlCreditos.MostrarCreditos("");
					CtrlCreditos.cambiarEstado(c);
					LimpiarPago();
					/*ctrlR.reportesDiarios(this.fecha);
					ctrlR.MostrarReportesDario(this.fecha);
					ctrlR.ReporteGlobal();
					ctrlR.SumaTotalFiltroReporte(this.fecha, this.fecha);*/
					CtrlCreditos.MostrarCreditosCreados("");
					menu.btnGuardarPago.setEnabled(true);
					menu.btnActualizarPago.setEnabled(false);
				}
			} else {

			}
		}
		if (e.getSource() == menu.btnNuevoPago) {
			HabilitarPago();
			LimpiarPago();
			menu.txtMontoPago.requestFocus();
		}
		if (e.getSource() == menu.EditarPago) {
			int filaseleccionada = menu.tblPagos.getSelectedRow();
			String id, monto, credito, formaPago, anotacion;
			Date fecha;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				if (filaseleccionada == -1) {

				} else {
					this.modelo = (DefaultTableModel) menu.tblPagos.getModel();
					id = (String) this.modelo.getValueAt(filaseleccionada, 0);
					monto = (String) this.modelo.getValueAt(filaseleccionada, 1);
					credito = (String) this.modelo.getValueAt(filaseleccionada, 2);
					fecha = sdf.parse(this.modelo.getValueAt(filaseleccionada, 3).toString());
					formaPago = (String) this.modelo.getValueAt(filaseleccionada, 6).toString();
					anotacion = (String) this.modelo.getValueAt(filaseleccionada, 7).toString();
					this.id = id;

					HabilitarPago();
					menu.txtMontoPago.setText(monto);
					menu.txtCreditoPago.setText(credito);
					menu.jcFechaPago.setDate(fecha);
					menu.cmbFormaPagoCredito.setSelectedItem(formaPago);
					menu.txtAreaAnotacionPago.setText(anotacion);
					menu.btnGuardarPago.setEnabled(false);
					menu.btnActualizarPago.setEnabled(true);
				}
			} catch (Exception err) {
			}
		}
		if (e.getSource() == menu.BorrarPago) {
			int filaseleccionada = menu.tblPagos.getSelectedRow(), id = 0;
			try {
				if (filaseleccionada == -1) {

				} else {
					this.modelo = (DefaultTableModel) menu.tblPagos.getModel();
					id = Integer.parseInt(this.modelo.getValueAt(filaseleccionada, 0).toString());
					int credito = Integer.parseInt(this.modelo.getValueAt(filaseleccionada, 2).toString());
					this.pagos.Eliminar(id);
					MostrarPagos("");
					CtrlCreditos.MostrarCreditos("");
					/*ctrlR.reportesDiarios(this.fecha);
					ctrlR.MostrarReportesDario(this.fecha);
					ctrlR.ReporteGlobal();*/
					this.ctrlR.SumaTotalFiltroReporte(this.fecha, this.fecha);
					CtrlCreditos.cambiarEstado(credito);
				}
			} catch (Exception err) {
				JOptionPane.showMessageDialog(null, e + " en la funcion Borrar Pago", "Error", JOptionPane.ERROR_MESSAGE);
			}

		}
		if (e.getSource() == menu.btnMostrarPagosRegistrados) {
			menu.pagosAcreditos.setSize(860, 540);
			menu.pagosAcreditos.setVisible(true);
			menu.pagosAcreditos.setLocationRelativeTo(null);
		}
	}

	public void MostrarPagos(String buscar) {
		menu.jcFechaPago.setDate(this.fecha);
		menu.tblPagos.getTableHeader().setFont(new Font("Sugoe UI", Font.PLAIN, 14));
		menu.tblPagos.getTableHeader().setOpaque(false);
		menu.tblPagos.getTableHeader().setBackground(new Color(69, 76, 89));
		menu.tblPagos.getTableHeader().setForeground(new Color(255, 255, 255));
		menu.tblPagos.setModel(this.pagos.Mostrar(buscar));
	}

	public boolean isNumeric(String cadena) {//metodo para la validacion de campos numericos
		try {
			Float.parseFloat(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	//metodo para limpiar el formulario Pagos
	public void LimpiarPago() {
		menu.txtMontoPago.setText("");
		menu.cmbFormaPagoCredito.setSelectedItem("Efectivo");
		menu.txtAreaAnotacionPago.setText("");
	}

	public void HabilitarPago() {
		menu.btnActualizarPago.setEnabled(false);
		menu.btnGuardarPago.setEnabled(true);
	}
	//funcion para deshabilitar componentes de el formulario de pago

	public void DeshabilitarPagos() {
		menu.btnActualizarPago.setEnabled(false);
		menu.btnGuardarPago.setEnabled(false);
	}//lbls para hacer visible el pago en la ventana pago

	//mostrar el id del pago actual
	public void UltimoPago() {
		this.menu.lblNumeroPago.setText("" + creditos.obtenerUltimoPago());
	}

	public void guardarPago() {
		int c;
		float montoPago,
			saldo = 0,
			saldoActual = 0;
		String fechaString = "",
			credito = menu.txtCreditoPago.getText(),
			monto = menu.txtMontoPago.getText(),
			formaPago = menu.cmbFormaPagoCredito.getSelectedItem().toString(),
			anotacion = menu.txtAreaAnotacionPago.getText();
		int idFormaPago = Integer.parseInt(pagos.ObtenerFormaPago(formaPago));
		Date f = menu.jcFechaPago.getDate();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-YYYY");
		long fecha = f.getTime();
		java.sql.Date fechaPago = new java.sql.Date(fecha);
		fechaString = sdf.format(fecha);
		if (isNumeric(credito) && isNumeric(monto)) {
			try {
				c = Integer.parseInt(credito);
				montoPago = Float.parseFloat(monto);
				saldo = pagos.deuda(credito) - pagos.PagosSegunCredito(credito);
				if (montoPago <= Float.parseFloat(this.formato.format(saldo))) {
					pagos.Guardar(c, montoPago, fechaPago, idFormaPago, anotacion);
					saldoActual = pagos.deuda(credito) - pagos.PagosSegunCredito(credito);
					UltimoPago();
					info.obtenerInfoFactura();
					imprimir(info.getNombre(),
						menu.lblNumeroPago.getText(),
						fechaString, this.pagos.cliente(credito),
						credito, this.formato.format(saldo),
						monto, this.formato.format(saldoActual));
					MostrarPagos("");
					LimpiarPago();
					CtrlCreditos.MostrarCreditos("");
					CtrlCreditos.cambiarEstado(c);
					CtrlCreditos.MostrarCreditos("");
					ctrlR.reportesDiarios(this.fecha);
					ctrlR.MostrarReportesDario(this.fecha);
					ctrlR.ReporteGlobal();
					ctrlR.SumaTotalFiltroReporte(this.fecha, this.fecha);
					MostrarPagos("");
					CtrlCreditos.MostrarCreditosCreados("");
					CtrlCreditos.MostrarCreditosAddFactura("");
					menu.btnGuardarPago.setEnabled(true);
					menu.btnActualizarPago.setEnabled(false);
				}else{
					JOptionPane.showMessageDialog(null, "Oops, el monto a pagar excede al saldo actual.!");
				}

			} catch (Exception e) {

			}
		}
	}

	/* VALIDAR SI EL PAGO ESTA EXCEDIENDO EL SALDO DEL CREDITO */
	public boolean isExcedeSaldo(float saldo, float monto, String credito) {
		if (monto <= saldo) {
			return true;
		} else {
			return false;
		}
	}

	//IMPRIMIR TICKET COMPOBANTE DE PAGO
	public void imprimir(String tienda, String idPago, String fecha, String cliente, String credito, String totalCredito, String monto, String saldo) {
		try {

			reiniciar();
			escpos.write(imageWrapper, escposImage).feed(1);

			escpos.writeLF(boldCenter, tienda)
				.feed(1)
				.writeLF(boldCenter, "COMPROBANTE DE PAGO")
				.feed(2)
				.writeLF("N° crédito:" + credito)
				.writeLF("N° pago:" + idPago)
				.writeLF("Cliente:" + cliente)
				.writeLF("Fecha:" + fecha)
				.writeLF("Total crédito:" + totalCredito)
				.writeLF("Monto de abono:" + monto)
				.write("Saldo:").writeLF(bold, saldo)
				.feed(4)
				.writeLF(centrar, "_____________________________________")
				.writeLF(centrar, "Firma vendedor")
				.feed(8)
				.writeLF(centrar, "_____________________________________")
				.writeLF(centrar, "Firma cliente")
				.feed(3)
				.cut(EscPos.CutMode.FULL);

			close();

		} catch (Exception e) {

		}
	}

	;
    
    
    @Override
	public void caretUpdate(CaretEvent e) {
		if (e.getSource() == menu.txtBuscarPago) {
			String valor = menu.txtBuscarPago.getText();
			MostrarPagos(valor);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == menu.tblPagos) {
			if (e.getClickCount() == 2) {
				String anotacion;
				int filaseleccionada = menu.tblPagos.getSelectedRow();
				if (filaseleccionada != -1) {
					anotacion = (String) menu.tblPagos.getValueAt(filaseleccionada, 7);
					menu.txtAreaInfoPago.setText(anotacion);
					menu.jdInfoPago.setLocationRelativeTo(null);
					menu.jdInfoPago.setSize(600, 270);
					menu.jdInfoPago.setVisible(true);
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
