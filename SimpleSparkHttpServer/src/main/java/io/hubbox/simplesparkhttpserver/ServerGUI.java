package io.hubbox.simplesparkhttpserver;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Permission;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.io.FileUtils;
//import org.eclipse.bittorrent.TorrentFile;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import spark.Filter;

import spark.Service;
import spark.Spark;
import spark.embeddedserver.jetty.EmbeddedJettyServer;
import spark.resource.AbstractFileResolvingResource;
import spark.resource.AbstractResourceHandler;
import spark.resource.ExternalResourceHandler;
import spark.staticfiles.MimeType;
import spark.staticfiles.StaticFilesConfiguration;
import spark.utils.GzipUtils;
import spark.utils.IOUtils;

/**
 *
 * @author halim
 */
public class ServerGUI extends javax.swing.JFrame {

    Service ss;
    int counter = 0;
    ExecutorService es;
    String url;
    Path home;
    File www;
    StaticFilesConfiguration staticHandler;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    int k = 0;

    public ServerGUI() {

        initComponents();

        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(jSpinner1, "#");
        jSpinner1.setEditor(editor);

        es = Executors.newCachedThreadPool();
        URL url = ServerGUI.class.getProtectionDomain().getCodeSource().getLocation();
        try {
            home = Paths.get(url.toURI()).getParent();
        } catch (URISyntaxException ex) {
            Logger.getLogger(ServerGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        staticHandler = new StaticFilesConfiguration();
        www = new File(home.toFile(), "www");

        if (www.exists()) {

            if (www.isDirectory()) {

            } else {
                log(www.getAbsolutePath() + " is not a directory");
            }

        } else {
            www.mkdir();
        }

        staticHandler.configureExternal(www.getAbsolutePath());
//        staticHandler.configureExternal("C:\\Users\\halim\\Desktop\\www\\");
        staticHandler.putCustomHeader("hbbx", "hello");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinner1 = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(0, 0, 65535, 1));

        jLabel1.setText("Port");

        jButton1.setText("Run");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText(" ");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jTabbedPane1.addTab("log", jScrollPane2);

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane3.setViewportView(jTextArea3);

        jTabbedPane1.addTab("api", jScrollPane3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void sabitle() {
        System.out.println("sabitliyorum");
        jSpinner1.setEnabled(false);
        jButton1.setEnabled(false);
        ss.awaitInitialization();
        int p = findActualPort();
        jSpinner1.setValue(p);
        jButton1.setText("Stop");
        jButton1.setEnabled(true);
        log("Started on port " + p);
        url = "http://localhost:" + p;
        jLabel3.setText("<html><a href='" + url + "'>" + url + "</a></html>");
        jLabel3.setEnabled(true);

    }

    public void birak() {
        System.out.println("birakiyorum");
        jSpinner1.setEnabled(true);

        ss.stop();
        ss = null;
        jButton1.setText("Run");
        jButton1.setEnabled(true);
        log("Stopped");
        jLabel3.setText("");
        jLabel3.setEnabled(false);
    }

    public void log(String s) {
        jTextArea2.insert(sdf.format(new Date()) + " " + s + System.lineSeparator(), 0);
    }

    public int findActualPort() {
        try {
//            Method getInstance = Spark.class.getDeclaredMethod("getInstance");
//            getInstance.setAccessible(true);

//            Service sparkService = (Service) getInstance.invoke(Spark.class);
            Field aServerField = Service.class.getDeclaredField("server");
            aServerField.setAccessible(true);

            EmbeddedJettyServer absrtactServer = (EmbeddedJettyServer) aServerField.get(ss);
            Field jettyServer = EmbeddedJettyServer.class.getDeclaredField("server");
            jettyServer.setAccessible(true);
            Server server = (Server) jettyServer.get(absrtactServer);
            ServerConnector sc = (ServerConnector) (server.getConnectors()[0]);
            return sc.getPort();

        } catch (SecurityException ex) {
            Logger.getLogger(ServerGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ServerGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(ServerGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(ServerGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        es.submit(new Runnable() {
            @Override
            public void run() {

                if (ss != null) {
                    birak();
                    return;
                }

//                try {
                int port = (int) jSpinner1.getValue();

                System.out.println("basliyorum " + port);

                ss = Service.ignite().port(port);

                ss.initExceptionHandler(new Consumer<Exception>() {
                    @Override
                    public void accept(Exception t) {
                        log(t.getMessage());
                        birak();
                    }
                });

//                    ss.init();
//                    ss.awaitInitialization();
                ss.before("/files/*", new Filter() {
                    @Override
                    public void handle(spark.Request request, spark.Response response) throws Exception {

                        String path = request.pathInfo();
                        File file = new File(URI.create(www.toURI() + path));

                        if (file.isDirectory()) {
                            if (path.endsWith("/") == false) {
                                response.redirect(request.url() + "/", 301);
                            }
                        }

                    }
                });

                ss.get("/files/*", (request, response) -> {
//                    System.out.println(staticHandler.isExternalStaticResourcesSet());
//                    k++;
//                    System.out.println("k= "+k+" "+request.pathInfo());
                    staticHandler.consume(request.raw(), response.raw());

                    String path = request.pathInfo();
                    String current = request.url();

                    File file = new File(URI.create(www.toURI() + path));
//                    System.out.println(k+" "+file.getAbsolutePath() + " ?exists= " + file.exists() + " ?isdir= " + file.isDirectory());

                    if (file.isDirectory()) {

                        String all = "";
                        File[] liste = file.listFiles();

                        for (File f : liste) {
                            if (f.isDirectory()) {

                                all = all + "<tr><td data-sort=\"*" + f.getName() + "\"><a href=\"" + current + f.getName() + "\">"
                                        + "<img class=\"icon\" src=\"/files/_autoindex/images/folder-fill.svg\" alt=\"Directory\">"
                                        + f.getName() + "</a></td><td>" + sdf.format(new Date(f.lastModified())) + "</td><td>-</td></tr>";
                            } else {
                                all = all + "<tr><td data-sort=\"" + f.getName() + "\"><a href=\"" + current + f.getName() + "\">"
                                        + "<img class=\"icon\" src=\"/files/_autoindex/images/file.svg\" >"+f.getName()+"</a>"
                                        + "</td><td data-sort=\""+f.lastModified()+"\">" + sdf.format(new Date(f.lastModified())) + "</td>"
                                        + "<td data-sort=\""+f.length()+"\">"+FileUtils.byteCountToDisplaySize(f.length())+"</td></tr>";

                            }
                        }

                        return "<!DOCTYPE html><html><head><meta http-equiv=\"Content-type\" content=\"text/html; charset=UTF-8\" />"
                                + "<link rel=\"stylesheet\" href=\"/files/_autoindex/autoindex.css\" />"
                                + "<script src=\"/files/_autoindex/tablesort.js\"></script>"
                                + "<script src=\"/files/_autoindex/tablesort.number.js\"></script>"
                                + "<title>Index of " + path + "</title></head>"
                                + "<body><div class=\"content\"><h1>Index of " + path + "</h1>"
                                + "<div id=\"table-list\"><table id=\"table-content\">"
                                + "<thead class=\"t-header\"><tr>"
                                + "<th class=\"colname\" aria-sort=\"descending\"><a class=\"name\" href=\"?NA\"  onclick=\"return false\"\">Name</a></th>"
                                + "<th class=\"colname\" data-sort-method=\"number\"><a href=\"?MA\"  onclick=\"return false\"\">Last Modified</a></th>"
                                + "<th class=\"colname\" data-sort-method=\"number\"><a href=\"?SA\"  onclick=\"return false\"\">Size</a></th>"
                                + "</tr></thead>"
                                + "<tr data-sort-method=\"none\" > "
                                + "<td> <a href =\"../" + "\"> <img class=\"icon\" src=\"/files/_autoindex/images/corner-left-up.svg\" alt=\"Up\">Parent Directory</a></td>"
                                + "<td></td><td></td></tr>"
                                + all
                                //<tr><td data-sort="*original"><a href="/siga/assets/uploads/docs/original/"><img class="icon" src="/_autoindex/assets/icons/folder-fill.svg" alt="Directory">original</a></td><td data-sort="-15026686">2019-06-29 01:55</td><td data-sort="-1">-</td></tr>
                                //<tr><td data-sort="*pdf"><a href="/siga/assets/uploads/docs/pdf/"><img class="icon" src="/_autoindex/assets/icons/folder-fill.svg" alt="Directory">pdf</a></td><td data-sort="57452902">2021-10-14 23:08</td><td data-sort="-1">-</td></tr>
                                //<tr><td data-sort="*thumbs"><a href="/siga/assets/uploads/docs/thumbs/"><img class="icon" src="/_autoindex/assets/icons/folder-fill.svg" alt="Directory">thumbs</a></td><td data-sort="-15026686">2019-06-29 01:55</td><td data-sort="-1">-</td></tr>
                                //<tr><td data-sort="1.jpg"><a href="/siga/assets/uploads/docs/1.jpg"><img class="icon" src="/_autoindex/assets/icons/image.svg" alt="[IMG]">1.jpg</a></td><td data-sort="1567389302">2019-09-02 01:55</td><td data-sort="634880"> 620k</td></tr>

                                //<tr><td data-sort="14454562615627e985254c3.png"><a href="/templates/images/firma2/14454562615627e985254c3.png"><img class="icon" src="/_autoindex/assets/icons/image.svg" alt="[IMG]">14454562615627e985254c3.png</a></td><td data-sort="1462633862">2016-05-07 18:11</td><td data-sort="12288">     12k</td></tr>
                                //<tr><td data-sort="14454561335627e90536e5b.png"><a href="/templates/images/firma2/14454561335627e90536e5b.png"><img class="icon" src="/_autoindex/assets/icons/image.svg" alt="[IMG]">14454561335627e90536e5b.png</a></td><td data-sort="1462633862">2016-05-07 18:11</td><td data-sort="49152">     48k</td></tr>
                                //<tr><td data-sort="144261412255fc8b6a08c17.jpg"><a href="/templates/images/firma2/144261412255fc8b6a08c17.jpg"><img class="icon" src="/_autoindex/assets/icons/image.svg" alt="[IMG]">144261412255fc8b6a08c17.jpg</a></td><td data-sort="1462633860">2016-05-07 18:11</td><td data-sort="237568">    232k</td></tr>
                                //<tr><td data-sort="144261406255fc8b2e204c4.png"><a href="/templates/images/firma2/144261406255fc8b2e204c4.png"><img class="icon" src="/_autoindex/assets/icons/image.svg" alt="[IMG]">144261406255fc8b2e204c4.png</a></td><td data-sort="1462633854">2016-05-07 18:10</td><td data-sort="249856">    244k</td></tr>
                                + " </table></div>"
                                + "</div><script>new Tablesort(document.getElementById(\"table-content\"));</script></body></html>";
                    } else {
//                        System.out.println("serving raw file ");
//                        staticHandler.consume(request.raw(), response.raw());

                        return "OK2";
                    }

                });

                ss.get("*", (request, response) -> {
                    System.out.println("b");
                    String path = request.pathInfo();
                    if (path.equals("/favicon.ico")) {
                        ss.halt(404);
                    }

                    return "<h1>"
                            + (++counter)
                            + "</h1>"
                            + "<h1>"
                            + new Date().toString()
                            + "</h1>"
                            + "<h1>"
                            + "<a href=\"vnc://192.168.145.33:5900\"> VNC </a>"
                            + "</h1>";

                });
                sabitle();

//                } catch (Exception ex) {
//
//                    System.out.println("yakaladim");
////                    ex.printStackTrace();
//                    birak();
//                }
            }
        });


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked

        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_jLabel3MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    // End of variables declaration//GEN-END:variables
}
