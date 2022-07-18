
package Vista;
import DAO.ProductoDAO;
import Model.Producto;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductoFrame extends JFrame implements ActionListener, ItemListener {
    private JLabel labelEnunciado,labelNombres,labelCodigo,labelPrecio,labelCantidad,labelFecha_vencimiento,
    labelAnho, labelMes,labelDia;
    private JTextField txtNombres,txtCodigo,txtPrecio,txtCantidad,txtFecha_vencimiento;
    private JButton btnGuardar, btnActualizar, btnEliminar;
    private JTable tableProductos,jt;
    private JPanel panelTabla;
    private JComboBox<String> comboAnho,comboMes,comboDia;

    private int id_aux;
    private static Logger logger = LogManager.getRootLogger();


    JLabel label = new JLabel("Selected Date:");
    //create object of JTextField and declare it final
    final JTextField text = new JTextField(20);
    //create object of JButton
    JButton b = new JButton("popup");
    //create object of JPanel
    JPanel p = new JPanel();
    final JFrame f = new JFrame();



    public ProductoFrame(){
        componentes();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,700); // Tamaño de la Ventana Ancho y Largo
        setLocationRelativeTo(null); // Centra la ventana en el monitor
        setLayout(null); // elimina toda plantilla.
        setResizable(false); // evita que se pueda modificar el tamaño de ventana
        setVisible(true); // hace visible la ventana
        setTitle("  ** Modulo de productos **"); // Le pone un titulo a la ventana

        txtPrecio.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)
                        && (c != '.')) {
                    e.consume();
                }
                if (c == '.' && txtCodigo.getText().contains(".")) {
                    e.consume();
                }
            }
        });
        txtCantidad.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
                char caracter = e.getKeyChar();

                // Verificar si la tecla pulsada no es un digito
                if(((caracter < '0') ||
                        (caracter > '9')) &&
                        (caracter != '\b' /*corresponde a BACK_SPACE*/))
                {
                    e.consume();  // ignorar el evento de teclado
                }
            }
        });
            //VALIDAR SOLO LETRAS
//        txtNombres.addKeyListener(new KeyAdapter()
//        {
//            public void keyTyped(KeyEvent e)
//            {
//                char c = e.getKeyChar();
//
//                if((c<'a' || c>'z') && (c<'A' || c>'Z') && (c<'!' || c>'.')
//                        && (c!=(char)KeyEvent.VK_SPACE))
//                {
//                    e.consume();
//                }
//            }
//        });


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
//        txtFecha_vencimiento = new  JTextField();
//        txtFecha_vencimiento.setBounds(20, 200, 250, 20);
//        add(txtFecha_vencimiento);

        labelAnho = new JLabel();
        labelAnho.setBounds(150,190,50,20);
        labelAnho.setText("Anho");
        add(labelAnho);

        comboAnho = new JComboBox();
        comboAnho.setBounds(150,210,100,20);
        comboAnho.addItem("2022");
        comboAnho.addItem("2023");
        comboAnho.addItem("2024");
        comboAnho.addItem("2025");
        comboAnho.addItem("2026");
        add(comboAnho);
        comboAnho.addItemListener(this);

        labelMes = new JLabel();
        labelMes.setBounds(270,190,50,20);
        labelMes.setText("Mes");
        add(labelMes);

        comboMes = new JComboBox();
        comboMes.setBounds(270,210,100,20);
        comboMes.addItem("01");
        comboMes.addItem("02");
        comboMes.addItem("03");
        comboMes.addItem("04");
        comboMes.addItem("05");
        comboMes.addItem("06");
        comboMes.addItem("07");
        comboMes.addItem("08");
        comboMes.addItem("09");
        comboMes.addItem("10");
        comboMes.addItem("11");
        comboMes.addItem("12");
        add(comboMes);
        comboMes.addItemListener(this);

        labelDia = new JLabel();
        labelDia.setBounds(390,190,50,20);
        labelDia.setText("Dia");
        add(labelDia);

        comboDia = new JComboBox();
        comboDia.setBounds(390,210,100,20);
        comboDia.addItem("01");
        comboDia.addItem("02");
        comboDia.addItem("03");
        comboDia.addItem("04");
        comboDia.addItem("05");
        comboDia.addItem("06");
        comboDia.addItem("07");
        comboDia.addItem("08");
        comboDia.addItem("09");
        comboDia.addItem("10");
        comboDia.addItem("11");
        comboDia.addItem("12");
        comboDia.addItem("13");
        comboDia.addItem("14");
        comboDia.addItem("15");
        comboDia.addItem("16");
        comboDia.addItem("17");
        comboDia.addItem("18");
        comboDia.addItem("19");
        comboDia.addItem("20");
        comboDia.addItem("21");
        comboDia.addItem("22");
        comboDia.addItem("23");
        comboDia.addItem("24");
        comboDia.addItem("25");
        comboDia.addItem("26");
        comboDia.addItem("27");
        comboDia.addItem("28");
        comboDia.addItem("29");
        comboDia.addItem("30");
        comboDia.addItem("31");
        add(comboDia);
        comboDia.addItemListener(this);




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

//        btnCancelar = new JButton();
//        btnCancelar.setBounds(220, 380, 100, 20);
//        btnCancelar.setText("Cancelar");
//        btnCancelar.addActionListener(this);
//        add(btnCancelar);

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
        String fecha = s.getFecha_vencimiento();
        String[] parts = fecha.split("-");
        String part1 = parts[0]; // 123
        String part2 = parts[1]; // 654321
        String part3 = parts[2]; // 654321

        comboAnho.setSelectedItem(part1);
        comboMes.setSelectedItem(part2);
        comboDia.setSelectedItem(part3);
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
            String fecha_vencimiento = comboAnho.getSelectedItem().toString()+"-"+comboMes.getSelectedItem().toString()+
                    "-"+comboDia.getSelectedItem().toString();
            Producto producto =  new Producto(nombre,codigo,txtPrecio.getText(),cantidad,String.valueOf(fecha_vencimiento));
            dd.create(producto);
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
                String fecha_vencimiento = comboAnho.getSelectedItem().toString()+"-"+comboMes.getSelectedItem().toString()+
                        "-"+comboDia.getSelectedItem().toString();
                Producto producto =  new Producto(nombre,codigo,txtPrecio.getText(),cantidad,String.valueOf(fecha_vencimiento));                producto.setId(id_aux);
                dd.update(producto);
                id_aux = 0;
                removeAll();
                dispose();
                new ProductoFrame();
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
                String fecha_vencimiento = comboAnho.getSelectedItem().toString()+"-"+comboMes.getSelectedItem().toString()+
                        "-"+comboDia.getSelectedItem().toString();
                Producto producto =  new Producto(nombre,codigo,txtPrecio.getText(),cantidad,String.valueOf(fecha_vencimiento));                producto.setId(id_aux);
            producto.setId(id_aux);
            dd.delete(producto);
            id_aux=0;
            removeAll();
            dispose();
            new ProductoFrame();

            }
        }


    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource()==comboAnho) {
            String seleccionado=(String)comboAnho.getSelectedItem();
            setTitle(seleccionado);
        }
        if (e.getSource()==comboMes) {
            String seleccionado=(String)comboMes.getSelectedItem();
            setTitle(seleccionado);
        }
        if (e.getSource()==comboDia) {
            String seleccionado=(String)comboDia.getSelectedItem();
            setTitle(seleccionado);
        }

    }
}

    

