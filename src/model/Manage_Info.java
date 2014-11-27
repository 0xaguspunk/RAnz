package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * S'encarrega de tractar tota la informació que llegim del fitxer
 * i distribuirla en diferents classes per després poder treballar
 * amb ella
 * 
 * @author Ranz
 */
public class Manage_Info {

	private ArrayList<POST_InfoGPS> post;
	private ArrayList<PRE_InfoGPS> pre;
	private Season s;
	private String circuitName;
	
	/**
	 * Constructor de la classe
	 */
	public  Manage_Info (){
		
		post = new ArrayList<POST_InfoGPS>();
		pre = new ArrayList<PRE_InfoGPS>();
		s = new Season();
		
	}
	
	public ArrayList<PRE_InfoGPS> getArrayPRE(){
		
		return pre;
	}
	
	public ArrayList<POST_InfoGPS>  getArrayPOST(){
		
		return post;
	}
	
	public Season getSeason() {
		return s;
	}

	/**
	 * Llegeix la informació del fitxer GPS i la preparem per a ser tractada
	 * 
	 * @param txt El path del fitxer que hem de llegir
	 */
	public void Read_txt (String txt) throws IOException { //Recibimos el nombre del fichero y cargamos la información en pre
		String [] aux;
		String line;
		String aux_temps;
		int numero=0;
		float longi;
		float lati,vel;
		PRE_InfoGPS aux_PRE;
		int time;
		
		BufferedReader br;
		br = new BufferedReader(new FileReader(txt));
		
		
		line = br.readLine();
		while ( (line = br.readLine()) != null ){
			
			aux_PRE = new PRE_InfoGPS();			
			aux = line.split (",");
						
			aux_temps = aux[1].substring(0,2);
			numero = Integer.parseInt(aux_temps);
			aux_PRE.setHour(numero);
			
			aux_temps = aux[1].substring(2,4);
			numero = Integer.parseInt(aux_temps);
			aux_PRE.setMinuts(numero);
			
			aux_temps = aux[1].substring(4,6);
			numero = Integer.parseInt(aux_temps);
			aux_PRE.setSeconds(numero);
			
			aux_temps = aux[3].substring(0,2);
			lati =  Float.parseFloat(aux_temps);
			aux_PRE.setLatDeg(lati);
			
			aux_temps = aux[3].substring(2,4);
			lati =  Float.parseFloat(aux_temps);
			aux_PRE.setLatMin(lati);
			
			aux_temps = aux[3].substring(2,9);
			lati =  Float.parseFloat(aux_temps);
			aux_PRE.setLatSec(lati);
			
			aux_temps = aux[4] + aux[6];
			aux_PRE.setOrientation(aux_temps);	
			
			aux_temps = aux[5].substring(0,3);
			longi =  Float.parseFloat(aux_temps);
			aux_PRE.setLongDeg(longi);
			
			aux_temps = aux[5].substring(3,5);
			longi =  Float.parseFloat(aux_temps);
			aux_PRE.setLongMin(longi);
			
			aux_temps = aux[5].substring(3,10);
			longi =  Float.parseFloat(aux_temps);
			aux_PRE.setLongSec(longi);
			
			aux_temps = aux[7];
			vel =  Float.parseFloat(aux_temps);
			aux_PRE.setSpeedKN(vel);
			
			aux_temps = aux[9].substring(0,2);
			time = Integer.parseInt(aux_temps);
			aux_PRE.setDay(time);
			
			aux_temps = aux[9].substring(2,4);
			time = Integer.parseInt(aux_temps);
			aux_PRE.setMonth(time);
			
			aux_temps = aux[9].substring(4,6);
			time = Integer.parseInt(aux_temps);
			aux_PRE.setYear(time);
			
			pre.add(aux_PRE);
			
			line = br.readLine();
		 }
		br.close();				
	}
	
