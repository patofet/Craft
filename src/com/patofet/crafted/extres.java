package com.patofet.crafted;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.os.Parcel;
import android.os.Parcelable;

class llegirFicher{
	static public llista llegir(InputStream is) throws IOException{
		crafteig prov;
		llista l=null;
		String[] pri,seg,ter;
		String n, desc;
		int i,aN, aI, aS, bI, bS, cI, cS, dI, dS, eI, eS, fI, fS, gI, gS, hI, hS, iI, iS, jI, jS;
	    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	    String mLine = reader.readLine();
	    while (mLine != null){
	    	
		    pri=mLine.split("/");
		    i=Integer.valueOf(pri[0]);
		    n=pri[1];
		    desc=pri[2];
		    pri=pri[3].split(";");
		    prov=null;
		    for(int j=pri.length-1;j>=0;j--){
		    	seg=pri[j].split("-");
	    		ter=seg[0].split(":");
	    		aN=Integer.valueOf(ter[2]);
	    		aI=Integer.valueOf(ter[0]);
	    		aS=Integer.valueOf(ter[1]);
	    		ter=seg[1].split(":");
	    		bI=Integer.valueOf(ter[0]);
	    		bS=Integer.valueOf(ter[1]);
	    		ter=seg[2].split(":");
	    		cI=Integer.valueOf(ter[0]);
	    		cS=Integer.valueOf(ter[1]);
	    		ter=seg[3].split(":");
	    		dI=Integer.valueOf(ter[0]);
	    		dS=Integer.valueOf(ter[1]);
	    		ter=seg[4].split(":");
	    		eI=Integer.valueOf(ter[0]);
    			eS=Integer.valueOf(ter[1]);
    			ter=seg[5].split(":");
    			fI=Integer.valueOf(ter[0]);
    			fS=Integer.valueOf(ter[1]);
    			ter=seg[6].split(":");
    			gI=Integer.valueOf(ter[0]);
    			gS=Integer.valueOf(ter[1]);
    			ter=seg[7].split(":");
    			hI=Integer.valueOf(ter[0]);
    			hS=Integer.valueOf(ter[1]);
    			ter=seg[8].split(":");
    			iI=Integer.valueOf(ter[0]);
    			iS=Integer.valueOf(ter[1]);
    			ter=seg[9].split(":");
    			jI=Integer.valueOf(ter[0]);
    			jS=Integer.valueOf(ter[1]);
    			if(prov==null)
    				prov=new crafteig(i, n, desc, aN, aI, aS, bI, bS, cI, cS, dI, dS, eI, eS, fI, fS, gI, gS, hI, hS, iI, iS, jI, jS);
    			else
    				prov.addCraft(aN, aI, aS, bI, bS, cI, cS, dI, dS, eI, eS, fI, fS, gI, gS, hI, hS, iI, iS, jI, jS);
    			
		    }
		    prov.seguentCraft();
		    if(l==null)
		    	l=new llista(prov,mLine);
		    else
		    	l.afegirObjece(prov,mLine);
		    mLine = reader.readLine(); 
		}
	    reader.close();
		return l;
	}
}
class llista implements Parcelable{
	nodeLlistaCraft inici,punt;
	llista(crafteig c, String lin){
		inici=new nodeLlistaCraft(c,lin);
		inici.seg=null;
		punt=inici;
		
	}
	public void afegirObjece(crafteig c,String lin){
		nodeLlistaCraft aux=new nodeLlistaCraft(c,lin);
		aux.seg=punt.seg;
		punt.seg=aux;
	}
	public crafteig getActual(){
		return punt.getCraft();
	}
	public String getlinea(){
		return punt.getLinia();
	}
	public void principi(){
		punt=inici;
	}
	public Boolean seguent(){
		Boolean ret;
		if(punt.seg==null)
			ret=false;
		else{
			ret=true;
			punt=punt.seg;
		}
		return ret;
	}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
}
class nodeLlistaCraft{
	private crafteig c;
	private String linea;
	public nodeLlistaCraft seg;
	public nodeLlistaCraft(crafteig cr,String lin) {
		c=cr;
		linea=lin;
		seg=null;
	}
	public String getLinia(){
		return linea;
	}
	public crafteig getCraft(){
		return c;
	}
}

