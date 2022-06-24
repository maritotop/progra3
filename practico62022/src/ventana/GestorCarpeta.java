package ventana;

import arbol.Arbol;
import arbol.ArbolCarpeta;
import arbol.Lista;
import com.sun.glass.events.KeyEvent;
import modelo.ModeloArbol;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GestorCarpeta extends JFrame implements ActionListener, MouseListener {

    private JLabel label_id;
    private JButton btnGuardar;
    private JTable tableCarpetas;
    private JPanel panelTabla;
    private Arbol<ArbolCarpeta> modelo=new Arbol();
    public List<ArbolCarpeta> data=new ArrayList<>();
    private ArbolCarpeta actual;
    private static Logger logger = LogManager.getRootLogger();

    public GestorCarpeta() {
        ModeloArbol model = ModeloArbol.getSingleton();
        ArbolCarpeta raiz=new ArbolCarpeta("raiz", "","carpeta", 0);
        actual=raiz;
        modelo.insertar("raiz",raiz,null);

        componentes();
        tableCarpetas.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                tableCarpetasMouseClicked(e);
            }
        });

        this.getContentPane().setLayout(null);


        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JMenuBar mbar = new JMenuBar();

        // Archivo
        JMenu m = new JMenu("Archivo");

        JMenuItem mi = new JMenuItem("Leer archivo", KeyEvent.VK_A);
        mi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leerDeArchivo();
            }

        });
        m.add(mi);
        JMenuItem mo = new JMenuItem("Crear carpeta", KeyEvent.VK_A);
        mo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearCarpeta();
            }

        });
        m.add(mo);
        mbar.add(m);

        this.setJMenuBar(mbar);
        this.pack();
    }



    public static void main(String[] args) {
        GestorCarpeta f = new GestorCarpeta();
        f.setExtendedState(600);
        f.setVisible(true);
        logger.info("Start");

    }

    public void componentes(){
        ModeloArbol model = ModeloArbol.getSingleton();
        label_id = new JLabel();
        label_id.setBounds(20, 20, 260, 20);
        String p=modelo.getById("raiz").getId();
        label_id.setText(p);
        add(label_id);



        panelTabla=datosTabla();
        tableCarpetas.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                tableCarpetasMouseClicked(e);
            }
        });

        panelTabla.setBounds(20, 110, 450, 400);


        add(panelTabla);




        /*---------- Botones ----------*/

        btnGuardar = new JButton();
        btnGuardar.setBounds(100, 500, 100, 20);
        btnGuardar.setText("atras");
        btnGuardar.addActionListener(this);
        add(btnGuardar);


    }


    private JPanel datosTabla() {

        JPanel pan = new JPanel();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("NAME");
        model.addColumn("TIPO");
        model.addColumn("TAMANO");
        String []datos = new String[4];

        try{

            data=modelo.getHijoById(actual.getId());
            List<ArbolCarpeta> listaHijos=data;


            for (ArbolCarpeta hijo : listaHijos) {
                datos[0] = String.valueOf(hijo.getId());
                datos[1] = hijo.getNombre();
                datos[2] = hijo.getTipo();
                datos[3] = String.valueOf(hijo.getTamano());
                model.addRow(datos);
                logger.info(datos);
            }
            tableCarpetas = new JTable(model);
            // Establecer el color del contenido de la tabla
            tableCarpetas.setForeground (Color.BLACK); // color de fuente
            tableCarpetas.setFont (new Font (null, Font.PLAIN, 14)); // estilo de fuente
            tableCarpetas.setSelectionForeground (Color.DARK_GRAY); // color de fuente después de la selepanelTablaión
            tableCarpetas.setSelectionBackground (Color.LIGHT_GRAY); // fondo de fuente después de la selepanelTablaión
            tableCarpetas.setGridColor (Color.GRAY); // color de cuadrícula

            // Establecer el encabezado de la tabla
            tableCarpetas.getTableHeader (). setFont (new Font (null, Font.BOLD, 14)); // Establece el estilo de fuente del nombre del encabezado de la tabla
            tableCarpetas.getTableHeader (). setForeground (Color.RED); // Establece el color de fuente del nombre del encabezado de la tabla
            tableCarpetas.getTableHeader (). setResizingAllowed (false); // Establecer para no permitir el cambio manual del ancho de columna
            tableCarpetas.getTableHeader (). setReorderingAllowed (false); // Establecer para no permitir arrastrar para reordenar columnas

            // Establecer la altura de la línea
            tableCarpetas.setRowHeight(30);

            // El ancho de la primera columna se establece en 40
            tableCarpetas.getColumnModel().getColumn(0).setPreferredWidth(40);

            // Establezca el tamaño de la ventana gráfica del panel de desplazamiento (los datos de línea que excedan este tamaño requieren arrastrar la barra de desplazamiento para verlo)
            tableCarpetas.setPreferredScrollableViewportSize(new Dimension(400, 300));

            // Coloque la tabla en el panel de desplazamiento (el encabezado de la tabla se agregará automáticamente a la parte superior del panel de desplazamiento)
            JScrollPane scrollPane = new JScrollPane(tableCarpetas);

            // Agregar panel de desplazamiento al panel de contenido

            pan.add(scrollPane);
            // Establecer el panel de contenido en la ventana



        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"No se encontraron coincidencias");
        }
        return pan;
    }

    private void tableCarpetasMouseClicked(MouseEvent evt) {
        JTable source = (JTable)evt.getSource();
        int row = source.rowAtPoint( evt.getPoint() );
        String id = source.getModel().getValueAt(row, 0)+"";
        ArbolCarpeta r= modelo.getById(id);
        actual=r;
        if(r.getTipo()==null){
            //si entra y el tipo es nulo = es carpeta
            logger.info("selecciono una carpeta");
            label_id.setText(r.getNombre());

            
            remove(label_id);
            remove(panelTabla);
            //data.add()
            panelTabla=datosTabla();
            tableCarpetas.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {

                    tableCarpetasMouseClicked(e);
                }
            });
            panelTabla.setBounds(20, 110, 450, 400);

            label_id.setBounds(20, 20, 260, 20);

            add(panelTabla);
            add(label_id);

            setVisible(true);

        };

        JOptionPane.showMessageDialog(null, r.toString());

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

    @Override
    public void actionPerformed(ActionEvent e) {


        if(e.getSource().equals(btnGuardar)){
            logger.info("boton guardar click");
            ArbolCarpeta p= modelo.getPadreById(label_id.getText());
            label_id.setText(p.getId());
            remove(label_id);
            remove(panelTabla);
            logger.info("datos quitados");
            //data.add()
            panelTabla=datosTabla();
            logger.info("datos agregados");
            tableCarpetas.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {

                    tableCarpetasMouseClicked(e);
                }
            });
            panelTabla.setBounds(20, 110, 450, 400);

            label_id.setBounds(20, 20, 260, 20);

            add(panelTabla);
            add(label_id);


            setVisible(true);
            logger.info("se cargaron los datos");
            // componentes();
        }


    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    private void leerDeArchivo()  {
        JFileChooser file = new JFileChooser("C:");
        if (file.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

            File archivo = file.getSelectedFile();
            String path= archivo.getAbsolutePath();
            String nombre= archivo.getName().substring(path.lastIndexOf(":")-1);
            logger.info("archivo leido");

            String extension = archivo.getName();
            int PosicionDelPunto = extension.indexOf(".");
            String sDesdePrimerPunto = extension.substring(PosicionDelPunto+1,extension.length()) ;
            Long size= archivo.length();
            //CREAR ID RANDOM
            String banco = "abcdefghijklmnopqrstuvwxyz01234567890";
            String idRandom= "";


            for (int x=0; x <=9; x++){
                Random rand = new Random();
                int indiceAleatorio = rand.nextInt( banco.length()-1);
                char caraceterAleatorio =banco.charAt(indiceAleatorio);
                idRandom+=caraceterAleatorio;
            }

            ArbolCarpeta v=new ArbolCarpeta(idRandom,nombre,sDesdePrimerPunto,size.intValue());
            logger.info("id:"+idRandom+"agregado");

            try {

                modelo.insertar(nombre, v, actual.getId());
                remove(panelTabla);
                //data.add()
                panelTabla=datosTabla();
                tableCarpetas.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        logger.info("click en metodo para leer archivo");
                        tableCarpetasMouseClicked(e);
                    }
                });
                panelTabla.setBounds(20, 110, 450, 400);


                add(panelTabla);

                setVisible(true);
                //ArbolDibujoPanel.getArch(v);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "No pudo leer archivo");
                logger.error("archivo no leido");
            }
        }

    }
    private void crearCarpeta()  {


            String path= "";
            String nombre= JOptionPane.showInputDialog("Nombre");

            String extension = null;

            //CREAR ID RANDOM
            String banco = "abcdefghijklmnopqrstuvwxyz01234567890";
            String idRandom= "";

            for (int x=0; x <=9; x++){
                Random rand = new Random();
                int indiceAleatorio = rand.nextInt( banco.length()-1);
                char caraceterAleatorio =banco.charAt(indiceAleatorio);
                idRandom+=caraceterAleatorio;
            }

            ArbolCarpeta v=new ArbolCarpeta(idRandom,nombre,extension,0);

            try {

                modelo.insertar(idRandom, v, actual.getId());

                remove(panelTabla);
                //data.add()
                panelTabla=datosTabla();
                logger.info("se agrego la carpeta");
                tableCarpetas.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {

                        tableCarpetasMouseClicked(e);
                    }
                });
                panelTabla.setBounds(20, 110, 450, 400);


                add(panelTabla);

                setVisible(true);
                //ArbolDibujoPanel.getArch(v);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "No pudo leer archivo");
            }


    }


}
