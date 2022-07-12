
package Vista;
import DAO.ProductoDAO;
import Model.Producto;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductoFrame extends JFrame implements ActionListener{
    private JLabel labelEnunciado,labelNombres,labelCodigo,labelPrecio,labelCantidad,labelFecha_vencimiento;
    private JTextField txtNombres,txtCodigo,txtPrecio,txtCantidad,txtFecha_vencimiento;
    private JButton btnGuardar,btnCancelar, btnActualizar, btnEliminar;
    private JTable tableProductos,jt;
    private JPanel panelTabla;
    private int id_aux;
    private UtilDateModel model;
    private JDatePanelImpl datePanel;
    private JDatePickerImpl datePicker;
    private static Logger logger = LogManager.getRootLogger();

    public ProductoFrame(){
        componentes();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,700); // Tamaño de la Ventana Ancho y Largo
        setLocationRelativeTo(null); // Centra la ventana en el monitor
        setLayout(null); // elimina toda plantilla.
        setResizable(false); // eviata que se pueda modificar el tamaño de ventana
        setVisible(true); // hace visible la ventana
        setTitle("  ** Modulo de productos **"); // Le pone un titulo a la ventana
    }

    private JPanel datosTabla() {
        JPanel pan = new JPanel();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn( "ID");
        model.addColumn("Nombre");
        model.addColumn("Codigo");
        model.addColumn("Precio");
        model.addColumn("Cantidad");
        model.addColumn("Fecha de vencimiento");
        String []datos = new String[6];
        try{
            ProductoDAO productoDAO = new ProductoDAO();
            List<Producto> data;
            data=productoDAO.read();
            for (Producto producto : data) {
                datos[0] = String.valueOf(producto.getId());
                datos[1] = producto.getNombre();
                datos[2] = producto.getCodigo();
                datos[3] = producto.getPrecio();
                datos[4] = String.valueOf(producto.getCantidad());
                datos[5] = producto.getFecha_vencimiento();
                model.addRow(datos);
                logger.info(datos);
            }
            tableProductos = new JTable(model);

            // Establecer el color del contenido de la tabla
            tableProductos.setForeground (Color.BLACK); // color de fuente
            tableProductos.setFont (new Font (null, Font.PLAIN, 10)); // estilo de fuente
            tableProductos.setSelectionForeground (Color.DARK_GRAY); // color de fuente después de la selepanelTablaión
            tableProductos.setSelectionBackground (Color.LIGHT_GRAY); // fondo de fuente después de la selepanelTablaión
            tableProductos.setGridColor (Color.GRAY); // color de cuadrícula

            // Establecer el encabezado de la tabla
            tableProductos.getTableHeader (). setFont (new Font (null, Font.BOLD, 10)); // Establece el estilo de fuente del nombre del encabezado de la tabla
            tableProductos.getTableHeader (). setForeground (Color.RED); // Establece el color de fuente del nombre del encabezado de la tabla
            tableProductos.getTableHeader (). setResizingAllowed (false); // Establecer para no permitir el cambio manual del ancho de columna
            tableProductos.getTableHeader (). setReorderingAllowed (false); // Establecer para no permitir arrastrar para reordenar columnas

            // Establecer la altura de la línea
            tableProductos.setRowHeight(30);

            // El ancho de la primera columna se establece en 40
            tableProductos.getColumnModel().getColumn(0).setPreferredWidth(40);

            // Establezca el tamaño de la ventana gráfica del panel de desplazamiento (los datos de línea que excedan este tamaño requieren arrastrar la barra de desplazamiento para verlo)
            tableProductos.setPreferredScrollableViewportSize(new Dimension(550, 80));

            // Coloque la tabla en el panel de desplazamiento (el encabezado de la tabla se agregará automáticamente a la parte superior del panel de desplazamiento)
            JScrollPane scrollPane = new JScrollPane(tableProductos);

            // Agregar panel de desplazamiento al panel de contenido
            pan.add(scrollPane);

        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"No se encontraron coincidencias");
        }
        return pan;
    }
    //Componentes en general setBounds
    public void componentes(){
        panelTabla = datosTabla();
        tableProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableProductosMouseClicked(evt);
            }
        });
        panelTabla.setBounds(20, 250, 580, 120);
        add(panelTabla);

        /*--------- Campos ---------*/
        labelEnunciado = new JLabel();
        labelEnunciado.setBounds(20,20,400,20);
        labelEnunciado.setText("Ingrese los datos del producto a registrar");
        add(labelEnunciado);

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
        tableProductos.setBounds(20, 250, 570, 120);
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

        btnActualizar = new JButton();
        btnActualizar.setBounds(100,420,100,20);
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(this);
        add(btnActualizar);

        btnEliminar = new JButton();
        btnEliminar.setBounds(220,420,100,20);
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(this);
        add(btnEliminar);
   
    }

    private void tableProductosMouseClicked(MouseEvent evt) {
        logger.info("Entro a al producto");
        JTable source = (JTable)evt.getSource();
        int row = source.rowAtPoint( evt.getPoint() );
        String id = source.getModel().getValueAt(row, 0)+"";
        ProductoDAO r=new ProductoDAO();
        int dd= Integer.parseInt(id);
        id_aux= dd;
        logger.info("id_aux=" + dd);
        Producto s=r.readById(dd);
        txtNombres.setText(s.getNombre());
        txtCodigo.setText(s.getCodigo());
        txtPrecio.setText(s.getPrecio());
        txtCantidad.setText(String.valueOf(s.getCantidad()));
        txtFecha_vencimiento.setText(String.valueOf(s.getFecha_vencimiento()));
        JOptionPane.showMessageDialog(null, s);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(btnGuardar)){
            logger.info("Lanzamos una rutina para grabar los datos");
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
            removeAll();
            dispose();
            new ProductoFrame();

        }
        if(e.getSource().equals(btnActualizar)){
            if (id_aux==0){
                JOptionPane.showMessageDialog(null,"Seleccione primero");
            }else {
                logger.info("Lanzamos una rutina para grabar los datos");
                ProductoDAO dd = new ProductoDAO();
                String nombre = txtNombres.getText();
                String codigo = txtCodigo.getText();
                double precio = Double.valueOf(txtPrecio.getText());
                int cantidad = Integer.parseInt(txtCantidad.getText());
                Date fecha_vencimiento = ParseFecha(txtFecha_vencimiento.getText());
                Producto producto = new Producto(nombre, codigo, txtPrecio.getText(), cantidad, txtFecha_vencimiento.getText());
                producto.setId(id_aux);
                dd.update(producto);
                id_aux = 0;
//            dd.create(new Producto(txtNombres.getText(),txtCodigo.getText(),,txtPrecio.getText()),
//                    txtCantidad.getText()),));

                removeAll();
                dispose();
                new ProductoFrame();
                //data.add()
            }
        }
        if(e.getSource().equals(btnEliminar)){
            if (id_aux==0){
                JOptionPane.showMessageDialog(null,"Seleccione primero");
            }else {
            logger.info("Lanzamos una rutina para eliminar los datos");
            ProductoDAO dd= new ProductoDAO();
            String nombre= txtNombres.getText();
            String codigo= txtCodigo.getText();
            double precio= Double.valueOf(txtPrecio.getText());
            int cantidad= Integer.parseInt(txtCantidad.getText());
            Date fecha_vencimiento = ParseFecha(txtFecha_vencimiento.getText());
            Producto producto =  new Producto(nombre,codigo,txtPrecio.getText(),cantidad,txtFecha_vencimiento.getText());
            producto.setId(id_aux);
            dd.delete(producto);
            id_aux=0;
//            dd.create(new Producto(txtNombres.getText(),txtCodigo.getText(),,txtPrecio.getText()),
//                    txtCantidad.getText()),));

            removeAll();
            dispose();
            new ProductoFrame();
            //data.add()
            }
        }
        if(e.getSource().equals(btnCancelar)){
            txtNombres.setText("");
            txtCodigo.setText("");
            txtPrecio.setText("");
            txtCantidad.setText("");
            txtFecha_vencimiento.setText("");
            id_aux=0;
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

}

    

