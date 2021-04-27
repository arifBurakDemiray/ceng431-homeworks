package model;

import storage.IContainer;
import storage.OutfitContainer;
import observation.Observable;

public class Collection extends Observable{


	private String name;
	private IContainer<Outfit> outfits;
	
	public Collection(String name)
	{
		this.name = name;
		outfits = new OutfitContainer();
	}

	public String getName() {
		return name;
	}

	public IContainer<Outfit> getOutfits() {
		return outfits;
	}
		
	
	public String toString(){
		return  "\t<collection name=\""+this.getName()+"\">\n\t"+
				"<ids>"+this.outfitsToString()+"</ids>\n\t</collection>\n";
	}
	
	private String outfitsToString(){
		String result = "";
		IContainer<Outfit> outfits = this.getOutfits();
		if(outfits.isEmpty())
			return result;
		for(Outfit outfit : outfits){
			result+=outfit.getId()+",";
		}
		if (result.endsWith(",")) { // if ends with , ignore it
			result = result.substring(0, result.length() - 1);
		}
		return result;
	}

	public boolean equals(String collectionName){
		return this.getName().equals(collectionName);
	}	
}
