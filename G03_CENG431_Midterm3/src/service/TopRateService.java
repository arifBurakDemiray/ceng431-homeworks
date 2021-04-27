package service;

import fileio.OutfitRepository;
import fileio.UserRepository;
import model.Outfit;
import model.Rates;
import model.User;
import storage.IContainer;

public class TopRateService {
	

	public TopRateService() {
		
	}
	
	public Rates initializeRateModel() {
		final String userName = findTopFollowedUser();
		final String topLiked = findTopLikedOutfit();
		final String topDisliked = findTopDislikedOutfit();
		return new Rates(userName,topLiked,topDisliked);
	}
	

	private static String findTopFollowedUser() {
		final IContainer<User> users = (new UserRepository()).getUsers();
		String result = "";
		int max = 0;
		for(User user: users) {
			int followerLen = user.getFollowers().getLength();
			if(followerLen>max) {
				max = followerLen;
				result = user.getUserName();
			}
		}
		return result;
		
	}
	
	private static String findTopLikedOutfit() {
		IContainer<Outfit> outfits = (new OutfitRepository()).getOutfits();
		String result = "";
		int max = 0;
		for(Outfit outfit: outfits) {
			int likes = outfit.getNumberOfLikes();
			if(likes>max) {
				max = likes;
				result = outfit.getType();
			}
		}
		return result;
		
	}

	private static String findTopDislikedOutfit() {
		IContainer<Outfit> outfits =  (new OutfitRepository()).getOutfits();
		String result = "";
		int max = 0;
		for(Outfit outfit: outfits) {
			int dislikes = outfit.getNumberOfDislikes();
			if(dislikes>max) {
				max = dislikes;
				result = outfit.getType();
			}
		}
		return result;
		
	}
	
	


}