	/**
	 * Converteix les coordenadades que llegeix del fitxer GPS en coordenades amb les
	 * quals podem treballar a la pantalla del PC
	 * 
	 * @param pantallaX El punt X de la pantalla (mida)
	 * @param pantallaY El punt Y de la pantalla (mida)
	 */
	public void Convert_POST (double pantallaX,double pantallaY){		
		
		float MinLA=0,MinLO=0,LAminutos=0,LOminutos=0,GraLA=0,GraLO=0,SegLA=0,SegLO=0,DdLA=0,DdLO=0;
		double Velocidad=0;
		double Y=0;
		double X=0;
		double C=0;
		double maxX=0 , maxY=0 , minX=7000 , minY=7000;
		double incremento=0;
		double dif=0;
		double factorX=0,factorY=0;
		float R = 6371;
		int i=0;
		int OK=0;
		int Horas=0,Minutos=0,Segundos=0,TotalSegundos=0;

		post = new ArrayList<POST_InfoGPS>();
			
		while(i < pre.size())
		{
			POST_InfoGPS aux = new POST_InfoGPS();
				
			GraLA=pre.get(i).getLatDeg();
			GraLO=pre.get(i).getLongDeg();
			MinLA=pre.get(i).getLatMin();
			MinLO=pre.get(i).getLongMin();
			LAminutos=pre.get(i).getLatSec();
			LOminutos=pre.get(i).getLongSec();
			Velocidad=pre.get(i).getSpeedKN();
			Horas=pre.get(i).getHour();
			Minutos=pre.get(i).getMinuts();
			Segundos=pre.get(i).getSeconds();			
			
			SegLA = (LAminutos - MinLA) * 60;

			SegLO = (LOminutos - MinLO) * 60;
 
			DdLA = GraLA + (MinLA/60) + (SegLA/3600);
		
			DdLO = GraLO + (MinLO/60) + (SegLO/3600);
			
			DdLA = (float)(DdLA* Math.PI*2) / 360 ;
			
			DdLO =(float) (DdLO* Math.PI*2) / 360 ;
	
			X = R * Math.sin(DdLA) * Math.cos(DdLO);
			Y = R * Math.sin(DdLA) * Math.sin(DdLO);
			
			C = X;
			X = Y;
			Y = C;
		
			if(X > maxX)
			{			
				maxX=X;
			}
			
			if(X < minX)
			{
				minX=X;
			}
			
			if(Y > maxY)
			{
				maxY=Y;
			}
			
			if(Y < minY)
			{
				minY=Y;
			}
			
			
			Velocidad= Velocidad * 1.852;
			
			TotalSegundos = Segundos + Horas*3600 + Minutos*60;
			
			aux.setHour(Horas);
			aux.setMinuts(Minutos);
			aux.setSeconds(Segundos);
			aux.setTotalSeconds(TotalSegundos);
			aux.setCoordX(X);
			aux.setCoordY(Y);
			aux.setSpeedKM(Velocidad);
			aux.setYear(pre.get(i).getYear());
			aux.setMonth(pre.get(i).getMonth());
			aux.setDay(pre.get(i).getDay());			
			
			post.add(aux);
			
			i ++;	
		}
		
		i=0;
				
		dif = (maxY + minY)/2;
		
		incremento = ((maxX - minX)/50);			
			
		factorX = (pantallaX)/(maxX-minX+incremento);						
			
		factorY = (pantallaY)/(maxY-minY+incremento);							
		
		while(i < post.size())
		{
			OK=0;
			
			if(post.get(i).getCoordY() < dif)
			{
				post.get(i).setCoordY(post.get(i).getCoordY()+(2*(dif - post.get(i).getCoordY())));
								
				OK=1;
				
			}
			
			if(post.get(i).getCoordY() > dif && OK==0)
			{
				post.get(i).setCoordY(post.get(i).getCoordY()-(2*(post.get(i).getCoordY() - dif )));
			}						
			i++;
		}
		
		i=0;		
					
		while(i < post.size())
		{
			
			post.get(i).setCoordX((post.get(i).getCoordX()+incremento-minX)*(factorX));
			post.get(i).setCoordY((post.get(i).getCoordY()+incremento-minY)*(factorY));					
			
			i++;
		}	
	}

