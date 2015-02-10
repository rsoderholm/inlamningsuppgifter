package inlamningsuppgift1;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class DiscRegister {
	private List<Movie> movieArr = new ArrayList<Movie>();

	public List<Movie> getArray() {
		return movieArr;
	}

	public void loadFile(File file) {
		movieArr.clear();
		
		BufferedReader br = null;
		try {
			br = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String line;
		
		try {
			while ((line = br.readLine()) != null) {

				String[] part = line.split("<&>");
				String[] actors = part[4].split("_");

				movieArr.add(new Movie(part[0], part[1], part[2], part[3],
						actors, Integer.parseInt(part[5]),Double.parseDouble(part[6])));
			}
			br.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "File could not be loaded");
		} catch (NumberFormatException e ){
			JOptionPane.showMessageDialog(null, "File is not correctly formatted or corrupt");
		} catch (ArrayIndexOutOfBoundsException e){
			JOptionPane.showMessageDialog(null, "File is not correctly formatted or corrupt");
		}
	}

	public void saveFile(File file) {
		BufferedWriter writer = null;
		try {
			writer = Files.newBufferedWriter(file.toPath(), StandardCharsets.UTF_8);
			
			for(int i = 0; i < movieArr.size(); i++){
				String str = "";
				Movie movie = movieArr.get(i);
				str += movie.getTitle() + "<&>" + movie.getGenre() + "<&>" + 
						movie.getType() + "<&>" + movie.getDirector() + "<&>";
				
				String[] actors = movie.getActors();
				for(int act = 0; act < actors.length; act++){
					if(act < actors.length-1){
						str += actors[act] + "_";
					}else{
						str += actors[act] + "<&>";
					}
				}				
				str += movie.getLength() + "<&>" + movie.getRating();
				writer.write(str);
				writer.newLine();
			}
			
		} catch (IOException ex) {
			// report
		} finally {
			try {
				writer.close();
			} catch (Exception ex) {
			}
		}

	}

}
