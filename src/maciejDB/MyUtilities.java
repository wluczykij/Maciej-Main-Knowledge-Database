package maciejDB;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;




import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class MyUtilities implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8539551356280565559L;

	public static boolean saveStringToFile(String fileName, String saveString) {
		boolean saved = false;
		BufferedWriter bw = null;
		
		try {
			bw = new BufferedWriter(new FileWriter(fileName));
			try {
				bw.write(saveString);
				saved = true;
			}
			finally {
				bw.close();
			}
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		return saved;
	}

	public static String getStringFromFile(String fileName) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		
		try {
			br = new BufferedReader(
					new FileReader(fileName));
			try {
				String s;
				while(( s = br.readLine()) != null) {
					// add linefeed back since stripped by readline
					sb.append(s);
					sb.append("\n");
				}
			}
			finally {
				br.close();
			}
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		return sb.toString();
	}

	public static String convertToXML(MainBookLibrary ml) {
		//to be able to access methods in this class:
		XStream xstream = new XStream(new DomDriver());
		xstream.setMode(XStream.ID_REFERENCES);
		// Converts MyLibrary object (or any object) to XML-formatted String
		return xstream.toXML(ml);
	}

	public static MainBookLibrary convertFromXML(String XMLString) {
		MainBookLibrary mk = null;
		XStream xstream = new XStream(new DomDriver());
		xstream.setMode(XStream.ID_REFERENCES);
		// this method does not know what type of object is in XMLString
		// hence need to check, as it returns an object, not any kind of object
		Object obj = xstream.fromXML(XMLString);
		if (obj instanceof MainBookLibrary) {
			mk = (MainBookLibrary) obj;
		}
		return mk;
	}
	
	public static boolean saveMyLibraryToXMLFlie(String fileName, 
			MainBookLibrary ml) {

		// 1: converts MyLibrary object ml to XML String
		// 2: writes String object to txt file and returns boolean true if 
		// successful
		return saveStringToFile(fileName, convertToXML(ml));
	}

	public static MainBookLibrary getMyLibraryFromXMLFile(String fileName) {
		// reads XML String from file
		// converts XML String to MyLibrary object
		return convertFromXML(getStringFromFile(fileName)); 
	}

	public static boolean saveMyLibraryToSerialFlie(String fileName,
			MainBookLibrary ml) {
		boolean saved = false;
	    try {
	    /*
	    // FileOutputStream writes byte data to a file
	    // serialized format is byte data
	    FileOutputStream fos = new FileOutputStream(fileName);

	    // using BufferedOutputStream will improve performance!!
	    BufferedOutputStream bos = new BufferedOutputStream(fos);

	     // ObjectOutputStream translates objects to serialized format
	     ObjectOutputStream oos = new ObjectOutputStream(bos);
	     */
	                
	      // instead of keeping all these - fos, bos, use it this way:
	      ObjectOutputStream oos =
	          new ObjectOutputStream(
	              new BufferedOutputStream(
	                  new FileOutputStream(fileName)));

	       try {
	           // this converts to serialized format
	           oos.writeObject(ml);
	           saved = true;
	       }
	       finally {
	          oos.close();
	       }          
	   }
	   catch (Exception ex) {
	       ex.printStackTrace();
	   }
	     return saved;

	}

	public static MainBookLibrary getMyLibraryFromSerialFile(String fileName) {
		MainBookLibrary ml = null;
		try {
			ObjectInputStream ois = 
					new ObjectInputStream(
							new BufferedInputStream(
									new FileInputStream(fileName)));
			try {
				Object obj = ois.readObject();
				if (obj instanceof MainBookLibrary) {
					ml = (MainBookLibrary) obj;
				}
			}
			finally {
				ois.close();
			}					
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return ml;
	}	
}