	/**
	 * Guarda totes les dades extretes del fitxer en l'estructura de dades 
	 * dissenyada. A més genera totes lesestadístiques que psoteriorment
	 * utilitzarem
	 * 
	 * @param meta Punt del circuit on hem clicar per marcar la meta
	 */
	public void Convert_Race(int meta)
	{
	
		Race auxRace = new Race();
		int i=meta, ok=0;
		double metaX = post.get(meta).getCoordX();
		double metaY = post.get(meta).getCoordY();
		String ID="HOLA";
		double MaxRaceSpeed = 0;
		double MinRaceSpeed = 0;
		int MaxTimeLap = 0;
		int MinTimeLap = 0;
		double AverageRaceSpeed = 0;
		int AverageRaceTime = 0;
		ArrayList<Lap> auxLaps = new ArrayList<Lap>();
		boolean fin = false;
		double AverageMaxSpeed=0;
		double AverageMinSpeed=0;
		int Seconds = 0;
		int inici = 0;
		double maxdis=0;
		int totaltime=0;
		int mintime=1000000;
		double totaldistance=0;
		double averagedistance=0;
		auxRace.setCircuitName(circuitName); //Ponemos nombre del circuito
		int tmin=0;
		double Y1=0, Y2=0, X1=0, X2=0;
		double mp=0,np=0;
		double m=0;
		double n =0;
		double alpha = 0;
		double h =0;
		double nparalela1=0 , nparalela2=0;
		
		m = (post.get(i+1).getCoordY() - post.get(i).getCoordY())/(post.get(i+1).getCoordX() - post.get(i).getCoordX());
		n = post.get(i).getCoordY() - m*post.get(i).getCoordX();
		mp= -1/m;
		np= metaY-mp*metaX;

		nparalela1= n + 20;
		nparalela2= n - 20;


		X1= (nparalela1 - np)/(mp-m);
		X2= (nparalela2 - np)/(mp-m);	
		Y1 = X1*mp + np;
		Y2 = X2*mp + np;
		
		ID = "";
		ID = ID.concat(circuitName.substring(0, 3));
		ID = ID.concat("_");
		if(post.get(i).getDay()>9)
			ID = ID.concat("");
		else
			ID = ID.concat("0");
		ID = ID.concat(Integer.toString(post.get(i).getDay()));
		if(post.get(i).getMonth()>9)
			ID = ID.concat("-");
		else
			ID = ID.concat("-0");
		ID = ID.concat(Integer.toString(post.get(i).getMonth()));
		if(post.get(i).getYear()>9)
			ID = ID.concat("-");
		else
			ID = ID.concat("-0");
		ID = ID.concat(Integer.toString(post.get(i).getYear()));
	
		auxRace.setID(ID);
	
		while (i < post.size()-1)
		{
			ArrayList<Point> auxPoints = new ArrayList<Point>();
			Lap auxLap = new Lap();
			//Estadisticas Lap
			int MinLapTime=0;
			int SecLapTime=0;
			double MaxLapSpeed=0;
			double AverageLapSpeed=0;
			double MinLapSpeed=100000;
			double Distance=0;
			double modulo=0;					
		
			while(ok == 0 && fin==false)
			{
				Point p = new Point();
			
				p.setCoordX(post.get(i).getCoordX());
				p.setCoordY(post.get(i).getCoordY());
				p.setSpeedKM(post.get(i).getSpeedKM());
				p.setHour(post.get(i).getHour());
				p.setMinuts(post.get(i).getMinuts());
				p.setSeconds(post.get(i).getSeconds());
			
				if(MaxLapSpeed <= p.getSpeedKM()) MaxLapSpeed = p.getSpeedKM();
				if(MinLapSpeed >= p.getSpeedKM()) MinLapSpeed = p.getSpeedKM();
				AverageLapSpeed = AverageLapSpeed + p.getSpeedKM();
			
				if(i<post.size()-2){
			
					Distance = Distance + (post.get(i+1).getSpeedKM()+p.getSpeedKM())/(2*3600);
					p.setDistance(Distance);
				}	
				else {
			
					fin = true;
					ok=1;
				}
			
				auxPoints.add(p);			
			
				if ((inici ==1)&&(fin==false)&&(creuaLinia(post.get(i).getCoordX(),post.get(i).getCoordY(),post.get(i+1).getCoordX(),post.get(i+1).getCoordY(),X1,Y1,X2,Y2)))
				{
					ok = 1;
				}
				
				inici=1;
				i++;	
			}
		
			if(fin==false)
			{	
				AverageLapSpeed = AverageLapSpeed / auxPoints.size(); //Añadimos toda la informacion restante de Lap
				AverageMaxSpeed = AverageMaxSpeed + MaxLapSpeed;
				AverageMinSpeed = AverageMinSpeed + MinLapSpeed;
				auxLap.setAverageLapSpeed(AverageLapSpeed);
				auxLap.setDistance(Distance);
				auxLap.setMaxLapSpeed(MaxLapSpeed);
				auxLap.setMinLapSpeed(MinLapSpeed);
				auxLap.setPoints(auxPoints);
				Seconds = auxPoints.size();
				totaltime = totaltime + Seconds;
				totaldistance = totaldistance + Distance;
				MinLapTime = (Seconds/ 60);
				SecLapTime = Seconds - (MinLapTime*60);
				auxLap.setMinLapTime(MinLapTime);
				auxLap.setSecLapTime(SecLapTime);
			
				if(auxPoints.size() <= MinTimeLap) MinTimeLap = auxPoints.size(); //Calculamos info para Race
				if(auxPoints.size() >= MaxTimeLap) MaxTimeLap = auxPoints.size();
				if(MaxLapSpeed >= MaxRaceSpeed) MaxRaceSpeed = MaxLapSpeed;
				if(MinLapSpeed <= MinRaceSpeed) MinRaceSpeed = MinLapSpeed;
				if(maxdis <= Distance) maxdis = Distance;
				if(mintime>= Seconds){ 
					mintime = Seconds;
					tmin=i;
				}
				
				averagedistance = averagedistance + Distance;
				AverageRaceSpeed = AverageRaceSpeed + AverageLapSpeed;
				
				auxLaps.add(auxLap);
			}
		
			ok=0;	
			inici=0;
		}
	
		auxRace.setLaps(auxLaps);
	
		averagedistance = averagedistance / auxRace.getLaps().size(); 
		AverageRaceSpeed = AverageRaceSpeed / auxRace.getLaps().size();
		AverageRaceTime = AverageRaceTime / auxRace.getLaps().size();
		AverageMinSpeed = AverageMinSpeed / auxRace.getLaps().size();
		AverageMaxSpeed = AverageMaxSpeed / auxRace.getLaps().size();
		AverageRaceTime = totaltime / auxRace.getLaps().size();

		auxRace.setTminRace(tmin);
		auxRace.setTotalLength(totaldistance);
		auxRace.setAverageLength(averagedistance);
		auxRace.setAverageMaxSpeed(AverageMaxSpeed);
		auxRace.setAverageMinSpeed(AverageMinSpeed);
		auxRace.setAverageSpeedRace(AverageRaceSpeed);
		auxRace.setAverageTimeRaceMin((int)(AverageRaceTime/60));
		auxRace.setAverageTimeRaceSec(AverageRaceTime);
		auxRace.setTotalTimeMin((int)totaltime/60);
		auxRace.setTotalTimeSec(totaltime-((int)totaltime/60)*60);
		auxRace.setLaps(auxLaps);
		auxRace.setVmaxRace(MaxRaceSpeed);
		auxRace.setVminRace(MinRaceSpeed);
		auxRace.setTmaxRace(MaxTimeLap);
		auxRace.setMaxDis(maxdis);
	
		s.Add_race(auxRace);
	
	}

