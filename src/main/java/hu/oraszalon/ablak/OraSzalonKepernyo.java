package hu.oraszalon.ablak;

import hu.oraszalon.adatbazis.OraBoltDML;
import hu.oraszalon.entity.OraSzalon;
import hu.oraszalon.entity.OraSzalonTipusEnum;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class OraSzalonKepernyo extends javax.swing.JFrame {

    private javax.swing.JLabel VizalloEjLabel;
    private javax.swing.JLabel jArLabel;
    private javax.swing.JTextField jArTextField;
    private javax.swing.JButton jKilepesButton;
    private javax.swing.JLabel jMegnevezesLabel;
    private javax.swing.JTextPane jMegnevezesTextPane;
    private javax.swing.JButton jMentesButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jTermekLabel;
    private javax.swing.JList<OraSzalon> jTermekList;
    private javax.swing.JComboBox<String> jTipusComboBox;
    private javax.swing.JLabel jTipusLabel;
    private javax.swing.JCheckBox jVizalloECheckBox;
    private DefaultListModel<OraSzalon> oraSzalonAdatok;

    public OraSzalonKepernyo() {
        letrehozas();
    }

    private void letrehozas() {
        setTitle("Órabolt");

        jKilepesButton = new javax.swing.JButton();
        jTermekLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        //jTermekList = new javax.swing.JList<>();
        oraSzalonAdatok = new DefaultListModel();
        jTermekList = new javax.swing.JList(oraSzalonAdatok);

        jPanel1 = new javax.swing.JPanel();
        jMentesButton = new javax.swing.JButton();
        jMegnevezesLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jMegnevezesTextPane = new javax.swing.JTextPane();
        jTipusLabel = new javax.swing.JLabel();
        jTipusComboBox = new javax.swing.JComboBox<>();
        jArLabel = new javax.swing.JLabel();
        jArTextField = new javax.swing.JTextField();
        VizalloEjLabel = new javax.swing.JLabel();
        jVizalloECheckBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jKilepesButton.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jKilepesButton.setForeground(new java.awt.Color(255, 0, 51));
        jKilepesButton.setText("Kilépés");
        jKilepesButton.setToolTipText("Ha a gombra kattintasz kilép az alkalmazásból");
        jKilepesButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jKilepesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jKilepesButtonActionPerformed(evt);
            }
        });

        jTermekLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jTermekLabel.setText("Terméklista:");

        jTermekList.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jTermekList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(javax.swing.JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (renderer instanceof javax.swing.JLabel && value instanceof OraSzalon) {
                    // Here value will be of the Type 'CD'
                    ((javax.swing.JLabel) renderer).setText(((OraSzalon) value).getMegnevezes());
                }
                return renderer;
            }
        });
        jTermekList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                javax.swing.JList list = (javax.swing.JList) evt.getSource();
                oraAdatMegjelenites(list.getSelectedValue());

            }

        });

        jScrollPane2.setViewportView(jTermekList);

        jMentesButton.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jMentesButton.setText("MENTÉS");
        jMentesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMentesButtonActionPerformed(evt);
            }
        });

        jMegnevezesLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jMegnevezesLabel.setText("Megnevezés");

        jScrollPane1.setViewportView(jMegnevezesTextPane);

        jTipusLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jTipusLabel.setText("Típus:");

        jTipusComboBox.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        List<String> oraBoltEnumLista = Stream.of(OraSzalonTipusEnum.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        Vector<String> oraBoltEnumVector = new Vector<String>(oraBoltEnumLista);
        jTipusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(oraBoltEnumVector));

        jArLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jArLabel.setText("Ár (Ft):");

        jArTextField.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        VizalloEjLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        VizalloEjLabel.setText("Vizálló-e:");

        jVizalloECheckBox.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(17, 17, 17)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(VizalloEjLabel)
                                                        .addComponent(jArLabel)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jMegnevezesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTipusLabel))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jTipusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jVizalloECheckBox)
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jArTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jMentesButton))))
                                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(25, 25, 25)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jArTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jVizalloECheckBox))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(13, 13, 13)
                                                .addComponent(jMegnevezesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jTipusLabel)
                                                        .addComponent(jTipusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(32, 32, 32)
                                                .addComponent(jArLabel)
                                                .addGap(18, 18, 18)
                                                .addComponent(VizalloEjLabel)))
                                .addGap(53, 53, 53)
                                .addComponent(jMentesButton)
                                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jKilepesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(107, 107, 107)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(178, 178, 178)
                                                .addComponent(jTermekLabel)))
                                .addContainerGap(196, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jTermekLabel)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jKilepesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(54, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void jKilepesButtonActionPerformed(java.awt.event.ActionEvent evt) {
        Object[] lehetosegek = {"Biztosan", "Mégsem"};
        int valasz = JOptionPane.showOptionDialog(null, "Biztosan kilépsz a programból?", "Kilépés",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                null, lehetosegek, lehetosegek[0]);
        if (valasz == 0) {
            this.setVisible(false);
        }
    }

    private void oraAdatMegjelenites(Object selectedValue) {
        JOptionPane.showMessageDialog(this, selectedValue, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    private void jMentesButtonActionPerformed(java.awt.event.ActionEvent evt) {
        final OraSzalon oraSzalon = new OraSzalon();

        String megnevezes = jMegnevezesTextPane.getText();
        if (megnevezes == null || "".equals(megnevezes)) {
            JOptionPane.showMessageDialog(this, "Megnevezés adat nem lehet üres", "Üres megnevezés adat", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String arSzoveg = jArTextField.getText();
        if (arSzoveg == null || "".equals(arSzoveg)) {
            JOptionPane.showMessageDialog(this, "Ár adat nem lehet üres", "Üres ár adat", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String tipusSelected = (String) jTipusComboBox.getSelectedItem();
        try {
            oraSzalon.setMegnevezes(megnevezes);
            oraSzalon.setAr(Integer.parseInt(arSzoveg));
            oraSzalon.setTipus(OraSzalonTipusEnum.valueOf(tipusSelected));
            oraSzalon.setVizalloe(jVizalloECheckBox.isSelected());
            OraBoltDML.oraSzalonMentes(oraSzalon);
            oraSzalonAdatok.addElement(oraSzalon);
            JOptionPane.showMessageDialog(this, "Sikeresen elmentette az adatokat!", "Sikeres mentés", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ár csak szám lehet kérem javítsa az adatokat", "Hibás ár adat", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            System.getLogger(OraSzalonKepernyo.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            JOptionPane.showMessageDialog(this, "Adatok elmentése nem sikerült!", "Sikertelen mentés", JOptionPane.ERROR_MESSAGE);
        }

    }

}
