/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pa;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.swing.JOptionPane;
import org.openide.filesystems.FileObject;
import net.gjerull.etherpad.client.EPLiteClient;
import org.openide.loaders.DataObject;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.filesystems.FileLock;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Team",
        id = "org.pa.VerOnTheLine"
)
        @ActionRegistration(
                iconBase = "org/pa/clones.png",
                displayName = "#CTL_VerOnTheLine"
        )
        @ActionReference(path = "Toolbars/File")
@Messages("CTL_VerOnTheLine=Me Ajuda")
public final class VerOnTheLine implements ActionListener {

    private final DataObject context;
    private EPLiteClient client;

    public VerOnTheLine(DataObject context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        try {
            this.client = new EPLiteClient(
                    "http://localhost:9001",
                    "a04f17343b51afaa036a7428171dd873469cd85911ab43be0503d29d2acbbd58"
            );
            
            Set<FileObject> files =  context.files();
            final FileObject fileObject = files.stream().findFirst().get();
            String nomeArq = fileObject.getName();
            int idxExt = nomeArq.indexOf('.');
            String ext = "";
            if (idxExt> 0) {
                ext = nomeArq.substring(idxExt);
                nomeArq = nomeArq.substring(0, idxExt);
            }
            final String nomePad = nomeArq + Long.toString((long)(Math.random() * 1_000_000), 36);
            String txt = fileObject.asText();
            
            this.client.createPad(nomePad, txt);
//            Thread.sleep(5000);
            Desktop.getDesktop().browse(new URI("http://localhost:9001/p/" + nomePad));
            
            long qtdUsers = 0;
            do {
                Map mapUsers = client.padUsersCount(nomePad);
                qtdUsers = (long) mapUsers.get("padUsersCount");
                Thread.sleep(200);
            } while (qtdUsers == 0);
            
            
           
            new Thread(new Runnable() {
                @Override
                public void run() {
                    boolean parar = false;
                    loop_thread:
                    while (!parar ){
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException ex) {
                            Exceptions.printStackTrace(ex);
                            break;
                        }
                        Map mapUsers = client.padUsersCount(nomePad);
                        Set keySet = mapUsers.keySet();
                        for (Iterator iterator = keySet.iterator(); iterator.hasNext();) {
                            Object k = iterator.next();
                            Object v = mapUsers.get(k);
                            System.out.println("users - [k = " + k + ",  " + "v = " + v + "]");
                            if (v instanceof Number && ((Number)v).intValue() == 0) {
                                
                                Map mapText = client.getText(nomePad);
                                String text = (String) mapText.get("text");
                                System.out.println("txt: " + text);
                                try {
                                    try(FileLock lock = fileObject.lock();){
                                        
                                        OutputStream out = fileObject.getOutputStream(lock);
                                        out.write(text.getBytes());
                                        out.flush();
                                        out.close();
                                        lock.releaseLock();
                                    }
                                    
                                    
                                    
                                } catch (IOException ex) {
                                    Exceptions.printStackTrace(ex);
                                }
                                break loop_thread;
                            }
                        }
                    }
                }
            }).start();
            
            
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        } catch (URISyntaxException ex) {
            Exceptions.printStackTrace(ex);
        } catch (InterruptedException ex) {
            Exceptions.printStackTrace(ex);
        } 
    }
}