	/**
	 * Guarda el path del fitxer GPS a tractar i extreu el nom d'aquest
	 * 
	 * @param txt String amb el path del fitxer
	 */
	public void saveFile(String txt) {

		ArrayList<PRE_InfoGPS> auxpre = new ArrayList<PRE_InfoGPS>();
		ArrayList<POST_InfoGPS> auxpost = new ArrayList<POST_InfoGPS>();
		String [] aux;
		
		try {
			this.Read_txt(txt);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		aux = txt.split ("\\\\");
		aux = aux[aux.length-1].split("\\.");
		circuitName = aux[0];		
		
		auxpre = this.getArrayPRE();
		
		this.Convert_POST(1100,625);
		auxpost = this.getArrayPOST();		
	}
	
	/**
	 * Serveix per a controlar si una linia s'ha creuat amb una altre
	 * 
	 * @param X1 punt 1 X
	 * @param Y1 punt 1 Y
	 * @param X2 punt 2 X
	 * @param Y2 punt 2 Y
	 * @param X3 punt 3 X
	 * @param Y3 punt 3 Y
	 * @param X4 punt 4 X
	 * @param Y4 punt 4 Y
	 * @return si les linies s'han creuat o no
	 */
	private static boolean creuaLinia(double X1, double Y1, double X2, double Y2, double X3, double Y3, double X4, double Y4)
	{
		float x1 = (float) X1;
		float y1 = (float) Y1;
		float x2 = (float) X2;
		float y2 = (float) Y2; 
		float x3 = (float) X3;
		float y3 = (float) Y3;
		float x4 = (float) X4;
		float y4 = (float) Y4;
		float a1, a2, b1, b2, c1, c2;
		float r1, r2 , r3, r4;
		float denom;
		// Compute a1, b1, c1, where line joining points 1 and 2
		// is "a1 x + b1 y + c1 = 0".
		a1 = y2 - y1;
		b1 = x1 - x2;
		c1 = (x2 * y1) - (x1 * y2);
		// Compute r3 and r4.
		r3 = ((a1 * x3) + (b1 * y3) + c1);
		r4 = ((a1 * x4) + (b1 * y4) + c1);
		// Check signs of r3 and r4. If both point 3 and point 4 lie on
		// same side of line 1, the line segments do not intersect.
		if ((r3 != 0) && (r4 != 0) && r3*r4 > 0)
		{
			return false;
		}
		// Compute a2, b2, c2
		a2 = y4 - y3;
		b2 = x3 - x4;
		c2 = (x4 * y3) - (x3 * y4);
		// Compute r1 and r2
		r1 = (a2 * x1) + (b2 * y1) + c2;
		r2 = (a2 * x2) + (b2 * y2) + c2;
		// Check signs of r1 and r2. If both point 1 and point 2 lie
		// on same side of second line segment, the line segments do
		// not intersect.
		if ((r1 != 0) && (r2 != 0) && r1*r2 > 0)
		{
			return false;
		}
		//Line segments intersect: compute intersection point.
		denom = (a1 * b2) - (a2 * b1);
		if (denom == 0) 
		{
			return false;
		}
		return true;
	}
}			