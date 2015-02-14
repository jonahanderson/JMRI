package apps.configurexml;

import apps.FileLocationPane;
import java.util.List;
import jmri.util.FileUtil;
import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handle XML persistence of directory locations.
 * <P>
 *
 * @author Kevin Dickerson Copyright: Copyright (c) 2010
 * @version $Revision$
 */
public class FileLocationPaneXml extends jmri.configurexml.AbstractXmlAdapter {

    public FileLocationPaneXml() {
    }

    /**
     * Default implementation for storing the static contents of the Swing LAF
     *
     * @param o Object to store, of type FileLocationPane
     * @return Element containing the complete info
     */
    @Override
    public Element store(Object o) {
        Element e = new Element("fileLocations");
        /*e.setAttribute("defaultScriptLocation", FileUtil.getPythonScriptsPath());
         e.setAttribute("defaultUserLocation", FileUtil.getUserFilesPath());
         e.setAttribute("defaultThrottleLocation", jmri.jmrit.throttle.ThrottleFrame.getDefaultThrottleFolder());*/
        storeLocation(e, "defaultScriptLocation", FileUtil.getScriptsPath());
        storeUserFilesLocation(e, FileUtil.getUserFilesPath());
        e.setAttribute("class", this.getClass().getName());
        return e;
    }

    private void storeLocation(Element locations, String attr, String value) {
        Element userLocation = new Element("fileLocation");
        userLocation.setAttribute(attr, FileUtil.getPortableFilename(value));
        locations.addContent(userLocation);
    }

    private void storeUserFilesLocation(Element locations, String value) {
        Element userLocation = new Element("fileLocation");
        userLocation.setAttribute("defaultUserLocation", FileUtil.getPortableFilename(value, true, false));
        locations.addContent(userLocation);
    }

    /**
     * Update static data from XML file
     *
     * @param e Top level Element to unpack.
     * @return true if successful
     */
    @Override
    public boolean load(Element e) {
        boolean result = true;
        //Attribute scriptLocation = e.getAttribute("defaultScriptLocation");
        //if (scriptLocation!=null)
        //FileUtil.setPythonScriptsPath(scriptLocation.getValue());
        /*Attribute userLocation = e.getAttribute("defaultUserLocation");
         if (userLocation!=null)
         FileUtil.setUserFilesPath(userLocation.getValue());*/
        String value = loadUserLocations(e, "defaultUserLocation");
        if (value != null) {
            FileUtil.setUserFilesPath(value);
        }
        value = loadUserLocations(e, "defaultScriptLocation");
        if (value != null) {
            FileUtil.setScriptsPath(value);
        }
        jmri.InstanceManager.configureManagerInstance().registerPref(new FileLocationPane());
        return result;
    }

    private String loadUserLocations(Element messages, String attr) {
        List<Element> messageList = messages.getChildren("fileLocation");
        for (Element message : messageList) {
            if (message.getAttribute(attr) != null) {
                return FileUtil.getAbsoluteFilename(message.getAttribute(attr).getValue());
            }
        }
        return null;

    }

    /**
     * Update static data from XML file
     *
     * @param element Top level Element to unpack.
     * @param o ignored
     */
    @Override
    public void load(Element element, Object o) {
        log.error("Unexpected call of load(Element, Object)");
    }
    // initialize logging
    static Logger log = LoggerFactory.getLogger(FileLocationPaneXml.class.getName());

}