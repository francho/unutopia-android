package com.valles.rssreader;

public class FeedItem {	
	public String Titulo;
	public long Date;
	public int Imagen;
	
	public String getTitulo() {
		return Titulo;
	}

	public long getDate() {
		return Date;
	}

	public int getImagen() {
		return Imagen;
	}

	public void FillItem (String tit,long dat, int img){
		
		Titulo = tit;
		Date = dat;
		Imagen = img;		
	}	
}
