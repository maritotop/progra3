/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import dao.ProductoDAO;
import model.Producto;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.text.SimpleDateFormat;
import java.text.ParseException;

public class ProductoFrame extends JFrame implements ActionListener{

    private JLabel labelNombres,labelCodigo,labelPrecio,labelCantidad,labelFecha_vencimiento;
    private JTextField txtNombres,txtCodigo,txtPrecio,txtCantidad,txtFecha_vencimiento;
    private JButton btnGuardar,btnCancelar;
    private JTable tableProductos,jt;
    private JPanel panelTabla;
    private UtilDateModel model;
    private JDatePanelImpl datePanel;
    private JDatePickerImpl datePicker;
    
    public ProductoFrame(){
       
        componentes();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,450); // Tamaño de la Ventana Ancho y Largo
        setLocationRelativeTo(null); // Centra la ventana en el monitor
        setLayout(null); // elimina toda plantilla.
        setResizable(false); // eviata que se pueda modificar el tamaño de ventana
        setVisible(true); // hace visible la ventana
        setTitle("  ** Módulo de Registros **"); // Le pone un titulo a la ventana
   
    }

    private JPanel datosTabla() {
            
        JPanel pan = new JPanel();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("Codigo");
        model.addColumn("Precio");
        model.addColumn("Cantidad");
        model.addColumn("Fecha de vencimiento");
        String []datos = new String[4];

        try{
            ProductoDAO productoDAO = new ProductoDAO();
            List<Producto> data;
            data=productoDAO.read();


            for (Producto producto : data) {
                datos[0] = producto.getNombre();
                datos[1] = producto.getCodigo();
                datos[2] = producto.getPrecio();
                datos[3] = String.valueOf(producto.getCantidad());
                datos[4] = String.valueOf(producto.getFecha_vencimiento());
                model.addRow(datos);
            //    logger.info(datos);
            }
            tableProductos = new JTable(model);
            // Establecer el color del contenido de la tabla
            tableProductos.setForeground (Color.BLACK); // color de fuente
            tableProductos.setFont (new Font (null, Font.PLAIN, 14)); // estilo de fuente
            tableProductos.setSelectionForeground (Color.DARK_GRAY); // color de fuente después de la selepanelTablaión
            tableProductos.setSelectionBackground (Color.LIGHT_GRAY); // fondo de fuente después de la selepanelTablaión
            tableProductos.setGridColor (Color.GRAY); // color de cuadrícula

            // Establecer el encabezado de la tabla
            tableProductos.getTableHeader (). setFont (new Font (null, Font.BOLD, 14)); // Establece el estilo de fuente del nombre del encabezado de la tabla
            tableProductos.getTableHeader (). setForeground (Color.RED); // Establece el color de fuente del nombre del encabezado de la tabla
            tableProductos.getTableHeader (). setResizingAllowed (false); // Establecer para no permitir el cambio manual del ancho de columna
            tableProductos.getTableHeader (). setReorderingAllowed (false); // Establecer para no permitir arrastrar para reordenar columnas

            // Establecer la altura de la línea
            tableProductos.setRowHeight(30);

            // El ancho de la primera columna se establece en 40
            tableProductos.getColumnModel().getColumn(0).setPreferredWidth(40);

            // Establezca el tamaño de la ventana gráfica del panel de desplazamiento (los datos de línea que excedan este tamaño requieren arrastrar la barra de desplazamiento para verlo)
            tableProductos.setPreferredScrollableViewportSize(new Dimension(400, 300));

            // Coloque la tabla en el panel de desplazamiento (el encabezado de la tabla se agregará automáticamente a la parte superior del panel de desplazamiento)
            JScrollPane scrollPane = new JScrollPane(tableProductos);

            // Agregar panel de desplazamiento al panel de contenido

            pan.add(scrollPane);
            // Establecer el panel de contenido en la ventana



        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"No se encontraron coincidencias");
        }
        return pan;
    }

    public void componentes(){

       
        /*--------- Campos ---------*/
       
        labelNombres = new JLabel(); // etiqueta
        labelNombres.setBounds(20, 50, 150,20);
        labelNombres.setText("Nombres");
        add(labelNombres);

        txtNombres = new JTextField(); // recuadro a rellenar
        txtNombres.setBounds(90, 50, 250, 20);
        add(txtNombres);
       
        labelCodigo = new JLabel();
        labelCodigo.setBounds(20, 80, 150, 20);
        labelCodigo.setText("Codigo");
        add(labelCodigo);

        txtCodigo = new  JTextField();
        txtCodigo.setBounds(90, 80, 250, 20);
        add(txtCodigo);

        labelPrecio = new JLabel();
        labelPrecio.setBounds(20, 110, 150, 20);
        labelPrecio.setText("Precio");
        add(labelPrecio);

        txtPrecio = new  JTextField();
        txtPrecio.setBounds(90, 110, 250, 20);
        add(txtPrecio);

        labelCantidad = new JLabel();
        labelCantidad.setBounds(20, 140, 150, 20);
        labelCantidad.setText("Cantidad");
        add(labelCantidad);

        txtCantidad = new  JTextField();
        txtCantidad.setBounds(90, 140, 250, 20);
        add(txtCantidad);

        labelFecha_vencimiento = new JLabel();
        labelFecha_vencimiento.setBounds(20, 170, 150, 20);
        labelFecha_vencimiento.setText("Fecha de vencimiento");
        add(labelFecha_vencimiento);

//         model = new UtilDateModel();
//         datePanel = new JDatePanelImpl(model);
//         datePicker = new JDatePickerImpl(datePanel);
//        add(datePicker);
        txtFecha_vencimiento = new  JTextField();
        txtFecha_vencimiento.setBounds(20, 200, 250, 20);
        add(txtFecha_vencimiento);





        //PANEL DE LISTA
        tableProductos = new JTable();
        tableProductos.setBounds(20, 250, 350, 100);
        add(tableProductos);

       
        /*---------- Botones ----------*/
       
        btnGuardar = new JButton();
        btnGuardar.setBounds(100, 380, 100, 20);
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(this);
        add(btnGuardar);

        btnCancelar = new JButton();
        btnCancelar.setBounds(220, 380, 100, 20);
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this);
        add(btnCancelar);
   
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
        if(e.getSource().equals(btnGuardar)){
            System.out.println("Lanzamos una rutina para grabar los datos");
            ProductoDAO dd= new ProductoDAO();
            String nombre= txtNombres.getText();
            String codigo= txtCodigo.getText();
            double precio= Double.valueOf(txtPrecio.getText());
            int cantidad= Integer.parseInt(txtCantidad.getText());
            Date fecha_vencimiento = ParseFecha(txtFecha_vencimiento.getText());
            Producto producto =  new Producto(nombre,codigo,txtPrecio.getText(),cantidad,txtFecha_vencimiento.getText());
            dd.create(producto);
//            dd.create(new Producto(txtNombres.getText(),txtCodigo.getText(),,txtPrecio.getText()),
//                    txtCantidad.getText()),));
            panelTabla=datosTabla();


        }
        if(e.getSource().equals(btnCancelar)){
            txtCodigo.setText("");
            txtNombres.setText("");
        }
       
    }
    public static Date ParseFecha(String fecha)
    {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fecha);
        }
        catch (ParseException ex)
        {
            System.out.println(ex);
        }
        return fechaDate;
    }
   
} // fin de la clase Registro

    

