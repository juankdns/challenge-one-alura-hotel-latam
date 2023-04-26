package com.alura.hotel.latam.views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.alura.hotel.latam.dao.HuespedDAO;
import com.alura.hotel.latam.dao.HuespedImpl;
import com.alura.hotel.latam.dao.ReservaDAO;
import com.alura.hotel.latam.dao.ReservaImpl;
import com.alura.hotel.latam.dto.HuespedDTO;
import com.alura.hotel.latam.dto.ReservaDTO;
import com.alura.hotel.latam.modelo.Huesped;
import com.alura.hotel.latam.modelo.Reserva;
import com.alura.hotel.latam.utils.JpaUtils;
import com.alura.hotel.latam.vo.HuespedVO;
import com.alura.hotel.latam.vo.ReservaVO;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class BusquedaView extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	private Long id;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private String nacionalidad;
	private String telefono;
	private Long numeroReserva;
	private Long idReserva;
	private Date fechaEntrada;
	private Date fechaSalida;
	private BigDecimal valor;
	private String metodoPago;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BusquedaView frame = new BusquedaView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BusquedaView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(BusquedaView.class.getResource("/img/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 20));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 15));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 15));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Número de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");

		filasReservas();

		tbReservas.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				int row = e.getFirstRow();
				TableModel model = (TableModel) e.getSource();
				if (model.getRowCount() != 0) {
					try {
						idReserva = (Long) model.getValueAt(row, 0);
					} catch (ClassCastException e1) {
						JOptionPane.showMessageDialog(null, "El número del reserva no puede ser editado.");
						actualizarReservas();
					}
					SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
					String fechaEntradaTexto = (String) model.getValueAt(row, 1);
					try {
						fechaEntrada = formato.parse(fechaEntradaTexto);
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(null, "Valor fecha inválido.");
						actualizarReservas();
					}
					String fechaSalidaTexto = (String) model.getValueAt(row, 2);
					try {
						fechaSalida = formato.parse(fechaSalidaTexto);
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(null, "Valor fecha inválido.");
						actualizarReservas();
					}
					String valorTexto = model.getValueAt(row, 3).toString();
					try {
						valor = new BigDecimal(valorTexto);
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "El valor de la reserva es inválido.");
						actualizarReservas();
					}
					metodoPago = (String) model.getValueAt(row, 4);
				}
			}
		});

		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(BusquedaView.class.getResource("/img/reservado.png")), scroll_table,
				null);
		scroll_table.setVisible(true);

		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 15));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Numero de Huésped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Teléfono");
		modeloHuesped.addColumn("Número de Reserva");

		filasHuespedes();

		tbHuespedes.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				int row = e.getFirstRow();
				TableModel model = (TableModel) e.getSource();
				if (model.getRowCount() != 0) {
					try {
						id = (Long) model.getValueAt(row, 0);
					} catch (ClassCastException e1) {
						JOptionPane.showMessageDialog(null, "El número del huésped no puede ser editado.");
						actualizarFilasHuespedes();
					}
					nombre = (String) model.getValueAt(row, 1);
					apellido = (String) model.getValueAt(row, 2);
					SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
					String fechaNacimientoTexto = (String) model.getValueAt(row, 3);
					try {
						fechaNacimiento = formato.parse(fechaNacimientoTexto);
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(null, "Valor fecha inválido.");
						actualizarFilasHuespedes();
					}
					nacionalidad = (String) model.getValueAt(row, 4);
					telefono = (String) model.getValueAt(row, 5);
					try {
						numeroReserva = (Long) model.getValueAt(row, 6);
					} catch (ClassCastException e1) {
						JOptionPane.showMessageDialog(null, "El número del reserva no puede ser editado.");
						actualizarFilasHuespedes();
					}
				}
			}
		});

		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(BusquedaView.class.getResource("/img/pessoas.png")),
				scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(BusquedaView.class.getResource("/img/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);

		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);

			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuarioView usuario = new MenuUsuarioView();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 20));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuarioView usuario = new MenuUsuarioView();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 20));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);

		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String textoBusqueda = txtBuscar.getText();
				filtrarTablaHuespedes(textoBusqueda);

			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);

		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 15));

		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HuespedDAO huespedDAO = new HuespedImpl(JpaUtils.getEntityManager());
				ReservaVO reservaVO = new ReservaVO(numeroReserva);
				HuespedDTO huespedDTO = new HuespedDTO(id, nombre, apellido, fechaNacimiento, nacionalidad, telefono,
						reservaVO);
				HuespedVO huespedVO = new HuespedVO(huespedDTO);
				Huesped huesped = huespedVO.huespedModeloActualizar();

				System.out.println(idReserva);
				ReservaDAO reservaDAO = new ReservaImpl(JpaUtils.getEntityManager());
				ReservaDTO reservaDTO = new ReservaDTO(idReserva, fechaEntrada, fechaSalida, valor, metodoPago);
				ReservaVO reservaVO2 = new ReservaVO(reservaDTO);
				Reserva reserva = reservaVO2.reservaModeloActualizar();

				if (id != null) {
					huespedDAO.actualizar(huesped);
					JOptionPane.showMessageDialog(null, String.format("Datos de huésped %d actualizados.", id));
					actualizarFilasHuespedes();
				} else if (idReserva != null) {
					reservaDAO.actualizar(reserva);
					JOptionPane.showMessageDialog(null, String.format("Datos de reserva %d actualizados.", idReserva));
					actualizarReservas();
				} else {
					JOptionPane.showMessageDialog(null, "Aún no ha hecho ninguna edición.");
				}

			}
		});
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);

		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 15));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);

		JPanel btnEliminar = new JPanel();
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowHuesped = tbHuespedes.getSelectedRow();
				int rowReserva = tbReservas.getSelectedRow();
				if (rowHuesped != -1 && rowReserva != -1) {
					JOptionPane.showMessageDialog(null, "Seleccione solo una fila para eliminar.");
					actualizarFilasHuespedes();
					actualizarReservas();
				} else if (rowReserva != -1) {
					Long idReservaEliminar = (Long) tbReservas.getValueAt(rowReserva, 0);
					ReservaDAO reservaDAO = new ReservaImpl(JpaUtils.getEntityManager());
					ReservaDTO reservaDTO = new ReservaDTO(idReservaEliminar);
					ReservaVO reservaVO = new ReservaVO(reservaDTO);
					Reserva reserva = reservaVO.reservaModeloConId();
					reservaDAO.eliminar(reserva);
					JOptionPane.showMessageDialog(null, String.format("Reserva %d eliminada. ", idReservaEliminar));
					actualizarReservas();
				} else if (rowHuesped != -1) {
					Long idEliminar = (Long) tbHuespedes.getValueAt(rowHuesped, 0);
					Long idReservaEliminar = (Long) tbHuespedes.getValueAt(rowHuesped, 6);
					HuespedDAO huespedDAO = new HuespedImpl(JpaUtils.getEntityManager());
					ReservaVO reservaVO = new ReservaVO(idReservaEliminar);
					HuespedDTO huespedDTO = new HuespedDTO(idEliminar, reservaVO);
					HuespedVO huespedVO = new HuespedVO(huespedDTO);
					Huesped huesped = huespedVO.huespedModeloEliminar();
					huespedDAO.eliminar(huesped);
					JOptionPane.showMessageDialog(null, String.format("Huésped %d eliminado. ", idEliminar));
					actualizarFilasHuespedes();
					actualizarReservas();

				} else {
					JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar.");
				}
			}
		});
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);

		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 15));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}

	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}

	private void filtrarTablaHuespedes(String textoBusqueda) {
		TableRowSorter<DefaultTableModel> rowSorterHuespedes = new TableRowSorter<DefaultTableModel>(modeloHuesped);
		tbHuespedes.setRowSorter(rowSorterHuespedes);

		TableRowSorter<DefaultTableModel> rowSorterReservas = new TableRowSorter<DefaultTableModel>(modelo);
		tbReservas.setRowSorter(rowSorterReservas);

		if (textoBusqueda.length() == 0) {
			rowSorterHuespedes.setRowFilter(null);
			rowSorterReservas.setRowFilter(null);

		} else {
			List<RowFilter<DefaultTableModel, Object>> filters = new ArrayList<>();
			filters.add(RowFilter.regexFilter("\\b" + textoBusqueda + "\\b", 6));
			filters.add(RowFilter.regexFilter("(?i)\\b" + textoBusqueda + "\\b", 2));
			RowFilter<DefaultTableModel, Object> orFilter = RowFilter.orFilter(filters);
			rowSorterHuespedes.setRowFilter(orFilter);

			List<String> numeroReserva = new ArrayList<>();
			for (int i = 0; i < tbHuespedes.getRowCount(); i++) {
				String numeroRow = tbHuespedes.getValueAt(i, 6).toString();
				numeroReserva.add(numeroRow);
			}

			if (!numeroReserva.isEmpty()) {
				filtrarTablaReservas(numeroReserva, rowSorterReservas);
			} else {
				rowSorterHuespedes.setRowFilter(null);
				rowSorterReservas.setRowFilter(null);
				JOptionPane.showMessageDialog(null, "No tenemos ninguna reserva registra.");
			}
		}
	}

	private void filtrarTablaReservas(List<String> numeroReserva, TableRowSorter<DefaultTableModel> rowSorterReservas) {
		List<RowFilter<DefaultTableModel, Object>> filters2 = new ArrayList<>();
		numeroReserva.forEach(numero -> {
			filters2.add(RowFilter.regexFilter("\\b" + numero + "\\b", 0));
		});
		RowFilter<DefaultTableModel, Object> orFilter2 = RowFilter.orFilter(filters2);
		rowSorterReservas.setRowFilter(orFilter2);
	}

	public void filasHuespedes() {
		HuespedDAO huespedDAO = new HuespedImpl(JpaUtils.getEntityManager());
		List<HuespedVO> huespedVO = huespedDAO.buscarTodo();
		huespedVO.forEach(huesped -> {
			modeloHuesped.addRow(huesped.generarFila());
		});
	}

	public void filasReservas() {
		ReservaDAO reservaDAO = new ReservaImpl(JpaUtils.getEntityManager());
		List<ReservaVO> reservaVO = reservaDAO.buscarTodo();
		reservaVO.forEach(reserva -> {
			modelo.addRow(reserva.generarFila());
		});
	}

	public void actualizarFilasHuespedes() {
		modeloHuesped.setRowCount(0);
		filasHuespedes();
	}

	public void actualizarReservas() {
		modelo.setRowCount(0);
		filasReservas();
	}

}
