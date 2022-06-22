package ventana;

import arbol.Arbol;
import arbol.ArbolCarpeta;
import arbol.Lista;
import com.sun.glass.events.KeyEvent;
import modelo.ModeloArbol;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GestorCarpeta extends JFrame implements ActionListener, MouseListener {

    private JLabel labelSubtitulo;
    private JButton btnGuardar;
    private JTable tableEmpresa;
    private JPanel cc;
    private Arbol<ArbolCarpeta> modelo=new Arbol();
    public List<ArbolCarpeta> data=new ArrayList<>();

    public GestorCarpeta() {
        ModeloArbol model = ModeloArbol.getSingleton();
        ArbolCarpeta raiz=new ArbolCarpeta("raiz", "","carpeta", 0);
        modelo.insertar("raiz",raiz,null);
        componentes();
        tableEmpresa.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.out.println("entre");
                tableEmpresaMouseClicked(e);
            }
        });

        this.getContentPane().setLayout(new BorderLayout());

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
        f.setVisible(true);
    }

    public void componentes(){
        ModeloArbol model = ModeloArbol.getSingleton();
        labelSubtitulo = new JLabel();
        labelSubtitulo.setBounds(20, 20, 260, 20);
        String p=modelo.getById("raiz").getId();
        labelSubtitulo.setText(p);
        add(labelSubtitulo);



        cc=datosTabla();
        tableEmpresa.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.out.println("entre");
                tableEmpresaMouseClicked(e);
            }
        });

        cc.setBounds(20, 110, 450, 400);


        add(cc);




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

            data=modelo.getHijoById(labelSubtitulo.getText().toString());
            List<ArbolCarpeta> produtos=data;
            System.out.println(produtos.size());

            for (ArbolCarpeta produto : produtos) {
                datos[0] = String.valueOf(produto.getId());
                datos[1] = produto.getNombre();
                datos[2] = produto.getTipo();
                datos[3] = String.valueOf(produto.getTamano());
                System.out.println(datos[0] +" "+ datos[1] );
                model.addRow(datos);
            }
            tableEmpresa = new JTable(model);
            // Establecer el color del contenido de la tabla
            tableEmpresa.setForeground (Color.BLACK); // color de fuente
            tableEmpresa.setFont (new Font (null, Font.PLAIN, 14)); // estilo de fuente
            tableEmpresa.setSelectionForeground (Color.DARK_GRAY); // color de fuente después de la selección
            tableEmpresa.setSelectionBackground (Color.LIGHT_GRAY); // fondo de fuente después de la selección
            tableEmpresa.setGridColor (Color.GRAY); // color de cuadrícula

            // Establecer el encabezado de la tabla
            tableEmpresa.getTableHeader (). setFont (new Font (null, Font.BOLD, 14)); // Establece el estilo de fuente del nombre del encabezado de la tabla
            tableEmpresa.getTableHeader (). setForeground (Color.RED); // Establece el color de fuente del nombre del encabezado de la tabla
            tableEmpresa.getTableHeader (). setResizingAllowed (false); // Establecer para no permitir el cambio manual del ancho de columna
            tableEmpresa.getTableHeader (). setReorderingAllowed (false); // Establecer para no permitir arrastrar para reordenar columnas

            // Establecer la altura de la línea
            tableEmpresa.setRowHeight(30);

            // El ancho de la primera columna se establece en 40
            tableEmpresa.getColumnModel().getColumn(0).setPreferredWidth(40);

            // Establezca el tamaño de la ventana gráfica del panel de desplazamiento (los datos de línea que excedan este tamaño requieren arrastrar la barra de desplazamiento para verlo)
            tableEmpresa.setPreferredScrollableViewportSize(new Dimension(400, 300));

            // Coloque la tabla en el panel de desplazamiento (el encabezado de la tabla se agregará automáticamente a la parte superior del panel de desplazamiento)
            JScrollPane scrollPane = new JScrollPane(tableEmpresa);

            // Agregar panel de desplazamiento al panel de contenido

            pan.add(scrollPane);
            // Establecer el panel de contenido en la ventana



        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"No se encontraron coincidencias");
        }
        return pan;
    }

    private void tableEmpresaMouseClicked(MouseEvent evt) {
        System.out.println("entre");
        JTable source = (JTable)evt.getSource();
        int row = source.rowAtPoint( evt.getPoint() );
        String id = source.getModel().getValueAt(row, 0)+"";
        String no = source.getModel().getValueAt(row, 1)+"";
        ArbolCarpeta r= modelo.getById(id);
        if(r.getTipo()==null){
            labelSubtitulo.setText(r.getId());
            remove(labelSubtitulo);
            remove(cc);
            //data.add()
            cc=datosTabla();
            tableEmpresa.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    System.out.println("entre");
                    tableEmpresaMouseClicked(e);
                }
            });
            cc.setBounds(20, 110, 450, 400);

            labelSubtitulo.setBounds(20, 20, 260, 20);

            add(cc);
            add(labelSubtitulo);

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



        System.out.println(e.getSource().toString());
        if(e.getSource().equals(btnGuardar)){

            ArbolCarpeta p= modelo.getPadreById(labelSubtitulo.getText());
            labelSubtitulo.setText(p.getId());
            remove(labelSubtitulo);
            remove(cc);
            //data.add()
            cc=datosTabla();
            tableEmpresa.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    System.out.println("entre");
                    tableEmpresaMouseClicked(e);
                }
            });
            cc.setBounds(20, 110, 450, 400);

            labelSubtitulo.setBounds(20, 20, 260, 20);

            add(cc);
            add(labelSubtitulo);

            setVisible(true);
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

            try {

                modelo.insertar(idRandom, v, labelSubtitulo.getText().toString());

                remove(cc);
                //data.add()
                cc=datosTabla();
                tableEmpresa.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("entre");
                        tableEmpresaMouseClicked(e);
                    }
                });
                cc.setBounds(20, 110, 450, 400);


                add(cc);

                setVisible(true);
                //ArbolDibujoPanel.getArch(v);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "No pudo leer archivo");
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

                modelo.insertar(idRandom, v, labelSubtitulo.getText().toString());

                remove(cc);
                //data.add()
                cc=datosTabla();
                tableEmpresa.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("entre");
                        tableEmpresaMouseClicked(e);
                    }
                });
                cc.setBounds(20, 110, 450, 400);


                add(cc);

                setVisible(true);
                //ArbolDibujoPanel.getArch(v);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "No pudo leer archivo");
            }


    }


}