class crafteig{
	private int id,totalCraft;
	private String nom,descripcio;
	private llistaCircular craftejos;
	crafteig(int i,String n,String desc,int aN,int aI,int aS,int bI,int bS,int cI,int cS,int dI,int dS,int eI,int eS,int fI,int fS,int gI,int gS,int hI,int hS,int iI,int iS,int jI,int jS){
		id=i;
		nom=n;
		descripcio=desc;
		totalCraft=1;
		craftejos=new llistaCircular(aN,aI, aS, bI, bS, cI, cS, dI, dS, eI, eS, fI, fS, gI, gS, hI, hS, iI, iS, jI, jS);
	}
	public void addCraft(int aN,int aI,int aS,int bI,int bS,int cI,int cS,int dI,int dS,int eI,int eS,int fI,int fS,int gI,int gS,int hI,int hS,int iI,int iS,int jI,int jS){
		totalCraft++;
		craftejos.addNode(aN,aI, aS, bI, bS, cI, cS, dI, dS, eI, eS, fI, fS, gI, gS, hI, hS, iI, iS, jI, jS);
	}
	public void iniciCraft(){
		craftejos.anarInici();
	}
	public int totalCraft(){
		return totalCraft;
	}
	public nodeItem getCraftActual(){
		return craftejos.actual();
	}
	public void seguentCraft(){
		craftejos.seguent();
	}
	public nodeItem actualSeguentCraft(){
		nodeItem aux=getCraftActual();
		seguentCraft();
		return aux;
	}
	public int getID(){
		return id;
	}
	public String getNom(){
		return nom;
	}
	public String getDescripcio(){
		return descripcio;
	}
}
class item{
	private int ID, sub, num;
	item(int i,int s){
		ID=i;
		sub=s;
		num=1;
	}
	item(int i,int s,int n){
		ID=i;
		sub=s;
		num=n;
	}
	public String getItemComplet(){
		return String.valueOf(ID)+":"+String.valueOf(sub)+":"+String.valueOf(num);
	}
	public int getItemID(){
		return ID;
	}
	public int getItemSub(){
		return sub;
	}
	public int getItemNum(){
		return num;
	}
	public void setItemID(int i){
		ID=i;
	}
	public void setItemSub(int s){
		sub=s;
	}
	public void setItemNum(int n){
		num=n;
	}
	public item clonar(){
		return new item(ID,sub,num);
	}
}
class llistaCircular{
	nodeItem inici,punt;
	llistaCircular(int aN,int aI,int aS,int bI,int bS,int cI,int cS,int dI,int dS,int eI,int eS,int fI,int fS,int gI,int gS,int hI,int hS,int iI,int iS,int jI,int jS){
		inici=new nodeItem(aN,aI, aS, bI, bS, cI, cS, dI, dS, eI, eS, fI, fS, gI, gS, hI, hS, iI, iS, jI, jS);
		inici.seg=inici;
		punt=inici;
	}
	public void addNode(int aN,int aI,int aS,int bI,int bS,int cI,int cS,int dI,int dS,int eI,int eS,int fI,int fS,int gI,int gS,int hI,int hS,int iI,int iS,int jI,int jS){
		nodeItem aux=new nodeItem(aN,aI, aS, bI, bS, cI, cS, dI, dS, eI, eS, fI, fS, gI, gS, hI, hS, iI, iS, jI, jS);
		aux.seg=inici.seg;
		inici.seg=aux;
	}
	public void anarInici(){
		punt=inici;
	}
	public void seguent(){
		punt=punt.seg;
	}
	public nodeItem actual(){
		return punt;
	}
	public nodeItem actualPassar(){
		nodeItem aux=actual();
		seguent();
		return aux;
	}
}
class nodeItem{
    public item[] craft;
    public nodeItem seg;
    nodeItem(int aN,int aI,int aS,int bI,int bS,int cI,int cS,int dI,int dS,int eI,int eS,int fI,int fS,int gI,int gS,int hI,int hS,int iI,int iS,int jI,int jS){
    	craft = new item[10];
    	craft[0]=new item(aI, aS,aN);
    	craft[1]=new item(bI, bS);
    	craft[2]=new item(cI, cS);
    	craft[3]=new item(dI, dS);
    	craft[4]=new item(eI, eS);
    	craft[5]=new item(fI, fS);
    	craft[6]=new item(gI, gS);
    	craft[7]=new item(hI, hS);
    	craft[8]=new item(iI, iS);
    	craft[9]=new item(jI, jS);
    	seg=null;
    }
    public item getObjecte(int posicio) {
		return craft[posicio];
	}
}