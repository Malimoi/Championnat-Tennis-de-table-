import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class main {

	public static void main(String[] args) {
		
		List<String> data = new ArrayList<String>();
		
		List<String> joueurs = new ArrayList<String>();
		/*
		 * Noms fictifs
		 */
		joueurs.add("Marius");
		joueurs.add("Mattis");
		joueurs.add("Justin");
		joueurs.add("Jules");
		joueurs.add("Baptiste");
		joueurs.add("Mathieu");
		joueurs.add("Sam");
		joueurs.add("Yves");
		joueurs.add("Leonard");
		joueurs.add("Patrice");
		joueurs.add("Romane");
		joueurs.add("Patricia");
		joueurs.add("Sandrine");
		joueurs.add("Fred");
		int n = 14;
		for (int i = 1;i<=(n-1);i++){
			System.out.println("Rencontre n°"+i);
			for (int r = 1;r<=(n/2);r++){
				int j1=0;
				int j2=0;
				if (r==1){
					j1 = r+(i-1)>(n-1) ? r+(i-1)-(n-1) : r+(i-1);
					j2 = 14;
				}else{
					j1 = r+(i-1)>(n-1) ? r+(i-1)-(n-1) : r+(i-1);
					j2 = j1+(n-(1+2*(r-1)))>(n-1) ? j1+(n-(1+2*(r-1)))-(n-1) : j1+(n-(1+2*(r-1)));
				}				
				System.out.println(r+"."+joueurs.get(j1-1)+" VS "+joueurs.get(j2-1)+
						(getFileMatch(joueurs.get(j1-1)+" VS "+joueurs.get(j2-1)) ? "    FINI ("+
						(getFileMatchResult(joueurs.get(j1-1)+" VS "+joueurs.get(j2-1)+" Resultat"))+")" : "    Non joue"));
			}
			System.out.println(" ");
		}
		while (true) {
			System.out.println("Entrez votre commande :");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        String s = null;
	        try {
				s = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        if (s.contains(":")){
	        	String first = s.split(" ")[0];
	        	String nd = s.split(" ")[1];
	        	String j1 = first.split(":")[0];
	        	String j2 = first.split(":")[1];
				String[] points = nd.split("/");
				int score1 = 0;
				int score2 = 0;
				
				for (int i = 0;i<points.length;i++){
					String[] PT = points[i].split("-");
					setInFile(j1+"TotalPt", Integer.valueOf(PT[0])-Integer.valueOf(PT[1])+getFile(j1+"TotalPt"));
					setInFile(j2+"TotalPt", Integer.valueOf(PT[1])-Integer.valueOf(PT[0])+getFile(j2+"TotalPt"));
					if(Integer.valueOf(PT[0])>Integer.valueOf(PT[1])){
						score1++;
					}else{
						score2++;
					}
				}
				int somme=Integer.valueOf(getFile(j1));			
				
				setInFile(j1,somme+score1);    
				setInFile(j1+"Match", getFile(j1+"Match")+1);
				
			    
			    somme=Integer.valueOf(getFile(j2));
			    
			    setInFile(j2, somme+score2);	
			    setInFile(j2+"Match", getFile(j2+"Match")+1);
			    
			    
			    if (score1>score2){
			    	setInFile(j1+"Victory", getFile(j1+"Victory")+1);
			    }else{
			    	setInFile(j2+"Victory", getFile(j2+"Victory")+1);
			    }
			    
			    setInFileMatch(j1+" VS "+j2, true);
			    setInFileMatchResult(j1+" VS "+j2+" Resultat", nd);
			    
				System.out.println(j1+" VS "+j2+" : "+nd);
	        	
	        }else if(s.contains("get")){
	        	for (int i = 0;i<joueurs.size();i++){
	        		int somme=getFile(joueurs.get(i));
					int sommetotal=getFile(joueurs.get(i)+"TotalPt");
					int sommevic=getFile(joueurs.get(i)+"Victory");
	        		data.add(joueurs.get(i)+" "+somme+" "+sommetotal+" "+sommevic+" "+getFile(joueurs.get(i)+"Match"));
	        	}
	        	Collections.sort(data, new StringComparator());
                Collections.reverse(data);
                for (int i = 0;i<data.size();i++){
                	System.out.println((i+1)+". "+data.get(i));
                }
                data.clear();
	        }else if(s.contains("pl")){
	        	for (int i = 1;i<=(n-1);i++){
	    			System.out.println("Rencontre n°"+i);
	    			for (int r = 1;r<=(n/2);r++){
	    				int j1=0;
	    				int j2=0;
	    				if (r==1){
	    					j1 = r+(i-1)>(n-1) ? r+(i-1)-(n-1) : r+(i-1);
	    					j2 = 14;
	    				}else{
	    					j1 = r+(i-1)>(n-1) ? r+(i-1)-(n-1) : r+(i-1);
	    					j2 = j1+(n-(1+2*(r-1)))>(n-1) ? j1+(n-(1+2*(r-1)))-(n-1) : j1+(n-(1+2*(r-1)));
	    				}				
	    				System.out.println(r+"."+joueurs.get(j1-1)+" VS "+joueurs.get(j2-1)+
	    						(getFileMatch(joueurs.get(j1-1)+" VS "+joueurs.get(j2-1)) ? "    FINI ("+
	    						(getFileMatchResult(joueurs.get(j1-1)+" VS "+joueurs.get(j2-1)+" Resultat"))+")" : "    Non joue"));
	    			}
	    			System.out.println(" ");
	    		}
	        }
	        else if(s.contains("start")){
	        	new Fenetre();
	        }
	        else{
	        	System.out.println("Mauvaise commande !");
	        }
		}
	}
	
	public static Integer getFile(String path){
		int s = 0;
		try{
			java.util.Scanner lecteur ;
			
			java.io.File fichier = new java.io.File("JOUEURS/"+path+".yml");
			lecteur = new java.util.Scanner(fichier);
			
			/* ou bien
			   java.io.InputStream entree = 
			   LireFichierTexteBis.class.getResourceAsStream((arg[0])); 
			   lecteur = new java.util.Scanner(entree);
			*/
			
			s=lecteur.nextInt();
			
		}catch(Exception ex){
			
		}
		return s;
	}
	
	public static void setInFile(String path,Integer score){
		PrintWriter pw;
		try {
			pw = new PrintWriter (new BufferedWriter (new FileWriter ("JOUEURS/"+path+".yml")));
			pw.println(score);
			 
		    pw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
	}

	public static boolean getFileMatch(String path){
		boolean b = false;
		try{
			java.util.Scanner lecteur ;
			
			java.io.File fichier = new java.io.File("MATCH/"+path+".yml");
			lecteur = new java.util.Scanner(fichier);
			
			/* ou bien
			   java.io.InputStream entree = 
			   LireFichierTexteBis.class.getResourceAsStream((arg[0])); 
			   lecteur = new java.util.Scanner(entree);
			*/
			
			b=lecteur.nextBoolean();
			
		}catch(Exception ex){
			
		}
		return b;
	}
	
	public static void setInFileMatch(String path,boolean score){
		PrintWriter pw;
		try {
			pw = new PrintWriter (new BufferedWriter (new FileWriter ("MATCH/"+path+".yml")));
			pw.println(score);
			 
		    pw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
	}
	
	public static void setInFileMatchResult(String path,String score){
		PrintWriter pw;
		try {
			pw = new PrintWriter (new BufferedWriter (new FileWriter ("MATCH/"+path+".yml")));
			pw.println(score);
			 
		    pw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
	}
	
	public static String getFileMatchResult(String path){
		String s = "";
		try{
			java.util.Scanner lecteur ;
			
			java.io.File fichier = new java.io.File("MATCH/"+path+".yml");
			lecteur = new java.util.Scanner(fichier);
			
			/* ou bien
			   java.io.InputStream entree = 
			   LireFichierTexteBis.class.getResourceAsStream((arg[0])); 
			   lecteur = new java.util.Scanner(entree);
			*/
			
			s=lecteur.next();
			
		}catch(Exception ex){
			
		}
		return s;
	}
}
