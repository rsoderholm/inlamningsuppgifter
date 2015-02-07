package inlamningsuppgift1;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FileFilterSpec extends FileFilter {

	@Override
	public boolean accept(File f) {
		boolean ret = false;
		
		if (f.isDirectory()) {
			ret = true;
		}
		
		String ext = null;
		String s = f.getName();
		int i = s.lastIndexOf('.');

		if (i > 0 && i < s.length() - 1) {
			ext = s.substring(i + 1);
		}
		
		if (ext != null) {
			if (ext.equals("JONATHAN")) {
				ret = true;

			}
		}

		return ret;
	}

	public String getDescription() {
        return "Just .LOL Files";
    }

}
